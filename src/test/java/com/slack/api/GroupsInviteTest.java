/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api;

import com.slack.Slack;
import com.slack.api.exception.CannotDoSelfException;
import com.slack.api.exception.ChannelNotFoundException;
import com.slack.api.exception.IsArchivedException;
import com.slack.api.exception.UserNotFoundException;
import com.slack.data.Channel;
import com.slack.data.Group;
import com.slack.data.User;
import com.slack.data.UserId;
import com.slack.data.impl.GroupIdImpl;
import com.slack.data.impl.UserIdImpl;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author bitter_fox
 */
public class GroupsInviteTest extends AbstractApiIssuerTest
{
    public GroupsInviteTest()
    {
        super(slack -> slack.groups().invite(new GroupIdImpl(""), new UserIdImpl("")));
    }

    @BeforeClass
    public static void setUpClass()
    {
    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
    }

    @Test
    public  void testInvite()
    {
        Slack slack = this.authedSlack();
        Group group = slack.groups().list().groups().get(0);
        boolean isArchived = group.isArchived();
        User user = slack.users().list().members().get(0);
        boolean isMember = group.members().contains(user.id());

        try
        {
            if (isArchived)
            {
                slack.groups().unarchive(group.id());
            }
            // kick

            Groups.Invite invite = slack.groups().invite(group.id(), user.id());

            Assert.assertEquals(false, invite.alreadyInGroup());
            Assert.assertEquals(true, invite.group().members().contains(user.id()));

            invite = slack.groups().invite(group.id(), user.id());

            Assert.assertEquals(true, invite.alreadyInGroup());
        }
        finally
        {
            if (isArchived)
            {
                slack.groups().archive(group.id());
            }

            if (!isMember)
            {
                // kick
            }
        }
    }

    @Test(expected = ChannelNotFoundException.class)
    public void testChannelNotFound()
    {
        Slack slack = this.authedSlack();

        User user = slack.users().list().members().get(0);

        slack.groups().invite(new GroupIdImpl(""), user.id());
    }

    @Test(expected = UserNotFoundException.class)
    public void testUserNotFound()
    {
        Slack slack = this.authedSlack();

        Group group = slack.groups().list().groups().get(0);

        slack.groups().invite(group.id(), new UserIdImpl(""));
    }

    @Test(expected = CannotDoSelfException.class)
    public void testCannotInviteSelf()
    {
        Slack slack = this.authedSlack();

        Channel channel = slack.channels().list().channels().get(0);

        slack.channels().invite(channel.id(), slack.auth().test().userId());
    }

    @Test(expected = IsArchivedException.class)
    public void testIsArchived()
    {
        Slack slack = this.authedSlack();
        Group group = slack.groups().list().groups().get(0);
        boolean isArchived = group.isArchived();

        slack.groups().archive(group.id());

        try
        {
            UserId user = slack.users().list().members().stream()
                .map(User::id)
                .findAny().get();

            slack.groups().invite(group.id(), user);
        }
        finally
        {
            if (isArchived)
            {
                slack.groups().archive(group.id());
            }
            else
            {
                slack.groups().unarchive(group.id());
            }
        }
    }

    // CannotInviteException
}

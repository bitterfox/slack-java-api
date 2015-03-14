/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api;

import com.slack.Slack;
import com.slack.api.exception.AlreadyInChannelException;
import com.slack.api.exception.CannotDoSelfException;
import com.slack.api.exception.ChannelNotFoundException;
import com.slack.api.exception.IsArchivedException;
import com.slack.api.exception.NotInChannelException;
import com.slack.api.exception.UserNotFoundException;
import com.slack.data.Channel;
import com.slack.data.User;
import com.slack.data.UserId;
import com.slack.data.impl.ChannelIdImpl;
import com.slack.data.impl.UserIdImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author bitter_fox
 */
public class ChannelsInviteTest extends AbstractApiIssuerTest
{
    public ChannelsInviteTest()
    {
        super(slack -> slack.channels().invite(new ChannelIdImpl(""), new UserIdImpl("")));
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
    public void testInvite()
    {
        // TODO
    }

    @Test(expected = ChannelNotFoundException.class)
    public void testChannelNotFound()
    {
        Slack slack = this.authedSlack();

        User user = slack.users().list().members().get(0);

        slack.channels().invite(new ChannelIdImpl(""), user.id());
    }

    @Test(expected = UserNotFoundException.class)
    public void testUserNotFound()
    {
        Slack slack = this.authedSlack();

        Channel channel = slack.channels().list().channels().get(0);

        slack.channels().invite(channel.id(), new UserIdImpl(""));
    }

    @Test(expected = CannotDoSelfException.class)
    public void testCannotInviteSelf()
    {
        Slack slack = this.authedSlack();

        Channel channel = slack.channels().list().channels().get(0);

        slack.channels().invite(channel.id(), slack.auth().test().userId());
    }

    @Test(expected = AlreadyInChannelException.class)
    public void testAlreadyInChannel()
    {
        Slack slack = this.authedSlack();

        Channel channel = slack.channels().list().channels().stream()
            .filter(c -> c.isMember())
            .filter(c -> c.numMembers() > 2)
            .findAny().get();

        UserId me = slack.auth().test().userId();

        UserId user = channel.members().stream()
            .filter(u -> !u.id().equals(me))
            .findAny().get();

        slack.channels().invite(channel.id(), user);
    }

    @Test(expected = IsArchivedException.class)
    public void testIsArchived()
    {
        Slack slack = this.authedSlack();

        Channel channel = slack.channels().list().channels().stream()
            .filter(Channel::isArchived)
            .findAny().get();

        UserId user = slack.users().list().members().stream()
            .map(User::id)
            .findAny().get();

        slack.channels().invite(channel.id(), user);
    }
}

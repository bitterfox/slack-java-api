/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api;

import com.slack.Slack;
import com.slack.api.exception.ChannelNotFoundException;
import com.slack.api.exception.IsArchivedException;
import com.slack.api.exception.NotInChannelException;
import com.slack.api.exception.TooLongException;
import com.slack.data.Channel;
import com.slack.data.Group;
import com.slack.data.GroupId;
import com.slack.data.impl.ChannelIdImpl;
import com.slack.data.impl.GroupIdImpl;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author bitter_fox
 */
public class GroupsSetPurposeTest extends AbstractApiIssuerTest
{
    public GroupsSetPurposeTest()
    {
        super(slack -> slack.groups().setPurpose(new GroupIdImpl(""), ""));
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

    private Group findGroup(Slack slack, GroupId groupId)
    {
        return slack.groups().findById(groupId).get();
    }

    private void setPurpose(Slack slack, Group group)
    {
        String purpose = "Test Purpose" + System.currentTimeMillis();
        Groups.SetPurpose setPurpose = slack.groups().setPurpose(group.id(), purpose);
        System.out.println(setPurpose.purpose());

        Assert.assertEquals(purpose, setPurpose.purpose());
        Assert.assertEquals(purpose, this.findGroup(slack, group.id()).purpose().value());

        setPurpose = slack.groups().setPurpose(group.id(), group.purpose().value());

        Assert.assertEquals(group.purpose().value(), setPurpose.purpose());
        Assert.assertEquals(group.purpose().value(), this.findGroup(slack, group.id()).purpose().value());
    }

    @Test
    public void testSetPurpose()
    {
        Slack slack = this.authedSlack();

        Group channel = slack.groups().list().groups().get(0);

        this.setPurpose(slack, channel);
    }

    @Test(expected = ChannelNotFoundException.class)
    public void testChannelNotFound()
    {
        Slack slack = this.authedSlack();

        slack.channels().setPurpose(new ChannelIdImpl(""), "");
    }

    @Test(expected = TooLongException.class)
    public void testTooLong()
    {
        Slack slack = this.authedSlack();

        Group group = slack.groups().list().groups().get(0);

        String tooLongPurpose = Stream.generate(() -> " ")
            .limit(251) // TODO: couldn't know limit length of purpose?
            .collect(Collectors.joining());

        slack.groups().setPurpose(group.id(), tooLongPurpose);
    }
}

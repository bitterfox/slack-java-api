/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api;

import com.slack.Slack;
import com.slack.api.exception.CannotDoGeneralException;
import com.slack.api.exception.CannotDoSelfException;
import com.slack.api.exception.ChannelNotFoundException;
import com.slack.api.exception.NotInChannelException;
import com.slack.api.exception.UserNotFoundException;
import com.slack.data.Channel;
import com.slack.data.User;
import com.slack.data.UserId;
import com.slack.data.impl.ChannelIdImpl;
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
public class ChannelsKickTest extends AbstractApiIssuerTest
{

    public ChannelsKickTest()
    {
        super(slack -> slack.channels().kick(new ChannelIdImpl(""), new UserIdImpl("")));
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

    private Channel kickableChannel(Slack slack)
    {
        Channel channel = slack.channels().list().channels().stream()
            .filter(Channel::isMember)
            .filter(c -> !c.isArchived())
            .filter(c -> c.numMembers() >= 2)
            .filter(c -> !c.isGeneral())
            .findAny().get();

        return channel;
    }

    @Test
    public void testKick()
    {
        Slack slack = this.authedSlack();

        Channel channel = this.kickableChannel(slack);

        UserId me = slack.auth().test().userId();
        UserId user = channel.members().stream()
            .filter(u -> !u.equals(me))
            .findAny().get();

        slack.channels().kick(channel.id(), user);

        Assert.assertEquals(false,
            slack.channels().info(channel.id()).channel().members().contains(user));

        slack.channels().invite(channel.id(), user);
    }

    @Test(expected = ChannelNotFoundException.class)
    public void testChannelNotFound()
    {
        Slack slack = this.authedSlack();

        slack.channels().kick(new ChannelIdImpl(""), new UserIdImpl(""));
    }

    @Test(expected = UserNotFoundException.class)
    public void testUserNotFound()
    {
        Slack slack = this.authedSlack();

        Channel channel = this.kickableChannel(slack);

        slack.channels().kick(channel.id(), new UserIdImpl(""));
    }

    @Test(expected = CannotDoSelfException.class)
    public void testCannotKickSelf()
    {
        Slack slack = this.authedSlack();

        Channel channel = this.kickableChannel(slack);
        UserId me = slack.auth().test().userId();

        slack.channels().kick(channel.id(), me);
    }

    @Test(expected = CannotDoGeneralException.class)
    public void testCannotKickFromGeneral()
    {
        Slack slack = this.authedSlack();

        Channel channel = slack.channels().list().channels().stream()
            .filter(Channel::isGeneral)
            .findAny().get();

        UserId me = slack.auth().test().userId();
        UserId user = channel.members().stream()
            .filter(u -> !u.equals(me))
            .findAny().get();

        slack.channels().kick(channel.id(), user);
    }
}

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
import com.slack.data.impl.ChannelIdImpl;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bitter_fox
 */
public class ChannelsSetPurposeTest extends AbstractApiTest
{

    public ChannelsSetPurposeTest()
    {
        super(slack -> slack.channels().setPurpose(new ChannelIdImpl(""), ""));
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

    private void setPurpose(Slack slack, Channel channel)
    {
        String purpose = "Test Purpose" + System.currentTimeMillis();
        Channels.SetPurpose setPurpose = slack.channels().setPurpose(channel.id(), purpose);
        System.out.println(setPurpose.purpose());

        Assert.assertEquals(purpose, setPurpose.purpose());
        Assert.assertEquals(purpose, slack.channels().info(channel.id()).channel().purpose().value());

        setPurpose = slack.channels().setPurpose(channel.id(), channel.purpose().value());

        Assert.assertEquals(channel.purpose().value(), setPurpose.purpose());
        Assert.assertEquals(channel.purpose().value(), slack.channels().info(channel.id()).channel().purpose().value());
    }

    @Test
    public void testSetPurpose()
    {
        Slack slack = this.authedSlack();

        Channel channel = slack.channels().list().channels().stream()
            .filter(Channel::isMember)
            .filter(((Predicate<Channel>)Channel::isArchived).negate())
            .findAny().get();

        this.setPurpose(slack, channel);
    }

    @Test(expected = NotInChannelException.class)
    public void testNotInChannel()
    {
        Slack slack = this.authedSlack();

        Channel channel = slack.channels().list().channels().stream()
            .filter(((Predicate<Channel>)Channel::isMember).negate())
            .filter(((Predicate<Channel>)Channel::isArchived).negate())
            .findAny().get();

        this.setPurpose(slack, channel);
    }

    @Test(expected = IsArchivedException.class)
    public void testIsArchived()
    {
        Slack slack = this.authedSlack();

        Channel channel = slack.channels().list().channels().stream()
            .filter(Channel::isArchived)
            .findAny().get();

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

        Channel channel = slack.channels().list().channels().stream()
            .filter(Channel::isMember)
            .filter(((Predicate<Channel>)Channel::isArchived).negate())
            .findAny().get();

        String tooLongPurpose = Stream.generate(() -> " ")
            .limit(251) // TODO: couldn't know limit length of purpose?
            .collect(Collectors.joining());

        slack.channels().setPurpose(channel.id(), tooLongPurpose);
    }

}

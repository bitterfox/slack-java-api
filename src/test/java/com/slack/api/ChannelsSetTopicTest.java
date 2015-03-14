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

/**
 *
 * @author bitter_fox
 */
public class ChannelsSetTopicTest extends AbstractApiIssuerTest
{
    public ChannelsSetTopicTest()
    {
        super(slack -> slack.channels().setTopic(new ChannelIdImpl(""), ""));
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

    private void setTopic(Slack slack, Channel channel)
    {
        String topic = "Test Topic" + System.currentTimeMillis();
        Channels.SetTopic setTopic = slack.channels().setTopic(channel.id(), topic);
        System.out.println(setTopic.topic());

        Assert.assertEquals(topic, setTopic.topic());
        Assert.assertEquals(topic, slack.channels().info(channel.id()).channel().topic().value());

        setTopic = slack.channels().setTopic(channel.id(), channel.topic().value());

        Assert.assertEquals(channel.topic().value(), setTopic.topic());
        Assert.assertEquals(channel.topic().value(), slack.channels().info(channel.id()).channel().topic().value());
    }

    @Test
    public void testSetTopic()
    {
        Slack slack = this.authedSlack();

        Channel channel = slack.channels().list().channels().stream()
            .filter(Channel::isMember)
            .filter(((Predicate<Channel>)Channel::isArchived).negate())
            .findAny().get();

        this.setTopic(slack, channel);
    }

    @Test(expected = NotInChannelException.class)
    public void testNotInChannel()
    {
        Slack slack = this.authedSlack();

        Channel channel = slack.channels().list().channels().stream()
            .filter(((Predicate<Channel>)Channel::isMember).negate())
            .filter(((Predicate<Channel>)Channel::isArchived).negate())
            .findAny().get();

        this.setTopic(slack, channel);
    }

    @Test(expected = IsArchivedException.class)
    public void testIsArchived()
    {
        Slack slack = this.authedSlack();

        Channel channel = slack.channels().list().channels().stream()
            .filter(Channel::isArchived)
            .findAny().get();

        this.setTopic(slack, channel);
    }

    @Test(expected = ChannelNotFoundException.class)
    public void testChannelNotFound()
    {
        Slack slack = this.authedSlack();

        slack.channels().setTopic(new ChannelIdImpl(""), "");
    }

    @Test(expected = TooLongException.class)
    public void testTooLong()
    {
        Slack slack = this.authedSlack();

        Channel channel = slack.channels().list().channels().stream()
            .filter(Channel::isMember)
            .filter(((Predicate<Channel>)Channel::isArchived).negate())
            .findAny().get();

        String tooLongTopic = Stream.generate(() -> " ")
            .limit(251) // TODO: couldn't know limit length of topic?
            .collect(Collectors.joining());

        slack.channels().setTopic(channel.id(), tooLongTopic);
    }
}

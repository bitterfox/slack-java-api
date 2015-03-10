/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api;

import com.slack.Slack;
import com.slack.api.exception.CannotLeaveGeneralException;
import com.slack.api.exception.ChannelNotFoundException;
import com.slack.data.Channel;
import java.util.List;
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
public class ChannelsLeaveTest extends AbstractApiTest
{
    public ChannelsLeaveTest()
    {
        super(slack -> slack.channels().leave(""));
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
    public void testLeave()
    {
        Slack slack = this.authedSlack();

        List<Channel> channels = slack.channels().list().channels();
        Channel channel = channels.stream()
            .filter(Channel::isMember)
            .filter(c -> !c.isGeneral())
            .findAny().get();

        Channels.Leave leave = slack.channels().leave(channel.id());
        Assert.assertFalse(leave.notInChannel());

        leave = slack.channels().leave(channel.id());
        Assert.assertTrue(leave.notInChannel());

        slack.channels().join(channel.name());
    }

    @Test
    public void testLeaveGeneral()
    {
        Slack slack = this.authedSlack();

        List<Channel> channels = slack.channels().list().channels();
        Channel channel = channels.stream()
            .filter(Channel::isMember)
            .filter(c -> c.isGeneral())
            .findAny().get();

        Tests.assertException(() -> slack.channels().leave(channel.id()), CannotLeaveGeneralException.class);
    }

    @Test
    public void testChannelNotFound()
    {
        Tests.assertException(() -> authedSlack().channels().leave(""), ChannelNotFoundException.class);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api;

import com.slack.Slack;
import com.slack.api.exception.ChannelNotFoundException;
import com.slack.api.exception.NotArchivedException;
import com.slack.data.Channel;
import com.slack.data.impl.ChannelIdImpl;
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
public class ChannelsUnarchiveTest extends AbstractApiTest
{

    public ChannelsUnarchiveTest()
    {
        super(slack -> slack.channels().unarchive(new ChannelIdImpl("")));
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
    public void testUnarchive()
    {
        Slack slack = this.authedSlack();

        Channel channel = slack.channels().list().channels().stream()
            .filter(Channel::isArchived)
            .findAny().get();

        slack.channels().unarchive(channel.id());

        Assert.assertEquals(false, slack.channels().info(channel.id()).channel().isArchived());

        slack.channels().archive(channel.id());
    }

    @Test(expected = ChannelNotFoundException.class)
    public void testChannelNotFound()
    {
        Slack slack = this.authedSlack();

        slack.channels().unarchive(new ChannelIdImpl(""));
    }

    @Test(expected = NotArchivedException.class)
    public void testNotArchivedException()
    {
        Slack slack = this.authedSlack();

        Channel channel = slack.channels().list().channels().stream()
            .filter(c -> !c.isArchived())
            .findAny().get();

        slack.channels().unarchive(channel.id());
    }
}

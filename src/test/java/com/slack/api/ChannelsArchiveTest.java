/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api;

import com.slack.Slack;
import com.slack.api.exception.CannotDoGeneralException;
import com.slack.api.exception.ChannelNotFoundException;
import com.slack.api.exception.IsArchivedException;
import com.slack.data.Channel;
import com.slack.data.impl.ChannelIdImpl;
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
public class ChannelsArchiveTest extends AbstractApiTest
{
    public ChannelsArchiveTest()
    {
        super(slack -> slack.channels().archive(new ChannelIdImpl("")));
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
    public void testArchive()
    {
        Slack slack = this.authedSlack();

        Channel channel = slack.channels().list().channels().stream()
            .filter(Channel::isMember)
            .filter(c -> !c.isArchived())
            .findAny().get();

        slack.channels().archive(channel.id());

        Assert.assertEquals(true, slack.channels().info(channel.id()).channel().isArchived());

        slack.channels().unarchive(channel.id());
    }

    @Test(expected = ChannelNotFoundException.class)
    public void testChannelNotFound()
    {
        Slack slack = this.authedSlack();

        slack.channels().archive(new ChannelIdImpl(""));
    }

    @Test(expected = IsArchivedException.class)
    public void testIsArchived()
    {
        Slack slack = this.authedSlack();

        Channel channel = slack.channels().list().channels().stream()
            .filter(Channel::isArchived)
            .findAny().get();

        slack.channels().archive(channel.id());
    }

    @Test(expected = CannotDoGeneralException.class)
    public void testCannotArchiveGeneral()
    {
        Slack slack = this.authedSlack();

        Channel channel = slack.channels().list().channels().stream()
            .filter(Channel::isGeneral)
            .findAny().get();

        slack.channels().archive(channel.id());
    }
}

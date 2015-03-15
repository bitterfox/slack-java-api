/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.Slack;
import com.slack.api.Channels;
import com.slack.data.impl.ChannelIdImpl;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author bitter_fox
 */
public class ChannelsRenameTest extends AbstractApiTest<Channels, Channels.Rename>
{
    public ChannelsRenameTest()
    {
        super(Slack::channels, channels -> channels.rename(new ChannelIdImpl(""), ""));
    }

    @Result("{\"ok\": true,"
        + "\"channel\":"
        + "{"
        +     "\"id\": \"C024BE91L\","
        +     "\"is_channel\": true,"
        +     "\"name\": \"new_name\","
        +     "\"created\": 1360782804"
        + "}"
        + "}")
    @Test
    public void test()
    {
        Channels.Rename rename = this.call();

        Assert.assertEquals("C024BE91L", rename.id().id());
        Assert.assertEquals(true, rename.isChannel());
        Assert.assertEquals("new_name", rename.name());
        Assert.assertEquals(1360782804, rename.created());
    }
}

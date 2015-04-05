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
public class ChannelsSetTopicTest extends AbstractApiTest<Channels, Channels.SetTopic>
{
    public ChannelsSetTopicTest()
    {
        super(Slack::channels, channels -> channels.setTopic(new ChannelIdImpl(""), ""));
    }

    @Result("{\"ok\": true,"
        + "\"topic\": \"new_topic\""
        + "}")
    @Test
    public void test()
    {
        Channels.SetTopic setTopic = this.call();

        Assert.assertEquals("new_topic", setTopic.topic());
    }
}

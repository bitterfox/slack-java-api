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
public class ChannelsLeaveTest extends AbstractApiTest<Channels, Channels.Leave>
{
    public ChannelsLeaveTest()
    {
        super(Slack::channels, channels -> channels.leave(new ChannelIdImpl("")));
    }

    @Result("{\"ok\": true}")
    @Test
    public void test()
    {
        Channels.Leave leave = this.call();

        Assert.assertEquals(false, leave.notInChannel());
    }

    @Result("{\"ok\": true,"
        + "\"not_in_channel\":true"
        + "}")
    @Test
    public void testNotInChannel()
    {
        Channels.Leave leave = this.call();

        Assert.assertEquals(true, leave.notInChannel());
    }
}

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
public class ChannelsSetPurposeTest extends AbstractApiTest<Channels, Channels.SetPurpose>
{
    public ChannelsSetPurposeTest()
    {
        super(Slack::channels, channels -> channels.setPurpose(new ChannelIdImpl(""), ""));
    }

    @Result("{\"ok\": true,"
        + "\"purpose\": \"new_purpose\""
        + "}")
    @Test
    public void test()
    {
        Channels.SetPurpose setPurpose = this.call();

        Assert.assertEquals("new_purpose", setPurpose.purpose());
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.Slack;
import com.slack.api.Channels;
import com.slack.data.impl.ChannelIdImpl;
import com.slack.data.impl.UserIdImpl;
import org.junit.Test;

/**
 *
 * @author bitter_fox
 */
public class ChannelsKickTest extends AbstractApiTest<Channels, Channels.Kick>
{
    public ChannelsKickTest()
    {
        super(Slack::channels, channels -> channels.kick(new ChannelIdImpl(""), new UserIdImpl("")));
    }

    @Result("{\"ok\": true}")
    @Test
    public void test()
    {
        this.call();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.Slack;
import com.slack.api.Channels;
import com.slack.data.impl.ChannelIdImpl;
import org.junit.Test;

/**
 *
 * @author bitter_fox
 */
public class ChannelsArchiveTest extends AbstractApiTest<Channels, Channels.Archive>
{
    public ChannelsArchiveTest()
    {
        super(Slack::channels, channels -> channels.archive(new ChannelIdImpl("")));
    }

    @Result("{\"ok\": true}")
    @Test
    public void test()
    {
        this.call();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.Slack;
import com.slack.api.Channels;
import java.util.Objects;
import org.junit.Test;

/**
 *
 * @author bitter_fox
 */
public class ChannelsCreateTest extends AbstractApiTest<Channels, Channels.Create>
{
    public ChannelsCreateTest()
    {
        super(Slack::channels, channels -> channels.create(""));
    }

    @Result("{\"ok\": true,"
        + "\"channel\": {}"
        + "}")
    @Test
    public void test()
    {
        Channels.Create create = this.call();

        Objects.requireNonNull(create.channel());
    }
}

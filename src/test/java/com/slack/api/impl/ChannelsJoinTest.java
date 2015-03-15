/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.Slack;
import com.slack.api.Channels;
import java.util.Objects;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author bitter_fox
 */
public class ChannelsJoinTest extends AbstractApiTest<Channels, Channels.Join>
{
    public ChannelsJoinTest()
    {
        super(Slack::channels, channels -> channels.join(""));
    }

    @Result("{\"ok\":true,"
        + "\"channel\":{}"
        + "}"
        )
    @Test
    public void test()
    {
        Channels.Join join = this.call();

        Objects.requireNonNull(join.channel());
        Assert.assertEquals(false, join.alreadyInChannel());
    }

    @Result("{\"ok\":true,"
        + "\"channel\":{},"
        + "\"already_in_channel\":true"
        + "}"
        )
    @Test
    public void testAlreadyInChannel()
    {
        Channels.Join join = this.call();

        Objects.requireNonNull(join.channel());
        Assert.assertEquals(true, join.alreadyInChannel());
    }

}

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
import java.util.Objects;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author bitter_fox
 */
public class ChannelsInviteTest extends AbstractApiTest<Channels, Channels.Invite>
{
    public ChannelsInviteTest()
    {
        super(Slack::channels, channels -> channels.invite(new ChannelIdImpl(""), new UserIdImpl("")));
    }

    @Result("{\"ok\":true,"
        + "\"channel\":{}"
        + "}"
        )
    @Test
    public void test()
    {
        Channels.Invite invite = this.call();

        Objects.requireNonNull(invite.channel());
    }

    @Result("{\"ok\":true,"
        + "\"channel\":{},"
        + "\"already_in_channel\":true"
        + "}"
        )
    @Test
    public void testAlreadyInChannel()
    {
        Channels.Invite invite = this.call();

        Objects.requireNonNull(invite.channel());
    }
}

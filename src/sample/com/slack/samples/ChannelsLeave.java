/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.samples;

import com.slack.Slack;
import com.slack.api.Channels;
import com.slack.data.Channel;
import java.util.List;

/**
 *
 * @author bitter_fox
 */
public class ChannelsLeave
{
    public static void main(String[] args)
    {
        Slack slack = Slack.create(config ->
            config.token("YOUR-TOKEN")
        );

        List<Channel> channels = slack.channels().list().channels();
        Channel channel = channels.stream()
            .filter(Channel::isMember)
            .filter(c -> !c.isGeneral())
            .findAny().get();

        Channels.Leave leave = slack.channels().leave(channel.id());
        System.out.println(leave.notInChannel());

        leave = slack.channels().leave(channel.id());
        System.out.println(leave.notInChannel());

        slack.channels().join(channel.name());
    }
}

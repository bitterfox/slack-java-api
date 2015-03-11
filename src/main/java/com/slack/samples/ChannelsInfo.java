/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.samples;

import com.slack.Slack;
import com.slack.api.Channels;
import com.slack.data.Channel;

/**
 *
 * @author bitter_fox
 */
public class ChannelsInfo
{
    public static void main(String[] args)
    {
        Slack slack = Slack.create(
            config ->
                config.token("YOUR-TOKEN")
        );

        Channel channel = slack.channels().list().channels().stream()
            .findAny().get();

        Channels.Info channelsInfo = slack.channels().info(channel.id());

        channel = channelsInfo.channel();

        System.out.println(channel.id());
        System.out.println(channel.name());
        System.out.println(channel.topic().value());
        System.out.println(channel.purpose().value());
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.samples;

import com.slack.Slack;
import com.slack.api.Channels;

/**
 *
 * @author bitter_fox
 */
public class ChannelsList
{
    public static void main(String[] args)
    {
        Slack slack = Slack.create(
            config ->
                config.token("YOUR-TOKEN")
            );

        Channels.List channelsList = slack.channels().list();

        channelsList.channels().stream()
            .forEach(
                channel ->
                {
                    System.out.println(channel.id());
                    System.out.println(channel.name());
                    System.out.println(channel.topic().value());
                    System.out.println(channel.purpose().value());
                });
    }
}

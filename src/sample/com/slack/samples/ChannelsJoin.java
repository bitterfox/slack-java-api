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
public class ChannelsJoin
{
    public static void main(String[] args)
    {
        Slack slack = Slack.create(
            config ->
                config.token("YOUR-TOKEN")
        );

        Channels.Join channelsJoin = slack.channels().join("general");

        System.out.println(channelsJoin.alreadyInChannel());
        System.out.println(channelsJoin.channel());

        channelsJoin = slack.channels().join("test");

        System.out.println(channelsJoin.alreadyInChannel());
        System.out.println(channelsJoin.channel());
    }
}

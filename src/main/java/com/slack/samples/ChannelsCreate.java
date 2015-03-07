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
public class ChannelsCreate
{
    public static void main(String[] args)
    {
        Slack slack = Slack.create(config ->
            config.token("YOUR-TOKEN")
        );

        Channels.Create channelsCreate = slack.channels().create("sample");

        System.out.println(channelsCreate.channel().id());
        System.out.println(channelsCreate.channel().name());
        System.out.println(channelsCreate.channel().creator());
        channelsCreate.channel().members().forEach(System.out::println);
    }
}

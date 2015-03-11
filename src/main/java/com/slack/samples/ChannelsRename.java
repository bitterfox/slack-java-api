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
public class ChannelsRename
{
    public static void main(String[] args)
    {
        Slack slack = Slack.create(config ->
            config.token("YOUR-TOKEN")
        );

        Channel test = slack.channels().list().channels().stream()
            .filter(channel -> channel.name().equals("test"))
            .findFirst().get();
        Channels.Rename rename = slack.channels().rename(test.id(), "testtesttest");

        System.out.println(rename.id());
        System.out.println(rename.isChannel());
        System.out.println(rename.created());
        System.out.println(rename.name());

        slack.channels().rename(test.id(), test.name());
    }
}

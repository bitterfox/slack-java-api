/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.samples;

import com.slack.Slack;
import com.slack.data.Channel;

/**
 *
 * @author bitter_fox
 */
public class ChannelsArchive
{
    public static void main(String[] args)
    {
        Slack slack = Slack.create(config ->
            config.token("YOUR-TOKEN")
        );

        Channel channel = slack.channels().list().channels().stream()
            .filter(Channel::isMember)
            .filter(c -> !c.isArchived())
            .findAny().get();

        slack.channels().archive(channel.id());
    }
}

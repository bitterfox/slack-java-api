/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.samples;

import com.slack.Slack;
import com.slack.api.Channels;
import com.slack.data.Channel;
import java.util.function.Predicate;

/**
 *
 * @author bitter_fox
 */
public class ChannelsSetTopic
{
    public static void main(String[] args)
    {
        Slack slack = Slack.create(config ->
            config.token("YOUR-TOKEN")
        );

        Channel channel = slack.channels().list().channels().stream()
            .filter(Channel::isMember)
            .filter(((Predicate<Channel>)Channel::isArchived).negate())
            .findAny().get();

        Channels.SetTopic setTopic = slack.channels().setTopic(channel.id(), "Test Topic");
        System.out.println(setTopic.topic());

        slack.channels().setTopic(channel.id(), channel.topic().value());
    }
}

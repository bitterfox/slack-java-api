/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.samples;

import com.slack.Slack;
import com.slack.api.Channels;
import com.slack.data.Channel;
import com.slack.data.User;

/**
 *
 * @author bitter_fox
 */
public class ChannelsInvite
{
    public static void main(String[] args)
    {
        Slack slack = Slack.create(config ->
            config.token("YOUR-TOKEN")
        );

        Channel channel = slack.channels().list().channels().stream()
            .filter(Channel::isMember)
            .findAny().get();

        User user = slack.users().list().members().stream()
            .filter(u -> !channel.members().contains(u.id()))
            .findAny().get();

        Channels.Invite invite = slack.channels().invite(channel.id(), user.id());

        System.out.println(invite.channel());
    }
}

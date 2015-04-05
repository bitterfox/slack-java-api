/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.samples;

import com.slack.Slack;
import com.slack.data.Channel;
import com.slack.data.User;
import com.slack.data.UserId;

/**
 *
 * @author bitter_fox
 */
public class ChannelsKick
{
    public static void main(String[] args)
    {
        Slack slack = Slack.create(config ->
            config.token("YOUR-TOKEN")
        );

        Channel channel = slack.channels().list().channels().stream()
            .filter(Channel::isMember)
            .filter(c -> c.numMembers() >= 2)
            .filter(c -> !c.isGeneral())
            .findAny().get();

        UserId me = slack.auth().test().userId();
        UserId user = slack.users().list().members().stream()
            .map(User::id)
            .filter(u -> !u.equals(me))
            .findAny().get();

        slack.channels().kick(channel.id(), user);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.samples;

import com.slack.Slack;
import com.slack.api.ChatApi;
import com.slack.data.Channel;

/**
 *
 * @author bitter_fox
 */
public class ChatDelete
{
    public static void main(String[] args)
    {
        Slack slack = Slack.create(config ->
            config.token("YOUR-TOKEN")
        );

        Channel channel = slack.channels().list().channels().stream()
            .filter(Channel::isGeneral)
            .findAny().get();

        ChatApi.PostMessage postMessage = slack.chat().postMessage(channel.id(), "test");
        ChatApi.Delete delete = slack.chat().delete(channel.id(), postMessage.timestamp());
    }
}

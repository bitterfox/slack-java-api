/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.samples;

import com.slack.Slack;
import com.slack.data.Channel;
import com.slack.util.Either;

/**
 *
 * @author bitter_fox
 */
public class ChatPostMessage
{
    public static void main(String[] args)
    {
        Slack slack = Slack.create(config ->
            config.token("YOUR-TOKEN")
        );

        Channel channel = slack.channels().list().channels().stream()
            .filter(Channel::isGeneral)
            .findAny().get();

        String message = "Hello http://google.com/ #general @slackbot";

        slack.chat().postMessage(channel.id(), message);
        slack.chat().postMessageAsBot(channel.id(), message, "api-test");
        slack.chat().postMessageAsBot(channel.id(), message, "api-test-with-icon", Either.right(":a:"));
    }
}

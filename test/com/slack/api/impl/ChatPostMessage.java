/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.Slack;
import com.slack.api.ChatApi;
import com.slack.data.impl.ChannelIdImpl;
import java.util.Objects;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author bitter_fox
 */
public class ChatPostMessage extends AbstractApiTest<ChatApi, ChatApi.PostMessage>
{
    public ChatPostMessage()
    {
        super(Slack::chat, chat -> chat.postMessage(new ChannelIdImpl(""), ""));
    }

    @Result("{\"ok\":true,"
        + "\"ts\":\"0000.0000\","
        + "\"message\":{}"
        + "}")
    @Test
    public void test()
    {
        ChatApi.PostMessage postMessage = this.call();

        Assert.assertEquals("0000.0000", postMessage.timestamp());
        Objects.requireNonNull(postMessage.message());
    }
}

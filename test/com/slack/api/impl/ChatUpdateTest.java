/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.Slack;
import com.slack.api.ChatApi;
import com.slack.data.impl.ChannelIdImpl;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author bitter_fox
 */
public class ChatUpdateTest extends AbstractApiTest<ChatApi, ChatApi.Update>
{
    public ChatUpdateTest()
    {
        super(Slack::chat, chat -> chat.update(new ChannelIdImpl(""), "", ""));
    }

    @Result("{\"ok\":true,"
        + "\"ts\":\"0000.0000\","
        + "\"text\":\"test\""
        + "}")
    @Test
    public void test()
    {
        ChatApi.Update update = this.call();

        Assert.assertEquals("0000.0000", update.timestamp());
        Assert.assertEquals("test", update.text());
    }
}

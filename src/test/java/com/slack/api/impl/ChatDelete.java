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
import static org.junit.Assert.*;

/**
 *
 * @author bitter_fox
 */
public class ChatDelete extends AbstractApiTest<ChatApi, ChatApi.Delete>
{
    public ChatDelete()
    {
        super(Slack::chat, chat -> chat.delete(new ChannelIdImpl(""), ""));
    }

    @Result("{\"ok\":true,"
        + "\"ts\":\"0000.0000\""
        + "}")
    @Test
    public void test()
    {
        ChatApi.Delete delete = this.call();

        Assert.assertEquals("0000.0000", delete.timestamp());
    }
}

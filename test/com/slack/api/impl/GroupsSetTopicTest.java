/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.Slack;
import com.slack.api.Channels;
import com.slack.api.Groups;
import com.slack.data.impl.GroupIdImpl;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author bitter_fox
 */
public class GroupsSetTopicTest extends AbstractApiTest<Groups, Groups.SetTopic>
{
    public GroupsSetTopicTest()
    {
        super(Slack::groups, groups -> groups.setTopic(new GroupIdImpl(""), ""));
    }

    @Result("{\"ok\": true,"
        + "\"topic\": \"new_topic\""
        + "}")
    @Test
    public void test()
    {
        Groups.SetTopic setTopic = this.call();

        Assert.assertEquals("new_topic", setTopic.topic());
    }
}

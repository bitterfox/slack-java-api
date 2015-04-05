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
public class GroupsSetPurposeTest extends AbstractApiTest<Groups, Groups.SetPurpose>
{
    public GroupsSetPurposeTest()
    {
        super(Slack::groups, groups -> groups.setPurpose(new GroupIdImpl(""), ""));
    }

    @Result("{\"ok\": true,"
        + "\"purpose\": \"new_purpose\""
        + "}")
    @Test
    public void test()
    {
        Groups.SetPurpose setPurpose = this.call();

        Assert.assertEquals("new_purpose", setPurpose.purpose());
    }
}

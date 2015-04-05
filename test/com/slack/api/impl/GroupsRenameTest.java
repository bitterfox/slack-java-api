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
public class GroupsRenameTest extends AbstractApiTest<Groups, Groups.Rename>
{
    public GroupsRenameTest()
    {
        super(Slack::groups, groups -> groups.rename(new GroupIdImpl(""), ""));
    }

    @Result("{\"ok\": true,"
        + "\"channel\":"
        + "{"
        +     "\"id\": \"G024BE91L\","
        +     "\"is_group\": true,"
        +     "\"name\": \"new_name\","
        +     "\"created\": 1360782804"
        + "}"
        + "}")
    @Test
    public void test()
    {
        Groups.Rename rename = this.call();

        Assert.assertEquals("G024BE91L", rename.id().id());
        Assert.assertEquals(true, rename.isGroup());
        Assert.assertEquals("new_name", rename.name());
        Assert.assertEquals(1360782804, rename.created());
    }
}

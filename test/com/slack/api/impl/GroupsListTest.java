/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.Slack;
import com.slack.api.Channels;
import com.slack.api.Groups;
import java.util.Objects;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author bitter_fox
 */
public class GroupsListTest extends AbstractApiTest<Groups, Groups.List>
{
    public GroupsListTest()
    {
        super(Slack::groups, Groups::list);
    }

    @Result("{\"ok\":true,"
        + "\"groups\":[{}, {}, {}]"
        + "}"
        )
    @Test
    public void test()
    {
        Groups.List list = this.call();

        Assert.assertEquals(3, list.groups().size());
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.Slack;
import com.slack.api.Groups;
import com.slack.data.Group;
import com.slack.data.impl.GroupIdImpl;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author bitter_fox
 */
public class GroupsCloseTest extends AbstractApiTest<Groups, Groups.Close>
{
    public GroupsCloseTest()
    {
        super(Slack::groups, groups -> groups.close(new GroupIdImpl("")));
    }

    @Result("{\"ok\": true}")
    @Test
    public void test()
    {
        Groups.Close close = this.call();

        Assert.assertEquals(false, close.alreadyClosed());
        Assert.assertEquals(false, close.noOperation());
    }

    @Result("{\"ok\": true,"
        + "\"no_op\": true,"
        + "\"already_closed\": true}")
    @Test
    public void testAlreadyClosed()
    {
        Groups.Close close = this.call();

        Assert.assertEquals(true, close.alreadyClosed());
        Assert.assertEquals(true, close.noOperation());
    }
}

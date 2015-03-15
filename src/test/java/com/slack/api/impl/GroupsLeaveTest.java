/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.Slack;
import com.slack.api.Groups;
import com.slack.data.impl.GroupIdImpl;
import org.junit.Test;

/**
 *
 * @author bitter_fox
 */
public class GroupsLeaveTest extends AbstractApiTest<Groups, Groups.Leave>
{
    public GroupsLeaveTest()
    {
        super(Slack::groups, groups -> groups.leave(new GroupIdImpl("")));
    }

    @Result("{\"ok\":true}")
    @Test
    public void test()
    {
        this.call();
    }
}

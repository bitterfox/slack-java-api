/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.Slack;
import com.slack.api.Groups;
import com.slack.data.impl.GroupIdImpl;
import com.slack.data.impl.UserIdImpl;
import org.junit.Test;

/**
 *
 * @author bitter_fox
 */
public class GroupsKickTest extends AbstractApiTest<Groups, Groups.Kick>
{
    public GroupsKickTest()
    {
        super(Slack::groups, groups -> groups.kick(new GroupIdImpl(""), new UserIdImpl("")));
    }

    @Result("{\"ok\": true}")
    @Test
    public void test()
    {
        this.call();
    }
}

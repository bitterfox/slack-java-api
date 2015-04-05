/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.Slack;
import com.slack.api.Groups;
import org.junit.Test;

/**
 *
 * @author bitter_fox
 */
public class GroupsCreateTest extends AbstractApiTest<Groups, Groups.Create>
{
    public GroupsCreateTest()
    {
        super(Slack::groups, groups -> groups.create(""));
    }

    @Result("{\"ok\":true,"
        + "\"group\":{}"
        + "}")
    @Test
    public void test()
    {
        this.call();
    }
}

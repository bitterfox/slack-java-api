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
import com.slack.data.impl.UserIdImpl;
import java.util.Objects;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author bitter_fox
 */
public class GroupsInviteTest extends AbstractApiTest<Groups, Groups.Invite>
{
    public GroupsInviteTest()
    {
        super(Slack::groups, groups -> groups.invite(new GroupIdImpl(""), new UserIdImpl("")));
    }

    @BeforeClass
    public static void setUpClass()
    {
    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
    }


    @Result("{\"ok\":true,"
        + "\"group\":{}"
        + "}"
        )
    @Test
    public void test()
    {
        Groups.Invite invite = this.call();

        Objects.requireNonNull(invite.group());
        Assert.assertEquals(false, invite.alreadyInGroup());
    }

    @Result("{\"ok\":true,"
        + "\"group\":{},"
        + "\"already_in_group\":true"
        + "}"
        )
    @Test
    public void testAlreadyInChannel()
    {
        Groups.Invite invite = this.call();

        System.out.println(invite.group());

        Objects.requireNonNull(invite.group());
        Assert.assertEquals(true, invite.alreadyInGroup());
    }
}

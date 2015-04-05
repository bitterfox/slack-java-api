/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.Slack;
import com.slack.api.Auth;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author bitter_fox
 */
public class AuthTestTest extends AbstractApiTest<Auth, Auth.Test>
{
    public AuthTestTest()
    {
        super(Slack::auth, Auth::test);
    }

    @Result("{\"ok\":true,"
        + "\"url\":\"https:\\/\\/test.slack.com\\/\","
        + "\"team\":\"TEST\","
        + "\"user\":\"TEST-USER\","
        + "\"team_id\":\"TEAM-ID\","
        + "\"user_id\":\"USER-ID\""
        + "}")
    @Test
    public void test()
    {
        Auth.Test test = this.call();

        Assert.assertEquals("https://test.slack.com/", test.url().toString());
        Assert.assertEquals("TEST", test.team());
        Assert.assertEquals("TEST-USER", test.user());
        Assert.assertEquals("TEAM-ID", test.teamId());
        Assert.assertEquals("USER-ID", test.userId().id());
    }
}

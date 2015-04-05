/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slack.api.impl;

import com.slack.Slack;
import com.slack.api.Users;
import org.junit.Test;

/**
 *
 * @author bitter_fox
 */
public class UsersSetActiveTest extends AbstractApiTest<Users, Users.SetActive>
{
    public UsersSetActiveTest()
    {
        super(Slack::users, Users::setActive);
    }

    @Result("{\"ok\":true}")
    @Test
    public void test()
    {
        this.call();
    }
}

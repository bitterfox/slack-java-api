/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.Slack;
import com.slack.api.Users;
import com.slack.data.impl.UserIdImpl;
import java.util.Objects;
import org.junit.Test;

/**
 *
 * @author bitter_fox
 */
public class UsersInfoTest extends AbstractApiTest<Users, Users.Info>
{
    public UsersInfoTest()
    {
        super(Slack::users, users -> users.info(new UserIdImpl("")));
    }

    @Result("{\"ok\": true," +
        "\"user\": {}" +
        "}")
    @Test()
    public void test()
    {
        Users.Info info = this.call();

        Objects.requireNonNull(info.user());
    }
}

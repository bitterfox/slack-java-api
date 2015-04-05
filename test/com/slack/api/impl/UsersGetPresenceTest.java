/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.Slack;
import com.slack.api.Users;
import com.slack.data.impl.UserIdImpl;
import java.util.Optional;
import java.util.OptionalInt;
import org.junit.Assert;
import org.junit.Test;


/**
 *
 * @author bitter_fox
 */
public class UsersGetPresenceTest extends AbstractApiTest<Users, Users.GetPresence>
{
    public UsersGetPresenceTest()
    {
        super(Slack::users, users -> users.getPresence(new UserIdImpl("")));
    }

    @Result("{"
        + "\"ok\": true,"
        + "\"presence\": \"active\""
        + "}")
    @Test
    public void test1()
    {
        Users.GetPresence getPresence = this.call();

        Assert.assertEquals(true, getPresence.isActive());
        Assert.assertEquals(getPresence.isActive(), !getPresence.isAway());
        Assert.assertEquals(Optional.<Boolean>empty(), getPresence.isOnline());
        Assert.assertEquals(Optional.<Boolean>empty(), getPresence.isAutoAway());
        Assert.assertEquals(Optional.<Boolean>empty(), getPresence.isManualAway());
        Assert.assertEquals(OptionalInt.empty(), getPresence.connectionCount());
        Assert.assertEquals(OptionalInt.empty(), getPresence.lastActivity());
    }

    @Result("{"
        + "\"ok\": true,"
        + "\"presence\": \"away\""
        + "}")
    @Test
    public void test2()
    {
        Users.GetPresence getPresence = this.call();

        Assert.assertEquals(true, getPresence.isAway());
        Assert.assertEquals(getPresence.isActive(), !getPresence.isAway());
        Assert.assertEquals(Optional.<Boolean>empty(), getPresence.isOnline());
        Assert.assertEquals(Optional.<Boolean>empty(), getPresence.isAutoAway());
        Assert.assertEquals(Optional.<Boolean>empty(), getPresence.isManualAway());
        Assert.assertEquals(OptionalInt.empty(), getPresence.connectionCount());
        Assert.assertEquals(OptionalInt.empty(), getPresence.lastActivity());
    }

    @Result("{"
        + "\"ok\": true,"
        + "\"presence\": \"active\","
        + "\"online\": true,"
        + "\"auto_away\": true,"
        + "\"manual_away\": true,"
        + "\"connection_count\": 1,"
        + "\"last_activity\": 1419027078"
        + "}")
    @Test
    public void test3()
    {
        Users.GetPresence getPresence = this.call();

        Assert.assertEquals(true, getPresence.isActive());
        Assert.assertEquals(getPresence.isActive(), !getPresence.isAway());
        Assert.assertEquals(Optional.of(true), getPresence.isOnline());
        Assert.assertEquals(Optional.of(true), getPresence.isAutoAway());
        Assert.assertEquals(Optional.of(true), getPresence.isManualAway());
        Assert.assertEquals(OptionalInt.of(1), getPresence.connectionCount());
        Assert.assertEquals(OptionalInt.of(1419027078), getPresence.lastActivity());
    }
}

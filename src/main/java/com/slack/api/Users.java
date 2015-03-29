/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api;

import com.slack.data.User;
import com.slack.data.UserId;
import java.util.Optional;
import java.util.OptionalInt;

/**
 *
 * @author bitter_fox
 */
public interface Users
{
    @ApiIssuer
    Users.GetPresence getPresence(UserId userId);

    @ApiIssuer
    Users.Info info(UserId userId);

    @ApiIssuer
    Users.List list();

    @ApiIssuer
    Users.SetActive setActive();

    interface GetPresence
    {
        boolean isAway();
        boolean isActive();

        boolean forSelf();
        Optional<Boolean> isOnline();
        Optional<Boolean> isAutoAway();
        Optional<Boolean> isManualAway();
        OptionalInt connectionCount();
        OptionalInt lastActivity();
    }

    interface Info
    {
        User user();
    }

    interface List
    {
        java.util.List<User> members();
    }

    interface SetActive {}
}

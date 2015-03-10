/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.Slack;
import com.slack.api.ApiFactory;
import com.slack.api.Auth;
import com.slack.api.Channels;
import com.slack.api.Users;

/**
 *
 * @author bitter_fox
 */
public class ApiFactoryImpl implements ApiFactory
{

    @Override
    public Auth createAuth(Slack slack)
    {
        return new AuthImpl(slack);
    }

    @Override
    public Channels createChannels(Slack slack)
    {
        return new ChannelsImpl(slack);
    }

    @Override
    public Users createUsers(Slack slack)
    {
        return new UsersImpl(slack);
    }
}
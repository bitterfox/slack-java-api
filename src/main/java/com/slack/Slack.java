/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack;

import com.slack.api.Auth;
import com.slack.api.Channels;
import com.slack.api.Users;
import java.util.function.Consumer;

/**
 *
 * @author bitter_fox
 */
public class Slack
{
    private Configure config;
    private Auth auth;
    private Channels channels;
    private Users users;

    private Slack(Configure config)
    {
        this.config = config;
        auth = new Auth(this);
        channels = new Channels(this);
        users = new Users(this);
    }

    public static Slack create(Consumer<Configure> consumer)
    {
        Configure config = new Configure();
        consumer.accept(config);

        return new Slack(config);
    }

    public Auth auth()
    {
        return auth;
    }

    public Channels channels()
    {
        return channels;
    }

    public Users users()
    {
        return users;
    }

    public void configure(Consumer<Configure> consumer)
    {
        consumer.accept(config);
    }

    public Configure getConfigure()
    {
        return config;
    }
}

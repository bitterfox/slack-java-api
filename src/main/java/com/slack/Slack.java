/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack;

import com.slack.api.Auth;
import com.slack.api.Channels;
import com.slack.api.Files;
import com.slack.api.Groups;
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
    private Files files;
    private Groups groups;
    private Users users;

    private Slack(Configure config)
    {
        this.config = config;
        this.auth = config.apiFactory().createAuth(this);
        this.channels = config.apiFactory().createChannels(this);
        this.files = config.apiFactory().createFiles(this);
        this.groups = config.apiFactory().createGroups(this);
        this.users = config.apiFactory().createUsers(this);
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

    public Files files()
    {
        return files;
    }

    public Groups groups()
    {
        return groups;
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

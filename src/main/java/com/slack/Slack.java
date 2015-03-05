/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack;

import com.slack.api.Auth;
import java.util.function.Consumer;

/**
 *
 * @author bitter_fox
 */
public class Slack
{
    private Configure config;
    private Auth auth;

    private Slack(Configure config)
    {
        this.config = config;
        auth = new Auth(this);
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

    public void configure(Consumer<Configure> consumer)
    {
        consumer.accept(config);
    }

    public Configure getConfigure()
    {
        return config;
    }
}

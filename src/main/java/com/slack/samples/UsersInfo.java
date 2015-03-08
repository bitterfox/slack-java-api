/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.samples;

import com.slack.Slack;
import com.slack.data.Profile;
import com.slack.data.User;

/**
 *
 * @author bitter_fox
 */
public class UsersInfo
{
    public static void main(String[] args)
    {
        Slack slack = Slack.create(config ->
                config.token("YOUR-TOKEN")
        );

        User user = slack.users().info("USER-ID").user();

        System.out.println(user.id());
        System.out.println(user.name());
        System.out.println(user.realName());
        System.out.println(user.profile().image(Profile.ImageSize.ORIGINAL));
    }
}

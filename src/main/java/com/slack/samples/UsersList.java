/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.samples;

import com.slack.Slack;
import com.slack.api.Users;
import com.slack.data.Profile;

/**
 *
 * @author bitter_fox
 */
public class UsersList
{
    public static void main(String[] args)
    {
        Slack slack = Slack.create(config ->
            config.token("YOUR-TOKEN")
        );

        Users.List usersList = slack.users().list();

        usersList.members().forEach(
            user ->
            {
                System.out.println(user.id());
                System.out.println(user.name());
                System.out.println(user.realName());
                System.out.println(user.profile().image(Profile.ImageSize.ORIGINAL));
            });
    }
}

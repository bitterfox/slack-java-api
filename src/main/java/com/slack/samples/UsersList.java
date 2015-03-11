/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.samples;

import com.slack.Slack;
import com.slack.api.Users;

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

        usersList.members().forEach(System.out::println);
    }
}

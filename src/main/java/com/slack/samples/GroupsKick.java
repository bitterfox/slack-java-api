/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.samples;

import com.slack.Slack;
import com.slack.data.Group;
import com.slack.data.UserId;

/**
 *
 * @author bitter_fox
 */
public class GroupsKick
{
    public static void main(String[] args)
    {
        Slack slack = Slack.create(config ->
            config.token("YOUR-TOKEN")
        );

        Group group = slack.groups().list().groups().get(0);
        UserId user = group.members().stream()
            .filter(uid -> !slack.auth().test().userId().equals(uid))
            .findAny().get();

        slack.groups().kick(group.id(), user);

        slack.groups().invite(group.id(), user);
    }
}

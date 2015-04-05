/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.samples;

import com.slack.Slack;
import com.slack.api.Groups;
import com.slack.data.Group;
import com.slack.data.User;

/**
 *
 * @author bitter_fox
 */
public class GroupsInvite
{
    public static void main(String[] args)
    {
        Slack slack = Slack.create(config ->
            config.token("YOUR-TOKEN")
        );

        Group group = slack.groups().list().groups().get(0);

        User user = slack.users().list().members().stream()
            .filter(u -> !group.members().contains(u.id()))
            .findAny().get();

        Groups.Invite invite = slack.groups().invite(group.id(), user.id());

        System.out.println(invite.group());
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.samples;

import com.slack.Slack;
import com.slack.api.Groups;
import com.slack.data.Group;

/**
 *
 * @author bitter_fox
 */
public class GroupsSetPurpose
{
    public static void main(String[] args)
    {
        Slack slack = Slack.create(config ->
            config.token("YOUR-TOKEN")
        );

        Group group = slack.groups().list().groups().get(0);

        Groups.SetPurpose setPurpose = slack.groups().setPurpose(group.id(), "Test Purpose");
        System.out.println(setPurpose.purpose());

        slack.groups().setPurpose(group.id(), group.purpose().value());
    }
}

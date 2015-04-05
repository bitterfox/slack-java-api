/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.samples;

import com.slack.Slack;
import com.slack.data.Group;

/**
 *
 * @author bitter_fox
 */
public class GroupsUnarchive
{
    public static void main(String[] args)
    {
        Slack slack = Slack.create(config ->
            config.token("YOUR-TOKEN")
        );

        Group group = slack.groups().list().groups().stream()
            .filter(Group::isArchived)
            .findAny().get();

        slack.groups().unarchive(group.id());
    }
}

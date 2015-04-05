/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.samples;

import com.slack.Slack;
import com.slack.api.Groups;

/**
 *
 * @author bitter_fox
 */
public class GroupsCreate
{
    public static void main(String[] args)
    {
        Slack slack = Slack.create(config ->
            config.token("YOUR-TOKEN")
        );

        Groups.Create create = slack.groups().create("sample");

        System.out.println(create.group());
    }
}

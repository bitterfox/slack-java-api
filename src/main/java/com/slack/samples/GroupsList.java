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
public class GroupsList
{
    public static void main(String[] args)
    {
        Slack slack = Slack.create(config ->
            config.token("YOUR-TOKEN")
        );

        Groups.List list = slack.groups().list();

        System.out.println(list.groups());
    }
}

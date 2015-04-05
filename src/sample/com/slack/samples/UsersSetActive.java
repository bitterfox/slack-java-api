package com.slack.samples;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.slack.Slack;

/**
 *
 * @author bitter_fox
 */
public class UsersSetActive
{
    public static void main(String[] args)
    {
        Slack slack = Slack.create(config ->
            config.token("YOUR-TOKEN")
        );

        slack.users().setActive();
    }
}

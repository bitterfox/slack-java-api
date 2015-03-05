/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.samples;

import com.slack.Slack;
import com.slack.api.Auth;

/**
 *
 * @author bitter_fox
 */
public class Main
{
    public static void main(String[] args)
    {
        Slack slack = Slack.create(
            config ->
                config.token("YOUR-TOKEN")
            );

        Auth.Test authTest = slack.auth().test();

        System.out.println(authTest.url());
        System.out.println(authTest.user());
        System.out.println(authTest.team());
    }
}

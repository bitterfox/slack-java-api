/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.samples;

import com.slack.Slack;
import com.slack.api.ImApi;

/**
 *
 * @author bitter_fox
 */
public class ImList
{
    public static void main(String[] args)
    {
        Slack slack = Slack.create(
            config ->
                config.token("YOUR-TOKEN")
        );

        ImApi.List imsList = slack.im().list();

        imsList.ims().stream()
            .forEach(System.out::println);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.samples;

import com.slack.Slack;
import com.slack.api.Files;

/**
 *
 * @author bitter_fox
 */
public class FilesList
{
    public static void main(String[] args)
    {
        Slack slack = Slack.create(
            config ->
                config.token("YOUR-TOKEN")
        );

        Files.List filesList = slack.files().list();

        System.out.println(filesList.paging());
        filesList.files().stream()
            .forEach(System.out::println);
    }
}

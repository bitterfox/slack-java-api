/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.samples;

import com.slack.Slack;
import com.slack.api.Files;
import com.slack.data.SharedFile;

/**
 *
 * @author bitter_fox
 */
public class FilesInfo
{
    public static void main(String[] args)
    {
        Slack slack = Slack.create(
            config ->
                config.token("YOUR-TOKEN")
        );

        SharedFile file = slack.files().list().files().stream()
            .filter(f -> f.commentsCount() >= 2)
            .findAny().get();
        Files.Info info = slack.files().info(file.id());

        System.out.println(info.file());
        System.out.println(info.comments());
        System.out.println(info.paging());
    }
}

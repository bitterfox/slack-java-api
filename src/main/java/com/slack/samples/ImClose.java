/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.samples;

import com.slack.Slack;
import com.slack.api.ImApi;
import com.slack.data.Im;

/**
 *
 * @author bitter_fox
 */
public class ImClose
{
    public static void main(String[] args)
    {
        Slack slack = Slack.create(config ->
            config.token("YOUR-TOKEN")
        );

        Im im = slack.im().list().ims().get(0);

        ImApi.Close close = slack.im().close(im.id());

        System.out.println(close.alreadyClosed());
        System.out.println(close.noOperation());
    }
}

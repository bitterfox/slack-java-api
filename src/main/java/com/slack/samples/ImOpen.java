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
public class ImOpen
{
    public static void main(String[] args)
    {
        Slack slack = Slack.create(config ->
            config.token("YOUR-TOKEN")
        );

        Im im = slack.im().list().ims().get(0);

        ImApi.Open open = slack.im().open(im.user());

        System.out.println(open.im());
        System.out.println(open.alreadyOpen());
        System.out.println(open.noOperation());
    }
}

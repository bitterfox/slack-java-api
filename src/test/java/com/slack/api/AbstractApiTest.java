/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api;

import com.slack.Slack;

/**
 *
 * @author bitter_fox
 */
public class AbstractApiTest
{
    private Slack authedSlack = Slack.create(config -> config.token("YOUR-TOKEN"));

    protected Slack authedSlack()
    {
        return authedSlack;
    }
}

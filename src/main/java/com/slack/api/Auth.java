
package com.slack.api;


import com.slack.data.UserId;
import java.net.URL;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bitter_fox
 */
public interface Auth
{
    @ApiIssuer
    Auth.Test test();

    interface Test
    {
        URL url();
        String team();
        String user();
        String teamId();
        UserId userId();
    }
}

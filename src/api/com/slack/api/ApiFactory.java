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
public interface ApiFactory
{
    Auth createAuth(Slack slack);
    Channels createChannels(Slack slack);
    ChatApi createChat(Slack slack);
    Files createFiles(Slack slack);
    Groups createGroups(Slack slack);
    ImApi createIm(Slack slack);
    Users createUsers(Slack slack);
}

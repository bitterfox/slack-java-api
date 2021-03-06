/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.api.Channels;
import com.slack.api.Files;
import com.slack.api.Groups;
import com.slack.api.ImApi;
import com.slack.api.Users;
import javax.json.JsonObject;

/**
 *
 * @author bitter_fox
 */
class EmptyResult extends ApiResult implements
    Channels.Archive,
    Channels.Unarchive,
    Channels.Kick,
    Channels.Mark,
    Files.Delete,
    Groups.Archive,
    Groups.Unarchive,
    Groups.Kick,
    Groups.Leave,
    Groups.Mark,
    ImApi.Mark,
    Users.SetActive
{
    @Override
    protected void apply(JsonObject result) {}
}

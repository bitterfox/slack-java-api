/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.api.Channels;
import com.slack.api.Groups;
import javax.json.JsonObject;

/**
 *
 * @author bitter_fox
 */
class EmptyResult extends ApiResult implements Channels.Archive, Channels.Unarchive, Channels.Kick, Channels.Mark, Groups.Archive, Groups.Unarchive, Groups.Kick, Groups.Leave
{
    @Override
    protected void apply(JsonObject result) {}
}

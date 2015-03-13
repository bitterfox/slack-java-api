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
final class SetTopicResult extends ApiResult implements Channels.SetTopic, Groups.SetTopic
{
    private String topic;

    @Override
    public String topic()
    {
        return topic;
    }

    @Override
    protected void apply(JsonObject result)
    {
        this.topic = result.getString("topic");
    }
}

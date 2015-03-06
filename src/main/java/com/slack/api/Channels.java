/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api;

import com.slack.Slack;
import com.slack.data.Channel;
import com.slack.util.JsonUtil;
import java.util.Collections;
import java.util.stream.Collectors;
import javax.json.JsonObject;

/**
 *
 * @author bitter_fox
 */
public class Channels
{
    private Slack slack;
    private Api api;

    public Channels(Slack slack)
    {
        this.slack = slack;
        api = new Api(slack, "channels");
    }

    public Channels.List list()
    {
        GetApiRequest apiRequest = api.get("list", builder -> {});

        return apiRequest.issue(Channels.List::new);
    }

    public final class List extends ApiResult
    {
        private java.util.List<Channel> channels;

        public java.util.List<Channel> channels()
        {
            return channels;
        }

        @Override
        protected void apply(JsonObject result)
        {
            java.util.List<Channel> list =
                result.getJsonArray("channels").stream()
                    .map(JsonUtil::castToObject)
                    .map(slack.getConfigure().unmarshaller()::asChannel)
                    .collect(Collectors.toList());
            channels = Collections.unmodifiableList(list);
        }
    }
}

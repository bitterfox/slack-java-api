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

        return apiRequest.issue(Channels.List::new,
            (result, rawResult) ->
            {
                java.util.List<Channel> channels =
                    rawResult.getJsonArray("channels").stream()
                        .map(JsonUtil::castToObject)
                        .map(slack.getConfigure().unmarshaller()::asChannel)
                        .collect(Collectors.toList());
                result.channels(channels);
            });
    }

    public static class List
    {
        private java.util.List<Channel> channels;

        void channels(java.util.List<Channel> channels)
        {
            this.channels = Collections.unmodifiableList(channels);
        }

        public java.util.List<Channel> channels()
        {
            return channels;
        }
    }
}

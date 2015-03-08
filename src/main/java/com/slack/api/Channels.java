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

    @ApiIssuer
    public Channels.Create create(String name)
    {
        GetApiRequest apiRequest = api.get("create", builder -> builder.put("name", name));

        return apiRequest.issue(Channels.Create::new);
    }

    @ApiIssuer
    public Channels.List list()
    {
        GetApiRequest apiRequest = api.get("list");

        return apiRequest.issue(Channels.List::new);
    }

    @ApiIssuer
    public Channels.Join join(String name)
    {
        GetApiRequest apiRequest = api.get("join", builder -> builder.put("name", name));

        return apiRequest.issue(Channels.Join::new);
    }

    public final class Create extends ApiResult
    {
        private Channel channel;

        public Channel channel()
        {
            return channel;
        }

        @Override
        protected void apply(JsonObject result)
        {
            channel = slack.getConfigure().unmarshaller().asChannel(result.getJsonObject("channel"));
        }
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
            channels = slack.getConfigure().unmarshaller().asChannels(result.getJsonArray("channels"));
        }
    }

    public final class Join extends ApiResult
    {
        private static final String ALREADY_IN_CHANNEL = "already_in_channel";

        private Channel channel;
        private boolean alreadyInChannel;

        public Channel channel()
        {
            return channel;
        }

        public boolean alreadyInChannel()
        {
            return alreadyInChannel;
        }

        @Override
        protected void apply(JsonObject result)
        {
            channel = slack.getConfigure().unmarshaller().asChannel(result.getJsonObject("channel"));
            alreadyInChannel = result.getBoolean(ALREADY_IN_CHANNEL, false);
        }
    }
}

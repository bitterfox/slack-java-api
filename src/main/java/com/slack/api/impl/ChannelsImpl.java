/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.Slack;
import com.slack.api.ApiIssuer;
import com.slack.api.Channels;
import com.slack.data.Channel;
import com.slack.data.ChannelId;
import com.slack.data.UserId;
import com.slack.data.impl.ChannelIdImpl;
import javax.json.JsonObject;

/**
 *
 * @author bitter_fox
 */
class ChannelsImpl implements Channels
{    private Slack slack;
    private Api api;

    public ChannelsImpl(Slack slack)
    {
        this.slack = slack;
        api = new Api(slack, "channels");
    }

    @ApiIssuer
    @Override
    public Channels.Create create(String name)
    {
        GetApiRequest apiRequest = api.get("create", builder -> builder.put("name", name));

        return apiRequest.issue(ChannelsImpl.ChannelResult::new);
    }

    @ApiIssuer
    @Override
    public Channels.Info info(ChannelId channelId)
    {
        GetApiRequest apiRequest = api.get("info", builder -> builder.put("channel", channelId.id()));

        return apiRequest.issue(ChannelsImpl.ChannelResult::new);
    }

    @ApiIssuer
    @Override
    public Channels.Invite invite(ChannelId channelId, UserId userId)
    {
        GetApiRequest apiRequest = api.get("invite", builder ->
            builder.put("channel", channelId.id())
                .put("user", userId.id()));

        return apiRequest.issue(ChannelsImpl.ChannelResult::new);
    }

    @ApiIssuer
    @Override
    public Channels.List list()
    {
        GetApiRequest apiRequest = api.get("list");

        return apiRequest.issue(ChannelsImpl.List::new);
    }

    @ApiIssuer
    @Override
    public Channels.Leave leave(ChannelId id)
    {
        GetApiRequest apiRequest = api.get("leave", builder -> builder.put("channel", id.id()));

        return apiRequest.issue(ChannelsImpl.Leave::new);
    }

    @ApiIssuer
    @Override
    public Channels.Join join(String name)
    {
        GetApiRequest apiRequest = api.get("join", builder -> builder.put("name", name));

        return apiRequest.issue(ChannelsImpl.Join::new);
    }

    @ApiIssuer
    @Override
    public Channels.Rename rename(ChannelId id, String newName)
    {
        GetApiRequest apiRequest = api.get("rename", builder ->
            builder.put("channel", id.id())
                .put("name", newName));

        return apiRequest.issue(ChannelsImpl.Rename::new);
    }

    @ApiIssuer
    @Override
    public Channels.SetTopic setTopic(ChannelId channelId, String topic)
    {
        GetApiRequest apiRequest = api.get("setTopic", builder ->
            builder.put("channel", channelId.id())
                .put("topic", topic));

        return apiRequest.issue(ChannelsImpl.SetTopic::new);
    }

    private class ChannelResult extends ApiResult implements Channels.Create, Channels.Info, Channels.Invite
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

    public final class List extends ApiResult implements Channels.List
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

    public static final class Leave extends ApiResult implements Channels.Leave
    {
        private boolean notInChannel;

        public boolean notInChannel()
        {
            return notInChannel;
        }

        @Override
        protected void apply(JsonObject result)
        {
            this.notInChannel = result.getBoolean("not_in_channel", false);
        }
    }

    public final class Join extends ApiResult implements Channels.Join
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

    public final class Rename extends ApiResult implements Channels.Rename
    {
        private ChannelId id;
        private boolean isChannel;
        private String name;
        private int created;

        public ChannelId id()
        {
            return id;
        }

        public boolean isChannel()
        {
            return isChannel;
        }

        public String name()
        {
            return name;
        }

        public int created()
        {
            return created;
        }

        @Override
        protected void apply(JsonObject result)
        {
            JsonObject channel = result.getJsonObject("channel");
            this.id = new ChannelIdImpl(channel.getString("id"));
            this.isChannel = channel.getBoolean("is_channel");
            this.name = channel.getString("name");
            this.created = channel.getInt("created");
        }
    }

    public final class SetTopic extends ApiResult implements Channels.SetTopic
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
}

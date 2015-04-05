/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.Slack;
import com.slack.api.ApiIssuer;
import com.slack.api.Channels;
import static com.slack.api.impl.Names.ALREADY_IN_CHANNEL;
import static com.slack.api.impl.Names.ARCHIVE;
import static com.slack.api.impl.Names.CHANNEL;
import static com.slack.api.impl.Names.CHANNELS;
import static com.slack.api.impl.Names.CREATED;
import static com.slack.api.impl.Names.ID;
import static com.slack.api.impl.Names.INFO;
import static com.slack.api.impl.Names.INVITE;
import static com.slack.api.impl.Names.IS_CHANNEL;
import static com.slack.api.impl.Names.JOIN;
import static com.slack.api.impl.Names.KICK;
import static com.slack.api.impl.Names.LEAVE;
import static com.slack.api.impl.Names.LIST;
import static com.slack.api.impl.Names.MARK;
import static com.slack.api.impl.Names.NAME;
import static com.slack.api.impl.Names.NOT_IN_CHANNEL;
import static com.slack.api.impl.Names.PURPOSE;
import static com.slack.api.impl.Names.RENAME;
import static com.slack.api.impl.Names.SET_PURPOSE;
import static com.slack.api.impl.Names.SET_TOPIC;
import static com.slack.api.impl.Names.TOPIC;
import static com.slack.api.impl.Names.TS;
import static com.slack.api.impl.Names.UNARCHIVE;
import static com.slack.api.impl.Names.USER;
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
{
    private Slack slack;
    private Api api;

    public ChannelsImpl(Slack slack)
    {
        this.slack = slack;
        api = new Api(slack, CHANNELS);
    }

    @ApiIssuer
    @Override
    public Channels.Archive archive(ChannelId channelId)
    {
        GetApiRequest apiRequest = api.get(ARCHIVE, builder -> builder.put(CHANNEL, channelId.id()));

        return apiRequest.issue(EmptyResult::new);
    }

    @ApiIssuer
    @Override
    public Channels.Create create(String name)
    {
        GetApiRequest apiRequest = api.get(Names.CREATE, builder -> builder.put(Names.NAME, name));

        return apiRequest.issue(ChannelsImpl.ChannelResult::new);
    }

    @ApiIssuer
    @Override
    public Channels.Info info(ChannelId channelId)
    {
        GetApiRequest apiRequest = api.get(INFO, builder -> builder.put(CHANNEL, channelId.id()));

        return apiRequest.issue(ChannelsImpl.ChannelResult::new);
    }

    @ApiIssuer
    @Override
    public Channels.Invite invite(ChannelId channelId, UserId userId)
    {
        GetApiRequest apiRequest = api.get(INVITE, builder ->
            builder.put(CHANNEL, channelId.id())
                .put(USER, userId.id()));

        return apiRequest.issue(ChannelsImpl.ChannelResult::new);
    }

    @ApiIssuer
    @Override
    public Channels.List list()
    {
        GetApiRequest apiRequest = api.get(LIST);

        return apiRequest.issue(ChannelsImpl.List::new);
    }

    @ApiIssuer
    @Override
    public Channels.Leave leave(ChannelId id)
    {
        GetApiRequest apiRequest = api.get(LEAVE, builder -> builder.put(CHANNEL, id.id()));

        return apiRequest.issue(ChannelsImpl.Leave::new);
    }

    @ApiIssuer
    @Override
    public Channels.Join join(String name)
    {
        GetApiRequest apiRequest = api.get(JOIN, builder -> builder.put(NAME, name));

        return apiRequest.issue(ChannelsImpl.Join::new);
    }

    @Override
    public Channels.Mark mark(ChannelId channelId, String timestamp)
    {
        GetApiRequest apiRequest = api.get(MARK, builder ->
            builder.put(CHANNEL, channelId.id())
                .put(TS, timestamp));

        return apiRequest.issue(EmptyResult::new);
    }

    @ApiIssuer
    @Override
    public Channels.Kick kick(ChannelId channelId, UserId userId)
    {
        GetApiRequest apiRequest = api.get(KICK, builder ->
            builder.put(CHANNEL, channelId.id())
                .put(USER, userId.id()));

        return apiRequest.issue(EmptyResult::new);
    }

    @ApiIssuer
    @Override
    public Channels.Rename rename(ChannelId id, String newName)
    {
        GetApiRequest apiRequest = api.get(RENAME, builder ->
            builder.put(CHANNEL, id.id())
                .put(NAME, newName));

        return apiRequest.issue(ChannelsImpl.Rename::new);
    }

    @ApiIssuer
    @Override
    public Channels.SetPurpose setPurpose(ChannelId channelId, String purpose)
    {
        GetApiRequest apiRequest = api.get(SET_PURPOSE, builder ->
            builder.put(CHANNEL, channelId.id())
                .put(PURPOSE, purpose));

        return apiRequest.issue(SetPurposeResult::new);
    }

    @ApiIssuer
    @Override
    public Channels.SetTopic setTopic(ChannelId channelId, String topic)
    {
        GetApiRequest apiRequest = api.get(SET_TOPIC, builder ->
            builder.put(CHANNEL, channelId.id())
                .put(TOPIC, topic));

        return apiRequest.issue(SetTopicResult::new);
    }

    @ApiIssuer
    @Override
    public Channels.Unarchive unarchive(ChannelId channelId)
    {
        GetApiRequest apiRequest = api.get(UNARCHIVE, builder -> builder.put(CHANNEL, channelId.id()));

        return apiRequest.issue(EmptyResult::new);
    }

    private class ChannelResult extends ApiResult implements Channels.Create, Channels.Info, Channels.Invite
    {
        private Channel channel;

        @Override
        public Channel channel()
        {
            return channel;
        }

        @Override
        protected void apply(JsonObject result)
        {
            channel = slack.getConfigure().unmarshaller().asChannel(result.getJsonObject(CHANNEL));
        }
    }

    public final class List extends ApiResult implements Channels.List
    {
        private java.util.List<Channel> channels;

        @Override
        public java.util.List<Channel> channels()
        {
            return channels;
        }

        @Override
        protected void apply(JsonObject result)
        {
            channels = slack.getConfigure().unmarshaller().asChannels(result.getJsonArray(CHANNELS));
        }
    }

    public static final class Leave extends ApiResult implements Channels.Leave
    {
        private boolean notInChannel;

        @Override
        public boolean notInChannel()
        {
            return notInChannel;
        }

        @Override
        protected void apply(JsonObject result)
        {
            this.notInChannel = result.getBoolean(NOT_IN_CHANNEL, false);
        }
    }

    public final class Join extends ApiResult implements Channels.Join
    {
        private Channel channel;
        private boolean alreadyInChannel;

        @Override
        public Channel channel()
        {
            return channel;
        }

        @Override
        public boolean alreadyInChannel()
        {
            return alreadyInChannel;
        }

        @Override
        protected void apply(JsonObject result)
        {
            channel = slack.getConfigure().unmarshaller().asChannel(result.getJsonObject(CHANNEL));
            alreadyInChannel = result.getBoolean(ALREADY_IN_CHANNEL, false);
        }
    }

    public final class Rename extends ApiResult implements Channels.Rename
    {
        private ChannelId id;
        private boolean isChannel;
        private String name;
        private int created;

        @Override
        public ChannelId id()
        {
            return id;
        }

        @Override
        public boolean isChannel()
        {
            return isChannel;
        }

        @Override
        public String name()
        {
            return name;
        }

        @Override
        public int created()
        {
            return created;
        }

        @Override
        protected void apply(JsonObject result)
        {
            JsonObject channel = result.getJsonObject(CHANNEL);
            this.id = new ChannelIdImpl(channel.getString(ID));
            this.isChannel = channel.getBoolean(IS_CHANNEL);
            this.name = channel.getString(NAME);
            this.created = channel.getInt(CREATED);
        }
    }
}

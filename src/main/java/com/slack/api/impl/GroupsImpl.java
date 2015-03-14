/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.Slack;
import com.slack.api.ApiBridge;
import com.slack.api.ApiIssuer;
import com.slack.api.Channels;
import com.slack.api.Groups;
import com.slack.data.ChannelId;
import com.slack.data.Group;
import com.slack.data.GroupId;
import com.slack.data.impl.GroupIdImpl;
import java.util.Optional;
import javax.json.JsonObject;

/**
 *
 * @author bitter_fox
 */
class GroupsImpl implements Groups
{
    private Slack slack;
    private Api api;

    public GroupsImpl(Slack slack)
    {
        this.slack = slack;

        this.api = new Api(slack, "groups");
    }


    @ApiIssuer
    @Override
    public Groups.Archive archive(GroupId groupId)
    {
        GetApiRequest apiRequest = api.get("archive", builder -> builder.put("channel", groupId.id()));

        return apiRequest.issue(EmptyResult::new);
    }

    @ApiIssuer
    @Override
    public Groups.Close close(GroupId groupId)
    {
        GetApiRequest apiRequest = api.get("close", builder ->
            builder.put("channel", groupId.id()));

        return apiRequest.issue(GroupsImpl.Close::new);
    }

    @ApiIssuer
    @Override
    public Groups.List list()
    {
        GetApiRequest apiRequest = api.get("list");

        return apiRequest.issue(GroupsImpl.List::new);
    }

    @ApiIssuer
    @Override
    public Groups.Open open(GroupId groupId)
    {
        GetApiRequest apiRequest = api.get("open", builder ->
            builder.put("channel", groupId.id()));

        return apiRequest.issue(GroupsImpl.Open::new);
    }

    @ApiIssuer
    @Override
    public Groups.Rename rename(GroupId id, String newName)
    {
        GetApiRequest apiRequest = api.get("rename", builder ->
            builder.put("channel", id.id())
                .put("name", newName));

        return apiRequest.issue(GroupsImpl.Rename::new);
    }

    @ApiIssuer
    @Override
    public Groups.SetPurpose setPurpose(GroupId groupId, String purpose)
    {
        GetApiRequest apiRequest = api.get("setPurpose", builder ->
            builder.put("channel", groupId.id())
                .put("purpose", purpose));

        return apiRequest.issue(SetPurposeResult::new);
    }

    @ApiIssuer
    @Override
    public Groups.SetTopic setTopic(GroupId groupId, String topic)
    {
        GetApiRequest apiRequest = api.get("setTopic", builder ->
            builder.put("channel", groupId.id())
                .put("topic", topic));

        return apiRequest.issue(SetTopicResult::new);
    }

    @ApiBridge
    @Override
    public Optional<Group> findById(GroupId groupId)
    {
        return this.list().groups().stream()
            .filter(g -> g.id().equals(groupId))
            .findAny();
    }

    private final class Close extends ApiResult implements Groups.Close
    {
        private boolean noOperation;
        private boolean alreadyClosed;

        @Override
        public boolean noOperation()
        {
            return noOperation;
        }

        @Override
        public boolean alreadyClosed()
        {
            return alreadyClosed;
        }

        @Override
        protected void apply(JsonObject result)
        {
            this.noOperation = result.getBoolean("no_op", false);
            this.alreadyClosed = result.getBoolean("already_closed", false);
        }
    }

    private final class List extends ApiResult implements Groups.List
    {
        private java.util.List<Group> groups;

        @Override
        public java.util.List<Group> groups()
        {
            return groups;
        }

        @Override
        protected void apply(JsonObject result)
        {
            this.groups = slack.getConfigure().unmarshaller().asGroups(result.getJsonArray("groups"));
        }
    }

    private final class Open extends ApiResult implements Groups.Open
    {
        private boolean noOperation;
        private boolean alreadyOpen;

        @Override
        public boolean noOperation()
        {
            return noOperation;
        }

        @Override
        public boolean alreadyOpen()
        {
            return alreadyOpen;
        }

        @Override
        protected void apply(JsonObject result)
        {
            this.noOperation = result.getBoolean("no_op", false);
            this.alreadyOpen = result.getBoolean("already_open", false);
        }
    }

    private final class Rename extends ApiResult implements Groups.Rename
    {
        private GroupId id;
        private boolean isGroup;
        private String name;
        private int created;

        @Override
        public GroupId id()
        {
            return id;
        }

        @Override
        public boolean isGroup()
        {
            return isGroup;
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
            JsonObject channel = result.getJsonObject("channel");
            this.id = new GroupIdImpl(channel.getString("id"));
            this.isGroup = channel.getBoolean("is_group");
            this.name = channel.getString("name");
            this.created = channel.getInt("created");
        }
    }
}

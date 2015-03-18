/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.Slack;
import com.slack.api.ApiBridge;
import com.slack.api.ApiIssuer;
import com.slack.api.Groups;
import static com.slack.api.impl.Names.ALREADY_CLOSED;
import static com.slack.api.impl.Names.ALREADY_IN_GROUP;
import static com.slack.api.impl.Names.ALREADY_OPEN;
import static com.slack.api.impl.Names.ARCHIVE;
import static com.slack.api.impl.Names.CHANNEL;
import static com.slack.api.impl.Names.CLOSE;
import static com.slack.api.impl.Names.CREATE;
import static com.slack.api.impl.Names.CREATED;
import static com.slack.api.impl.Names.CREATE_CHILD;
import static com.slack.api.impl.Names.GROUP;
import static com.slack.api.impl.Names.GROUPS;
import static com.slack.api.impl.Names.ID;
import static com.slack.api.impl.Names.INVITE;
import static com.slack.api.impl.Names.IS_GROUP;
import static com.slack.api.impl.Names.KICK;
import static com.slack.api.impl.Names.LEAVE;
import static com.slack.api.impl.Names.LIST;
import static com.slack.api.impl.Names.NAME;
import static com.slack.api.impl.Names.NO_OP;
import static com.slack.api.impl.Names.OPEN;
import static com.slack.api.impl.Names.PURPOSE;
import static com.slack.api.impl.Names.RENAME;
import static com.slack.api.impl.Names.SET_PURPOSE;
import static com.slack.api.impl.Names.SET_TOPIC;
import static com.slack.api.impl.Names.TOPIC;
import static com.slack.api.impl.Names.UNARCHIVE;
import static com.slack.api.impl.Names.USER;
import com.slack.data.Group;
import com.slack.data.GroupId;
import com.slack.data.UserId;
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

        this.api = new Api(slack, GROUPS);
    }


    @ApiIssuer
    @Override
    public Groups.Archive archive(GroupId groupId)
    {
        GetApiRequest apiRequest = api.get(ARCHIVE, builder ->
            builder.put(CHANNEL, groupId.id()));

        return apiRequest.issue(EmptyResult::new);
    }

    @ApiIssuer
    @Override
    public Groups.Close close(GroupId groupId)
    {
        GetApiRequest apiRequest = api.get(CLOSE, builder ->
            builder.put(CHANNEL, groupId.id()));

        return apiRequest.issue(GroupsImpl.Close::new);
    }

    @ApiIssuer
    @Override
    public Groups.Create create(String name)
    {
        GetApiRequest apiRequest = api.get(CREATE, builder ->
            builder.put(NAME, name));

        return apiRequest.issue(GroupsImpl.Create::new);
    }

    @ApiIssuer
    @Override
    public Groups.CreateChild createChild(GroupId groupId)
    {
        GetApiRequest apiRequest = api.get(CREATE_CHILD, builder ->
            builder.put(CHANNEL, groupId.id()));

        return apiRequest.issue(GroupsImpl.Create::new);
    }

    @ApiIssuer
    @Override
    public Groups.Invite invite(GroupId groupId, UserId userId)
    {
        GetApiRequest apiRequest = api.get(INVITE, builder ->
            builder.put(CHANNEL, groupId.id())
                .put(USER, userId.id()));

        return apiRequest.issue(GroupsImpl.Invite::new);
    }

    @ApiIssuer
    @Override
    public Groups.Kick kick(GroupId groupId, UserId userId)
    {
        GetApiRequest apiRequest = api.get(KICK, builder ->
            builder.put(CHANNEL, groupId.id())
                .put(USER, userId.id()));

        return apiRequest.issue(EmptyResult::new);

    }

    @ApiIssuer
    @Override
    public Groups.Leave leave(GroupId groupId)
    {
        GetApiRequest apiRequest = api.get(LEAVE, builder ->
            builder.put(CHANNEL, groupId.id()));

        return apiRequest.issue(EmptyResult::new);

    }

    @ApiIssuer
    @Override
    public Groups.List list()
    {
        GetApiRequest apiRequest = api.get(LIST);

        return apiRequest.issue(GroupsImpl.List::new);
    }

    @ApiIssuer
    @Override
    public Groups.Open open(GroupId groupId)
    {
        GetApiRequest apiRequest = api.get(OPEN, builder ->
            builder.put(CHANNEL, groupId.id()));

        return apiRequest.issue(GroupsImpl.Open::new);
    }

    @ApiIssuer
    @Override
    public Groups.Rename rename(GroupId id, String newName)
    {
        GetApiRequest apiRequest = api.get(RENAME, builder ->
            builder.put(CHANNEL, id.id())
                .put(NAME, newName));

        return apiRequest.issue(GroupsImpl.Rename::new);
    }

    @ApiIssuer
    @Override
    public Groups.SetPurpose setPurpose(GroupId groupId, String purpose)
    {
        GetApiRequest apiRequest = api.get(SET_PURPOSE, builder ->
            builder.put(CHANNEL, groupId.id())
                .put(PURPOSE, purpose));

        return apiRequest.issue(SetPurposeResult::new);
    }

    @ApiIssuer
    @Override
    public Groups.SetTopic setTopic(GroupId groupId, String topic)
    {
        GetApiRequest apiRequest = api.get(SET_TOPIC, builder ->
            builder.put(CHANNEL, groupId.id())
                .put(TOPIC, topic));

        return apiRequest.issue(SetTopicResult::new);
    }

    @ApiIssuer
    @Override
    public Groups.Unarchive unarchive(GroupId groupId)
    {
        GetApiRequest apiRequest = api.get(UNARCHIVE, builder ->
            builder.put(CHANNEL, groupId.id()));

        return apiRequest.issue(EmptyResult::new);
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
            this.noOperation = result.getBoolean(NO_OP, false);
            this.alreadyClosed = result.getBoolean(ALREADY_CLOSED, false);
        }
    }

    private final class Create extends ApiResult implements Groups.Create, Groups.CreateChild
    {
        private Group group;

        @Override
        public Group group()
        {
            return group;
        }

        @Override
        protected void apply(JsonObject result)
        {
            this.group = slack.getConfigure().unmarshaller().asGroup(result.getJsonObject(GROUP));
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
            this.groups = slack.getConfigure().unmarshaller().asGroups(result.getJsonArray(GROUPS));
        }
    }

    private final class Invite extends ApiResult implements Groups.Invite
    {
        private Group group;
        private boolean alreadyInGroup;

        @Override
        public Group group()
        {
            return group;
        }

        @Override
        public boolean alreadyInGroup()
        {
            return alreadyInGroup;
        }

        @Override
        protected void apply(JsonObject result)
        {
            group = slack.getConfigure().unmarshaller().asGroup(result.getJsonObject(GROUP));
            alreadyInGroup = result.getBoolean(ALREADY_IN_GROUP, false);
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
            this.noOperation = result.getBoolean(NO_OP, false);
            this.alreadyOpen = result.getBoolean(ALREADY_OPEN, false);
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
            JsonObject channel = result.getJsonObject(CHANNEL);
            this.id = new GroupIdImpl(channel.getString(ID));
            this.isGroup = channel.getBoolean(IS_GROUP);
            this.name = channel.getString(NAME);
            this.created = channel.getInt(CREATED);
        }
    }
}

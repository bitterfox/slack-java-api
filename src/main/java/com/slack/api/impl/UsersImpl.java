/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.Slack;
import com.slack.api.ApiIssuer;
import com.slack.api.Users;
import static com.slack.api.impl.Names.AUTO_AWAY;
import static com.slack.api.impl.Names.CONNECTION_COUNT;
import static com.slack.api.impl.Names.GET_PRESENCE;
import static com.slack.api.impl.Names.INFO;
import static com.slack.api.impl.Names.LAST_ACTIVITY;
import static com.slack.api.impl.Names.LIST;
import static com.slack.api.impl.Names.MANUAL_AWAY;
import static com.slack.api.impl.Names.MEMBERS;
import static com.slack.api.impl.Names.ONLINE;
import static com.slack.api.impl.Names.PRESENCE;
import static com.slack.api.impl.Names.USER;
import static com.slack.api.impl.Names.USERS;
import com.slack.data.User;
import com.slack.data.UserId;
import java.util.Optional;
import java.util.OptionalInt;
import javax.json.JsonObject;

/**
 *
 * @author bitter_fox
 */
class UsersImpl implements Users
{
    private Slack slack;
    private Api api;

    public UsersImpl(Slack slack)
    {
        this.slack = slack;
        api = new Api(slack, USERS);
    }

    @ApiIssuer
    @Override
    public Users.GetPresence getPresence(UserId userId)
    {
        GetApiRequest apiRequest = api.get(GET_PRESENCE, builder ->
            builder.put(USER, userId.id()));

        return apiRequest.issue(UsersImpl.GetPresence::new);
    }

    @ApiIssuer
    @Override
    public Users.Info info(UserId userId)
    {
        ApiRequest apiRequest = api.get(INFO, builder -> builder.put(USER, userId.id()));

        return apiRequest.issue(UsersImpl.Info::new);
    }

    @ApiIssuer
    @Override
    public Users.List list()
    {
        ApiRequest apiRequest = api.get(LIST);

        return apiRequest.issue(UsersImpl.List::new);
    }

    private final class GetPresence extends ApiResult implements Users.GetPresence
    {
        private static final String AWAY = "away";
        private boolean isAway;
        private boolean forSelf;
        private Optional<Boolean> isOnline = Optional.empty();
        private Optional<Boolean> isAutoAway = Optional.empty();
        private Optional<Boolean> isManualAway = Optional.empty();
        private OptionalInt connectionCount = OptionalInt.empty();
        private OptionalInt lastActivity = OptionalInt.empty();

        @Override
        public boolean isAway()
        {
            return isAway;
        }

        @Override
        public boolean isActive()
        {
            return !isAway;
        }

        @Override
        public boolean forSelf()
        {
            return forSelf;
        }

        @Override
        public Optional<Boolean> isOnline()
        {
            return isOnline;
        }

        @Override
        public Optional<Boolean> isAutoAway()
        {
            return isAutoAway;
        }

        @Override
        public Optional<Boolean> isManualAway()
        {
            return isManualAway;
        }

        @Override
        public OptionalInt connectionCount()
        {
            return connectionCount;
        }

        @Override
        public OptionalInt lastActivity()
        {
            return lastActivity;
        }

        @Override
        protected void apply(JsonObject result)
        {
            this.isAway = result.getString(PRESENCE).equals(AWAY);

            if (this.forSelf = result.containsKey(ONLINE))
            {
                this.isOnline = Optional.of(result.getBoolean(ONLINE));
                this.isAutoAway = Optional.of(result.getBoolean(AUTO_AWAY));
                this.isManualAway = Optional.of(result.getBoolean(MANUAL_AWAY));
                this.connectionCount = OptionalInt.of(result.getInt(CONNECTION_COUNT));
                this.lastActivity = result.containsKey(Names.LAST_ACTIVITY)
                    ? OptionalInt.of(result.getInt(LAST_ACTIVITY))
                    : OptionalInt.empty();
            }
        }
    }

    private final class Info extends ApiResult implements Users.Info
    {
        private User user;

        @Override
        public User user()
        {
            return user;
        }

        @Override
        protected void apply(JsonObject result)
        {
            user = slack.getConfigure().unmarshaller().asUser(result.getJsonObject(USER));
        }
    }

    private final class List extends ApiResult implements Users.List
    {
        private java.util.List<User> members;

        @Override
        public java.util.List<User> members()
        {
            return members;
        }

        @Override
        protected void apply(JsonObject result)
        {
            members = slack.getConfigure().unmarshaller().asUsers(result.getJsonArray(MEMBERS));
        }
    }
}

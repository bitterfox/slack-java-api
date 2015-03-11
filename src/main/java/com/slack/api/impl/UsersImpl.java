/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.Slack;
import com.slack.api.ApiIssuer;
import com.slack.api.Users;
import com.slack.data.User;
import com.slack.data.UserId;
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
        api = new Api(slack, "users");
    }

    @ApiIssuer
    @Override
    public Users.Info info(UserId userId)
    {
        ApiRequest apiRequest = api.get("info", builder -> builder.put("user", userId.id()));

        return apiRequest.issue(UsersImpl.Info::new);
    }

    @ApiIssuer
    @Override
    public Users.List list()
    {
        ApiRequest apiRequest = api.get("list");

        return apiRequest.issue(UsersImpl.List::new);
    }

    public final class Info extends ApiResult implements Users.Info
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
            user = slack.getConfigure().unmarshaller().asUser(result.getJsonObject("user"));
        }
    }

    public final class List extends ApiResult implements Users.List
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
            members = slack.getConfigure().unmarshaller().asUsers(result.getJsonArray("members"));
        }
    }

}

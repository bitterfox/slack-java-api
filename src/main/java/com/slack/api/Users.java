/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api;

import com.slack.Slack;
import com.slack.data.User;
import com.slack.util.JsonUtil;
import java.util.Collections;
import java.util.stream.Collectors;
import javax.json.JsonObject;

/**
 *
 * @author bitter_fox
 */
public class Users
{
    private Slack slack;
    private Api api;

    public Users(Slack slack)
    {
        this.slack = slack;
        api = new Api(slack, "users");
    }

    public Users.Info info(String userId)
    {
        ApiRequest apiRequest = api.get("info", builder -> builder.put("user", userId));

        return apiRequest.issue(Users.Info::new);
    }

    public Users.List list()
    {
        ApiRequest apiRequest = api.get("list", builder -> {});

        return apiRequest.issue(Users.List::new);
    }

    public final class Info extends ApiResult
    {
        private User user;

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

    public final class List extends ApiResult
    {
        private java.util.List<User> members;

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

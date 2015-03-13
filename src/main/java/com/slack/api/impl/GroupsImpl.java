/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.Slack;
import com.slack.api.ApiIssuer;
import com.slack.api.Groups;
import com.slack.data.Group;
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
    public Groups.List list()
    {
        GetApiRequest apiRequest = api.get("list");

        return apiRequest.issue(GroupsImpl.List::new);
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
}

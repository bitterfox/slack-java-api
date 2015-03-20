/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.Slack;
import com.slack.api.ApiIssuer;
import com.slack.api.ImApi;
import static com.slack.api.impl.Names.IM;
import static com.slack.api.impl.Names.IMS;
import static com.slack.api.impl.Names.LIST;
import com.slack.data.Im;
import javax.json.JsonObject;

/**
 *
 * @author bitter_fox
 */
class ImApiImpl implements ImApi
{
    private Slack slack;
    private Api api;

    public ImApiImpl(Slack slack)
    {
        this.slack = slack;
        this.api = new Api(slack, IM);
    }

    @ApiIssuer
    @Override
    public ImApi.List list()
    {
        GetApiRequest apiRequest = api.get(LIST);

        return apiRequest.issue(ImApiImpl.List::new);
    }

    private final class List extends ApiResult implements ImApi.List
    {
        private java.util.List<Im> ims;

        @Override
        public java.util.List<Im> ims()
        {
            return ims;
        }

        @Override
        protected void apply(JsonObject result)
        {
            this.ims = slack.getConfigure().unmarshaller().asIms(result.getJsonArray(IMS));
        }
    }
}

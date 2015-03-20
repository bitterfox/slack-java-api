/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.Slack;
import com.slack.api.ApiIssuer;
import com.slack.api.ImApi;
import static com.slack.api.impl.Names.ALREADY_OPEN;
import static com.slack.api.impl.Names.CHANNEL;
import static com.slack.api.impl.Names.CLOSE;
import static com.slack.api.impl.Names.ID;
import static com.slack.api.impl.Names.IM;
import static com.slack.api.impl.Names.IMS;
import static com.slack.api.impl.Names.LIST;
import static com.slack.api.impl.Names.NO_OP;
import static com.slack.api.impl.Names.OPEN;
import static com.slack.api.impl.Names.USER;
import com.slack.data.Im;
import com.slack.data.ImId;
import com.slack.data.UserId;
import com.slack.data.impl.ImIdImpl;
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
    public ImApi.Close close(ImId imId)
    {
        GetApiRequest apiRequest = api.get(CLOSE, builder ->
            builder.put(CHANNEL, imId.id()));

        return apiRequest.issue(CloseResult::new);
    }

    @ApiIssuer
    @Override
    public ImApi.List list()
    {
        GetApiRequest apiRequest = api.get(LIST);

        return apiRequest.issue(ImApiImpl.List::new);
    }

    @ApiIssuer
    @Override
    public ImApi.Open open(UserId userId)
    {
        GetApiRequest apiRequest = api.get(OPEN, builder ->
            builder.put(USER, userId.id()));

        return apiRequest.issue(ImApiImpl.Open::new);
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

    private final class Open extends ApiResult implements ImApi.Open
    {
        private ImId im;
        private boolean noOperation;
        private boolean alreadyOpen;

        @Override
        public ImId im()
        {
            return im;
        }

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
            this.im = new ImIdImpl(result.getJsonObject(CHANNEL).getString(ID));
            this.noOperation = result.getBoolean(NO_OP, false);
            this.alreadyOpen = result.getBoolean(ALREADY_OPEN, false);
        }
    }
}

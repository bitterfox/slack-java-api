/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.api.Groups;
import com.slack.api.ImApi;
import static com.slack.api.impl.Names.ALREADY_CLOSED;
import static com.slack.api.impl.Names.NO_OP;
import javax.json.JsonObject;

/**
 *
 * @author bitter_fox
 */
class CloseResult extends ApiResult implements Groups.Close, ImApi.Close
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

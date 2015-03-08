/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api;

import com.slack.api.exception.AccountInactiveException;
import com.slack.api.exception.ChannelNotFoundException;
import com.slack.api.exception.InvalidAuthException;
import com.slack.api.exception.IsArchivedException;
import com.slack.api.exception.NameTakenException;
import com.slack.api.exception.NoChannelException;
import com.slack.api.exception.NotAuthedException;
import com.slack.api.exception.RestrictedActionException;
import com.slack.api.exception.SlackException;
import com.slack.api.exception.TokenRevokedException;
import com.slack.api.exception.UserIsBotException;
import com.slack.api.exception.UserIsRestrictedException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 *
 * @author bitter_fox
 */
public abstract class AbstractApiRequest implements ApiRequest
{
    private Map<String, Supplier<? extends SlackException>> errors = new HashMap<>();

    public AbstractApiRequest()
    {
        this.putError(Error.TOKEN_REVOKED, TokenRevokedException::new);
        this.putError(Error.NOT_AUTHED, NotAuthedException::new);
        this.putError(Error.INVALID_AUTH, InvalidAuthException::new);
        this.putError(Error.ACCOUNT_INACTIVE, AccountInactiveException::new);

        this.putError(Error.CHANNEL_NOT_FOUND, ChannelNotFoundException::new);
        this.putError(Error.NAME_TAKEN, NameTakenException::new);
        this.putError(Error.RESTRICTED_ACTION, RestrictedActionException::new);
        this.putError(Error.NO_CHANNEL, NoChannelException::new);
        this.putError(Error.IS_ARCHIVED, IsArchivedException::new);
        
        this.putError(Error.USER_IS_BOT, UserIsBotException::new);
        this.putError(Error.USER_IS_RESTRICTED, UserIsRestrictedException::new);
    }

    private void putError(Error e, Supplier<? extends SlackException> s)
    {
        errors.put(e.code(), s);
    }

    protected void error(String error)
    {
        throw errors.getOrDefault(error, () -> new SlackException(error)).get();
    }
}

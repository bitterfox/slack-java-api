/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api;

/**
 *
 * @author bitter_fox
 */
public enum Error
{
    TOKEN_REVOKED("token_revoked"),
    NOT_AUTHED("not_authed"),
    INVALID_AUTH("invalid_auth"),
    ACCOUNT_INACTIVE("account_inactive"),

    CHANNEL_NOT_FOUND("channel_not_found"),
    NAME_TAKEN("name_taken"),
    RESTRICTED_ACTION("restricted_action"),
    NO_CHANNEL("no_channel"),
    IS_ARCHIVED("is_archived"),

    USER_IS_BOT("user_is_bot"),
    USER_IS_RESTRICTED("user_is_restricted"),
    USER_NOT_FOUND("user_not_found"),
    USER_NOT_VISIBLE("user_not_visible"),
    ;

    private String code;

    Error(String code)
    {
        this.code = code;
    }

    public String code()
    {
        return code;
    }
}

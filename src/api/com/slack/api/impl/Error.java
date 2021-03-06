/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.api.exception.AccountInactiveException;
import com.slack.api.exception.AlreadyInChannelException;
import com.slack.api.exception.CannotDoException;
import com.slack.api.exception.CannotDoGeneralException;
import com.slack.api.exception.CannotDoSelfException;
import com.slack.api.exception.CannotInviteException;
import com.slack.api.exception.ChannelNotFoundException;
import com.slack.api.exception.InvalidAuthException;
import com.slack.api.exception.InvalidNameException;
import com.slack.api.exception.IsArchivedException;
import com.slack.api.exception.MessageNotFoundException;
import com.slack.api.exception.NameTakenException;
import com.slack.api.exception.NoChannelException;
import com.slack.api.exception.NoTextException;
import com.slack.api.exception.NotArchivedException;
import com.slack.api.exception.NotAuthedException;
import com.slack.api.exception.NotAuthorizedOperationException;
import com.slack.api.exception.NotInChannelException;
import com.slack.api.exception.RestrictedActionException;
import com.slack.api.exception.SharedFileDeletedException;
import com.slack.api.exception.SharedFileNotFoundException;
import com.slack.api.exception.SlackException;
import com.slack.api.exception.TokenRevokedException;
import com.slack.api.exception.TooLongException;
import com.slack.api.exception.UserIsBotException;
import com.slack.api.exception.UserIsRestrictedException;
import com.slack.api.exception.UserNotFoundException;
import com.slack.api.exception.UserNotVisibleException;
import java.util.function.Supplier;

/**
 *
 * @author bitter_fox
 */
enum Error
{
    TOKEN_REVOKED("token_revoked", TokenRevokedException::new),
    NOT_AUTHED("not_authed", NotAuthedException::new),
    INVALID_AUTH("invalid_auth", InvalidAuthException::new),
    ACCOUNT_INACTIVE("account_inactive", AccountInactiveException::new),

    TOO_LONG("too_long", TooLongException::new),

    CHANNEL_NOT_FOUND("channel_not_found", ChannelNotFoundException::new),
    NOT_IN_CHANNEL("not_in_channel", NotInChannelException::new),
    NOT_IN_GROUP("not_in_group", NotInChannelException::new),
    NOT_AUTHORIZED("not_authorized", NotAuthorizedOperationException::new),
    INVALID_NAME("invalid_name", InvalidNameException::new),
    NAME_TAKEN("name_taken", NameTakenException::new),
    RESTRICTED_ACTION("restricted_action", RestrictedActionException::new),
    NO_CHANNEL("no_channel", NoChannelException::new),
    NOT_ARCHIVED("not_archived", NotArchivedException::new),
    IS_ARCHIVED("is_archived", IsArchivedException::new),
    ALREADY_ARCHIVED("already_archived", IsArchivedException::new),
    CANT_ARCHIVE_GENERAL("cant_archive_general", CannotDoGeneralException::new),
    CANT_LEAVE_GENERAL("cant_leave_general", CannotDoGeneralException::new),
    CANT_KICK_GENERAL("cant_kick_from_general", CannotDoGeneralException::new),
    CANT_INVITE_SELF("cant_invite_self", CannotDoSelfException::new),
    CANT_KICK_SELF("cant_kick_self", CannotDoSelfException::new),
    CANT_INVITE("cant_invite", CannotInviteException::new),
    ALREADY_IN_CHANNEL("already_in_channel", AlreadyInChannelException::new),

    USER_IS_BOT("user_is_bot", UserIsBotException::new),
    USER_IS_RESTRICTED("user_is_restricted", UserIsRestrictedException::new),
    USER_NOT_FOUND("user_not_found", UserNotFoundException::new),
    USER_NOT_VISIBLE("user_not_visible", UserNotVisibleException::new),

    MESSAGE_NOT_FOUND("message_not_found", MessageNotFoundException::new),
    CANT_DELETE_MESSAGE("cant_delete_message", CannotDoException::new),

    FILE_NOT_FOUND("file_not_found", SharedFileNotFoundException::new),
    FILE_DELETED("file_deleted", SharedFileDeletedException::new),

    NO_TEXT("no_text", NoTextException::new),
    ;

    private String code;
    private Supplier<? extends SlackException> supplier;

    Error(String code, Supplier<? extends SlackException> supplier)
    {
        this.code = code;
        this.supplier = supplier;
    }

    public String code()
    {
        return code;
    }

    public void throwException()
    {
        throw supplier.get();
    }

    public SlackException createException()
    {
        return supplier.get();
    }
}

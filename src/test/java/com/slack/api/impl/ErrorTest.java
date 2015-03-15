/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.Slack;
import com.slack.api.Auth;
import com.slack.api.exception.AccountInactiveException;
import com.slack.api.exception.AlreadyInChannelException;
import com.slack.api.exception.CannotDoGeneralException;
import com.slack.api.exception.CannotDoSelfException;
import com.slack.api.exception.ChannelNotFoundException;
import com.slack.api.exception.InvalidAuthException;
import com.slack.api.exception.InvalidNameException;
import com.slack.api.exception.IsArchivedException;
import com.slack.api.exception.NameTakenException;
import com.slack.api.exception.NoChannelException;
import com.slack.api.exception.NotArchivedException;
import com.slack.api.exception.NotAuthedException;
import com.slack.api.exception.NotAuthorizedOperationException;
import com.slack.api.exception.NotInChannelException;
import com.slack.api.exception.RestrictedActionException;
import com.slack.api.exception.TokenRevokedException;
import com.slack.api.exception.TooLongException;
import com.slack.api.exception.UserIsBotException;
import com.slack.api.exception.UserIsRestrictedException;
import com.slack.api.exception.UserNotFoundException;
import com.slack.api.exception.UserNotVisibleException;
import org.junit.Test;

/**
 *
 * @author bitter_fox
 */
public class ErrorTest extends AbstractApiTest<Auth, Auth.Test>
{
    public ErrorTest()
    {
        super(Slack::auth, Auth::test);
    }

    @ErrorResult("token_revoked")
    @Test(expected = TokenRevokedException.class)
    public void testTokenRevoked()
    {
        this.call();
    }

    @ErrorResult("not_authed")
    @Test(expected = NotAuthedException.class)
    public void testNotAuthed()
    {
        this.call();
    }

    @ErrorResult("invalid_auth")
    @Test(expected = InvalidAuthException.class)
    public void testInvalidAuth()
    {
        this.call();
    }

    @ErrorResult("account_inactive")
    @Test(expected = AccountInactiveException.class)
    public void testAccountInactive()
    {
        this.call();
    }

    @ErrorResult("too_long")
    @Test(expected = TooLongException.class)
    public void testTooLong()
    {
        this.call();
    }

    @ErrorResult("channel_not_found")
    @Test(expected = ChannelNotFoundException.class)
    public void testChannelNotFound()
    {
        this.call();
    }

    @ErrorResult("not_in_channel")
    @Test(expected = NotInChannelException.class)
    public void testNotInChannel()
    {
        this.call();
    }

    @ErrorResult("not_authorized")
    @Test(expected = NotAuthorizedOperationException.class)
    public void testNotAuthorizedOperation()
    {
        this.call();
    }

    @ErrorResult("invalid_name")
    @Test(expected = InvalidNameException.class)
    public void testInvalidName()
    {
        this.call();
    }

    @ErrorResult("name_taken")
    @Test(expected = NameTakenException.class)
    public void testNameTaken()
    {
        this.call();
    }

    @ErrorResult("restricted_action")
    @Test(expected = RestrictedActionException.class)
    public void testRestrictedAction()
    {
        this.call();
    }

    @ErrorResult("no_channel")
    @Test(expected = NoChannelException.class)
    public void testNoChannel()
    {
        this.call();
    }

    @ErrorResult("not_archived")
    @Test(expected = NotArchivedException.class)
    public void testNotArchived()
    {
        this.call();
    }

    @ErrorResult("is_archived")
    @Test(expected = IsArchivedException.class)
    public void testIsArchived()
    {
        this.call();
    }

    @ErrorResult("already_archived")
    @Test(expected = IsArchivedException.class)
    public void testAlreadyArchived()
    {
        this.call();
    }

    @ErrorResult("cant_archive_general")
    @Test(expected = CannotDoGeneralException.class)
    public void testCannotArchiveGeneral()
    {
        this.call();
    }

    @ErrorResult("cant_leave_general")
    @Test(expected = CannotDoGeneralException.class)
    public void testCannotLeaveGeneral()
    {
        this.call();
    }

    @ErrorResult("cant_kick_from_general")
    @Test(expected = CannotDoGeneralException.class)
    public void testCannotKickFromGeneral()
    {
        this.call();
    }

    @ErrorResult("cant_invite_self")
    @Test(expected = CannotDoSelfException.class)
    public void testCannotInviteSelf()
    {
        this.call();
    }

    @ErrorResult("cant_kick_self")
    @Test(expected = CannotDoSelfException.class)
    public void testCannotKickSelf()
    {
        this.call();
    }

    @ErrorResult("already_in_channel")
    @Test(expected = AlreadyInChannelException.class)
    public void testAlreadyInChannel()
    {
        this.call();
    }

    @ErrorResult("user_is_bot")
    @Test(expected = UserIsBotException.class)
    public void testUserIsBot()
    {
        this.call();
    }

    @ErrorResult("user_is_restricted")
    @Test(expected = UserIsRestrictedException.class)
    public void testUserIsRestricted()
    {
        this.call();
    }

    @ErrorResult("user_not_found")
    @Test(expected = UserNotFoundException.class)
    public void testUserNotFound()
    {
        this.call();
    }

    @ErrorResult("user_not_visible")
    @Test(expected = UserNotVisibleException.class)
    public void testUserNotVisible()
    {
        this.call();
    }
}

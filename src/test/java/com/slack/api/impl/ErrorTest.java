/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.Slack;
import com.slack.api.Auth;
import com.slack.api.exception.InvalidAuthException;
import com.slack.api.exception.NotAuthedException;
import com.slack.api.exception.TokenRevokedException;
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
}

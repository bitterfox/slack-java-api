/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api;

import com.slack.Slack;
import com.slack.api.exception.InvalidAuthException;
import com.slack.api.exception.NotAuthedException;
import com.slack.api.exception.TokenRevokedException;
import java.util.function.Consumer;
import org.junit.Test;

/**
 *
 * @author bitter_fox
 */
public abstract class AbstractApiTest
{
    private Slack authedSlack = Slack.create(config -> config.token("YOUR-TOKEN"));
    private Slack tokenRevokedSlack = Slack.create(config -> config.token("xoxp-2569650323-2570554292-2604375399-4dc6fa"));
    private Slack notAuthedSlack = Slack.create(config -> config.token(""));
    private Slack invalidAuthSlack = Slack.create(config -> config.token("invalid"));

    private Consumer<Slack> api;

    public AbstractApiTest(Consumer<Slack> api)
    {
        this.api = api;
    }

    protected Slack authedSlack()
    {
        return authedSlack;
    }

    protected Slack tokenRevokedSlack()
    {
        return tokenRevokedSlack;
    }

    protected Slack notAuthedSlack()
    {
        return notAuthedSlack;
    }

    protected Slack invalidAuthSlack()
    {
        return invalidAuthSlack;
    }

    @Test
    public void testTokenRevoked()
    {
        this.testError(this.tokenRevokedSlack(), TokenRevokedException.class);
    }

    @Test
    public void testNotAuthed()
    {
        this.testError(this.notAuthedSlack(), NotAuthedException.class);
    }

    @Test
    public void testInvalidAuth()
    {
        this.testError(this.invalidAuthSlack(), InvalidAuthException.class);

    }

    // FIXME: account inactive

    private void testError(Slack slack, Class<? extends Exception> clazz)
    {
        Tests.assertException(() -> api.accept(slack), clazz);
    }
}

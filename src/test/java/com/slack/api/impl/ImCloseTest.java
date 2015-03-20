/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.Slack;
import com.slack.api.ImApi;
import com.slack.data.impl.ImIdImpl;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author bitter_fox
 */
public class ImCloseTest extends AbstractApiTest<ImApi, ImApi.Close>
{
    public ImCloseTest()
    {
        super(Slack::im, im -> im.close(new ImIdImpl("")));
    }

    @Result("{\"ok\":true,\"no_op\":true,\"already_closed\":true}")
    @Test
    public void test1()
    {
        ImApi.Close close = this.call();

        Assert.assertEquals(true, close.alreadyClosed());
        Assert.assertEquals(true, close.noOperation());
    }

    @Result("{\"ok\":true}")
    @Test
    public void test2()
    {
        ImApi.Close close = this.call();

        Assert.assertEquals(false, close.alreadyClosed());
        Assert.assertEquals(false, close.noOperation());
    }
}

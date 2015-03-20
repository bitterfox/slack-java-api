/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.Slack;
import com.slack.api.ImApi;
import com.slack.data.impl.UserIdImpl;
import java.util.Objects;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author bitter_fox
 */
public class ImOpen extends AbstractApiTest<ImApi, ImApi.Open>
{

    public ImOpen()
    {
        super(Slack::im, im -> im.open(new UserIdImpl("")));
    }

    @Result("{\"ok\":true,\"no_op\":true,\"already_open\":true,\"channel\":{\"id\":\"D02GSGA8Y\"}}")
    @Test
    public void test()
    {
        ImApi.Open open = this.call();

        Objects.requireNonNull(open.im());
        Assert.assertEquals(true, open.alreadyOpen());
        Assert.assertEquals(true, open.noOperation());
    }
}

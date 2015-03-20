/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.Slack;
import com.slack.api.ImApi;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author bitter_fox
 */
public class ImList extends AbstractApiTest<ImApi, ImApi.List>
{
    public ImList()
    {
        super(Slack::im, ImApi::list);
    }

    @Result("{\"ok\":true,"
        + "\"ims\": [{},{},{}]"
        + "}")
    @Test
    public void test()
    {
        ImApi.List list = this.call();
        Assert.assertEquals(3, list.ims().size());
    }
}

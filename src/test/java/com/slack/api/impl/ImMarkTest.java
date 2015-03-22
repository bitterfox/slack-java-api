/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.Slack;
import com.slack.api.ImApi;
import com.slack.data.impl.ImIdImpl;
import org.junit.Test;

/**
 *
 * @author bitter_fox
 */
public class ImMarkTest extends AbstractApiTest<ImApi, ImApi.Mark>
{
    public ImMarkTest()
    {
        super(Slack::im, im -> im.mark(new ImIdImpl(""), ""));
    }

    @Result("{\"ok\":true}")
    @Test
    public void test()
    {
        this.call();
    }
}

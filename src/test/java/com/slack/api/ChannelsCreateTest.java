/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api;

import com.slack.api.exception.NameTakenException;
import com.slack.api.exception.NoChannelException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author bitter_fox
 */
public class ChannelsCreateTest extends AbstractApiIssuerTest
{

    public ChannelsCreateTest()
    {
        super(slack -> slack.channels().create(""));
    }

    @BeforeClass
    public static void setUpClass()
    {
    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
    }

    @Test(expected = NoChannelException.class)
    public void testNoChannel()
    {
        this.authedSlack().channels().create("");
    }

    @Test(expected = NameTakenException.class)
    public void testNameTaken()
    {
        this.authedSlack().channels().create("general");
    }
}

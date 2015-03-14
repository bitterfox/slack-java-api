/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api;

import com.slack.api.exception.NoChannelException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bitter_fox
 */
public class ChannelsJoinTest extends AbstractApiIssuerTest
{
    public ChannelsJoinTest()
    {
        super(s -> s.channels().join(""));
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
        this.authedSlack().channels().join("");
    }
}

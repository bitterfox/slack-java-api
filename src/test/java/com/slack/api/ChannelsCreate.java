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
import static org.junit.Assert.*;

/**
 *
 * @author bitter_fox
 */
public class ChannelsCreate extends AbstractApiTest
{

    public ChannelsCreate()
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

    @Test
    public void testNoChannel()
    {
        Tests.assertException(
            () -> this.authedSlack().channels().create(""),
            NoChannelException.class);
    }

    @Test
    public void testNameTaken()
    {
        Tests.assertException(
            () -> this.authedSlack().channels().create("general"),
            NameTakenException.class);
    }
}

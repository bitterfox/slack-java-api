/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api;

import com.slack.api.exception.UserNotFoundException;
import com.slack.data.impl.UserIdImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author bitter_fox
 */
public class UsersInfoTest extends AbstractApiIssuerTest
{
    public UsersInfoTest()
    {
        super(slack -> slack.users().info(new UserIdImpl("")));
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

    @Test(expected = UserNotFoundException.class)
    public void testUserNotFound()
    {
        this.authedSlack().users().info(new UserIdImpl(""));
    }
}

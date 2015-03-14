/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api;

import com.slack.Slack;
import com.slack.api.exception.ChannelNotFoundException;
import com.slack.data.Group;
import com.slack.data.impl.GroupIdImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bitter_fox
 */
public class GroupsCloseTest extends AbstractApiIssuerTest
{
    private Group group;
    private boolean alreadyOpen;

    public GroupsCloseTest()
    {
        super(slack -> slack.groups().close(new GroupIdImpl("")));
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
        Slack slack = this.authedSlack();
        group = slack.groups().list().groups().get(0);
        alreadyOpen = slack.groups().open(group.id()).alreadyOpen();
    }

    @After
    public void tearDown()
    {
        if (alreadyOpen)
        {
            this.authedSlack().groups().open(group.id());
        }
        else
        {
            this.authedSlack().groups().close(group.id());
        }
    }

    @Test
    public void testClose()
    {
        Slack slack = this.authedSlack();

        Groups.Close close = slack.groups().close(group.id()); // close
        Assert.assertEquals(close.alreadyClosed(), close.noOperation());
        Assert.assertEquals(false, close.noOperation());

        close = slack.groups().close(group.id()); // already close
        Assert.assertEquals(close.alreadyClosed(), close.noOperation());
        Assert.assertEquals(true, close.noOperation());
    }

    @Test(expected = ChannelNotFoundException.class)
    public void testChannelNotFound()
    {
        this.authedSlack().groups().close(new GroupIdImpl(""));
    }
}

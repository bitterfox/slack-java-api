/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api;

import com.slack.Slack;
import com.slack.api.exception.ChannelNotFoundException;
import com.slack.api.exception.NotArchivedException;
import com.slack.data.Channel;
import com.slack.data.Group;
import com.slack.data.impl.ChannelIdImpl;
import com.slack.data.impl.GroupIdImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author bitter_fox
 */
public class GroupsUnarchiveTest extends AbstractApiIssuerTest
{
    public GroupsUnarchiveTest()
    {
        super(slack -> slack.groups().unarchive(new GroupIdImpl("")));
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
    public void testUnarchive()
    {
        Slack slack = this.authedSlack();

        Group group = slack.groups().list().groups().stream()
            .filter(Group::isArchived)
            .findAny().get();

        slack.groups().unarchive(group.id());

        Assert.assertEquals(false, slack.groups().findById(group.id()).get().isArchived());

        slack.groups().archive(group.id());
    }

    @Test(expected = ChannelNotFoundException.class)
    public void testChannelNotFound()
    {
        Slack slack = this.authedSlack();

        slack.groups().unarchive(new GroupIdImpl(""));
    }

    @Test(expected = NotArchivedException.class)
    public void testNotArchivedException()
    {
        Slack slack = this.authedSlack();

        Group group = slack.groups().list().groups().stream()
            .filter(c -> !c.isArchived())
            .findAny().get();

        slack.groups().unarchive(group.id());
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api;

import com.slack.Slack;
import com.slack.api.exception.CannotDoGeneralException;
import com.slack.api.exception.ChannelNotFoundException;
import com.slack.api.exception.IsArchivedException;
import com.slack.data.Channel;
import com.slack.data.Group;
import com.slack.data.impl.ChannelIdImpl;
import com.slack.data.impl.GroupIdImpl;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author bitter_fox
 */
public class GroupsArchiveTest extends AbstractApiTest
{
    public GroupsArchiveTest()
    {
        super(slack -> slack.groups().archive(new GroupIdImpl("")));
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
    public void testArchive()
    {
        Slack slack = this.authedSlack();

        Group group = slack.groups().list().groups().stream()
            .filter(c -> !c.isArchived())
            .findAny().get();

        slack.groups().archive(group.id());

        Assert.assertEquals(true,
            slack.groups().list().groups().stream()
                .filter(g -> g.id().equals(group.id()))
                .findAny().get().isArchived());

//        slack.groups().unarchive(group.id());
    }

    @Test(expected = ChannelNotFoundException.class)
    public void testChannelNotFound()
    {
        Slack slack = this.authedSlack();

        slack.groups().archive(new GroupIdImpl(""));
    }

    @Test(expected = IsArchivedException.class)
    public void testIsArchived()
    {
        Slack slack = this.authedSlack();

        Group group = slack.groups().list().groups().stream()
            .filter(Group::isArchived)
            .findAny().get();

        slack.groups().archive(group.id());
    }
}

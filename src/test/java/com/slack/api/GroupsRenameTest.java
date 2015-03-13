/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api;

import com.slack.Slack;
import com.slack.api.exception.ChannelNotFoundException;
import com.slack.api.exception.InvalidNameException;
import com.slack.api.exception.NameTakenException;
import com.slack.data.Group;
import com.slack.data.GroupId;
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
public class GroupsRenameTest extends AbstractApiTest
{
    public GroupsRenameTest()
    {
        super(slack -> slack.groups().rename(new GroupIdImpl(""), ""));
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
    public void testRename()
    {
        Slack slack = this.authedSlack();

        Group group = slack.groups().list().groups().get(0);
        GroupId groupId = group.id();
        String oldName = group.name();

        String newName = "testgroup";

        Groups.Rename rename = slack.groups().rename(groupId, newName);

        Assert.assertEquals(groupId, rename.id());
        Assert.assertEquals(group.created(), rename.created());
        Assert.assertEquals(newName, rename.name());

        group = slack.groups().list().groups().stream()
            .filter(g -> g.id().equals(groupId))
            .findAny().get();

        Assert.assertEquals(newName, group.name());

        slack.groups().rename(groupId, oldName);
    }

    @Test(expected = ChannelNotFoundException.class)
    public void testChannelNotFound()
    {
        this.authedSlack().groups().rename(new GroupIdImpl(""), "");
    }

    @Test(expected = NameTakenException.class)
    public void testNameTaken()
    {
        Slack slack = this.authedSlack();

        Group group = slack.groups().list().groups().get(0);

        slack.groups().rename(group.id(), group.name());

    }
}

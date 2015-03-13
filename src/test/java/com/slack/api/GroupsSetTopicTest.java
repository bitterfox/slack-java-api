/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api;

import com.slack.Slack;
import com.slack.api.exception.ChannelNotFoundException;
import com.slack.api.exception.IsArchivedException;
import com.slack.api.exception.NotInChannelException;
import com.slack.api.exception.TooLongException;
import com.slack.data.Channel;
import com.slack.data.Group;
import com.slack.data.GroupId;
import com.slack.data.impl.ChannelIdImpl;
import com.slack.data.impl.GroupIdImpl;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
public class GroupsSetTopicTest extends AbstractApiTest
{
    public GroupsSetTopicTest()
    {
        super(slack -> slack.groups().setTopic(new GroupIdImpl(""), ""));
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

    private Group findGroup(Slack slack, GroupId groupId)
    {
        return slack.groups().list().groups().stream()
            .filter(g -> g.id().equals(groupId))
            .findAny().get();
    }

    private void setTopic(Slack slack, Group group)
    {
        String topic = "Test Topic" + System.currentTimeMillis();
        Groups.SetTopic setTopic = slack.groups().setTopic(group.id(), topic);
        System.out.println(setTopic.topic());

        Assert.assertEquals(topic, setTopic.topic());
        Assert.assertEquals(topic, this.findGroup(slack, group.id()).topic().value());

        setTopic = slack.groups().setTopic(group.id(), group.topic().value());

        Assert.assertEquals(group.topic().value(), setTopic.topic());
        Assert.assertEquals(group.topic().value(), this.findGroup(slack, group.id()).topic().value());
    }

    @Test
    public void testSetTopic()
    {
        Slack slack = this.authedSlack();

        Group group = slack.groups().list().groups().get(0);

        this.setTopic(slack, group);
    }

    @Test(expected = ChannelNotFoundException.class)
    public void testChannelNotFound()
    {
        Slack slack = this.authedSlack();

        slack.groups().setTopic(new GroupIdImpl(""), "");
    }

    @Test(expected = TooLongException.class)
    public void testTooLong()
    {
        Slack slack = this.authedSlack();

        Group group = slack.groups().list().groups().get(0);

        String tooLongTopic = Stream.generate(() -> " ")
            .limit(251) // TODO: couldn't know limit length of topic?
            .collect(Collectors.joining());

        slack.groups().setTopic(group.id(), tooLongTopic);
    }
}

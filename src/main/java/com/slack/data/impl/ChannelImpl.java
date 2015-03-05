/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.data.impl;

import com.slack.data.Channel;
import com.slack.data.Purpose;
import com.slack.data.Topic;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author bitter_fox
 */
public class ChannelImpl implements Channel
{
    private String id;
    private String name;
    private boolean isChannel;
    private int created;
    private String creator;
    private boolean isArchived;
    private boolean isGeneral;
    private boolean isMember;
    private List<String> members;
    private Topic topic;
    private Purpose purpose;
    private int numMembers;

    public void id(String id)
    {
        this.id = id;
    }

    @Override
    public String id()
    {
        return id;
    }

    public void name(String name)
    {
        this.name = name;
    }

    @Override
    public String name()
    {
        return name;
    }

    public void isChannel(boolean isChannel)
    {
        this.isChannel = isChannel;
    }

    @Override
    public boolean isChannel()
    {
        return isChannel;
    }

    public void created(int created)
    {
        this.created = created;
    }

    @Override
    public int created()
    {
        return created;
    }

    public void creator(String creator)
    {
        this.creator = creator;
    }

    @Override
    public String creator()
    {
        return creator;
    }

    public void isArchived(boolean isArchived)
    {
        this.isArchived = isArchived;
    }

    @Override
    public boolean isArchived()
    {
        return isArchived;
    }

    public void isGeneral(boolean isGeneral)
    {
        this.isGeneral = isGeneral;
    }

    @Override
    public boolean isGeneral()
    {
        return isGeneral;
    }

    public void isMember(boolean isMember)
    {
        this.isMember = isMember;
    }

    @Override
    public boolean isMember()
    {
        return isMember;
    }

    public void members(List<String> members)
    {
        this.members = Collections.unmodifiableList(members);
    }

    @Override
    public List<String> members()
    {
        return Collections.unmodifiableList(members);
    }

    public void topic(Topic topic)
    {
        this.topic = topic;
    }

    @Override
    public Topic topic()
    {
        return topic;
    }

    public void purpose(Purpose purpose)
    {
        this.purpose = purpose;
    }

    @Override
    public Purpose purpose()
    {
        return purpose;
    }

    public void numMembers(int numMembers)
    {
        this.numMembers = numMembers;
    }

    @Override
    public int numMembers()
    {
        return numMembers;
    }
}

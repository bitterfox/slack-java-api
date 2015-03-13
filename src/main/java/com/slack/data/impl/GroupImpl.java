/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.data.impl;

import com.slack.data.Group;
import com.slack.data.GroupId;
import com.slack.data.Purpose;
import com.slack.data.Topic;
import com.slack.data.UserId;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author bitter_fox
 */
public class GroupImpl implements Group
{
    private GroupId id;
    private String name;
    private boolean isGroup;
    private int created;
    private UserId creator;
    private boolean isArchived;
    private List<UserId> members;
    private Topic topic;
    private Purpose purpose;

    public void id(GroupId id)
    {
        this.id = id;
    }

    @Override
    public GroupId id()
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

    public void isGroup(boolean isGroup)
    {
        this.isGroup = isGroup;
    }

    @Override
    public boolean isGroup()
    {
        return isGroup;
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

    public void creator(UserId creator)
    {
        this.creator = creator;
    }

    @Override
    public UserId creator()
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

    public void members(List<UserId> members)
    {
        this.members = Collections.unmodifiableList(members);
    }

    @Override
    public List<UserId> members()
    {
        return this.members;
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

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + (this.isGroup ? 1 : 0);
        hash = 89 * hash + this.created;
        hash = 89 * hash + Objects.hashCode(this.creator);
        hash = 89 * hash + (this.isArchived ? 1 : 0);
        hash = 89 * hash + Objects.hashCode(this.members);
        hash = 89 * hash + Objects.hashCode(this.topic);
        hash = 89 * hash + Objects.hashCode(this.purpose);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final GroupImpl other = (GroupImpl)obj;
        return Objects.equals(this.id, other.id) &&
            Objects.equals(this.name, other.name) &&
            this.isGroup == other.isGroup &&
            this.created == other.created &&
            Objects.equals(this.creator, other.creator) &&
            this.isArchived == other.isArchived &&
            Objects.equals(this.members, other.members) &&
            Objects.equals(this.topic, other.topic) &&
            Objects.equals(this.purpose, other.purpose)
            ;
    }

    @Override
    public String toString()
    {
        return "GroupImpl{" + "id=" + id + ", name=" + name + ", isGroup=" + isGroup + ", created=" + created + ", creator=" + creator + ", isArchived=" + isArchived + ", members=" + members + ", topic=" + topic + ", purpose=" + purpose + '}';
    }
}

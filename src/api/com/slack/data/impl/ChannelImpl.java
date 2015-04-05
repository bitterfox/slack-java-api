/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.data.impl;

import com.slack.data.Channel;
import com.slack.data.ChannelId;
import com.slack.data.Purpose;
import com.slack.data.Topic;
import com.slack.data.UserId;
import com.slack.data.event.Message;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;

/**
 *
 * @author bitter_fox
 */
public class ChannelImpl implements Channel
{
    private ChannelId id;
    private String name;
    private boolean isChannel;
    private int created;
    private UserId creator;
    private boolean isArchived;
    private boolean isGeneral;
    private boolean isMember;
    private List<UserId> members;
    private Topic topic;
    private Purpose purpose;
    private int numMembers;

    private Optional<String> lastRead = Optional.empty();
    private Optional<Message> latest = Optional.empty();
    private OptionalInt unreadCount = OptionalInt.empty();
    private OptionalInt unreadCountDisplay = OptionalInt.empty();

    public void id(ChannelId id)
    {
        this.id = id;
    }

    @Override
    public ChannelId id()
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

    public void numMembers(int numMembers)
    {
        this.numMembers = numMembers;
    }

    @Override
    public int numMembers()
    {
        return numMembers;
    }

    public void lastRead(String lastRead)
    {
        this.lastRead = Optional.of(lastRead);
    }

    @Override
    public Optional<String> lastRead()
    {
        return lastRead;
    }

    public void latest(Message latest)
    {
        this.latest = Optional.of(latest);
    }

    @Override
    public Optional<Message> latest()
    {
        return latest;
    }

    public void unreadCount(int unreadCount)
    {
        this.unreadCount = OptionalInt.of(unreadCount);
    }

    @Override
    public OptionalInt unreadCount()
    {
        return unreadCount;
    }

    public void unreadCountDisplay(int unreadCountDisplay)
    {
        this.unreadCountDisplay = OptionalInt.of(unreadCountDisplay);
    }

    @Override
    public OptionalInt unreadCountDisplay()
    {
        return unreadCountDisplay;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + (this.isChannel ? 1 : 0);
        hash = 79 * hash + this.created;
        hash = 79 * hash + Objects.hashCode(this.creator);
        hash = 79 * hash + (this.isArchived ? 1 : 0);
        hash = 79 * hash + (this.isGeneral ? 1 : 0);
        hash = 79 * hash + (this.isMember ? 1 : 0);
        hash = 79 * hash + Objects.hashCode(this.members);
        hash = 79 * hash + Objects.hashCode(this.topic);
        hash = 79 * hash + Objects.hashCode(this.purpose);
        hash = 79 * hash + this.numMembers;
        hash = 79 * hash + Objects.hashCode(this.lastRead);
        hash = 79 * hash + Objects.hashCode(this.latest);
        hash = 79 * hash + Objects.hashCode(this.unreadCount);
        hash = 79 * hash + Objects.hashCode(this.unreadCountDisplay);
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
        final ChannelImpl other = (ChannelImpl)obj;

        return Objects.equals(this.id, other.id) &&
            Objects.equals(this.name, other.name) &&
            this.isChannel == other.isChannel &&
            this.created == other.created &&
            Objects.equals(this.creator, other.creator) &&
            this.isArchived == other.isArchived &&
            this.isGeneral == other.isGeneral &&
            this.isMember == other.isMember &&
            Objects.equals(this.members, other.members) &&
            Objects.equals(this.topic, other.topic) &&
            Objects.equals(this.purpose, other.purpose) &&
            this.numMembers == other.numMembers &&
            Objects.equals(this.lastRead, other.lastRead) &&
            Objects.equals(this.latest, other.latest) &&
            Objects.equals(this.unreadCount, other.unreadCount) &&
            Objects.equals(this.unreadCountDisplay, other.unreadCountDisplay)
            ;
    }

    @Override
    public String toString()
    {
        return "ChannelImpl{" + "id=" + id + ", name=" + name + ", isChannel=" + isChannel + ", created=" + created + ", creator=" + creator + ", isArchived=" + isArchived + ", isGeneral=" + isGeneral + ", isMember=" + isMember + ", members=" + members + ", topic=" + topic + ", purpose=" + purpose + ", numMembers=" + numMembers + ", lastRead=" + lastRead + ", latest=" + latest + ", unreadCount=" + unreadCount + ", unreadCountDisplay=" + unreadCountDisplay + '}';
    }
}

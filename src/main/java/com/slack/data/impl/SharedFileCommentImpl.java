/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.data.impl;

import com.slack.data.SharedFileComment;
import com.slack.data.SharedFileCommentId;
import com.slack.data.UserId;
import java.util.Objects;

/**
 *
 * @author bitter_fox
 */
public class SharedFileCommentImpl implements SharedFileComment
{
    private SharedFileCommentId id;
    private int created;
    private int timestamp;
    private UserId user;
    private String comment;

    public void id(SharedFileCommentId id)
    {
        this.id = id;
    }

    @Override
    public SharedFileCommentId id()
    {
        return id;
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

    public void timestamp(int timestamp)
    {
        this.timestamp = timestamp;
    }

    @Override
    public int timestamp()
    {
        return timestamp;
    }

    public void user(UserId user)
    {
        this.user = user;
    }

    @Override
    public UserId user()
    {
        return user;
    }

    public void comment(String comment)
    {
        this.comment = comment;
    }

    @Override
    public String comment()
    {
        return comment;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.id);
        hash = 43 * hash + this.created;
        hash = 43 * hash + this.timestamp;
        hash = 43 * hash + Objects.hashCode(this.user);
        hash = 43 * hash + Objects.hashCode(this.comment);
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
        final SharedFileCommentImpl other = (SharedFileCommentImpl)obj;
        if (!Objects.equals(this.id, other.id))
        {
            return false;
        }
        if (this.created != other.created)
        {
            return false;
        }
        if (this.timestamp != other.timestamp)
        {
            return false;
        }
        if (!Objects.equals(this.user, other.user))
        {
            return false;
        }
        if (!Objects.equals(this.comment, other.comment))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "SharedFileCommentImpl{" + "id=" + id + ", created=" + created + ", timestamp=" + timestamp + ", user=" + user + ", comment=" + comment + '}';
    }
}

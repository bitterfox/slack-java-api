/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.data.impl;

import com.slack.data.Im;
import com.slack.data.ImId;
import com.slack.data.UserId;
import java.util.Objects;

/**
 *
 * @author bitter_fox
 */
public class ImImpl implements Im
{
    private ImId id;
    private boolean isIm;
    private UserId user;
    private int created;
    private boolean isUserDeleted;

    public void id(ImId id)
    {
        this.id = id;
    }

    @Override
    public ImId id()
    {
        return id;
    }

    public void isIm(boolean isIm)
    {
        this.isIm = isIm;
    }

    @Override
    public boolean isIm()
    {
        return isIm;
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

    public void created(int created)
    {
        this.created = created;
    }

    @Override
    public int created()
    {
        return created;
    }

    public void isUserDeleted(boolean isUserDeleted)
    {
        this.isUserDeleted = isUserDeleted;
    }

    @Override
    public boolean isUserDeleted()
    {
        return isUserDeleted;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + (this.isIm ? 1 : 0);
        hash = 67 * hash + Objects.hashCode(this.user);
        hash = 67 * hash + this.created;
        hash = 67 * hash + (this.isUserDeleted ? 1 : 0);
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
        final ImImpl other = (ImImpl)obj;
        if (!Objects.equals(this.id, other.id))
        {
            return false;
        }
        if (this.isIm != other.isIm)
        {
            return false;
        }
        if (!Objects.equals(this.user, other.user))
        {
            return false;
        }
        if (this.created != other.created)
        {
            return false;
        }
        if (this.isUserDeleted != other.isUserDeleted)
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "ImImpl{" + "id=" + id + ", isIm=" + isIm + ", user=" + user + ", created=" + created + ", isUserDeleted=" + isUserDeleted + '}';
    }
}

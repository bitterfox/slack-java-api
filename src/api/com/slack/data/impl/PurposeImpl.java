/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.data.impl;

import com.slack.data.Purpose;
import com.slack.data.UserId;
import java.util.Objects;

/**
 *
 * @author bitter_fox
 */
public class PurposeImpl implements Purpose
{
    private String value;
    private UserId creator;
    private int lastSet;

    public void value(String value)
    {
        this.value = value;
    }

    @Override
    public String value()
    {
        return value;
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

    public void lastSet(int lastSet)
    {
        this.lastSet = lastSet;
    }

    @Override
    public int lastSet()
    {
        return lastSet;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.value);
        hash = 37 * hash + Objects.hashCode(this.creator);
        hash = 37 * hash + this.lastSet;
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
        final PurposeImpl other = (PurposeImpl)obj;

        return Objects.equals(this.value, other.value) &&
            Objects.equals(this.creator, other.creator) &&
            this.lastSet == other.lastSet;
    }

    @Override
    public String toString()
    {
        return "PurposeImpl{" + "value=" + value + ", creator=" + creator + ", lastSet=" + lastSet + '}';
    }
}

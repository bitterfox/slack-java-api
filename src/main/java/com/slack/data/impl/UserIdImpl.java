/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.data.impl;

import com.slack.data.UserId;
import java.util.Objects;

/**
 *
 * @author bitter_fox
 */
public class UserIdImpl implements UserId
{
    private String id;

    public UserIdImpl(String id)
    {
        this.id = id;
    }

    @Override
    public String id()
    {
        return id;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final UserIdImpl other = (UserIdImpl)obj;

        return Objects.equals(this.id, other.id);
    }
}

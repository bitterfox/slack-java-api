/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.data.impl;

import java.util.Objects;

/**
 *
 * @author bitter_fox
 */
public class IdImpl<T>
{
    private T id;

    public IdImpl(T id)
    {
        this.id = id;
    }

    public T id()
    {
        return id;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
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
        final IdImpl<T> other = (IdImpl<T>)obj;
        if (!Objects.equals(this.id, other.id))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return this.getClass().getSimpleName()+"{" + "id=" + id + '}';
    }
}

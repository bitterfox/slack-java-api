/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.data.impl;

import com.slack.data.Purpose;
import com.slack.data.UserId;

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
}

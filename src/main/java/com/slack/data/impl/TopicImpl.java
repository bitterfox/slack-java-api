/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.data.impl;

import com.slack.data.Topic;

/**
 *
 * @author bitter_fox
 */
public class TopicImpl implements Topic
{
    private String value;
    private String creator;
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

    public void creator(String creator)
    {
        this.creator = creator;
    }

    @Override
    public String creator()
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

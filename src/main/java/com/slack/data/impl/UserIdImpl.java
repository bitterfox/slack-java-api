/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.data.impl;

import com.slack.data.UserId;

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
}

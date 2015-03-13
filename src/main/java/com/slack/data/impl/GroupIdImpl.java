/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.data.impl;

import com.slack.data.GroupId;

/**
 *
 * @author bitter_fox
 */
public class GroupIdImpl extends IdImpl<String> implements GroupId
{
    public GroupIdImpl(String id)
    {
        super(id);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.data.impl;

import com.slack.data.ChannelId;
import java.util.Objects;

/**
 *
 * @author bitter_fox
 */
public class ChannelIdImpl extends IdImpl<String> implements ChannelId
{
    public ChannelIdImpl(String id)
    {
        super(id);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api;

import com.slack.data.Channel;
import com.slack.data.ChannelId;

/**
 *
 * @author bitter_fox
 */
public interface Channels
{
    @ApiIssuer
    Channels.Create create(String name);

    @ApiIssuer
    Channels.Join join(String channelName);

    @ApiIssuer
    Channels.Leave leave(ChannelId channelId);

    @ApiIssuer
    Channels.List list();

    @ApiIssuer
    Channels.Rename rename(ChannelId channelId, String newName);

    interface Create
    {
        Channel channel();
    }

    interface Join
    {
        Channel channel();
        boolean alreadyInChannel();
    }

    interface Leave
    {
        boolean notInChannel();
    }

    interface List
    {
        java.util.List<Channel> channels();
    }

    interface Rename
    {
        ChannelId id();
        boolean isChannel();
        String name();
        int created();
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api;

import com.slack.data.Group;
import com.slack.data.GroupId;

/**
 *
 * @author bitter_fox
 */
public interface Groups
{
    @ApiIssuer
    Groups.Close close(GroupId groupId);

    @ApiIssuer
    Groups.List list();

    @ApiIssuer
    Groups.Open open(GroupId groupId);

    @ApiIssuer
    Groups.SetPurpose setPurpose(GroupId groupId, String purpose);

    @ApiIssuer
    Groups.SetTopic setTopic(GroupId groupId, String topic);

    interface Close
    {
        boolean noOperation();
        boolean alreadyClosed();
    }

    interface List
    {
        java.util.List<Group> groups();
    }

    interface Open
    {
        boolean noOperation();
        boolean alreadyOpen();
    }

    interface SetPurpose
    {
        String purpose();
    }

    interface SetTopic
    {
        String topic();
    }
}

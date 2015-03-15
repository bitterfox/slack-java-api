/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api;

import com.slack.data.Group;
import com.slack.data.GroupId;
import com.slack.data.UserId;
import java.util.Optional;

/**
 *
 * @author bitter_fox
 */
public interface Groups
{
    @ApiIssuer
    Groups.Archive archive(GroupId groupId);

    @ApiIssuer
    Groups.Close close(GroupId groupId);

    @ApiIssuer
    Groups.Create create(String name);

    @ApiIssuer
    Groups.Invite invite(GroupId groupId, UserId userId);

    @ApiIssuer
    Groups.Leave leave(GroupId groupId);

    @ApiIssuer
    Groups.List list();

    @ApiIssuer
    Groups.Open open(GroupId groupId);

    @ApiIssuer
    Groups.Rename rename(GroupId groupId, String newName);

    @ApiIssuer
    Groups.SetPurpose setPurpose(GroupId groupId, String purpose);

    @ApiIssuer
    Groups.SetTopic setTopic(GroupId groupId, String topic);

    @ApiIssuer
    Groups.Unarchive unarchive(GroupId groupId);

    @ApiBridge
    Optional<Group> findById(GroupId groupId);

    interface Archive {}

    interface Close
    {
        boolean noOperation();
        boolean alreadyClosed();
    }

    interface Create
    {
        Group group();
    }

    interface Invite
    {
        boolean alreadyInGroup();
        Group group();
    }

    interface Leave {}

    interface List
    {
        java.util.List<Group> groups();
    }

    interface Open
    {
        boolean noOperation();
        boolean alreadyOpen();
    }

    interface Rename
    {
        GroupId id();
        boolean isGroup();
        String name();
        int created();
    }

    interface SetPurpose
    {
        String purpose();
    }

    interface SetTopic
    {
        String topic();
    }

    interface Unarchive {}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.data;

import com.slack.data.event.Message;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

/**
 *
 * @author bitter_fox
 */
public interface Channel
{
    ChannelId id();
    String name();
    boolean isChannel();
    int created();
    UserId creator();
    boolean isArchived();
    boolean isGeneral();
    boolean isMember();
    List<UserId> members();
    Topic topic();
    Purpose purpose();
    int numMembers();

    Optional<String> lastRead(); // TODO
    Optional<Message> latest();
    OptionalInt unreadCount();
    OptionalInt unreadCountDisplay();
}

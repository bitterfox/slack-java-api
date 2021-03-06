/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.data;

import java.util.List;

/**
 *
 * @author bitter_fox
 */
public interface Group
{
    GroupId id();
    String name();
    boolean isGroup();
    int created();
    UserId creator();
    boolean isArchived();
    List<UserId> members();
    Topic topic();
    Purpose purpose();
}

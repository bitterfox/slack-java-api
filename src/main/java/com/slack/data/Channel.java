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
public interface Channel
{
    String id();
    String name();
    boolean isChannel();
    int created();
    String creator();
    boolean isArchived();
    boolean isGeneral();
    boolean isMember();
    List<String> members();
    Topic topic();
    Purpose purpose();
    int numMembers();
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api;

import com.slack.data.Group;

/**
 *
 * @author bitter_fox
 */
public interface Groups
{
    @ApiIssuer
    Groups.List list();

    interface List
    {
        java.util.List<Group> groups();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api;

import com.slack.data.Im;
import com.slack.data.ImId;
import com.slack.data.UserId;

/**
 *
 * @author bitter_fox
 */
public interface ImApi
{
    @ApiIssuer
    ImApi.Close close(ImId imId);

    @ApiIssuer
    ImApi.List list();

    @ApiIssuer
    ImApi.Open open(UserId userId);

    interface Close
    {
        boolean noOperation();
        boolean alreadyClosed();
    }

    interface List
    {
        java.util.List<Im> ims();
    }

    interface Open
    {
        ImId im();
        boolean noOperation();
        boolean alreadyOpen();
    }
}

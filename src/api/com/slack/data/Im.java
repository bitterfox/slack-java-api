/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.data;

import com.slack.data.UserId;

/**
 *
 * @author bitter_fox
 */
public interface Im
{
    ImId id();
    boolean isIm();
    UserId user();
    int created();
    boolean isUserDeleted();
}

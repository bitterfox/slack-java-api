/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.data.impl;

import com.slack.data.SharedFileCommentId;

/**
 *
 * @author bitter_fox
 */
public class SharedFileCommentIdImpl extends IdImpl<String> implements SharedFileCommentId
{
    public SharedFileCommentIdImpl(String id)
    {
        super(id);
    }
}

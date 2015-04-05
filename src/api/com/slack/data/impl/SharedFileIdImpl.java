/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.data.impl;

import com.slack.data.SharedFileId;

/**
 *
 * @author bitter_fox
 */
public class SharedFileIdImpl extends IdImpl<String> implements SharedFileId
{
    public SharedFileIdImpl(String id)
    {
        super(id);
    }
}

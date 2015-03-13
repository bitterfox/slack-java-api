/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.api.Channels;
import com.slack.api.Groups;
import javax.json.JsonObject;

/**
 *
 * @author bitter_fox
 */
final class SetPurposeResult extends ApiResult implements Channels.SetPurpose, Groups.SetPurpose
{
    private String purpose;

    @Override
    public String purpose()
    {
        return purpose;
    }

    @Override
    public void apply(JsonObject result)
    {
        this.purpose = result.getString("purpose");
    }

}

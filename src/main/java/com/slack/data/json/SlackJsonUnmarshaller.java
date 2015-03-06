/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.data.json;

import com.slack.data.Channel;
import com.slack.data.Purpose;
import com.slack.data.Topic;
import javax.json.JsonObject;

/**
 *
 * @author bitter_fox
 */
public interface SlackJsonUnmarshaller
{
    Channel asChannel(JsonObject jo);
    Topic asTopic(JsonObject jo);
    Purpose asPurpose(JsonObject jo);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.data.json;

import com.slack.data.Channel;
import com.slack.data.Profile;
import com.slack.data.Purpose;
import com.slack.data.Topic;
import com.slack.data.User;
import com.slack.util.JsonUtil;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.json.JsonArray;
import javax.json.JsonObject;

/**
 *
 * @author bitter_fox
 */
public interface SlackJsonUnmarshaller
{
    Channel asChannel(JsonObject jo);
    default List<Channel> asChannels(JsonArray ja)
    {
        return Collections.unmodifiableList(
            ja.stream()
                .map(JsonUtil::castToObject)
                .map(this::asChannel)
                .collect(Collectors.toList()));
    }
    Topic asTopic(JsonObject jo);
    Purpose asPurpose(JsonObject jo);

    User asUser(JsonObject jo);
    default List<User> asUsers(JsonArray ja)
    {
        return Collections.unmodifiableList(
            ja.stream()
                .map(JsonUtil::castToObject)
                .map(this::asUser)
                .collect(Collectors.toList()));
    }
    Profile asProfile(JsonObject jo);
}

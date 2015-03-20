/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.data.json;

import com.slack.data.Channel;
import com.slack.data.Group;
import com.slack.data.Profile;
import com.slack.data.Purpose;
import com.slack.data.SharedFile;
import com.slack.data.SharedFileComment;
import com.slack.data.Topic;
import com.slack.data.User;
import com.slack.data.event.Message;
import com.slack.util.JsonUtil;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
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
        return SlackJsonUnmarshallers.asList(ja, this::asChannel);
    }
    Topic asTopic(JsonObject jo);
    Purpose asPurpose(JsonObject jo);

    Group asGroup(JsonObject jo);
    default List<Group> asGroups(JsonArray ja)
    {
        return SlackJsonUnmarshallers.asList(ja, this::asGroup);
    }

    User asUser(JsonObject jo);
    default List<User> asUsers(JsonArray ja)
    {
        return SlackJsonUnmarshallers.asList(ja, this::asUser);
    }
    Profile asProfile(JsonObject jo);

    Message asMessage(JsonObject jo);

    SharedFile asSharedFile(JsonObject jo);
    default List<SharedFile> asSharedFiles(JsonArray ja)
    {
        return SlackJsonUnmarshallers.asList(ja, this::asSharedFile);
    }
    SharedFileComment asSharedFileComment(JsonObject jo);
    default List<SharedFileComment> asSharedFileComments(JsonArray ja)
    {
        return SlackJsonUnmarshallers.asList(ja, this::asSharedFileComment);
    }
}

class SlackJsonUnmarshallers
{
    static <T> List<T> asList(JsonArray ja, Function<JsonObject, T> function)
    {
        return Collections.unmodifiableList(
            ja.getValuesAs(JsonObject.class).stream()
                .map(function::apply)
                .collect(Collectors.toList()));
    }
}
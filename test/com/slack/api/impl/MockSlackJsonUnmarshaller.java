/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.data.Channel;
import com.slack.data.Group;
import com.slack.data.Im;
import com.slack.data.Profile;
import com.slack.data.Purpose;
import com.slack.data.SharedFile;
import com.slack.data.SharedFileComment;
import com.slack.data.Topic;
import com.slack.data.User;
import com.slack.data.event.Message;
import com.slack.data.impl.ChannelImpl;
import com.slack.data.impl.GroupImpl;
import com.slack.data.impl.ImImpl;
import com.slack.data.impl.ProfileImpl;
import com.slack.data.impl.PurposeImpl;
import com.slack.data.impl.SharedFileCommentImpl;
import com.slack.data.impl.SharedFileImpl;
import com.slack.data.impl.TopicImpl;
import com.slack.data.impl.UserImpl;
import com.slack.data.impl.event.MessageImpl;
import com.slack.data.json.SlackJsonUnmarshaller;
import java.util.Objects;
import javax.json.JsonObject;

/**
 *
 * @author bitter_fox
 */
public class MockSlackJsonUnmarshaller implements SlackJsonUnmarshaller
{
    @Override
    public Channel asChannel(JsonObject jo)
    {
        Objects.requireNonNull(jo);
        return new ChannelImpl();
    }

    @Override
    public Topic asTopic(JsonObject jo)
    {
        Objects.requireNonNull(jo);
        return new TopicImpl();
    }

    @Override
    public Purpose asPurpose(JsonObject jo)
    {
        Objects.requireNonNull(jo);
        return new PurposeImpl();
    }

    @Override
    public Group asGroup(JsonObject jo)
    {
        Objects.requireNonNull(jo);
        return new GroupImpl();
    }

    @Override
    public Im asIm(JsonObject jo)
    {
        Objects.requireNonNull(jo);
        return new ImImpl();
    }

    @Override
    public User asUser(JsonObject jo)
    {
        Objects.requireNonNull(jo);
        return new UserImpl();
    }

    @Override
    public Profile asProfile(JsonObject jo)
    {
        Objects.requireNonNull(jo);
        return new ProfileImpl();
    }

    @Override
    public Message asMessage(JsonObject jo)
    {
        Objects.requireNonNull(jo);
        return new MessageImpl();
    }

    @Override
    public SharedFile asSharedFile(JsonObject jo)
    {
        Objects.requireNonNull(jo);
        return new SharedFileImpl();
    }

    @Override
    public SharedFileComment asSharedFileComment(JsonObject jo)
    {
        Objects.requireNonNull(jo);
        return new SharedFileCommentImpl();
    }
}

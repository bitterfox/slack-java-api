/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.data.impl.json;

import com.slack.data.Channel;
import com.slack.data.Purpose;
import com.slack.data.Topic;
import com.slack.data.impl.ChannelImpl;
import com.slack.data.impl.PurposeImpl;
import com.slack.data.impl.TopicImpl;
import com.slack.data.json.SlackJsonUnmarshaller;
import com.slack.util.JsonUtil;
import java.util.stream.Collectors;
import javax.json.JsonObject;
import javax.json.JsonString;

/**
 *
 * @author bitter_fox
 */
public class SlackJsonUnmarshallerImpl implements SlackJsonUnmarshaller
{
    public static class Names
    {
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String IS_CHANNEL = "is_channel";
        public static final String CREATED = "created";
        public static final String CREATOR = "creator";
        public static final String IS_ARCHIVED = "is_archived";
        public static final String IS_GENERAL = "is_general";
        public static final String IS_MEMBER = "is_member";
        public static final String MEMBERS = "members";
        public static final String TOPIC = "topic";
        public static final String PURPOSE = "purpose";
        public static final String NUM_MEMBERS = "num_members";

        public static final String VALUE = "value";
        public static final String LAST_SET = "last_set";
    }

    @Override
    public Channel asChannel(JsonObject jo)
    {
        ChannelImpl channel = new ChannelImpl();

        channel.id(jo.getString(Names.ID));
        channel.name(jo.getString(Names.NAME));
        channel.isChannel(jo.getBoolean(Names.IS_CHANNEL));
        channel.created(jo.getInt(Names.CREATED));
        channel.creator(jo.getString(Names.CREATOR));
        channel.isArchived(jo.getBoolean(Names.IS_ARCHIVED));
        channel.isGeneral(jo.getBoolean(Names.IS_GENERAL));
        channel.isMember(jo.getBoolean(Names.IS_MEMBER));
        channel.members(jo.getJsonArray(Names.MEMBERS).stream().map(JsonUtil::castToString).map(JsonString::getString).collect(Collectors.toList()));
        channel.topic(this.asTopic(jo.getJsonObject(Names.TOPIC)));
        channel.purpose(this.asPurpose(jo.getJsonObject(Names.PURPOSE)));
        channel.numMembers(jo.getInt(Names.NUM_MEMBERS));

        return channel;
    }

    @Override
    public Topic asTopic(JsonObject jo)
    {
        TopicImpl topic = new TopicImpl();

        topic.value(jo.getString(Names.VALUE));
        topic.creator(jo.getString(Names.CREATOR));
        topic.lastSet(jo.getInt(Names.LAST_SET));

        return topic;
    }

    @Override
    public Purpose asPurpose(JsonObject jo)
    {
        PurposeImpl purpose = new PurposeImpl();

        purpose.value(jo.getString(Names.VALUE));
        purpose.creator(jo.getString(Names.CREATOR));
        purpose.lastSet(jo.getInt(Names.LAST_SET));

        return purpose;
    }
}

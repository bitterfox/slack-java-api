/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api;

import com.slack.Slack;
import com.slack.data.Channel;
import com.slack.data.impl.ChannelImpl;
import com.slack.data.impl.PurposeImpl;
import com.slack.data.impl.TopicImpl;
import java.util.Collections;
import java.util.stream.Collectors;
import javax.json.JsonObject;

/**
 *
 * @author bitter_fox
 */
public class Channels
{
    private Api api;

    public Channels(Slack slack)
    {
        api = new Api(slack, "channels");
    }

    public Channels.List list()
    {
        GetApiRequest apiRequest = api.get("list", builder -> {});

        return apiRequest.issue(Channels.List::new,
            (result, rawResult) ->
            {
                java.util.List<Channel> channels =
                    rawResult.getJsonArray("channels").stream()
                        .map(jv -> (JsonObject)jv)
                        .map(
                            jo ->
                            {
                                // TODO
                                ChannelImpl channel = new ChannelImpl();
                                channel.id(jo.getString("id"));
                                channel.name(jo.getString("name"));
                                channel.created(jo.getInt("created"));
                                channel.creator(jo.getString("creator"));
                                channel.isArchived(jo.getBoolean("is_archived"));
                                channel.isMember(jo.getBoolean("is_member"));
                                channel.numMembers(jo.getInt("num_members"));

                                JsonObject topicObject = jo.getJsonObject("topic");
                                {
                                    TopicImpl topic = new TopicImpl();
                                    topic.value(topicObject.getString("value"));
                                    topic.creator(topicObject.getString("creator"));
                                    topic.lastSet(topicObject.getInt("last_set"));

                                    channel.topic(topic);
                                }

                                JsonObject purposeObject = jo.getJsonObject("purpose");
                                {
                                    PurposeImpl purpose = new PurposeImpl();
                                    purpose.value(purposeObject.getString("value"));
                                    purpose.creator(purposeObject.getString("creator"));
                                    purpose.lastSet(purposeObject.getInt("last_set"));

                                    channel.purpose(purpose);
                                }

                                return channel;
                            }).collect(Collectors.toList());
                result.channels(channels);
            });
    }

    public static class List
    {
        private java.util.List<Channel> channels;

        void channels(java.util.List<Channel> channels)
        {
            this.channels = Collections.unmodifiableList(channels);
        }

        public java.util.List<Channel> channels()
        {
            return channels;
        }
    }
}

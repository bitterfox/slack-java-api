/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.Slack;
import com.slack.api.ApiBridge;
import com.slack.api.ApiIssuer;
import com.slack.api.ChatApi;
import static com.slack.api.impl.Names.AS_USER;
import static com.slack.api.impl.Names.CHANNEL;
import static com.slack.api.impl.Names.CHAT;
import static com.slack.api.impl.Names.DELETE;
import static com.slack.api.impl.Names.ICON_EMOJI;
import static com.slack.api.impl.Names.ICON_URL;
import static com.slack.api.impl.Names.LINK_NAMES;
import static com.slack.api.impl.Names.MESSAGE;
import static com.slack.api.impl.Names.PARSE;
import static com.slack.api.impl.Names.POST_MESSAGE;
import static com.slack.api.impl.Names.TEXT;
import static com.slack.api.impl.Names.TS;
import static com.slack.api.impl.Names.UNFURL_LINKS;
import static com.slack.api.impl.Names.UNFURL_MEDIA;
import static com.slack.api.impl.Names.UPDATE;
import static com.slack.api.impl.Names.USER_NAME;
import com.slack.data.RoomableId;
import com.slack.data.event.Message;
import com.slack.util.Either;
import java.net.URL;
import java.util.Optional;
import javax.json.JsonObject;

/**
 *
 * @author bitter_fox
 */
public class ChatApiImpl implements ChatApi
{
    private Slack slack;
    private Api api;

    public ChatApiImpl(Slack slack)
    {
        this.slack = slack;
        this.api = new Api(slack, CHAT);
    }

    @ApiIssuer
    @Override
    public ChatApi.Delete delete(RoomableId channel, String timestamp)
    {
        GetApiRequest apiRequest = api.get(DELETE, builder ->
            builder.put(CHANNEL, channel.id())
                .put(TS, timestamp));

        return apiRequest.issue(ChatApiImpl.Delete::new);
    }

    @ApiBridge
    @Override
    public PostMessage postMessage(RoomableId channel, String text)
    {
        return this.postMessage(channel, text, Optional.empty(), Optional.of(true), Optional.of(ParseMode.FULL), Optional.of(true), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
    }

    @ApiBridge
    @Override
    public PostMessage postMessageAsBot(RoomableId channel, String text, String name)
    {
        return this.postMessage(channel, text, Optional.of(name), Optional.empty(), Optional.of(ParseMode.FULL), Optional.of(true), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
    }

    @ApiBridge
    @Override
    public PostMessage postMessageAsBot(RoomableId channel, String text, String name, Either<URL, String> icon)
    {
        return this.postMessage(channel, text, Optional.of(name), Optional.empty(), Optional.of(ParseMode.FULL), Optional.of(true), Optional.empty(), Optional.empty(), icon.getLeft(), icon.getRight());
    }

    @ApiIssuer
    @Override
    public PostMessage postMessage(RoomableId channel, String text, Optional<String> username, Optional<Boolean> asUser, Optional<ParseMode> parse, Optional<Boolean> linkNames, Optional<Boolean> unfurlLinks, Optional<Boolean> unfurlMedia, Optional<URL> iconUrl, Optional<String> iconEmoji)
    {
        GetApiRequest apiRequest = api.get(POST_MESSAGE,
            builder ->
            {
                builder.put(CHANNEL, channel.id())
                    .put(TEXT, text);
                username.ifPresent($ -> builder.put(USER_NAME, $));
                asUser.ifPresent($ -> builder.put(AS_USER, $));
                parse.filter($ -> $ != ParseMode.DEFAULT)
                    .ifPresent($ -> builder.put(PARSE, $.name().toLowerCase()));
                linkNames.ifPresent($ -> builder.put(LINK_NAMES, $));
                unfurlLinks.ifPresent($ -> builder.put(UNFURL_LINKS, $));
                unfurlMedia.ifPresent($ -> builder.put(UNFURL_MEDIA, $));
                iconUrl.map(URL::toString).ifPresent($ -> builder.put(ICON_URL, $));
                iconEmoji.ifPresent($ -> builder.put(ICON_EMOJI, $));
            });

        return apiRequest.issue(ChatApiImpl.PostMessage::new);
    }

    @ApiIssuer
    @Override
    public ChatApi.Update update(RoomableId channel, String timestamp, String text)
    {
        GetApiRequest apiRequest = api.get(UPDATE, builder ->
            builder.put(CHANNEL, channel.id())
                .put(TS, timestamp)
                .put(TEXT, text));

        return apiRequest.issue(ChatApiImpl.Update::new);
    }

    private final class Delete extends ApiResult implements ChatApi.Delete
    {
        private String timestamp;

        @Override
        public String timestamp()
        {
            return timestamp;
        }

        @Override
        protected void apply(JsonObject result)
        {
            this.timestamp = result.getString(TS);
        }
    }

    private final class PostMessage extends ApiResult implements ChatApi.PostMessage
    {
        private Message message;
        private String timestamp;

        @Override
        public Message message()
        {
            return message;
        }

        @Override
        public String timestamp()
        {
            return timestamp;
        }

        @Override
        protected void apply(JsonObject result)
        {
            this.message = slack.getConfigure().unmarshaller().asMessage(result.getJsonObject(MESSAGE));
            this.timestamp = result.getString(TS);
        }
    }

    private final class Update extends ApiResult implements ChatApi.Update
    {
        private String timestamp;
        private String text;

        @Override
        public String timestamp()
        {
            return timestamp;
        }

        @Override
        public String text()
        {
            return text;
        }

        @Override
        protected void apply(JsonObject result)
        {
            this.timestamp = result.getString(TS);
            this.text = result.getString(TEXT);
        }
    }
}

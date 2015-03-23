/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api;

import com.slack.data.ChannelId;
import com.slack.data.RoomableId;
import com.slack.data.event.Message;
import com.slack.util.Either;
import java.net.URL;
import java.util.Optional;

/**
 *
 * @author bitter_fox
 */
public interface ChatApi
{
    @ApiIssuer
    ChatApi.Delete delete(RoomableId channel, String timestamp);

    @ApiBridge
    ChatApi.PostMessage postMessage(RoomableId channel, String text); // channel=channel, text=str, parse=full,as_user=true, link_names=true

    @ApiBridge
    ChatApi.PostMessage postMessageAsBot(RoomableId channel, String text, String name);
    @ApiBridge
    ChatApi.PostMessage postMessageAsBot(RoomableId channel, String text, String name, Either<URL, String/*EmojiName*/> icon);

    @ApiIssuer
    PostMessage postMessage(RoomableId channel, String text, Optional<String> username, Optional<Boolean> asUser, Optional<ParseMode> parse, Optional<Boolean> linkNames, Optional<Boolean> unfurlLinks, Optional<Boolean> unfurlMedia, Optional<URL> iconUrl, Optional<String> iconEmoji);

    enum ParseMode
    {
        FULL, DEFAULT, NONE;
    }

    interface Delete
    {
        // current, ignoring channel
        String timestamp();
    }

    interface PostMessage
    {
        // current, ignoring channel
        Message message();
        String timestamp();
    }
}

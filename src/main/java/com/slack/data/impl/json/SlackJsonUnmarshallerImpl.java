/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.data.impl.json;

import com.slack.api.util.URLUtil;
import com.slack.data.Channel;
import com.slack.data.Profile;
import com.slack.data.Purpose;
import com.slack.data.Topic;
import com.slack.data.User;
import com.slack.data.impl.ChannelImpl;
import com.slack.data.impl.ProfileImpl;
import com.slack.data.impl.PurposeImpl;
import com.slack.data.impl.TopicImpl;
import com.slack.data.impl.UserImpl;
import com.slack.data.json.SlackJsonUnmarshaller;
import com.slack.util.JsonUtil;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.ZoneId;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.scene.paint.Color;
import javax.json.JsonObject;
import javax.json.JsonString;

/**
 *
 * @author bitter_fox
 */
public class SlackJsonUnmarshallerImpl implements SlackJsonUnmarshaller
{
    private static class Names
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

        public static final String DELETED = "deleted";
        public static final String STATUS = "status";
        public static final String COLOR = "color";
        public static final String REAL_NAME = "real_name";
        public static final String TZ = "tz";
        public static final String TZ_LABEL = "tz_label";
        public static final String TZ_OFFSET = "tz_offset";
        public static final String PROFILE ="profile";

        public static final String IS_ADMIN = "is_admin";
        public static final String IS_OWNER = "is_owner";
        public static final String IS_PRIMARY_OWNER = "is_primary_owner";
        public static final String IS_RESTRICTED = "is_restricted";
        public static final String IS_ULTRA_RESTRICTED = "is_ultra_restricted";
        public static final String IS_BOT = "is_bot";
        public static final String HAS_FILES = "has_files";

        public static final String IMAGE_24 = "image_24";
        public static final String IMAGE_32 = "image_32";
        public static final String IMAGE_48 = "image_48";
        public static final String IMAGE_72 = "image_72";
        public static final String IMAGE_192 = "image_192";
        public static final String IMAGE_ORIGINAL = "image_original";
        public static final String FIRST_NAME = "first_name";
        public static final String LAST_NAME = "last_name";
        public static final String TITLE = "title";
        public static final String SKYPE = "skype";
        public static final String PHONE = "phone";
        public static final String REAL_NAME_NORMALIZED = "real_name_normalized";
        public static final String EMAIL = "email";
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

        if (jo.containsKey(Names.NUM_MEMBERS))
        {
            channel.numMembers(jo.getInt(Names.NUM_MEMBERS));
        }
        else
        {
            channel.numMembers(channel.members().size());
        }

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

    @Override
    public User asUser(JsonObject jo)
    {
        UserImpl user = new UserImpl();

        user.id(jo.getString(Names.ID));
        user.name(jo.getString(Names.NAME));
        user.deleted(jo.getBoolean(Names.DELETED));
        // TODO user.status
        user.color(Color.web(jo.getString(Names.COLOR)));
        user.realName(jo.getString(Names.REAL_NAME));
        user.timeZone(ZoneId.of(jo.getString(Names.TZ))); // ignoreing tz_offset
        user.timeZoneLabel(jo.getString(Names.TZ_LABEL));

        user.profile(this.asProfile(jo.getJsonObject(Names.PROFILE)));

        user.isAdmin(jo.getBoolean(Names.IS_ADMIN));
        user.isOwner(jo.getBoolean(Names.IS_OWNER));
        user.isPrimaryOwner(jo.getBoolean(Names.IS_PRIMARY_OWNER));
        user.isRestricted(jo.getBoolean(Names.IS_RESTRICTED));
        user.isUltraRestricted(jo.getBoolean(Names.IS_ULTRA_RESTRICTED));
        user.isBot(jo.getBoolean(Names.IS_BOT));
        user.hasFiles(jo.getBoolean(Names.HAS_FILES));

        return user;
    }

    @Override
    public Profile asProfile(JsonObject jo)
    {
        ProfileImpl profile = new ProfileImpl();

        try
        {
            profile.image(Profile.ImageSize.XS,
                JsonUtil.getStringOpt(jo, Names.IMAGE_24)
                    .map(URLUtil::unsafeCreate));
            profile.image(Profile.ImageSize.S,
                JsonUtil.getStringOpt(jo, Names.IMAGE_32)
                    .map(URLUtil::unsafeCreate));
            profile.image(Profile.ImageSize.M,
                JsonUtil.getStringOpt(jo, Names.IMAGE_48)
                    .map(URLUtil::unsafeCreate));
            profile.image(Profile.ImageSize.L,
                JsonUtil.getStringOpt(jo, Names.IMAGE_72)
                    .map(URLUtil::unsafeCreate));
            profile.image(Profile.ImageSize.XL,
                JsonUtil.getStringOpt(jo, Names.IMAGE_192)
                    .map(URLUtil::unsafeCreate));
            profile.image(Profile.ImageSize.ORIGINAL,
                JsonUtil.getStringOpt(jo, Names.IMAGE_ORIGINAL)
                    .map(URLUtil::unsafeCreate));

            JsonUtil.getStringOpt(jo, Names.FIRST_NAME)
                .ifPresent(profile::firstName);
            JsonUtil.getStringOpt(jo, Names.LAST_NAME)
                .ifPresent(profile::firstName);
            JsonUtil.getStringOpt(jo, Names.TITLE)
                .ifPresent(profile::title);
            JsonUtil.getStringOpt(jo, Names.SKYPE)
                .ifPresent(profile::skype);
            JsonUtil.getStringOpt(jo, Names.PHONE)
                .ifPresent(profile::phone);
            profile.realName(jo.getString(Names.REAL_NAME));
            profile.realNameNormalized(jo.getString(Names.REAL_NAME_NORMALIZED));
        }
        catch (Exception e)
        {
            Logger.getLogger(SlackJsonUnmarshallerImpl.class.getName()).log(Level.SEVERE, jo.toString());
            throw e;
        }

        return profile;
    }
}

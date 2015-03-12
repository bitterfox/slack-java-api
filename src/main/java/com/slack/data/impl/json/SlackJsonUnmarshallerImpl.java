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
import com.slack.data.UserId;
import com.slack.data.event.Message;
import com.slack.data.impl.ChannelIdImpl;
import com.slack.data.impl.ChannelImpl;
import com.slack.data.impl.ProfileImpl;
import com.slack.data.impl.PurposeImpl;
import com.slack.data.impl.TopicImpl;
import com.slack.data.impl.UserIdImpl;
import com.slack.data.impl.UserImpl;
import com.slack.data.impl.event.MessageImpl;
import com.slack.data.json.SlackJsonUnmarshaller;
import com.slack.util.JsonUtil;
import java.time.ZoneId;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
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

        public static final String LAST_READ = "last_read";
        public static final String LATEST = "latest";
        public static final String UNREAD_COUNT = "unread_count";
        public static final String UNREAD_COUNT_DISPLAY = "unread_count_display";

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

        public static final String TYPE = "type";

        public static final String MESSAGE = "message";

        public static final String TEXT = "text";
        public static final String TIMESTAMP = "ts";
    }

    private <T, U> void unmarshal(JsonObject jo, String key, BiFunction<JsonObject, String, T> getter, Consumer<U> consumer, Function<T, U> mapper)
    {
        consumer.accept(mapper.apply(getter.apply(jo, key)));
    }

    private <T, U> void unmarshalOpt(JsonObject jo, String key, BiFunction<JsonObject, String, T> getter, Consumer<U> consumer, Function<T, U> mapper)
    {
        if (jo.containsKey(key))
        {
            consumer.accept(mapper.apply(getter.apply(jo, key)));
        }
    }
    private <T, U> void unmarshalOpt(JsonObject jo, String key, BiFunction<JsonObject, String, T> getter, Consumer<U> consumer, Function<T, U> mapper, Supplier<U> onEmpty)
    {
        if (jo.containsKey(key))
        {
            consumer.accept(mapper.apply(getter.apply(jo, key)));
        }
        else
        {
            consumer.accept(onEmpty.get());
        }
    }

    private void unmarshalString(JsonObject jo, String key, Consumer<String> consumer)
    {
        this.unmarshal(jo, key, JsonObject::getString, consumer, Function.identity());
    }
    private <T> void unmarshalString(JsonObject jo, String key, Consumer<T> consumer, Function<String, T> mapper)
    {
        this.unmarshal(jo, key, JsonObject::getString, consumer, mapper);
    }
    private void unmarshalStringOpt(JsonObject jo, String key, Consumer<String> consumer)
    {
        this.unmarshalOpt(jo, key, JsonObject::getString, consumer, Function.identity());
    }
    private <T> void unmarshalStringOpt(JsonObject jo, String key, Consumer<T> consumer, Function<String, T> mapper)
    {
        this.unmarshalOpt(jo, key, JsonObject::getString, consumer, mapper);
    }

    private void unmarshalBoolean(JsonObject jo, String key, Consumer<Boolean> consumer)
    {
        this.unmarshal(jo, key, JsonObject::getBoolean, consumer, Function.identity());
    }

    private void unmarshalInt(JsonObject jo, String key, Consumer<Integer> consumer)
    {
        this.unmarshal(jo, key, JsonObject::getInt, consumer, Function.identity());
    }
    private <T> void unmarshalInt(JsonObject jo, String key, Consumer<T> consumer, Function<Integer, T> mapper)
    {
        this.unmarshal(jo, key, JsonObject::getInt, consumer, mapper);
    }
    private void unmarshalIntOpt(JsonObject jo, String key, Consumer<Integer> consumer)
    {
        this.unmarshalOpt(jo, key, JsonObject::getInt, consumer, Function.identity());
    }
    private void unmarshalIntOpt(JsonObject jo, String key, Consumer<Integer> consumer, Supplier<Integer> onEmpty)
    {
        this.unmarshalOpt(jo, key, JsonObject::getInt, consumer, Function.identity(), onEmpty);
    }

    private <T> void unmarshalStringArray(JsonObject jo, String key, Consumer<List<T>> consumer, Function<String, T> mapper)
    {
        this.unmarshal(jo, key, JsonObject::getJsonArray, consumer,
            o -> o.stream().map(JsonUtil::castToString).map(JsonString::getString)
                .map(mapper)
                .collect(Collectors.toList()));
    }

    private <T> void unmarshalObject(JsonObject jo, String key, Consumer<T> consumer, Function<JsonObject, T> mapper)
    {
        this.unmarshal(jo, key, JsonObject::getJsonObject, consumer, mapper);
    }
    private <T> void unmarshalObjectOpt(JsonObject jo, String key, Consumer<T> consumer, Function<JsonObject, T> mapper)
    {
        this.unmarshalOpt(jo, key, JsonObject::getJsonObject, consumer, mapper);
    }

    private void requireString(JsonObject jo, String key, String require)
    {
        String actual = jo.getString(key);

        if (!actual.equals(require))
        {
            throw new RuntimeException("require " + require + " but " + actual);
        }
    }

    private void asChannel(JsonObject jo, ChannelImpl channel)
    {
    }

    @Override
    public Channel asChannel(JsonObject jo)
    {
        ChannelImpl channel = new ChannelImpl();

        this.unmarshalString(jo,              Names.ID,                   channel::id,                  ChannelIdImpl::new);
        this.unmarshalString(jo,              Names.NAME,                 channel::name);
        this.unmarshalBoolean(jo,             Names.IS_CHANNEL,           channel::isChannel);
        this.unmarshalInt(jo,                 Names.CREATED,              channel::created);
        this.unmarshalString(jo,              Names.CREATOR,              channel::creator,             UserIdImpl::new);
        this.unmarshalBoolean(jo,             Names.IS_ARCHIVED,          channel::isArchived);
        this.unmarshalBoolean(jo,             Names.IS_GENERAL,           channel::isGeneral);
        this.unmarshalBoolean(jo,             Names.IS_MEMBER,            channel::isMember);
        this.<UserId>unmarshalStringArray(jo, Names.MEMBERS,              channel::members,             UserIdImpl::new); // XXX
        this.unmarshalObject(jo,              Names.TOPIC,                channel::topic,               this::asTopic);
        this.unmarshalObject(jo,              Names.PURPOSE,              channel::purpose,             this::asPurpose);
        this.unmarshalIntOpt(jo,              Names.NUM_MEMBERS,          channel::numMembers,                               () -> channel.members().size());

        this.unmarshalStringOpt(jo,           Names.LAST_READ,            channel::lastRead);
        this.unmarshalObjectOpt(jo,           Names.LATEST,               channel::latest,              this::asMessage);
        this.unmarshalIntOpt(jo,              Names.UNREAD_COUNT,         channel::unreadCount);
        this.unmarshalIntOpt(jo,              Names.UNREAD_COUNT_DISPLAY, channel::unreadCountDisplay);

        return channel;
    }

    @Override
    public Topic asTopic(JsonObject jo)
    {
        TopicImpl topic = new TopicImpl();

        this.unmarshalString(jo, Names.VALUE,    topic::value);
        this.unmarshalString(jo, Names.CREATOR,  topic::creator,  UserIdImpl::new);
        this.unmarshalInt(jo,    Names.LAST_SET, topic::lastSet);

        return topic;
    }

    @Override
    public Purpose asPurpose(JsonObject jo)
    {
        PurposeImpl purpose = new PurposeImpl();

        this.unmarshalString(jo, Names.VALUE,    purpose::value);
        this.unmarshalString(jo, Names.CREATOR,  purpose::creator,  UserIdImpl::new);
        this.unmarshalInt(jo,    Names.LAST_SET, purpose::lastSet);

        return purpose;
    }

    @Override
    public User asUser(JsonObject jo)
    {
        UserImpl user = new UserImpl();

        this.unmarshalString(jo,  Names.ID,                  user::id,                 UserIdImpl::new);
        this.unmarshalString(jo,  Names.NAME,                user::name);
        this.unmarshalBoolean(jo, Names.DELETED,             user::deleted);
        // TODO user.status
        this.unmarshalString(jo,  Names.COLOR,               user::color,              Color::web);
        this.unmarshalString(jo,  Names.REAL_NAME,           user::realName);
        this.unmarshalString(jo,  Names.TZ,                  user::timeZone,           ZoneId::of);
        // ignore tz_offset
        this.unmarshalString(jo,  Names.TZ_LABEL,            user::timeZoneLabel);

        this.unmarshalObject(jo,  Names.PROFILE,             user::profile,            this::asProfile);

        this.unmarshalBoolean(jo, Names.IS_ADMIN,            user::isAdmin);
        this.unmarshalBoolean(jo, Names.IS_OWNER,            user::isOwner);
        this.unmarshalBoolean(jo, Names.IS_PRIMARY_OWNER,    user::isPrimaryOwner);
        this.unmarshalBoolean(jo, Names.IS_RESTRICTED,       user::isRestricted);
        this.unmarshalBoolean(jo, Names.IS_ULTRA_RESTRICTED, user::isUltraRestricted);
        this.unmarshalBoolean(jo, Names.IS_BOT,              user::isBot);
        this.unmarshalBoolean(jo, Names.HAS_FILES,           user::hasFiles);

        return user;
    }

    @Override
    public Profile asProfile(JsonObject jo)
    {
        ProfileImpl profile = new ProfileImpl();

        try
        {
            BiConsumer<String, Profile.ImageSize> unmarshalImage = (name, size) ->
                this.unmarshalStringOpt(jo, name,
                    url -> profile.image(size, url), URLUtil::unsafeCreate);

            unmarshalImage.accept(      Names.IMAGE_24,       Profile.ImageSize.XS);
            unmarshalImage.accept(      Names.IMAGE_32,       Profile.ImageSize.S);
            unmarshalImage.accept(      Names.IMAGE_48,       Profile.ImageSize.M);
            unmarshalImage.accept(      Names.IMAGE_72,       Profile.ImageSize.L);
            unmarshalImage.accept(      Names.IMAGE_192,      Profile.ImageSize.XL);
            unmarshalImage.accept(      Names.IMAGE_ORIGINAL, Profile.ImageSize.ORIGINAL);

            this.unmarshalStringOpt(jo, Names.FIRST_NAME,           profile::firstName);
            this.unmarshalStringOpt(jo, Names.LAST_NAME,            profile::lastName);
            this.unmarshalStringOpt(jo, Names.TITLE,                profile::title);
            this.unmarshalStringOpt(jo, Names.SKYPE,                profile::skype);
            this.unmarshalStringOpt(jo, Names.PHONE,                profile::phone);
            this.unmarshalString(jo,    Names.REAL_NAME,            profile::realName);
            this.unmarshalString(jo,    Names.REAL_NAME_NORMALIZED, profile::realNameNormalized);
            this.unmarshalString(jo,    Names.EMAIL,                profile::email);
        }
        catch (Exception e)
        {
            Logger.getLogger(SlackJsonUnmarshallerImpl.class.getName()).log(Level.SEVERE, jo.toString());
            throw e;
        }

        return profile;
    }

    @Override
    public Message asMessage(JsonObject jo)
    {
        this.requireString(jo, Names.TYPE, Names.MESSAGE);

        // TODO: SUBTYPE
        MessageImpl message = new MessageImpl();

        this.unmarshalString(jo, Names.TEXT,      message::text);
        this.unmarshalString(jo, Names.TIMESTAMP, message::timestamp);

        // TODO: edited

        return message;
    }
}

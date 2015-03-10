/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.data.impl.json;

import com.slack.data.Channel;
import com.slack.data.Profile;
import com.slack.data.Purpose;
import com.slack.data.Topic;
import com.slack.data.User;
import com.slack.data.UserId;
import com.slack.data.impl.ChannelIdImpl;
import com.slack.data.impl.ChannelImpl;
import com.slack.data.impl.ProfileImpl;
import com.slack.data.impl.PurposeImpl;
import com.slack.data.impl.TopicImpl;
import com.slack.data.impl.UserIdImpl;
import com.slack.data.impl.UserImpl;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.scene.paint.Color;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bitter_fox
 */
public class SlackJsonUnmarshallerImplTest
{
    public SlackJsonUnmarshallerImplTest()
    {
    }

    @BeforeClass
    public static void setUpClass()
    {
    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
    }

    private Channel channel()
    {
        ChannelImpl channel = new ChannelImpl();

        channel.id(new ChannelIdImpl("CHANNEL-ID"));
        channel.created(123456);
        channel.creator(new UserIdImpl("USER-ID"));
        channel.isArchived(true);
        channel.isChannel(false);
        channel.isGeneral(true);
        channel.isMember(true);
        channel.name("NAME");
        List<UserId> members = Stream.of("aaa", "bbb", "ccc").map(UserIdImpl::new).collect(Collectors.toList());
        channel.members(members);
        channel.numMembers(members.size());
        channel.topic(this.topic());
        channel.purpose(this.purpose());

        return channel;
    }
    private Topic topic()
    {
        TopicImpl topic = new TopicImpl();

        topic.lastSet(123456);
        topic.value("TOPIC");
        topic.creator(new UserIdImpl("TOPIC-CREATOR"));

        return topic;
    }
    private Purpose purpose()
    {
        PurposeImpl purpose = new PurposeImpl();

        purpose.lastSet(654321);
        purpose.value("PURPOSE");
        purpose.creator(new UserIdImpl("PURPOSE-CREATOR"));

        return purpose;
    }
    private User user()
    {
        UserImpl user = new UserImpl();

        user.id(new UserIdImpl("USER-ID"));
        user.name("NAME");
        user.realName("REAL NAME");
        user.color(Color.AQUA);
        user.timeZone(ZoneId.systemDefault());
        user.timeZoneLabel("LABEL");
        user.profile(this.profile());
        user.deleted(true);
        user.isAdmin(true);
        user.isOwner(true);
        user.isPrimaryOwner(true);
        user.isRestricted(true);
        user.isUltraRestricted(true);
        user.isBot(true);
        user.hasFiles(true);

        return user;
    }
    private Profile profile()
    {
        ProfileImpl profile = new ProfileImpl();

        try
        {
            profile.image(Profile.ImageSize.XS, new URL("http://localhost/xs"));
            profile.image(Profile.ImageSize.M,  new URL("http://localhost/s"));
        }
        catch (MalformedURLException ex)
        {
            Logger.getLogger(SlackJsonUnmarshallerImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        profile.firstName("firstName");
        profile.lastName("lastName");
        profile.title("title");
        profile.skype("skype");
        profile.phone("phone");
        profile.realName("realName");
        profile.realNameNormalized("realNameNormalized");
        profile.email("email");

        return profile;
    }

    private JsonObject channelToJsonObject(Channel channel)
    {
        JsonArrayBuilder members = channel.members().stream()
            .map(UserId::id)
            .reduce(Json.createArrayBuilder(), JsonArrayBuilder::add, (a1, a2) -> a1);
        return Json.createObjectBuilder()
            .add("id", channel.id().id())
            .add("created", channel.created())
            .add("creator", channel.creator().id())
            .add("is_archived", channel.isArchived())
            .add("is_channel", channel.isChannel())
            .add("is_general", channel.isGeneral())
            .add("is_member", channel.isMember())
            .add("name", channel.name())
            .add("members", members)
            .add("num_members", channel.numMembers())
            .add("topic", this.topicToJsonObject(channel.topic()))
            .add("purpose", this.purposeToJsonObject(channel.purpose()))
            .build();
    }
    private JsonObject topicToJsonObject(Topic topic)
    {
        return Json.createObjectBuilder()
            .add("value", topic.value())
            .add("creator", topic.creator().id())
            .add("last_set", topic.lastSet())
            .build();
    }
    private JsonObject purposeToJsonObject(Purpose purpose)
    {
        return Json.createObjectBuilder()
            .add("value", purpose.value())
            .add("creator", purpose.creator().id())
            .add("last_set", purpose.lastSet())
            .build();
    }
    private JsonObject userToJsonObject(User user)
    {
        Color color = user.colorAsFx();
        return Json.createObjectBuilder()
            .add("id", user.id().id())
            .add("name", user.name())
            .add("deleted", user.deleted())
            .add("color", String.format("%02X%02X%02X", (int)color.getRed()*255, (int)color.getGreen()*255, (int)color.getBlue()*255))
            .add("real_name", user.realName())
            .add("deleted", user.deleted())
            .add("tz", user.timeZoneId().toString())
            .add("tz_offset", user.timeZone().getTotalSeconds())
            .add("tz_label", user.timeZoneLabel())
            .add("profile", this.profileToJsonObject(user.profile()))
            .add("is_admin", user.isAdmin())
            .add("is_owner", user.isOwner())
            .add("is_primary_owner", user.isPrimaryOwner())
            .add("is_restricted", user.isRestricted())
            .add("is_ultra_restricted", user.isUltraRestricted())
            .add("is_bot", user.isBot())
            .add("has_files", user.hasFiles())
            .build();
    }
    private JsonObject profileToJsonObject(Profile profile)
    {
        JsonObjectBuilder job = Json.createObjectBuilder();

        Arrays.stream(Profile.ImageSize.values())
            .forEach(
                is ->
                {
                    Optional<URL> image = profile.image(is);
                    image.ifPresent(url ->
                        job.add(is == Profile.ImageSize.ORIGINAL
                            ? "image_original"
                            : "image_"+is.getSize(), url.toString()));
                });

        return job
            .add("first_name", profile.firstName())
            .add("last_name", profile.lastName())
            .add("title", profile.title())
            .add("skype", profile.skype())
            .add("phone", profile.phone())
            .add("real_name", profile.realName())
            .add("real_name_normalized", profile.realNameNormalized())
            .add("email", profile.email())
            .build();
    }

    /**
     * Test of asChannel method, of class SlackJsonUnmarshallerImpl.
     */
    @Test
    public void testAsChannel()
    {
        Channel expResult = this.channel();

        JsonObject jo = this.channelToJsonObject(expResult);

        SlackJsonUnmarshallerImpl instance = new SlackJsonUnmarshallerImpl();
        Channel result = instance.asChannel(jo);
        assertEquals(expResult, result);
    }

    /**
     * Test of asTopic method, of class SlackJsonUnmarshallerImpl.
     */
    @Test
    public void testAsTopic()
    {
        Topic expResult = this.topic();

        JsonObject jo = this.topicToJsonObject(expResult);

        SlackJsonUnmarshallerImpl instance = new SlackJsonUnmarshallerImpl();

        Topic result = instance.asTopic(jo);
        assertEquals(expResult, result);
    }

    /**
     * Test of asPurpose method, of class SlackJsonUnmarshallerImpl.
     */
    @Test
    public void testAsPurpose()
    {
        Purpose expResult = this.purpose();

        JsonObject jo = this.purposeToJsonObject(expResult);

        SlackJsonUnmarshallerImpl instance = new SlackJsonUnmarshallerImpl();
        Purpose result = instance.asPurpose(jo);
        assertEquals(expResult, result);
    }

    /**
     * Test of asUser method, of class SlackJsonUnmarshallerImpl.
     */
    @Test
    public void testAsUser()
    {
        User expResult = this.user();

        JsonObject jo = this.userToJsonObject(expResult);

        SlackJsonUnmarshallerImpl instance = new SlackJsonUnmarshallerImpl();
        User result = instance.asUser(jo);
        assertEquals(expResult, result);
    }

    /**
     * Test of asProfile method, of class SlackJsonUnmarshallerImpl.
     */
    @Test
    public void testAsProfile()
    {
        Profile expResult = this.profile();

        JsonObject jo = this.profileToJsonObject(expResult);

        SlackJsonUnmarshallerImpl instance = new SlackJsonUnmarshallerImpl();
        Profile result = instance.asProfile(jo);
        assertEquals(expResult, result);
    }
}

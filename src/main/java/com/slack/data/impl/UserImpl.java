/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.data.impl;

import com.slack.data.Profile;
import com.slack.data.User;
import com.slack.data.UserId;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Objects;
import javafx.scene.paint.Color;


public class UserImpl implements User
{
    private UserId id;
    private String name;
    private boolean deleted;
    private Color color;
    private String realName;
    private ZoneOffset timeZone;
    private ZoneId timeZoneId;
    private String timeZoneLabel;
    private Profile profile;
    private boolean isAdmin;
    private boolean isOwner;
    private boolean isPrimaryOwner;
    private boolean isRestricted;
    private boolean isUltraRestricted;
    private boolean isBot;
    private boolean hasFiles;

    public void id(UserId id)
    {
        this.id = id;
    }

    @Override
    public UserId id()
    {
        return id;
    }

    public void name(String name)
    {
        this.name = name;
    }

    @Override
    public String name()
    {
        return name;
    }

    public void deleted(boolean deleted)
    {
        this.deleted = deleted;
    }

    @Override
    public boolean deleted()
    {
        return deleted;
    }

    public void color(Color color)
    {
        this.color = color;
    }

    @Override
    public java.awt.Color color()
    {
        return new java.awt.Color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue());
    }
    @Override
    public Color colorAsFx()
    {
        return color;
    }

    public void realName(String realName)
    {
        this.realName = realName;
    }

    @Override
    public String realName()
    {
        return realName;
    }

    public void timeZone(ZoneId timeZoneId)
    {
        this.timeZoneId = timeZoneId;
        this.timeZone = timeZoneId.getRules().getOffset(Instant.EPOCH);
    }

    @Override
    public ZoneOffset timeZone()
    {
        return timeZone;
    }
    @Override
    public ZoneId timeZoneId()
    {
        return timeZoneId;
    }

    public void timeZoneLabel(String timeZoneLabel)
    {
        this.timeZoneLabel = timeZoneLabel;
    }

    @Override
    public String timeZoneLabel()
    {
        return timeZoneLabel;
    }

    public void profile(Profile profile)
    {
        this.profile = profile;
    }

    @Override
    public Profile profile()
    {
        return profile;
    }

    public void isAdmin(boolean isAdmin)
    {
        this.isAdmin = isAdmin;
    }

    @Override
    public boolean isAdmin()
    {
        return isAdmin;
    }

    public void isOwner(boolean isOwner)
    {
        this.isOwner = isOwner;
    }

    @Override
    public boolean isOwner()
    {
        return isOwner;
    }

    public void isPrimaryOwner(boolean isPrimaryOwner)
    {
        this.isPrimaryOwner = isPrimaryOwner;
    }

    @Override
    public boolean isPrimaryOwner()
    {
        return isPrimaryOwner;
    }

    public void isRestricted(boolean isRestricted)
    {
        this.isRestricted = isRestricted;
    }

    @Override
    public boolean isRestricted()
    {
        return isRestricted;
    }

    public void isUltraRestricted(boolean isUltraRestricted)
    {
        this.isUltraRestricted = isUltraRestricted;
    }

    @Override
    public boolean isUltraRestricted()
    {
        return isUltraRestricted;
    }

    public void isBot(boolean isBot)
    {
        this.isBot = isBot;
    }

    @Override
    public boolean isBot()
    {
        return isBot;
    }

    public void hasFiles(boolean hasFiles)
    {
        this.hasFiles = hasFiles;
    }

    @Override
    public boolean hasFiles()
    {
        return hasFiles;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + (this.deleted ? 1 : 0);
        hash = 59 * hash + Objects.hashCode(this.color);
        hash = 59 * hash + Objects.hashCode(this.realName);
        hash = 59 * hash + Objects.hashCode(this.timeZone);
        hash = 59 * hash + Objects.hashCode(this.timeZoneId);
        hash = 59 * hash + Objects.hashCode(this.timeZoneLabel);
        hash = 59 * hash + Objects.hashCode(this.profile);
        hash = 59 * hash + (this.isAdmin ? 1 : 0);
        hash = 59 * hash + (this.isOwner ? 1 : 0);
        hash = 59 * hash + (this.isPrimaryOwner ? 1 : 0);
        hash = 59 * hash + (this.isRestricted ? 1 : 0);
        hash = 59 * hash + (this.isUltraRestricted ? 1 : 0);
        hash = 59 * hash + (this.isBot ? 1 : 0);
        hash = 59 * hash + (this.hasFiles ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final UserImpl other = (UserImpl)obj;

        return Objects.equals(this.id, other.id) &&
            Objects.equals(this.name, other.name) &&
            this.deleted == other.deleted &&
            Objects.equals(this.color, other.color) &&
            Objects.equals(this.realName, other.realName) &&
            Objects.equals(this.timeZone, other.timeZone) &&
            Objects.equals(this.timeZoneId, other.timeZoneId) &&
            Objects.equals(this.timeZoneLabel, other.timeZoneLabel) &&
            Objects.equals(this.profile, other.profile) &&
            this.isAdmin == other.isAdmin &&
            this.isOwner == other.isOwner &&
            this.isPrimaryOwner == other.isPrimaryOwner &&
            this.isRestricted == other.isRestricted &&
            this.isUltraRestricted == other.isUltraRestricted &&
            this.isBot == other.isBot &&
            this.hasFiles == other.hasFiles;
    }
}

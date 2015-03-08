/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.data;

import java.awt.Color;

/**
 *
 * @author bitter_fox
 */
public interface User
{
    UserId id();
    String name();
    boolean deleted();
    // TODO status();
    Color color();
    javafx.scene.paint.Color colorAsFx();
    String realName();
    java.time.ZoneOffset timeZone();
    java.time.ZoneId timeZoneId();
    String timeZoneLabel();
    Profile profile();

    boolean isAdmin();
    boolean isOwner();
    boolean isPrimaryOwner();
    boolean isRestricted();
    boolean isUltraRestricted();
    boolean isBot();
    boolean hasFiles();
}

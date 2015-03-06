/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.util;

import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonValue;

/**
 *
 * @author bitter_fox
 */
public class JsonUtil
{
    public static JsonString castToString(JsonValue jv)
    {
        return (JsonString)jv;
    }

    public static JsonObject castToObject(JsonValue jv)
    {
        return (JsonObject)jv;
    }
}

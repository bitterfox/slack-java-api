/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api;

import java.util.function.BiConsumer;
import java.util.function.Supplier;
import javax.json.JsonObject;

/**
 *
 * @author bitter_fox
 */
interface ApiRequest
{
    <T> T issue(Supplier<? extends T> supplier, BiConsumer<T, JsonObject> consumer);
}
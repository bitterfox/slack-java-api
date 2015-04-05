/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.api.impl.ApiResult;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import javax.json.JsonObject;

/**
 *
 * @author bitter_fox
 */
interface ApiRequest
{
    <T extends ApiResult> T issue(Supplier<? extends T> supplier);
}
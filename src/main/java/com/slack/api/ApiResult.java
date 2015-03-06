/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api;

import javax.json.JsonObject;

/**
 *
 * @author bitter_fox
 */
public abstract class ApiResult
{
    protected abstract void apply(JsonObject result);
}

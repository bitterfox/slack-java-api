/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.api.exception.SlackException;
import java.util.Arrays;

/**
 *
 * @author bitter_fox
 */
abstract class AbstractApiRequest implements ApiRequest
{
    protected void error(String error)
    {
        throw Arrays.stream(Error.values())
            .filter(e -> e.code().equals(error))
            .map(Error::createException)
            .findFirst()
            .orElseGet(() -> new SlackException(error));
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.Slack;
import com.slack.api.exception.InvalidAuthException;
import com.slack.api.exception.NotAuthedException;
import com.slack.api.exception.TokenRevokedException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author bitter_fox
 */
public abstract class AbstractApiTest<T, R>
{
    private Slack slack = Slack.create(
        config ->
        {
            config.token("");
            config.unmarshaller(new MockSlackJsonUnmarshaller());
        });

    private Function<Slack, T> apiFactory;
    private Function<T, R> apiIssuer;
    private T apiClass;

    public AbstractApiTest(Function<Slack, T> apiFactory, Function<T, R> apiIssuer)
    {
        this.apiFactory = apiFactory;
        this.apiIssuer = apiIssuer;

        this.apiClass = apiFactory.apply(this.slack);
        Class<? extends Object> clazz = apiClass.getClass();
        Api mock = new MockApi(slack, apiClass.getClass().getSimpleName());

        Field[] fields = clazz.getDeclaredFields();
        Arrays.stream(fields)
            .filter(f -> f.getType().equals(Api.class))
            .forEach(f ->
            {
                f.setAccessible(true);
                try
                {
                    f.set(apiClass, mock);
                }
                catch (IllegalArgumentException | IllegalAccessException ex)
                {
                    Logger.getLogger(AbstractApiTest.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
    }

    protected R call()
    {
        return apiIssuer.apply(apiClass);
    }
}

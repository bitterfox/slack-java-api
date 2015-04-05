/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.Slack;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mockito.Mockito;

/**
 *
 * @author bitter_fox
 */
public class MockApi extends Api
{
    public MockApi(Slack slack, String clazz)
    {
        super(slack, clazz);
    }

    @Override
    public GetApiRequest get(String method, Consumer<GetBuilder> consumer)
    {
        super.get(method, consumer);

        return new GetApiRequest(this.createMockedHttpURLConnection());
    }

    @Override
    public GetApiRequest get(String method)
    {
        super.get(method);

        return new GetApiRequest(this.createMockedHttpURLConnection());
    }

    private HttpURLConnection createMockedHttpURLConnection()
    {
        String json;

        Optional<Result> resultOpt = this.findAnnotation(Result.class);

        if (resultOpt.isPresent())
        {
            json = resultOpt.get().value();
        }
        else
        {
            json = this.findAnnotation(ErrorResult.class)
                .map(e ->"{\"ok\":false, \"error\":\"" + e.value() + "\"}")
                .orElseThrow(() -> new RuntimeException("No result"));
        }

        InputStream is = new ByteArrayInputStream(json.getBytes(Charset.defaultCharset()));

        HttpURLConnection mock = Mockito.mock(HttpURLConnection.class);
        try
        {
            Mockito.when(mock.getInputStream()).thenReturn(is);
        }
        catch (IOException ex)
        {
            Logger.getLogger(MockApi.class.getName()).log(Level.SEVERE, null, ex);
        }

        return mock;
    }

    private <T extends Annotation> Optional<T> findAnnotation(Class<T> clazz)
    {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        Optional<T> t = Arrays.stream(stackTraceElements)
            .map(ste ->
            {
                try
                {
                    return Class.forName(ste.getClassName()).getMethod(ste.getMethodName());
                }
                catch (Exception e) {}
                return null;
            })
            .filter(Objects::nonNull)
            .map(m -> m.getAnnotation(clazz))
            .filter(Objects::nonNull)
            .findAny();

        return t;
    }
}

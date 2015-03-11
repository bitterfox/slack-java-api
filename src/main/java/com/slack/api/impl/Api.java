/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.Slack;
import com.slack.api.util.URLUtil;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author bitter_fox
 */
class Api
{
    private Slack slack;
    private String clazz;

    public Api(Slack slack, String clazz)
    {
        this.slack = slack;
        this.clazz = clazz;
    }

    public GetApiRequest get(String method)
    {
        return this.get(method, builder -> {});
    }

    public GetApiRequest get(String method, Consumer<Api.GetBuilder> consumer)
    {
        GetBuilder builder = new GetBuilder();

        builder.put("token", slack.getConfigure().token());

        consumer.accept(builder);

        return builder.build(clazz, method);
    }

    private interface Builder<T>
    {
        BiFunction<String, String, String> apiPath = (clazz, method) ->
            new StringBuilder("api/")
                .append(clazz).append(".").append(method)
                .toString();

        T build(String clazz, String method);
    }

    public class GetBuilder implements Builder<GetApiRequest>
    {
        private Map<String, String> queries = new HashMap<>();

        public GetBuilder put(String key, String value)
        {
            // escape
            key = this.escape(key);
            value = this.escape(value);

            queries.put(key, value);

            return this;
        }

        private String escape(String str)
        {
            try
            {
                str = URLEncoder.encode(str, "UTF-8");
                str = str.replace("+", "%20");
            }
            catch (UnsupportedEncodingException ex)
            {
                Logger.getLogger(Api.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }

            return str;
        }

        public GetApiRequest build(String clazz, String method)
        {
            try
            {
                URL apiUrl = URLUtil.url(slack.getConfigure().url(), apiPath.apply(clazz, method), queries);
                HttpURLConnection httpURLConnection = (HttpURLConnection)apiUrl.openConnection();
                return new GetApiRequest(httpURLConnection);
            }
            catch (IOException ex)
            {
                Logger.getLogger(Api.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
        }
    }
}

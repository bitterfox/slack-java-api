/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author bitter_fox
 */
class GetApiRequest extends AbstractApiRequest
{
    private HttpURLConnection httpURLConnection;

    public GetApiRequest(HttpURLConnection httpURLConnection)
    {
        this.httpURLConnection = httpURLConnection;
    }

    @Override
    public <T extends ApiResult> T issue(Supplier<? extends T> supplier)
    {
        try
        {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "GET: " + httpURLConnection.getURL());
            httpURLConnection.connect();

            InputStream is = httpURLConnection.getInputStream();
            JsonReader jr = Json.createReader(is);

            JsonObject rawResult = jr.readObject();
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, rawResult.toString());

            if (rawResult.getBoolean("ok"))
            {
                T result = supplier.get();
                result.apply(rawResult);
                return result;
            }
            else
            {
                this.error(rawResult.getString("error")); // always throw some exception
                return null; // not reached // XXX
            }
        }
        catch (IOException e)
        {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
            throw new RuntimeException(e);
        }
    }
}

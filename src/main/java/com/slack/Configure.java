/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack;

import com.slack.data.json.SlackJsonUnmarshaller;
import com.slack.data.impl.json.SlackJsonUnmarshallerImpl;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bitter_fox
 */
public class Configure
{
    private static URL DEFAULT_URL;
    private static SlackJsonUnmarshaller DEFAULT_UNMARSHALLER = new SlackJsonUnmarshallerImpl();

    static
    {
        try
        {
            DEFAULT_URL = new URL("https://slack.com/");
        }
        catch (MalformedURLException ex)
        {
            Logger.getLogger(Configure.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String token;
    private URL url = DEFAULT_URL;

    public void token(String token)
    {
        this.token = token;
    }

    public String token()
    {
        return token;
    }

    public void url(URL url)
    {
        this.url = url;
    }

    public URL url()
    {
        return url;
    }

    public SlackJsonUnmarshaller unmarshaller()
    {
        return DEFAULT_UNMARSHALLER;
    }
}

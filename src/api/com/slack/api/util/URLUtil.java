/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bitter_fox
 */
public class URLUtil
{
    private URLUtil() {throw new UnsupportedOperationException("No instance for you");}

    public static URL url(URL context, String path, Map<String, String> query) throws MalformedURLException
    {
        StringBuilder sb = new StringBuilder(path);

        sb.append("?");
        query.forEach((k, v) -> sb.append(k).append("=").append(v).append("&"));
        sb.deleteCharAt(sb.length()-1);

        return new URL(context, sb.toString());
    }

    public static URL unsafeCreate(String url)
    {
        try
        {
            return new URL(url);
        }
        catch (MalformedURLException ex)
        {
            Logger.getLogger(URLUtil.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }
}

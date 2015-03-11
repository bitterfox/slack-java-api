/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.Slack;
import com.slack.api.ApiIssuer;
import com.slack.api.Auth;
import com.slack.data.UserId;
import com.slack.data.impl.UserIdImpl;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonObject;

/**
 *
 * @author bitter_fox
 */
class AuthImpl implements Auth
{
    private Api api;

    public AuthImpl(Slack slack)
    {
        api = new Api(slack, "auth");
    }

    @ApiIssuer
    @Override
    public Auth.Test test()
    {
        ApiRequest req = api.get("test");

        return req.issue(AuthImpl.Test::new);
    }

    public static final class Test extends ApiResult implements Auth.Test
    {
        private URL url;
        private String team;
        private String user;
        private String teamId;
        private UserId userId;

        @Override
        public URL url()
        {
            return url;
        }

        @Override
        public String team()
        {
            return team;
        }

        @Override
        public String user()
        {
            return user;
        }

        @Override
        public String teamId()
        {
            return teamId;
        }

        @Override
        public UserId userId()
        {
            return userId;
        }

        @Override
        protected void apply(JsonObject result)
        {
            try
            {
                url = new URL(result.getString("url"));
                team = result.getString("team");
                user = result.getString("user");
                userId = new UserIdImpl(result.getString("user_id"));
                teamId = result.getString("team_id");
            }
            catch (MalformedURLException ex)
            {
                Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

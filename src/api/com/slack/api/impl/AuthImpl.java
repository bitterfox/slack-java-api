/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slack.api.impl;

import com.slack.Slack;
import com.slack.api.ApiIssuer;
import com.slack.api.Auth;
import static com.slack.api.impl.Names.AUTH;
import static com.slack.api.impl.Names.TEAM;
import static com.slack.api.impl.Names.TEAM_ID;
import static com.slack.api.impl.Names.TEST;
import static com.slack.api.impl.Names.URL;
import static com.slack.api.impl.Names.USER;
import static com.slack.api.impl.Names.USER_ID;
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
        api = new Api(slack, AUTH);
    }

    @ApiIssuer
    @Override
    public Auth.Test test()
    {
        ApiRequest req = api.get(TEST);

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
                url = new URL(result.getString(URL));
                team = result.getString(TEAM);
                user = result.getString(USER);
                userId = new UserIdImpl(result.getString(USER_ID));
                teamId = result.getString(TEAM_ID);
            }
            catch (MalformedURLException ex)
            {
                Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

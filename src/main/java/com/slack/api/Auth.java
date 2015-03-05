
package com.slack.api;


import com.slack.Slack;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bitter_fox
 */
public class Auth
{
    private Api api;

    public Auth(Slack slack)
    {
        api = new Api(slack, "auth");
    }

    public Auth.Test test()
    {
        ApiRequest req = api.get("test", b -> {});

        return req.issue(Auth.Test::new ,
            (result, rawResult) ->
            {
                try
                {
                    result.url(new URL(rawResult.getString("url")));
                    result.team(rawResult.getString("team"));
                    result.user(rawResult.getString("user"));
                    result.userId(rawResult.getString("user_id"));
                    result.teamId(rawResult.getString("team_id"));
                }
                catch (MalformedURLException ex)
                {
                    Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
    }

    public static class Test
    {
        private URL url;
        private String team;
        private String user;
        private String teamId;
        private String userId;

        void url(URL url)
        {
            this.url = url;
        }

        public URL url()
        {
            return url;
        }

        void team(String team)
        {
            this.team = team;
        }

        public String team()
        {
            return team;
        }

        void user(String user)
        {
            this.user = user;
        }

        public String user()
        {
            return user;
        }

        void teamId(String teamId)
        {
            this.teamId = teamId;
        }

        public String teamId()
        {
            return teamId;
        }

        void userId(String userId)
        {
            this.userId = userId;
        }

        public String userId()
        {
            return userId;
        }
    }
}

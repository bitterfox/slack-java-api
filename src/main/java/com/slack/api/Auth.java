
package com.slack.api;


import com.slack.Slack;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonObject;

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

        return req.issue(Auth.Test::new);
    }

    public static final class Test extends ApiResult
    {
        private URL url;
        private String team;
        private String user;
        private String teamId;
        private String userId;

        public URL url()
        {
            return url;
        }

        public String team()
        {
            return team;
        }

        public String user()
        {
            return user;
        }

        public String teamId()
        {
            return teamId;
        }

        public String userId()
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
                userId = result.getString("user_id");
                teamId = result.getString("team_id");
            }
            catch (MalformedURLException ex)
            {
                Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

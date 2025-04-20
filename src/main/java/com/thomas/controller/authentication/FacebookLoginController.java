package com.thomas.controller.authentication;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.thomas.constant.Iconstant;
import com.thomas.dao.model.User;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

import java.io.IOException;

public class FacebookLoginController {
    public static String getToken(String code) throws ClientProtocolException, IOException {
        
        String response = Request.Get(Iconstant.FACEBOOK_LINK_GET_TOKEN +
                        "?client_id=" + Iconstant.FACEBOOK_CLIENT_ID +
                        "&redirect_uri=" + Iconstant.FACEBOOK_REDIRECT_URI +
                        "&client_secret=" + Iconstant.FACEBOOK_CLIENT_SECRET +
                        "&code=" + code)
                .execute().returnContent().asString();

        JsonObject json = new Gson().fromJson(response, JsonObject.class);
        return json.get("access_token").getAsString();
    }

    public static User getUserInfo(String accessToken) throws IOException {
        String response = Request.Get(Iconstant.FACEBOOK_LINK_GET_USER_INFO + "&access_token=" + accessToken)
                .execute().returnContent().asString();

        JsonObject json = new Gson().fromJson(response, JsonObject.class);
        User user = new User();
        user.setOauthId(json.get("id").getAsString());
        user.setEmail(json.get("email").getAsString());
        user.setName(json.get("name").getAsString());
        user.setImage(json.get("picture").getAsJsonObject().get("data").getAsJsonObject().get("url").getAsString());
        user.setOauthProvider("facebook");
        return user;
    }
}


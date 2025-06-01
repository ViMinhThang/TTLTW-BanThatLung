package com.thomas.controller.authentication;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.thomas.constant.Iconstant;
import com.thomas.dao.model.User;
import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

public class FacebookLoginController {
    public static String getToken(String code) throws ClientProtocolException, IOException {
        String response = Request.Post(Iconstant.FACEBOOK_LINK_GET_TOKEN)
                .bodyForm(
                        Form.form()
                                .add("client_id", Iconstant.FACEBOOK_CLIENT_ID)
                                .add("client_secret", Iconstant.FACEBOOK_CLIENT_SECRET)
                                .add("redirect_uri", Iconstant.FACEBOOK_REDIRECT_URI)
                                .add("code", code)
                                .build()
                )
                .execute().returnContent().asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static User getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = Iconstant.FACEBOOK_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);

        User user = new User();
        user.setOauthId(jsonObject.get("id").getAsString());
        user.setName(jsonObject.get("name").getAsString());
        if (jsonObject.has("email")) {
            user.setEmail(jsonObject.get("email").getAsString());
        }
        if (jsonObject.has("picture") && jsonObject.get("picture").getAsJsonObject().has("data")) {
            String pictureUrl = jsonObject.get("picture").getAsJsonObject()
                    .get("data").getAsJsonObject()
                    .get("url").getAsString();
            user.setImage(pictureUrl);
        }
        return user;
    }
}
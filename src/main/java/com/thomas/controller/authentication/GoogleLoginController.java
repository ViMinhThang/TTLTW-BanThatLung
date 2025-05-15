package com.thomas.controller.authentication;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.thomas.constant.Iconstant;
import com.thomas.dao.model.User;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

public class GoogleLoginController {
    public static String getToken(String code) throws ClientProtocolException, IOException {
        String response = Request.Post(Iconstant.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(
                        Form.form()
                                .add("client_id", Iconstant.GOOGLE_CLIENT_ID)
                                .add("client_secret", Iconstant.GOOGLE_CLIENT_SECRET)
                                .add("redirect_uri", Iconstant.GOOGLE_REDIRECT_URI)
                                .add("code", code)
                                .add("grant_type", Iconstant.GOOGLE_GRANT_TYPE)
                                .build()
                )
                .execute().returnContent().asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static User getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = Iconstant.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);

        User user = new User();
        user.setOauthId(jsonObject.get("id").getAsString());
        user.setEmail(jsonObject.get("email").getAsString());
        user.setName(jsonObject.get("name").getAsString());
        user.setImage(jsonObject.get("picture").getAsString());
        // Các trường khác có thể thêm tùy theo response từ Google
        return user;
    }


}

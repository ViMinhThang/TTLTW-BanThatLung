package com.thomas.constant;

public interface Iconstant {
    String FACEBOOK_LINK_GET_TOKEN = "https://graph.facebook.com/v18.0/oauth/access_token";
    String FACEBOOK_CLIENT_ID = "1432473621419377";
    String FACEBOOK_REDIRECT_URI = "http://localhost:8080/login-facebook";
    String FACEBOOK_CLIENT_SECRET = "f6071c829bae436cdc05a12e260dcf86";
    String FACEBOOK_LINK_GET_USER_INFO = "https://graph.facebook.com/me?fields=id,name,email,picture";
    String GOOGLE_LINK_GET_TOKEN = "https://oauth2.googleapis.com/token";
    String GOOGLE_CLIENT_ID = "1015777247670-u0bbabl38u6h7n4n232lkaajl38vj10k.apps.googleusercontent.com";
    String GOOGLE_CLIENT_SECRET = "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";
    String GOOGLE_REDIRECT_URI = "http://localhost:8080/login-google";
    String GOOGLE_GRANT_TYPE = "authorization_code";
    String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v2/userinfo?access_token=";
}

package com.thomas.dao;

import com.thomas.dao.db.JDBIConnect;
import com.thomas.dao.model.Token;

public class TokenDao {
    public TokenDao() {

    }

    public Token getTokenByUserId(int userId) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "select * from token where userId=:userId";
            return h.createQuery(sql).bind("userId", userId).mapToBean(Token.class).findFirst().orElse(null);
        });
    }

    public Boolean saveToken(Token token) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "INSERT INTO tokens(tokenId,userId,expiresAt) values(:tokenId,:userId,:expiresAt)";
            return h.createUpdate(sql)
                    .bind("tokenId", token.getTokenId())
                    .bind("userId", token.getUserId())
                    .bind("expiresAt", token.getExpiryDate())
                    .execute() > 0;
        });
    }

    public boolean deleteToken(int userId) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "delete from tokens where userId=:userId";
            return h.createUpdate(sql).bind("userId", userId).execute() > 0;
        });
    }

    public int verifyToken(String token) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "select userId from tokens where tokenId=:token AND NOW() < expiresAt";
            return h.createQuery(sql).bind("token", token).mapTo(Integer.class).findFirst().orElse(0);
        });
    }
}

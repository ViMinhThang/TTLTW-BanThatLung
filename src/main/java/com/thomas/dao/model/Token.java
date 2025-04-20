package com.thomas.dao.model;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Token implements Serializable {
    private String tokenId;
    private int userId;
    private LocalDateTime expiryDate;

    public Token() {

    }

    public Token(String tokenId, int userId, LocalDateTime expiryDate) {
        this.tokenId = tokenId;
        this.userId = userId;
        this.expiryDate = expiryDate;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }
}

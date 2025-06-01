package com.thomas.dao.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

public class WardResponse {
    public int code;
    public String message;
    public List<Ward> data;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Ward {
        public String WardCode;
        public String WardName;
    }
}

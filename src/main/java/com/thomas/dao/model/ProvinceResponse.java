package com.thomas.dao.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProvinceResponse {
    public int code;
    public String message;
    public List<Province> data;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Province {
        public int ProvinceID;
        public String ProvinceName;
    }
}

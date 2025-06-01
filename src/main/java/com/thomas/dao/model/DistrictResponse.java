package com.thomas.dao.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

public class DistrictResponse {
    public int code;
    public String message;
    public List<District> data;


    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class District {
        public int DistrictID;
        public String DistrictName;
    }
}

package com.thomas.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thomas.dao.model.DistrictResponse;
import com.thomas.dao.model.ProvinceResponse;
import com.thomas.dao.model.WardResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class LocationService {
    private final ObjectMapper mapper = new ObjectMapper();
    private static final String TOKEN = "332d8f6e-17c7-11f0-ae25-deeae188dbc1";

    public String findProvinceName(int provinceId) throws IOException {
        String json = callGHNApi(
                "https://online-gateway.ghn.vn/shiip/public-api/master-data/province", "GET", null
        );
        ProvinceResponse response = mapper.readValue(json, ProvinceResponse.class);
        for (ProvinceResponse.Province p : response.data) {
            if (p.ProvinceID == provinceId) return p.ProvinceName;
        }
        return null;
    }

    public String findDistrictName(int provinceId, int districtId) throws IOException {
        Map<String, Object> body = new HashMap<>();
        body.put("province_id", provinceId);

        String bodyJson = mapper.writeValueAsString(body);

        String json = callGHNApi(
                "https://online-gateway.ghn.vn/shiip/public-api/master-data/district",
                "POST",
                bodyJson
        );
        DistrictResponse response = mapper.readValue(json, DistrictResponse.class);
        for (DistrictResponse.District d : response.data) {
            if (d.DistrictID == districtId) return d.DistrictName;
        }
        return null;
    }


    public String findWardName(int districtId, String wardCode) throws IOException {
        Map<String, Object> body = new HashMap<>();
        body.put("district_id", districtId);

        String bodyJson = mapper.writeValueAsString(body);
        System.out.println("Request JSON for ward: " + bodyJson);

        String json = callGHNApi(
                "https://online-gateway.ghn.vn/shiip/public-api/master-data/ward",
                "POST",
                bodyJson
        );
        WardResponse response = mapper.readValue(json, WardResponse.class);
        for (WardResponse.Ward w : response.data) {
            if (w.WardCode.equals(wardCode)) return w.WardName;
        }
        return null;
    }


    private String callGHNApi(String urlStr, String method, String bodyJson) throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL(urlStr).openConnection();
        con.setRequestMethod(method);
        con.setRequestProperty("Token", TOKEN);

        if ("POST".equalsIgnoreCase(method)) {
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);
            con.getOutputStream().write(bodyJson.getBytes(StandardCharsets.UTF_8));
        }

        return readResponse(con);
    }

    private String readResponse(HttpURLConnection con) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            content.append(line);
        }
        in.close();
        return content.toString();
    }
}

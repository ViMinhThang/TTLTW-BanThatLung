package com.thomas.controller.CartRoutes;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@WebServlet("/location")
public class LocationController extends HttpServlet {
//    5730895
    private static final String TOKEN = "332d8f6e-17c7-11f0-ae25-deeae188dbc1";

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        String jsonResponse = "";

        switch (action) {
            case "province":
                jsonResponse = getProvinces();
                break;
            case "district":
                jsonResponse = getDistricts(req.getParameter("province_id"));
                break;
            case "ward":
                jsonResponse = getWards(req.getParameter("district_id"));
                break;
            default:
                jsonResponse = "{\"error\":\"Invalid action\"}";
        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonResponse);
    }

    private String getProvinces() throws IOException {
        return callGHNApi("https://online-gateway.ghn.vn/shiip/public-api/master-data/province", "GET", null);
    }

    private String getDistricts(String provinceId) throws IOException {
        String body = "{\"province_id\":" + provinceId + "}";
        return callGHNApi("https://online-gateway.ghn.vn/shiip/public-api/master-data/district", "POST", body);
    }

    private String getWards(String districtId) throws IOException {
        return callGHNApi("https://online-gateway.ghn.vn/shiip/public-api/master-data/ward?district_id=" + districtId, "GET", null);
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

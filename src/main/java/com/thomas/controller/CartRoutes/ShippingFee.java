package com.thomas.controller.CartRoutes;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.*;
import java.net.*;

import org.json.JSONObject;

import java.util.stream.Collectors;

@WebServlet("/shipping-fee")
public class ShippingFee extends HttpServlet {
    private static final String TOKEN = "332d8f6e-17c7-11f0-ae25-deeae188dbc1";
    private static final String SHOP_ID = "5730895";
    private static final int FROM_DISTRICT_ID = 1463; //Thu Duc, Linh Trung 21808

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        JSONObject json = new JSONObject(body);
        int toDistrictId = json.getInt("to_district_id");
        String toWardCode = json.getString("to_ward_code");

        HttpURLConnection conn = (HttpURLConnection) new URL("https://online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/fee").openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Token", TOKEN);
        conn.setRequestProperty("ShopId", SHOP_ID);
        conn.setDoOutput(true);

        JSONObject payload = new JSONObject();
        payload.put("from_district_id", FROM_DISTRICT_ID);
        payload.put("to_district_id", toDistrictId);
        payload.put("to_ward_code", toWardCode);
        payload.put("service_type_id", 2);
        payload.put("height", 10);
        payload.put("length", 20);
        payload.put("weight", 500);
        payload.put("width", 20);

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = payload.toString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        String result = new BufferedReader(new InputStreamReader(conn.getInputStream())).lines().collect(Collectors.joining(" "));
        response.setContentType("application/json");
        response.getWriter().write(result);
    }
}
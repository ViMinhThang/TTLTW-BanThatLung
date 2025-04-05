package com.thomas.controller;

import com.thomas.dao.model.*;
import com.thomas.services.*;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;
import java.util.Base64;
import java.util.UUID;
import java.text.DecimalFormat;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//MoMo test account
//NGUYEN VAN A
//9704 0000 0000 0018
//03/07
//OTP

@WebServlet(name = "OrderController", value = "/Order")
public class OrderController extends HttpServlet {
    // Các tham số MoMo API
    private static final String MOMO_ENDPOINT = "https://test-payment.momo.vn/v2/gateway/api/create";
    private static final String PARTNER_CODE = "MOMOBKUN20180529";
    private static final String ACCESS_KEY = "klm05TvNBzhg7h7j";
    private static final String SECRET_KEY = "at67qH6mk8w5Y1nAyMoYKMWACiEi2bsa";

    UploadAddressService uploadAddressService = new UploadAddressService();
    UploadOrderService uploadOrderService = new UploadOrderService();
    UploadPaymentMethod uploadPaymentMethod = new UploadPaymentMethod();
    UploadOrderDetailService uploadOrderDetailService = new UploadOrderDetailService();
    EmailService emailService = new EmailService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Map<String, CartItem> cart = (Map<String, CartItem>) session.getAttribute("cart");
        Coupon cp = (Coupon) session.getAttribute("appliedCoupon");
        User user = (User) session.getAttribute("auth");
        int userId = user.getId();
        String paymentMethod = request.getParameter("paymentMethod");
        Address address = uploadAddressService.getAddressByUserId(userId);
        int paymentMethodId = uploadPaymentMethod.getPaymentMethodId(paymentMethod);
        double totalPrice = 0, shippingCost = 0;
        for (CartItem cartItem : cart.values()) {
            totalPrice += cartItem.getPrice() * cartItem.getQuantity();
            shippingCost = cartItem.getQuantity() * 15.000;
        }
        double discountRate = cp == null ? 0 : cp.getDiscountRate();
        double discountAmount = totalPrice * (discountRate / 100);
        double grandTotal = totalPrice + shippingCost + discountAmount;
        if (paymentMethod.isEmpty()) {
            request.setAttribute("errorMessage", "Vui lòng chọn phương thức thanh toán");
            request.getRequestDispatcher("/frontend/checkoutPage/checkout.jsp").forward(request, response);
            return;
        } else if (paymentMethod.equals("MoMo")) {
            try {
                handleMomoPayment(request, response);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } catch (InvalidKeyException e) {
                throw new RuntimeException(e);
            }
//            if (uploadOrderService.createOrder(userId, paymentMethodId, address.getId(), LocalDate.now(), grandTotal, "Đang xử lý", 0, user.getId())) {
//                Order order = uploadOrderService.getLatestOrder();
//                for (CartItem cartItem : cart.values()) {
//                    uploadOrderDetailService.createOrderDetail(order.getId(), cartItem.getPrice(), cartItem.getBelt().getId(), cartItem.getQuantity(), cartItem.getVariant().getId());
//                }
//                String subject = "Thông báo đơn hàng";
//                String content = "Đơn hàng của bạn đã được đặt thành công. Tổng giá trị đơn hàng là: " + totalPrice + " VNĐ.\n" +
//                        "Chi tiết đơn hàng:\n" + cart.toString() + "\n" + "cảm ơn bạn đã mua hàng tại cửa hàng của chúng tôi.\n";
//                emailService.sendEmail(user.getEmail(), subject, content);
//                response.sendRedirect("/verify?messageRedirect=orderDetailSuccess");
//            }
        }
    }

    private void handleMomoPayment(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, NoSuchAlgorithmException, InvalidKeyException {
        String address = "aaaaa";
        String phone = "123";
        String customerName = "abc";
        DecimalFormat df = new DecimalFormat("#");
        String amount = df.format(Float.parseFloat("100000")); // Số tiền thanh toán
        String extraData = "MoMo";

        String orderId = String.valueOf(System.currentTimeMillis());
        String requestId = String.valueOf(System.currentTimeMillis());
        String redirectUrl = "http://localhost:8080/momo-return";
        String separator = redirectUrl.contains("?") ? "&" : "?";
        redirectUrl += separator + "name=" + customerName + "&address=" + address + "&phone=" + phone
                + "&amount=" + amount + "&totalAmount=" + amount;
        String IpnUrl = "http://localhost:8080/momo-notify";

        System.out.println("Redirect URL: " + redirectUrl);

        // Tạo signature
        String rawSignature = "accessKey=" + ACCESS_KEY +
                "&amount=" + amount +
                "&extraData=" + extraData +
                "&ipnUrl=" + IpnUrl +
                "&orderId=" + orderId +
                "&orderInfo=" + "MomoPayment" + customerName +
                "&partnerCode=" + PARTNER_CODE +
                "&redirectUrl=" + redirectUrl +
                "&requestId=" + requestId +
                "&requestType=payWithATM";

        String signature = "";
        try {
            signature = generateSignature(SECRET_KEY, rawSignature);
        } catch (SignatureException e) {
            e.printStackTrace();
            request.setAttribute("msg", "Lỗi khi tạo chữ kí");
            request.getRequestDispatcher("/Order").forward(request, response);
            return;
        }

        JSONObject json = new JSONObject();
        json.put("partnerCode", PARTNER_CODE);
        json.put("accessKey", ACCESS_KEY);
        json.put("requestId", requestId);
        json.put("amount", amount);
        json.put("orderId", orderId);
        json.put("orderInfo", "MomoPayment" + customerName);
        json.put("redirectUrl", redirectUrl);
        json.put("ipnUrl", IpnUrl);
        json.put("requestType", "payWithATM");
        json.put("extraData", extraData);
        json.put("signature", signature);

        try {
            String responseBody = sendPostRequest(MOMO_ENDPOINT, json.toString()); // Gửi request HTTP POST

            // Lấy URL thanh toán từ response
            JSONObject jsonResponse = new JSONObject(responseBody);
            String payUrl = jsonResponse.optString("payUrl", null);
            if (payUrl == null || payUrl.isEmpty()) {
                System.out.println("Không nhận được URL thanh toán từ MoMo.");
                request.setAttribute("msg", "Không nhận được URL thanh toán từ MoMo.");
                request.getRequestDispatcher("/Checkout").forward(request, response);
                return;
            }

            int resultCode = jsonResponse.optInt("resultCode", -1);
            if (resultCode != 0) {
                System.out.println("Lỗi thanh toán MoMo");
                String errorMessage = jsonResponse.optString("message", "Lỗi không xác định");
                request.setAttribute("msg", "Lỗi thanh toán MoMo: " + errorMessage);
                request.getRequestDispatcher("/Checkout").forward(request, response);
                return;
            }

            response.sendRedirect(payUrl); // Chuyển hướng người dùng đến trang thanh toán

        } catch (IOException e) {
            e.printStackTrace();
            request.setAttribute("msg", "Lỗi khi gửi yêu cầu thanh toán đến MoMo.");
            request.getRequestDispatcher("/Checkout").forward(request, response);
        }
    }

    // Tạo HMAC-SHA256 signature
    private String generateSignature(String secretKey, String rawSignature) throws SignatureException {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKeySpec);
            byte[] rawHmac = mac.doFinal(rawSignature.getBytes());
            return bytesToHex(rawHmac);
        } catch (Exception e) {
            throw new SignatureException("Lỗi khi tạo signature", e);
        }
    }

    // Chuyển byte array thành hex string
    private String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xFF & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

//    public static String hmacSHA256(String data, String secretKey) throws NoSuchAlgorithmException, InvalidKeyException {
//        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
//        SecretKeySpec secret_key = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
//        sha256_HMAC.init(secret_key);
//        byte[] hash = sha256_HMAC.doFinal(data.getBytes(StandardCharsets.UTF_8));
//        return Base64.getEncoder().encodeToString(hash);
//    }

    private String sendPostRequest(String url, String jsonData) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(jsonData, ContentType.APPLICATION_JSON);
        httpPost.setEntity(entity);
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        String response = EntityUtils.toString(httpResponse.getEntity());
        httpClient.close();
        return response;
    }
}


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
import java.time.LocalDate;
import java.util.*;
import java.text.DecimalFormat;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Map;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.Session;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//MoMo test account
//9704 0000 0000 0018
//NGUYEN VAN A
//03/07
//OTP

//VNPay test account
//NCB
//9704198526191432198
//NGUYEN VAN A
//07/15
//123456

@WebServlet(name = "OrderController", value = "/Order")
public class OrderController extends HttpServlet {
    // Các tham số MoMo API
    private static final String MOMO_ENDPOINT = "https://test-payment.momo.vn/v2/gateway/api/create";
    private static final String PARTNER_CODE = "MOMOBKUN20180529";
    private static final String ACCESS_KEY = "klm05TvNBzhg7h7j";
    private static final String SECRET_KEY = "at67qH6mk8w5Y1nAyMoYKMWACiEi2bsa";

    // Các tham số VNPay API
    public static final String VNP_PAY_URL = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
    public static final String VNP_RETURN_URL = "http://localhost:8080/checkout-return";
    public static final String VNP_TMN_CODE = "BQSZL9KZ";
    public static final String VNP_SECRET_KEY = "T1J0EC8NQ7DYM9HDIR5MCGDD2LSK9Z44";
    public static final String VNP_API_URL = "https://sandbox.vnpayment.vn/merchant_webapi/api/transaction";

    UploadAddressService uploadAddressService = new UploadAddressService();
    UploadOrderService uploadOrderService = new UploadOrderService();
    UploadPaymentMethod uploadPaymentMethod = new UploadPaymentMethod();
    UploadOrderDetailService uploadOrderDetailService = new UploadOrderDetailService();
    CartService cartService = new CartService();
    EmailService emailService = new EmailService();
    ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Coupon cp = (Coupon) session.getAttribute("appliedCoupon");
        User user = (User) session.getAttribute("auth");
        int userId = user.getId();
        List<CartItem> cartItemList = cartService.getCart(userId);
        String paymentMethod = request.getParameter("paymentMethod");
        List<Address> address = uploadAddressService.getAddressList(userId);
        Address userAddress = null;
        for (Address addr : address) {
            if (addr.getIsUse() == 1) {
                userAddress = addr;
            }
        }
        int paymentMethodId = uploadPaymentMethod.getPaymentMethodId(paymentMethod);
        double totalPrice = 0, shippingCost = 0;
        for (CartItem cartItem : cartItemList) {
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
        } else if (paymentMethod.equals("COD")) {
            createOrder(request, response, userId, user.getEmail());
            request.setAttribute("paymentMethod", "COD");
            request.setAttribute("orderId", uploadOrderService.getLatestOrder().getId());
            request.setAttribute("total", grandTotal);
            request.setAttribute("userName", user.getName());
            request.setAttribute("phoneNumber", user.getPhoneNumber());
            request.setAttribute("userAddress", userAddress);
            request.setAttribute("status", "Đang xử lý");
            request.setAttribute("message", "success");
            request.getRequestDispatcher("/frontend/cartPage/checkoutPage/checkout-return.jsp").forward(request, response);
        } else if (paymentMethod.equals("MoMo")) {
            try {
//                request.setAttribute("paymentMethod", "MoMo");
                handleMomoPayment(request, response);
            } catch (NoSuchAlgorithmException | InvalidKeyException e) {
                throw new RuntimeException(e);
            }
        } else if (paymentMethod.equals("VNPay")) {
//            request.setAttribute("paymentMethod", "VNPay");
            handleVNPayPayment(request, response);
        }
    }

    private void createOrder(HttpServletRequest request, HttpServletResponse response, int userId, String userEmail) throws IOException {
        HttpSession session = request.getSession();
        List<CartItem> cartItemList = cartService.getCart(userId);
        Coupon cp = (Coupon) session.getAttribute("appliedCoupon");
        String paymentMethod = request.getParameter("paymentMethod");
        List<Address> address = uploadAddressService.getAddressList(userId);
        Address userAddress = null;
        for (Address addr : address) {
            if (addr.getIsUse() == 1) {
                userAddress = addr;
            }
        }
        int paymentMethodId = uploadPaymentMethod.getPaymentMethodId(paymentMethod);
        double totalPrice = 0, shippingCost = 0;
        for (CartItem cartItem : cartItemList) {
            totalPrice += cartItem.getPrice() * cartItem.getQuantity();
            shippingCost = cartItem.getQuantity() * 15.000;
        }
        double discountRate = cp == null ? 0 : cp.getDiscountRate();
        double discountAmount = totalPrice * (discountRate / 100);
        double grandTotal = totalPrice + shippingCost + discountAmount;

        if (uploadOrderService.createOrder(userId, paymentMethodId, userAddress.getId(), LocalDate.now(), grandTotal, "Đang xử lý", 0, userId)) {
            Order order = uploadOrderService.getLatestOrder();
            for (CartItem cartItem : cartItemList) {
                for (CartItem item : cartItemList) {
                    item.setBelt(productService.find(item.getBeltId()).get(0));
                    item.setVariant(productService.findVariant(item.getBeltId(), item.getVariantId(), null, null));
                }
                uploadOrderDetailService.createOrderDetail(order.getId(), cartItem.getPrice(), cartItem.getBelt().getId(), cartItem.getQuantity(), cartItem.getVariant().getId());
            }
        }
        String subject = "Thông báo đơn hàng";
        String content = "Đơn hàng của bạn đã được đặt thành công. Tổng giá trị đơn hàng là: " + totalPrice + " VNĐ.\n" +
                "Chi tiết đơn hàng:\n" + cartItemList.toString() + "\n" + "cảm ơn bạn đã mua hàng tại cửa hàng của chúng tôi.\n";
        emailService.sendEmail(userEmail, subject, content);
    }

    private void handleMomoPayment(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, NoSuchAlgorithmException, InvalidKeyException {
        User user = (User) request.getSession().getAttribute("auth");

        String address = request.getParameter("address");
        String customerName = user.getName();
        DecimalFormat df = new DecimalFormat("#");
        String amountStr = request.getParameter("grandTotal").replace(".", "");
        ;
        String amount = df.format(Float.parseFloat(amountStr)); // Số tiền thanh toán
        String extraData = "Thanh toan don hang";

        String orderId = String.valueOf(System.currentTimeMillis());
        String requestId = String.valueOf(System.currentTimeMillis());
        String redirectUrl = "http://localhost:8080/checkout-return";
        String separator = redirectUrl.contains("?") ? "&" : "?";
        redirectUrl += separator + "&address=" + address + "&phone="
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
//            System.out.println("Response from MoMo: " + responseBody);

            // Lấy URL thanh toán từ response
            JSONObject jsonResponse = new JSONObject(responseBody);
            String payUrl = jsonResponse.optString("payUrl", null);
            if (payUrl == null || payUrl.isEmpty()) {
                request.setAttribute("msg", "Không nhận được URL thanh toán từ MoMo.");
                request.getRequestDispatcher("/Checkout").forward(request, response);
                return;
            }

            int resultCode = jsonResponse.optInt("resultCode", -1);
            if (resultCode != 0) {
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

    private void handleVNPayPayment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String orderType = "other";
        String amountStr = req.getParameter("grandTotal").replace(".", "");
        ;
        long amount = Integer.parseInt(amountStr) * 100L;
        String bankCode = req.getParameter("bankCode");

        String vnp_TxnRef = getRandomNumber(8);
        String vnp_IpAddr = getIpAddress(req);

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", VNP_TMN_CODE);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");

        if (bankCode != null && !bankCode.isEmpty()) {
            vnp_Params.put("vnp_BankCode", bankCode);
        }
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);

        String locate = req.getParameter("language");
        if (locate != null && !locate.isEmpty()) {
            vnp_Params.put("vnp_Locale", locate);
        } else {
            vnp_Params.put("vnp_Locale", "vn");
        }
        vnp_Params.put("vnp_ReturnUrl", VNP_RETURN_URL);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = hmacSHA512(VNP_SECRET_KEY, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = VNP_PAY_URL + "?" + queryUrl;
//        com.google.gson.JsonObject job = new JsonObject();
//        job.addProperty("code", "00");
//        job.addProperty("message", "success");
//        job.addProperty("data", paymentUrl);
        resp.sendRedirect(paymentUrl);
    }

    //Util for VNPAY
    public static String md5(String message) {
        String digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(message.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                sb.append(String.format("%02x", b & 0xff));
            }
            digest = sb.toString();
        } catch (UnsupportedEncodingException ex) {
            digest = "";
        } catch (NoSuchAlgorithmException ex) {
            digest = "";
        }
        return digest;
    }

    public static String Sha256(String message) {
        String digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(message.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                sb.append(String.format("%02x", b & 0xff));
            }
            digest = sb.toString();
        } catch (UnsupportedEncodingException ex) {
            digest = "";
        } catch (NoSuchAlgorithmException ex) {
            digest = "";
        }
        return digest;
    }

    public static String hashAllFields(Map fields) {
        List fieldNames = new ArrayList(fields.keySet());
        Collections.sort(fieldNames);
        StringBuilder sb = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) fields.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                sb.append(fieldName);
                sb.append("=");
                sb.append(fieldValue);
            }
            if (itr.hasNext()) {
                sb.append("&");
            }
        }
        return hmacSHA512(VNP_SECRET_KEY, sb.toString());
    }

    public static String hmacSHA512(final String key, final String data) {
        try {

            if (key == null || data == null) {
                throw new NullPointerException();
            }
            final Mac hmac512 = Mac.getInstance("HmacSHA512");
            byte[] hmacKeyBytes = key.getBytes();
            final SecretKeySpec secretKey = new SecretKeySpec(hmacKeyBytes, "HmacSHA512");
            hmac512.init(secretKey);
            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
            byte[] result = hmac512.doFinal(dataBytes);
            StringBuilder sb = new StringBuilder(2 * result.length);
            for (byte b : result) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();

        } catch (Exception ex) {
            return "";
        }
    }

    public static String getIpAddress(HttpServletRequest request) {
        String ipAdress;
        try {
            ipAdress = request.getHeader("X-FORWARDED-FOR");
            if (ipAdress == null) {
                ipAdress = request.getRemoteAddr();
            }
        } catch (Exception e) {
            ipAdress = "Invalid IP:" + e.getMessage();
        }
        return ipAdress;
    }

    public static String getRandomNumber(int len) {
        Random rnd = new Random();
        String chars = "0123456789";
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }
}


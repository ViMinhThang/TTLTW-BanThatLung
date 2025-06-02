package com.thomas.controller.CartRoutes;

import com.thomas.controller.OrderController;
import com.thomas.dao.model.*;
import com.thomas.services.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/checkout-return")
public class CheckoutReturn extends HttpServlet {
    UploadAddressService uploadAddressService = new UploadAddressService();
    UploadOrderService uploadOrderService = new UploadOrderService();
    UploadPaymentMethod uploadPaymentMethod = new UploadPaymentMethod();
    UploadOrderDetailService uploadOrderDetailService = new UploadOrderDetailService();
    CartService cartService = new CartService();
    EmailService emailService = new EmailService();
    ProductService productService = new ProductService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("auth");
        boolean isMoMo = request.getParameter("resultCode") != null; // MoMo có resultCode
        boolean isVNPay = request.getParameter("vnp_TransactionStatus") != null; // VNPay có vnp_TransactionStatus

        Map fields = new HashMap();
        for (Enumeration params = request.getParameterNames(); params.hasMoreElements(); ) {
            String fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
            String fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                fields.put(fieldName, fieldValue);
            }
        }

        String vnp_SecureHash = request.getParameter("vnp_SecureHash");
        if (fields.containsKey("vnp_SecureHashType")) {
            fields.remove("vnp_SecureHashType");
        }
        if (fields.containsKey("vnp_SecureHash")) {
            fields.remove("vnp_SecureHash");
        }
        String signValue = OrderController.hashAllFields(fields);

        String total = request.getParameter("total");
        String resultCode = request.getParameter("resultCode");
        String status = "Đang xử lý";

        if (isMoMo && "0".equals(resultCode)) {
            // Thanh toán MoMo thành công
            request.setAttribute("paymentMethod", "MoMo");
            request.setAttribute("orderId", request.getParameter("orderId"));
            request.setAttribute("total", total);
            request.setAttribute("status", status);
            request.setAttribute("message", "success");
            createOrder(request, response, "MoMo");
        } else if (isVNPay && signValue.equals(vnp_SecureHash)) {
            if ("00".equals(request.getParameter("vnp_TransactionStatus"))) {
                request.setAttribute("paymentMethod", "VNPay");
                request.setAttribute("orderId", request.getParameter("vnp_TxnRef"));
                request.setAttribute("total", request.getParameter("vnp_Amount"));
                request.setAttribute("status", status);
                request.setAttribute("message", "success");
                createOrder(request, response, "VNPay");
            } else {
                request.setAttribute("status", "Lỗi");
                request.setAttribute("message", "failed");
            }
        } else {
            request.setAttribute("status", "Lỗi");
            request.setAttribute("message", "failed");
        }
        request.setAttribute("userName", user.getName());
        request.getRequestDispatcher("/frontend/cartPage/checkoutPage/checkout-return.jsp").forward(request, response);
    }

    private void createOrder(HttpServletRequest request, HttpServletResponse response, String paymentMethod) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) request.getSession().getAttribute("auth");
        List<CartItem> cartItemList = cartService.getCart(user.getId());
        Coupon cp = (Coupon) session.getAttribute("appliedCoupon");
        List<Address> address = uploadAddressService.getAddressList(user.getId());
        Address userAddress = null;
        for (Address addr : address) {
            if (addr.getIsUse() == 1) {
                userAddress = addr;
            }
        }
        request.setAttribute("userAddress", userAddress);
        request.setAttribute("userName", user.getName());
        request.setAttribute("phoneNumber", user.getPhoneNumber());
        int paymentMethodId = uploadPaymentMethod.getPaymentMethodId(paymentMethod);
        double totalPrice = 0, shippingCost = 0;
        for (CartItem cartItem : cartItemList) {
            totalPrice += cartItem.getPrice() * cartItem.getQuantity();
            shippingCost = cartItem.getQuantity() * 15.000;
        }
        double discountRate = cp == null ? 0 : cp.getDiscountRate();
        double discountAmount = totalPrice * (discountRate / 100);
        double grandTotal = totalPrice + shippingCost + discountAmount;
        request.setAttribute("total", grandTotal);
        if (uploadOrderService.createOrder(user.getId(), paymentMethodId, userAddress.getId(), LocalDate.now(), grandTotal, "Đang xử lý", 0, user.getId())) {

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
        emailService.sendEmail(user.getEmail(), subject, content);
    }
}

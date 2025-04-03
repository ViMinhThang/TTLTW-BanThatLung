package com.thomas.services;

import com.thomas.dao.OrderDao;
import com.thomas.dao.model.Order;

import java.time.LocalDate;
import java.util.List;

public class UploadOrderService {
    OrderDao orderDao;

    public UploadOrderService() {
        orderDao = new OrderDao();
    }

    public List<Order> getAllOrders() {
        return orderDao.getAllOrder();
    }

    public void setUserName(Order order) {
        if (order != null) {
            String orderName = orderDao.getUserName(order.getId());
            order.setUserName(orderName);
        }
    }

    public void setPaymentName(Order order) {
        if (order != null) {
            String paymentName = orderDao.getPaymentName(order.getId());
            order.setPaymentMethod(paymentName);
        }
    }

    public void setAddressName(Order order) {
        if (order != null) {
            String addressName = orderDao.getAddress(order.getId());
            order.setAddresse(addressName);
        }
    }

    public Order getOrderById(int id) {
        return orderDao.getOrderById(id);
    }

    public boolean deteleOrder(int orderId) {
        return orderDao.deleteOrderById(orderId);
    }

    public boolean createOrder(int userId, int paymentMethodId, int addressId, LocalDate orderDate, double grandTotal, String state, int isDeleted, int ediorId) {
        Order order = new Order();
        order.setUserId(userId);
        order.setPaymentMethodId(paymentMethodId);
        order.setAddressesId(addressId);
        order.setOrderDate(orderDate);
        order.setOrderTotal(grandTotal);
        order.setOrderStatus(state);
        order.setIsDeleted(isDeleted);
        return orderDao.createOrderAndLog(order, ediorId);
    }

    public Order getLatestOrder() {
        return orderDao.getOrderLatestOrder();
    }

    public int getOrderCount() {
        return getAllOrders().size();
    }

    public double getTotalOrderMoney() {
        List<Order> allOrders = getAllOrders();
        double total = 0;
        for (Order order : allOrders) {
            if (order.getOrderStatus().equals("Đã giao")) {
                total += order.getOrderTotal();
            }
        }
        return total;
    }

    public List<Order> getAllOrdersByUserId(int userId) {
        return orderDao.getAllOrderByUserId(userId);
    }

    public boolean updateOrder(Order order, int userId) {
        return orderDao.updateOrderAndLog(order, userId);
    }
}

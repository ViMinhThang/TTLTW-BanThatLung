package com.thomas.services;

import com.thomas.dao.AddressDao;
import com.thomas.dao.OrderDao;
import com.thomas.dao.model.Address;
import com.thomas.dao.model.Order;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class UploadOrderService {
    OrderDao orderDao;
    LocationService locationService;
    AddressDao addressDao;

    public UploadOrderService() {
        orderDao = new OrderDao();
        addressDao = new AddressDao();
        locationService = new LocationService();
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

    public void setAddressName(Order order) throws IOException {
        if (order != null) {
            String addressId = orderDao.getAddress(order.getId());
            Address address = addressDao.findOne(Integer.parseInt(addressId));
            address.setProvinceName(getProvinceName(address.getProvinceId()));
            address.setDistrictName(getDistrictName(address.getProvinceId(), address.getDistrictId()));
            address.setWardName(getWardName(address.getDistrictId(), address.getWardId()));
            order.setAddresse(address.getProvinceName() + " " + address.getDistrictName() + " " + address.getWardName());
        }
    }

    public String getProvinceName(int provinceId) throws IOException {
        return locationService.findProvinceName(provinceId);
    }

    public String getDistrictName(int provinceId, int districtId) throws IOException {
        return locationService.findDistrictName(provinceId, districtId);
    }

    public String getWardName(int districtId, String wardCode) throws IOException {
        return locationService.findWardName(districtId, wardCode);
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

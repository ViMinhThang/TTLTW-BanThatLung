package com.thomas.dao;

import com.thomas.dao.db.JDBIConnect;
import com.thomas.dao.model.Order;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDao implements UsageInterface {
    public List<Order> getAllOrder() {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "select * from orders";
            return h.createQuery(sql).mapToBean(Order.class).list();
        });
    }

    public Order getOrderById(int id) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "select * from orders where id = :id";
            return h.createQuery(sql).bind("id", id).mapToBean(Order.class).findFirst().orElse(null);
        });
    }

    public String getUserName(int id) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "SELECT u.name " + "FROM orders o " + "JOIN users u ON u.id = o.userId " + "WHERE o.id = :id";
            return h.createQuery(sql).bind("id", id).mapTo(String.class).findFirst().orElse(null);
        });
    }

    public String getPaymentName(int id) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "SELECT pm.name " + "FROM orders o " + "JOIN paymentMethods pm ON pm.id = o.paymentMethodId " + "WHERE o.id = :id";
            return h.createQuery(sql).bind("id", id).mapTo(String.class).findFirst().orElse(null);
        });
    }

    public String getBeltName(int id) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "SELECT b.name " + "FROM orders o " + "JOIN belts b ON b.id = o.beltId " + "WHERE o.id = :id";
            return h.createQuery(sql).bind("id", id).mapTo(String.class).findFirst().orElse(null);
        });
    }

    public String getAddress(int id) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "SELECT CONCAT(ad.addressCity, ' ', ad.addressStreet) AS fullAddress " + "FROM orders o " + "JOIN addresses ad ON ad.id = o.addressesId " + "WHERE o.id = :id";
            return h.createQuery(sql).bind("id", id).mapTo(String.class).findFirst().orElse(null);
        });
    }

    public boolean deleteOrderById(int orderId) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "DELETE FROM orders WHERE id = :id";
            return h.createUpdate(sql).bind("id", orderId).execute() > 0;
        });
    }

    public boolean createOrder(Order order) {
        return JDBIConnect.get().withHandle(handle -> {
            String sql = "INSERT INTO orders (userID, paymentMethodId, addressesId,orderDate, orderTotal, orderStatus,isDeleted) " + "VALUES (:userId, :paymentMethodId, :addressId,:orderDate, :orderTotal, :orderStatus,:isDeleted)";

            return handle.createUpdate(sql).bind("userId", order.getUserId()).bind("paymentMethodId", order.getPaymentMethodId()).bind("addressId", order.getAddressesId()).bind("orderTotal", order.getOrderTotal()).bind("orderStatus", order.getOrderStatus()).bind("isDeleted", order.getIsDeleted()).bind("orderDate", order.getOrderDate()).execute() > 0;
        });
    }


    public boolean createOrderAndLog(Order order, int userId) {
        boolean result = createOrder(order);
        if (result) {
            saveLogToDB(userId, "Tạo đơn hàng mới", "Xanh");
            return true;
        } else {
            return false;
        }
    }

    public Order getOrderLatestOrder() {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "SELECT * FROM orders ORDER BY id DESC LIMIT 1";

            return h.createQuery(sql).map((rs, ctx) -> {
                Order latestOrder = new Order();
                latestOrder.setId(rs.getInt("id"));
                latestOrder.setUserId(rs.getInt("userID"));
                latestOrder.setPaymentMethodId(rs.getInt("paymentMethodId"));
                latestOrder.setAddressesId(rs.getInt("addressesId"));
                latestOrder.setOrderTotal(rs.getDouble("orderTotal"));
                latestOrder.setOrderStatus(rs.getString("orderStatus"));
                latestOrder.setOrderDate(rs.getDate("orderDate").toLocalDate());
                latestOrder.setIsDeleted(rs.getInt("isDeleted"));
                return latestOrder;
            }).one();
        });
    }

    public List<Order> getAllOrderByUserId(int userId) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "SELECT * FROM orders WHERE userID = ? AND isDeleted = 0";

            return h.createQuery(sql).bind(0, userId).map((rs, ctx) -> {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setUserId(rs.getInt("userID"));
                order.setPaymentMethodId(rs.getInt("paymentMethodId"));
                order.setAddressesId(rs.getInt("addressesId"));
                order.setOrderTotal(rs.getDouble("orderTotal"));
                order.setOrderStatus(rs.getString("orderStatus"));
                order.setOrderDate(rs.getDate("orderDate").toLocalDate());
                order.setIsDeleted(rs.getInt("isDeleted"));
                return order;
            }).list();
        });
    }


    public boolean updateOrder(Order order) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "UPDATE orders SET userID = :userID, paymentMethodId = :paymentMethodId, addressesId = :addressesId, orderDate = :orderDate, orderTotal = :orderTotal, orderStatus = :orderStatus, isDeleted = :isDeleted WHERE id = :id";
            return h.createUpdate(sql).bind("userID", order.getUserId()).bind("paymentMethodId", order.getPaymentMethodId()).bind("addressesId", order.getAddressesId()).bind("orderDate", order.getOrderDate()).bind("orderTotal", order.getOrderTotal()).bind("orderStatus", order.getOrderStatus()).bind("isDeleted", order.getIsDeleted()).bind("id", order.getId()).execute() > 0;
        });
    }

    public boolean updateOrderAndLog(Order order, int userId) {
        boolean result = updateOrder(order);
        if (result) {
            saveLogToDB(userId, "Chỉnh sửa đơn hàng" + order.getId(), "Vàng");
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void saveLogToDB(int userId, String log, String alert) {
        JDBIConnect.get().withHandle(h -> {
            String sql = "INSERT INTO usersusage (userId,lastActivity,label,alert) VALUES(:userId,:lastActivity,:label,:alert)";
            return h.createUpdate(sql).bind("userId", userId).bind("lastActivity", LocalDateTime.now()).bind("alert", alert).bind("label", log).execute() > 0;
        });
    }
}

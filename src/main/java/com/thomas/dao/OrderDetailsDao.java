package com.thomas.dao;

import com.thomas.dao.db.JDBIConnect;
import com.thomas.dao.model.OrderDetails;

import java.util.List;

public class OrderDetailsDao {
    public OrderDetailsDao() {

    }

    public List<OrderDetails> findByOrderId(int id) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "select * from orderDetails where orderId=:id";
            return h.createQuery(sql).bind("id", id).mapToBean(OrderDetails.class).list();
        });
    }

    public String getBeltName(int id) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "SELECT b.name " +
                    "FROM orderDetails od " +
                    "JOIN belts b ON b.id = od.beltId " +
                    "WHERE od.id = :id";
            return h.createQuery(sql)
                    .bind("id", id)
                    .mapTo(String.class)
                    .findFirst()
                    .orElse(null);
        });
    }

    public boolean deleteOrderDetail(int orderItemId) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "DELETE FROM orderDetails WHERE id=:orderItemId";
            return h.createUpdate(sql).bind("orderItemId", orderItemId).execute() > 0;
        });
    }

    public void createOrderDetail(OrderDetails od) {
        JDBIConnect.get().withHandle(handle -> {
            String sql = "INSERT INTO orderDetails (orderId, price, beltId, quantity,variantId) " +
                    "VALUES (?, ?, ?, ?,?)";

            handle.createUpdate(sql)
                    .bind(0, od.getOrderId())
                    .bind(1, od.getPrice())
                    .bind(2, od.getBeltId())
                    .bind(3, od.getQuantity())
                    .bind(4, od.getVariantId())
                    .execute();
            return null;
        });
    }

    public boolean addOrderDetails(OrderDetails newOrderDetail) {
        return JDBIConnect.get().withHandle(handle -> {
            String sql = "INSERT INTO orderDetails (orderId, price, beltId, quantity) " +
                    "VALUES (:orderId, :price, :beltId, :quantity)";

            return handle.createUpdate(sql)
                    .bind("orderId", newOrderDetail.getOrderId())
                    .bind("price", newOrderDetail.getPrice())
                    .bind("quantity", newOrderDetail.getQuantity())
                    .bind("beltId", newOrderDetail.getBeltId())
                    .execute() > 0;
        });
    }

    public OrderDetails findOrderDetailById(int orderItemId) {
        return JDBIConnect.get().withHandle(h -> {
            String sql = "SELECT * FROM orderDetails WHERE id=:orderItemId";
            return h.createQuery(sql).bind("orderItemId", orderItemId).mapToBean(OrderDetails.class).findOne().orElse(null);
        });
    }
}

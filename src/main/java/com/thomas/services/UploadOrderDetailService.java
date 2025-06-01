package com.thomas.services;

import com.thomas.dao.BeltVariantDao;
import com.thomas.dao.OrderDetailsDao;
import com.thomas.dao.ProductDao;
import com.thomas.dao.model.Order;
import com.thomas.dao.model.OrderDetails;

import java.util.List;

public class UploadOrderDetailService {

    OrderDetailsDao orderDetailsDao;
    BeltVariantDao beltVariantDao;
    ProductDao productDao;

    public UploadOrderDetailService() {
        orderDetailsDao = new OrderDetailsDao();
        beltVariantDao = new BeltVariantDao();
        productDao = new ProductDao();
    }

    public List<OrderDetails> getAllOrderDetails(int orderId) {
        List<OrderDetails> list = orderDetailsDao.findByOrderId(orderId);

        for (OrderDetails od : list) {
            od.setBeltVariant(beltVariantDao.findVariants(od.getBeltId(), null, null, od.getVariantId()).get(0));
            od.setBeltImages(productDao.getProductImages(od.getBeltId(), od.getVariantId()));
            od.setBeltName(productDao.find(od.getBeltId()).get(0).getName());
        }
        return list;
    }

    public void setBeltName(OrderDetails od) {
        if (od != null) {
            String addressName = orderDetailsDao.getBeltName(od.getId());
            od.setBeltName(addressName);
        }
    }

    public boolean deleteOrderDetail(int orderItemId) {
        return orderDetailsDao.deleteOrderDetail(orderItemId);
    }

    public void createOrderDetail(int id, double price, int beltId, int quantity, int variantId) {
        OrderDetails od = new OrderDetails();
        od.setOrderId(id);
        od.setPrice(price);
        od.setBeltId(beltId);
        od.setQuantity(quantity);
        od.setVariantId(variantId);
        orderDetailsDao.createOrderDetail(od);
    }

    public List<OrderDetails> orderDetailsList(int orderId) {
        return orderDetailsDao.findByOrderId(orderId);
    }

    public void setOrderDetails(Order order) {
        order.setOrderDetails(orderDetailsList(order.getId()));
    }

    public boolean addOrderDetails(OrderDetails newOrderDetail) {
        return orderDetailsDao.addOrderDetails(newOrderDetail);
    }

    public OrderDetails findOrderDetailById(int orderItemId) {
        return orderDetailsDao.findOrderDetailById(orderItemId);
    }
}

package com.thomas.controller.AdminRoute.inventory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;
import com.thomas.dao.model.Purchases;
import com.thomas.dao.model.Supplier;
import com.thomas.dao.model.Transactions;
import com.thomas.services.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.LocalDateTimeAdapter;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "TransactionsController", value = "/admin/inventory/transactions")
public class TransactionsController extends HttpServlet {
    TransactionService transactionService = new TransactionService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("message") != null) {
            int purchaseId = Integer.parseInt(request.getParameter("purchaseId"));
            Transactions transactions = transactionService.getTransactions(purchaseId).get(0);
            transactionService.setBeltName(transactions);
            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();
            String json = gson.toJson(transactions);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
            return;
        }

        List<Transactions> transactionsList = transactionService.getTransactions(null);
        for (Transactions transaction : transactionsList) {
            transactionService.setBeltName(transaction);
        }
        request.setAttribute("transactionsList", transactionsList);
        request.getRequestDispatcher("/frontend/AdminPage/inventory/allTransactions/allTransactions.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = request.getParameter("message");
        if (message.equals("create")) {
            String name = request.getParameter("name").trim();
            String productName = request.getParameter("productName").trim();
            String[] parts = productName.split(" ");
            String beltName = String.join(" ", Arrays.copyOfRange(parts, 0, parts.length - 2));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            LocalDateTime transactionDate = LocalDate.parse(request.getParameter("TransactionDate"), DateTimeFormatter.ISO_LOCAL_DATE).atStartOfDay();
            int beltId = transactionService.findBeltId(beltName);
            int variantId = transactionService.getVariantId(beltName, parts[parts.length - 1], parts[parts.length - 2]);
            LocalDateTime createdAt = LocalDate.parse(request.getParameter("createdDate"), DateTimeFormatter.ISO_LOCAL_DATE).atStartOfDay();
            Transactions transactions = new Transactions(variantId, "IMPORT - " + name + " - " + productName, quantity, transactionDate, createdAt, beltId);
            boolean created = transactionService.addTransactions(transactions);
            if (created) {
                response.sendRedirect("/admin/inventory/purchases");
            }
        } else if (message.equals("delete")) {
            Transactions transactions = new Transactions();
            transactions.setId(Integer.parseInt(request.getParameter("transactionId")));
            boolean deleted = transactionService.deleteTransactions(transactions);
            if (deleted) {
                response.sendRedirect("/admin/inventory/purchases");
            }
        }
    }
}
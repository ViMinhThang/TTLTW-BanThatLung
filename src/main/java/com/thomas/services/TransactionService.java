package com.thomas.services;

import com.thomas.dao.TransactionsDao;
import com.thomas.dao.model.Transactions;

import java.util.List;

public class TransactionService {
    TransactionsDao transactionsDao;

    public TransactionService() {
        this.transactionsDao = new TransactionsDao();
    }

    public List<Transactions> getTransactions(Integer id) {
        return transactionsDao.getTransactions(id);
    }

    public boolean updateTransactions(Transactions transactions) {
        return transactionsDao.updateTransactions(transactions);
    }

    public boolean addTransactions(Transactions transactions) {
        return transactionsDao.addTransactions(transactions);
    }

    public boolean deleteTransactions(Transactions transactions) {
        return transactionsDao.deleteTransactions(transactions);
    }

    public void setBeltName(Transactions transactions) {
        transactions.setBeltName(transactionsDao.findBeltName(transactions.getBeltVariantId(), transactions.getTransactionType(), transactions.getTransactionDate(), transactions.getBeltId()));
    }

    public int findBeltId(String beltName) {
        return transactionsDao.findBeltId(beltName);
    }

    public int getVariantId(String beltName, String part, String part1) {
        return transactionsDao.findVariantId(beltName, part, part1);
    }
}

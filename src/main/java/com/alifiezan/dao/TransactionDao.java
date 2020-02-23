package com.alifiezan.dao;

import java.util.List;

import com.alifiezan.model.Transaction;

public interface TransactionDao {

	public void insert(List<? extends Transaction> transactions);
	public List<Transaction> loadAllTransactions();
}

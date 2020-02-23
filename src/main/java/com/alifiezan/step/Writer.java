package com.alifiezan.step;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.alifiezan.dao.TransactionDao;
import com.alifiezan.model.Transaction;


public class Writer implements ItemWriter<Transaction>{

	private final TransactionDao transactionDao;

	public Writer(TransactionDao transactionDao) {
		this.transactionDao = transactionDao;
	}

	@Override
	public void write(List<? extends Transaction> transactions) throws Exception {
		transactionDao.insert(transactions);
	}
}

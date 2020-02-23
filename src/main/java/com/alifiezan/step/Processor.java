package com.alifiezan.step;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.alifiezan.model.Transaction;

public class Processor implements ItemProcessor<Transaction, Transaction>{

	private static final Logger log = LoggerFactory.getLogger(Processor.class);
	
	@Override
	public Transaction process(Transaction transaction) throws Exception {
		
		final long cust_id = transaction.getCust_id();
		final long acc_no = transaction.getAcc_no();
		final String trx_date = transaction.getTrx_date();
		final String trx_time = transaction.getTrx_time();
		final double trx_amount = transaction.getTrx_amount();
		final String description = transaction.getDescription();
		
		final Transaction fixedTransaction = new Transaction(cust_id, acc_no, trx_date, trx_time, trx_amount, description);

		log.info("Converting (" + transaction + ") into (" + fixedTransaction + ")");

		return fixedTransaction;
	}

	
}

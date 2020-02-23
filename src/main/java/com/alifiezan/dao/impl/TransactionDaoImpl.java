package com.alifiezan.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.alifiezan.dao.TransactionDao;
import com.alifiezan.model.Transaction;

@Repository
public class TransactionDaoImpl extends JdbcDaoSupport implements TransactionDao {

	@Autowired
	DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	@Override
	public void insert(List<? extends Transaction> transactions) {

		String sql = "INSERT INTO transaction " 
		+ "(cust_id, acc_no, trx_date, trx_time, trx_amount, description) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";

		getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Transaction transaction = transactions.get(i);
				ps.setLong(1, transaction.getCust_id());
				ps.setLong(2, transaction.getAcc_no());
				ps.setString(3, transaction.getTrx_date());
				ps.setString(4, transaction.getTrx_time());
				ps.setDouble(5, transaction.getTrx_amount());
				ps.setString(6, transaction.getDescription());
			}

			public int getBatchSize() {
				return transactions.size();
			}
		});
	}

	@Override
	public List<Transaction> loadAllTransactions() {
		String sql = "SELECT * FROM transaction";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		

		List<Transaction> result = new ArrayList<Transaction>();
		for (Map<String, Object> row : rows) {
			Transaction transaction = new Transaction();
			transaction.setTrx_idx((Long)row.get("trx_idx"));
			transaction.setCust_id((Long) row.get("cust_id"));
			transaction.setAcc_no((Long) row.get("acc_no"));
			transaction.setTrx_date((String) row.get("trx_date"));
			transaction.setTrx_time((String) row.get("trx_time"));
			transaction.setTrx_amount((Double) row.get("trx_amount"));
			transaction.setDescription((String) row.get("description"));
		}

		return result;
	}

}

package com.alifiezan.model;

public class Transaction {

	private long trx_idx;
	private long cust_id;
	private long acc_no;
	private String trx_date;
	private String trx_time;
	private double trx_amount;
	private String description;
	
	public Transaction() {
		
	}
	
	public Transaction(long cust_id, long acc_no, String trx_date, String trx_time, double trx_amount,
			String description) {
		super();
		this.cust_id = cust_id;
		this.acc_no = acc_no;
		this.trx_date = trx_date;
		this.trx_time = trx_time;
		this.trx_amount = trx_amount;
		this.description = description;
	}
	
	public long getTrx_idx() {
		return trx_idx;
	}

	public void setTrx_idx(long trx_idx) {
		this.trx_idx = trx_idx;
	}

	public long getCust_id() {
		return cust_id;
	}
	public void setCust_id(long cust_id) {
		this.cust_id = cust_id;
	}
	public long getAcc_no() {
		return acc_no;
	}
	public void setAcc_no(long acc_no) {
		this.acc_no = acc_no;
	}
	public String getTrx_date() {
		return trx_date;
	}
	public void setTrx_date(String trx_date) {
		this.trx_date = trx_date;
	}
	public String getTrx_time() {
		return trx_time;
	}
	public void setTrx_time(String trx_time) {
		this.trx_time = trx_time;
	}
	public double getTrx_amount() {
		return trx_amount;
	}
	public void setTrx_amount(double trx_amount) {
		this.trx_amount = trx_amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Transaction [cust_id=" + cust_id + ", acc_no=" + acc_no + ", trx_date=" + trx_date + ", trx_time="
				+ trx_time + ", trx_amount=" + trx_amount + ", description=" + description + "]";
	}
}

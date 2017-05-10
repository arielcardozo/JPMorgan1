package com.jpmg.common;

import java.math.*;
import java.time.*;

public class ReportAmount {
	
	private LocalDate settlementDate;
	private BigDecimal amount;
	
	public ReportAmount() {

	}
	/**
	 * @param settlementDate
	 * @param amount
	 */
	public ReportAmount(LocalDate settlementDate, BigDecimal amount) {
		super();
		this.settlementDate = settlementDate;
		this.amount = amount;
	}
	/**
	 * @return the settlementDate
	 */
	public synchronized LocalDate getSettlementDate() {
		return settlementDate;
	}
	/**
	 * @param settlementDate the settlementDate to set
	 */
	public synchronized void setSettlementDate(LocalDate settlementDate) {
		this.settlementDate = settlementDate;
	}
	/**
	 * @return the amount
	 */
	public synchronized BigDecimal getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public synchronized void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	

}

package com.jpmg.common;

import java.math.*;
import java.time.*;

public class Instruction {
    private Entity entity;
    
    private BuySellFlag bsf;
    private BigDecimal bAgreed;
    private Currency currency;
    private LocalDate  instructionDate;
    private LocalDate settlementDate;
    private int iUnits;
    private BigDecimal pricePUnit;
    public Instruction() {
    }
	/**
	 * @param entity
	 * @param bsf
	 * @param bAgreed
	 * @param currency
	 * @param instructionDate
	 * @param settlementDate
	 * @param iUnits
	 * @param pricePUnit
	 */
	public Instruction(Entity entity, BuySellFlag bsf, BigDecimal bAgreed,
			Currency currency, LocalDate instructionDate, LocalDate settlementDate,
			int iUnits, BigDecimal pricePUnit) {
		super();
		this.entity = entity;
		this.bsf = bsf;
		this.bAgreed = bAgreed;
		this.currency = currency;
		this.instructionDate = instructionDate;
		this.settlementDate = settlementDate;
		this.iUnits = iUnits;
		this.pricePUnit = pricePUnit;
	}
	/**
	 * @return the entity
	 */
	public synchronized Entity getEntity() {
		return entity;
	}
	/**
	 * @param entity the entity to set
	 */
	public synchronized void setEntity(Entity entity) {
		this.entity = entity;
	}
	/**
	 * @return the bsf
	 */
	public synchronized BuySellFlag getBsf() {
		return bsf;
	}
	/**
	 * @param bsf the bsf to set
	 */
	public synchronized void setBsf(BuySellFlag bsf) {
		this.bsf = bsf;
	}
	/**
	 * @return the bAgreed
	 */
	public synchronized BigDecimal getbAgreed() {
		return bAgreed;
	}
	/**
	 * @param bAgreed the bAgreed to set
	 */
	public synchronized void setbAgreed(BigDecimal bAgreed) {
		this.bAgreed = bAgreed;
	}
	/**
	 * @return the currency
	 */
	public synchronized Currency getCurrency() {
		return currency;
	}
	/**
	 * @param currency the currency to set
	 */
	public synchronized void setCurrency(Currency currency) {
		this.currency = currency;
	}
	/**
	 * @return the instructionDate
	 */
	public synchronized LocalDate getInstructionDate() {
		return instructionDate;
	}
	/**
	 * @param instructionDate the instructionDate to set
	 */
	public synchronized void setInstructionDate(LocalDate instructionDate) {
		this.instructionDate = instructionDate;
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
	 * @return the iUnits
	 */
	public synchronized int getiUnits() {
		return iUnits;
	}
	/**
	 * @param iUnits the iUnits to set
	 */
	public synchronized void setiUnits(int iUnits) {
		this.iUnits = iUnits;
	}
	/**
	 * @return the pricePUnit
	 */
	public synchronized BigDecimal getPricePUnit() {
		return pricePUnit;
	}
	/**
	 * @param pricePUnit the pricePUnit to set
	 */
	public synchronized void setPricePUnit(BigDecimal pricePUnit) {
		this.pricePUnit = pricePUnit;
	}
    
    

   
}

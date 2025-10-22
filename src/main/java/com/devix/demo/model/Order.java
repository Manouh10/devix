package com.devix.demo.model; 

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

 
/**
 * 
 */
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	public enum Type {
		ACHAT, VENTE
	}

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 10)
	private Type type;

	@ManyToOne
	@JoinColumn(name = "currency_from_id", nullable = false)
	private Currency currencyFrom;

	@ManyToOne
	@JoinColumn(name = "currency_to_id", nullable = false)
	private Currency currencyTo;

	@Column(nullable = false, precision = 20, scale = 2)
	private BigDecimal amount;

	@Column(nullable = false, precision = 20, scale = 6)
	private BigDecimal rate;

	@Column(name = "converted_amount", precision = 20, scale = 2)
	private BigDecimal convertedAmount;

	@ManyToOne
	@JoinColumn(name = "status_id", nullable = false)
	private OrderStatus status;

	@ManyToOne
	@JoinColumn(name = "created_by", nullable = false)
	private User createdBy;

	@OneToMany(mappedBy = "order")
	private List<OrderHistory> history;
 
	@PrePersist
	@PreUpdate
	public void computeConvertedAmount() {
		if (amount != null && rate != null) {
			convertedAmount = amount.multiply(rate);
		}
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Currency getCurrencyFrom() {
		return currencyFrom;
	}

	public void setCurrencyFrom(Currency currencyFrom) {
		this.currencyFrom = currencyFrom;
	}

	public Currency getCurrencyTo() {
		return currencyTo;
	}

	public void setCurrencyTo(Currency currencyTo) {
		this.currencyTo = currencyTo;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public BigDecimal getConvertedAmount() {
		return convertedAmount;
	}

	public void setConvertedAmount(BigDecimal convertedAmount) {
		this.convertedAmount = convertedAmount;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public List<OrderHistory> getHistory() {
		return history;
	}

	public void setHistory(List<OrderHistory> history) {
		this.history = history;
	}

}

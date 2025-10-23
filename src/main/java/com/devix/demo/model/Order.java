package com.devix.demo.model; 

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.devix.demo.dto.TimeEntity; 
 
@Entity  
@Getter
@Setter
@Table(name = "orders")
@EntityListeners(AuditingEntityListener.class)
public class Order   {
	
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
	
	@ManyToOne
	@JoinColumn(name = "used_by", nullable = false)
	private Customer usedBy;

	@OneToMany(mappedBy = "order")
	private List<OrderHistory> history;
	
	@Embedded
    private TimeEntity timeEntity = new TimeEntity(); 
 
	@PrePersist
	@PreUpdate
	public void computeConvertedAmount() {
		if (amount != null && rate != null) {
			convertedAmount = amount.multiply(rate);
		}
	}  
}

package com.devix.demo.model; 

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    public enum Type { ACHAT, VENTE }

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

    // Getters et Setters
}

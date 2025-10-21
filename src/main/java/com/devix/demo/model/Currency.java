package com.devix.demo.model; 

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "currencies")
public class Currency extends BaseEntity {

    @Column(nullable = false, unique = true, length = 3)
    private String code;

    @Column(nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "currencyFrom")
    private List<Order> ordersFrom;

    @OneToMany(mappedBy = "currencyTo")
    private List<Order> ordersTo;

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}


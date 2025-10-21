package com.devix.demo.model; 

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "order_status")
public class OrderStatus extends BaseEntity {

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @Column(length = 255)
    private String description;

    @OneToMany(mappedBy = "status")
    private List<Order> orders;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}


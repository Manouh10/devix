package com.devix.demo.model; 

import jakarta.persistence.*;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.devix.demo.dto.TimeEntity;

@Entity
@Table(name = "order_status")
@EntityListeners(AuditingEntityListener.class)
public class OrderStatus   {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @Column(length = 255)
    private String description;

    @OneToMany(mappedBy = "status")
    private List<Order> orders;
    
    @Embedded
    private TimeEntity timeEntity = new TimeEntity(); 
 

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}


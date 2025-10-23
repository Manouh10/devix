package com.devix.demo.model; 

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.devix.demo.dto.TimeEntity;

@Entity
@Getter
@Setter
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
}


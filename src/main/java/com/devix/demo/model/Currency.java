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
@Table(name = "currencies")
@EntityListeners(AuditingEntityListener.class)
public class Currency  {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 3)
    private String code;

    @Column(nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "currencyFrom")
    private List<Order> ordersFrom;

    @OneToMany(mappedBy = "currencyTo")
    private List<Order> ordersTo;
    
    @Embedded
    private TimeEntity timeEntity = new TimeEntity();  
}


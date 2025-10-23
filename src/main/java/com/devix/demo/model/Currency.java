package com.devix.demo.model; 

import jakarta.persistence.*;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.devix.demo.dto.TimeEntity;

@Entity
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
    private TimeEntity timeTrackable = new TimeEntity(); 
    
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}


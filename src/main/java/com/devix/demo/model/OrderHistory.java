package com.devix.demo.model; 

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.devix.demo.dto.TimeEntity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity 
@Getter
@Setter
@Table(name = "order_history")
@EntityListeners(AuditingEntityListener.class)
public class OrderHistory  {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "changed_by", nullable = false)
    private User changedBy; 
    
    @ManyToOne 
    @JoinColumn(name = "old_status" , nullable = false)
    private OrderStatus oldStatus;

    @ManyToOne 
    @JoinColumn(name = "new_status",  nullable = false)
    private OrderStatus newStatus; 
    
    @Embedded
    private TimeEntity timeEntity = new TimeEntity();  
}


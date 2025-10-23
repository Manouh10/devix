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
@Table(name = "roles")
@EntityListeners(AuditingEntityListener.class)
public class Role  {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @OneToMany(mappedBy = "role")
    private List<User> users;

    @OneToMany(mappedBy = "role")
    private List<Authorization> authorizations;
    
    @Embedded
    private TimeEntity timeEntity = new TimeEntity();  
}


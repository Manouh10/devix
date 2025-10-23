package com.devix.demo.model; 

import jakarta.persistence.*;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.devix.demo.dto.TimeEntity;

@Entity
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
    
   
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
    
}


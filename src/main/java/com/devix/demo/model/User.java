package com.devix.demo.model; 
import jakarta.persistence.*;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.devix.demo.dto.TimeEntity;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User   {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role; 

    @OneToMany(mappedBy = "changedBy")
    private List<OrderHistory> orderHistories; 
   
    @Embedded
    private TimeEntity timeEntity = new TimeEntity(); 
   
	public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
}


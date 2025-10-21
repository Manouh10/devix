package com.devix.demo.model; 

import jakarta.persistence.*;

@Entity
@Table(name = "authorizations")
public class Authorization extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(nullable = false, length = 50)
    private String entity;

    @Column(name = "can_read", nullable = false)
    private boolean canRead = false;

    @Column(name = "can_write", nullable = false)
    private boolean canWrite = false;

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    public String getEntity() { return entity; }
    public void setEntity(String entity) { this.entity = entity; }
    public boolean isCanRead() { return canRead; }
    public void setCanRead(boolean canRead) { this.canRead = canRead; }
    public boolean isCanWrite() { return canWrite; }
    public void setCanWrite(boolean canWrite) { this.canWrite = canWrite; }
}


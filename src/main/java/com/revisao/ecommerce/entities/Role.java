package com.revisao.ecommerce.entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
@Entity
@Table(name = "tb_role")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authority;

    public Role() {
    }

    public Role(String authority, Long id) {
        this.authority = authority;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}

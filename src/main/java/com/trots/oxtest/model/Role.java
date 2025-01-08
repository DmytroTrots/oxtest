package com.trots.oxtest.model;

import jakarta.persistence.Embeddable;
import org.springframework.security.core.GrantedAuthority;

@Embeddable
public class Role implements GrantedAuthority {

    private RoleAuth name;

    @Override
    public String getAuthority() {
        return name.name();
    }
}

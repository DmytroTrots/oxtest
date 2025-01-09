package com.trots.oxtest.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Embeddable
@Data
public class Role implements GrantedAuthority {

    @Enumerated(EnumType.STRING)
    private RoleAuth name;

    @Override
    public String getAuthority() {
        return name.name();
    }
}

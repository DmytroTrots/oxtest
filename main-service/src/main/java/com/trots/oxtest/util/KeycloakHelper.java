package com.trots.oxtest.util;

import java.util.Collections;
import java.util.List;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class KeycloakHelper {

    public String getUsername() {
        Jwt jwt = getJwt();
        if (jwt != null) {
            return jwt.getClaimAsString("preferred_username");
        }
        return null;
    }

    private Jwt getJwt() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()
                && authentication.getPrincipal() instanceof Jwt) {
            return (Jwt) authentication.getPrincipal();
        }
        return null;
    }
}

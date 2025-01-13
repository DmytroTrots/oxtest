package com.trots.oxtest.controller.dto;

import com.trots.oxtest.model.Role;
import java.util.List;

public record AuthResponse(Long id, String name, List<Role> role, String token) {
}
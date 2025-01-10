package com.trots.oxtest.controller;

import com.trots.oxtest.controller.dto.AuthResponse;
import com.trots.oxtest.controller.dto.LoginRequest;
import com.trots.oxtest.model.entity.UserEntity;
import com.trots.oxtest.service.UserService;
import jakarta.validation.Valid;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        Optional<UserEntity> optionalUser = userService.validUsernameAndPassword(loginRequest.username(), loginRequest.password());
        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            return ResponseEntity.ok(new AuthResponse(user.getId(), user.getUsername(), user.getRoles()));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}

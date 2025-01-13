package com.trots.oxtest.service;

import com.trots.oxtest.dto.UserDTO;
import com.trots.oxtest.model.entity.UserEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserDTO save(UserDTO user);

    void deleteById(Long id);

    UserDTO findById(Long id);

    List<UserDTO> findAll();

    Optional<UserEntity> validUsernameAndPassword(String username, String password);

    Optional<UserEntity> findByUsername(String username);
}

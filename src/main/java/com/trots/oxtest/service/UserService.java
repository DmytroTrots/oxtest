package com.trots.oxtest.service;

import com.trots.oxtest.dto.UserDTO;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserDTO save(UserDTO user);

    void deleteById(Long id);

    UserDTO findById(Long id);

    List<UserDTO> findAll();
}

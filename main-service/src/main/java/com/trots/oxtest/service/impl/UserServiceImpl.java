package com.trots.oxtest.service.impl;

import com.trots.oxtest.dto.UserDTO;
import com.trots.oxtest.exception.ResourceNotFoundException;
import com.trots.oxtest.mapper.UserMapper;
import com.trots.oxtest.model.entity.UserEntity;
import com.trots.oxtest.repository.UserRepository;
import com.trots.oxtest.service.UserService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDTO save(UserDTO userDTO) {
        UserEntity entityToSave = userMapper.toEntity(userDTO);
        entityToSave.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userMapper.toDto(userRepository.save(entityToSave));
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO findById(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(UserEntity.class, id));
        return userMapper.toDto(user);
    }

    @Override
    public List<UserDTO> findAll() {
        return userMapper.toDtos(userRepository.findAll());
    }

    @Override
    public Optional<UserEntity> validUsernameAndPassword(String username, String password) {
        return userRepository.findUserByEmailWithRoles(username)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()));
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findUserByEmailWithRoles(username);
    }

}

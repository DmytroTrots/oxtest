package com.trots.oxtest.service.impl;

import static com.trots.oxtest.service.impl.UserDetailsServiceImpl.BEAN_NAME;

import com.trots.oxtest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(BEAN_NAME)
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    public static final String BEAN_NAME = "UserDetailsServiceImpl";

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByEmailWithRoles(username);
    }

}

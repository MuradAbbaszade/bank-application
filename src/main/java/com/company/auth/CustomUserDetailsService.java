package com.company.auth;

import com.company.jpa.entity.UserEntity;
import com.company.jpa.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserJpaRepository userJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = null;
        userEntity = userJpaRepository.findByUsername(username);
        if (userEntity==null) throw new UsernameNotFoundException(username);
        return userEntity;
    }
}

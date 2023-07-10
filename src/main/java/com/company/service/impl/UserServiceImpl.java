package com.company.service.impl;

import com.company.controller.dto.UserDto;
import com.company.exception.EmailAlreadyUsedException;
import com.company.jpa.entity.Authority;
import com.company.jpa.entity.UserEntity;
import com.company.jpa.repository.AuthorityJpaRepository;
import com.company.jpa.repository.UserJpaRepository;
import com.company.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AuthorityJpaRepository authorityJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserEntity register(UserDto userDto) throws Exception {
        if(userJpaRepository.findByUsername(userDto.getUsername())!=null) throw new EmailAlreadyUsedException(userDto.getUsername());
        UserEntity userEntity = createUserEntity(userDto);
        return userJpaRepository.save(userEntity);
    }


    private UserEntity createUserEntity(UserDto dto) {
        UserEntity userEntity = new UserEntity();
        Authority authority;
        if(authorityJpaRepository.findByAuthority("ROLE_USER").isPresent()) authority = authorityJpaRepository.findByAuthority("ROLE_USER").get();
        else authority = authorityJpaRepository.save(Authority.builder().authority("ROLE_USER").build());
        Set<Authority> userAuthority = new HashSet<>();
        userAuthority.add(authority);
        userEntity.setSurname(dto.getSurname());
        userEntity.setName(dto.getName());
        userEntity.setUsername(dto.getUsername());
        userEntity.setAuthorities(userAuthority);
        userEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
        userEntity.setEnabled(true);
        userEntity.setAccountNonExpired(true);
        userEntity.setAccountNonLocked(true);
        userEntity.setCredentialsNonExpired(true);
        return userEntity;
    }
}

package com.company.auth;


import com.company.jpa.entity.UserEntity;
import com.company.jpa.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final PasswordEncoder passwordEncoder;
    private final UserJpaRepository userJpaRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        UserEntity userEntity = null;
        userEntity = userJpaRepository.findByUsername(username);
        if (userEntity==null) throw new UsernameNotFoundException(username+" not found");
        String hashPassword = userEntity.getPassword();
        String password = authentication.getCredentials().toString();
        if (!passwordEncoder.matches(password,hashPassword)) throw new BadCredentialsException("Username or password is invalid");
        return new UsernamePasswordAuthenticationToken(userEntity.getUsername(),userEntity.getPassword(),userEntity.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}

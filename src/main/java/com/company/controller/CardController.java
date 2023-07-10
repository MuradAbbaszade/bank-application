package com.company.controller;

import com.company.jpa.entity.Card;
import com.company.jpa.entity.UserEntity;
import com.company.jpa.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class CardController {

    private final UserJpaRepository userJpaRepository;

    @GetMapping("/cards")
    public ResponseEntity<Set<Card>> getUserCards(Authentication authentication){
        String username = authentication.getName();
        UserEntity userEntity = userJpaRepository.findByUsername(username);
        return ResponseEntity.ok(userEntity.getCards());
    }
}

package com.example.pairprogramming.user.repository;

import com.example.pairprogramming.user.domain.AuthenticationDetails;

import java.util.List;
import java.util.Optional;

public interface TokenRepository {

    AuthenticationDetails save(AuthenticationDetails authenticationDetails);
    Optional<AuthenticationDetails> findById(Long id);
//    Optional<AuthenticationDetails> findByToken(String token);
    List<AuthenticationDetails> findAll();


}

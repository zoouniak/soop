package com.example.soop.login.domain.repository;

import com.example.soop.login.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}

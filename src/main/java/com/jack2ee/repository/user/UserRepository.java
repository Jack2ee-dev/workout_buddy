package com.jack2ee.repository.user;

import com.jack2ee.model.user.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("FROM User "
        + "WHERE email = :email and"
        + "      oauthProvider = :oauthProvider")
    Optional<User> findByEmailAndOauthProvider(
        @Param("email") String email,
        @Param("oauthProvider") String oauthProvider
    );

    @Query("FROM User "
        + "WHERE token = :token")
    Optional<User> findByToken(
        @Param("token") String token
    );
}

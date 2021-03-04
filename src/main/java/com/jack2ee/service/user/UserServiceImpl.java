package com.jack2ee.service.user;

import com.jack2ee.model.user.User;
import com.jack2ee.model.user.UserRole;
import com.jack2ee.repository.user.UserRepository;
import com.jack2ee.util.TokenUtils;

import org.springframework.stereotype.Service;

import java.util.Optional;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User addIfNotExist(String name, String email, String oauthProvider, UserRole userRole) {
        Optional<User> optionalUser = userRepository
            .findByEmailAndOauthProvider(email, oauthProvider);
        return optionalUser.orElseGet(() -> userRepository.save(User.builder()
            .name(name)
            .email(email)
            .oauthProvider(oauthProvider)
            .token(TokenUtils.create(name, email, oauthProvider))
            .roleValue(userRole.getIntValue())
            .build()));
    }

    @Override
    public boolean changeRole(String token, UserRole roleTo) {
        Optional<User> optionalUser = userRepository
            .findByToken(token);
        if (optionalUser.isPresent()) {
            User updatedUser = optionalUser.get();
            updatedUser.changeRole(roleTo);
            userRepository.save(updatedUser);
            return true;
        }
        return false;
    }
}

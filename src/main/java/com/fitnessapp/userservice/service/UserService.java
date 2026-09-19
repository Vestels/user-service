package com.fitnessapp.userservice.service;

import com.fitnessapp.userservice.entity.UserEntity;
import com.fitnessapp.userservice.exception.UserNotFoundException;
import com.fitnessapp.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void saveUser(UserEntity user) {
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public Optional<UserEntity> findByPublicId(UUID publicId) {
        return userRepository.findByPublicId(publicId);
    }

    @Transactional(readOnly = true)
    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }

    @Transactional
    public void updateUserLastActivityAt(UUID publicId) {
        UserEntity user = userRepository.findByPublicId(publicId)
                .orElseThrow(() -> new UserNotFoundException("User not found."));

        user.updateLastActivityAt();
    }
}

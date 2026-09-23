package com.fitnessapp.userservice.service;

import com.fitnessapp.userservice.entity.UserEntity;
import com.fitnessapp.userservice.enums.UserStatus;
import com.fitnessapp.userservice.exception.UserNotFoundException;
import com.fitnessapp.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserEntity findByPublicId(UUID publicId) {
        return userRepository.findByPublicId(publicId)
                .orElseThrow(() -> new UserNotFoundException("User not found."));
    }

    @Transactional(readOnly = true)
    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }

    @Transactional(readOnly = true)
    public List<UserEntity> findAllByStatusInAndScheduledDeletionAtBefore(List<UserStatus> statuses, Instant timestamp) {
        return userRepository.findAllByStatusInAndScheduledDeletionAtBefore(statuses, timestamp);
    }

    @Transactional
    public void saveUser(UserEntity user) {
        userRepository.save(user);
    }

    @Transactional
    public void updateUserLastActivityAt(UUID publicId) {
        UserEntity user = userRepository.findByPublicId(publicId)
                .orElseThrow(() -> new UserNotFoundException("User not found."));

        user.updateLastActivityAt();
    }

    @Transactional
    public void setUserScheduledDeletion(UUID publicId) {
        UserEntity user = userRepository.findByPublicId(publicId)
                .orElseThrow(() -> new UserNotFoundException("User not found."));

        user.setScheduledDeletion();
    }

    @Transactional
    public void clearUserScheduledDeletion(UUID publicId) {
        UserEntity user = userRepository.findByPublicId(publicId)
                .orElseThrow(() -> new UserNotFoundException("User not found."));

        user.clearScheduledDeletion();
    }
}

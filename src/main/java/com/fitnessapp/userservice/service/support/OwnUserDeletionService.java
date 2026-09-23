package com.fitnessapp.userservice.service.support;

import com.fitnessapp.userservice.repository.UserIdentityRepository;
import com.fitnessapp.userservice.repository.UserPreferencesRepository;
import com.fitnessapp.userservice.repository.UserProfileRepository;
import com.fitnessapp.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OwnUserDeletionService {

    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final UserPreferencesRepository userPreferencesRepository;
    private final UserIdentityRepository userIdentityRepository;

    @Transactional
    public void deleteUser(UUID publicId) {
        userProfileRepository.deleteByUserId(publicId);
        userPreferencesRepository.deleteByUserId(publicId);
        userIdentityRepository.deleteByUserId(publicId);
        userRepository.deleteByPublicId(publicId);
    }
}

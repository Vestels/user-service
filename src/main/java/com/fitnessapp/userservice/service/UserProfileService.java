package com.fitnessapp.userservice.service;

import com.fitnessapp.userservice.dto.response.UserProfileResponseDto;
import com.fitnessapp.userservice.entity.UserProfileEntity;
import com.fitnessapp.userservice.exception.UserProfileNotFoundException;
import com.fitnessapp.userservice.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    @Transactional(readOnly = true)
    public UserProfileResponseDto getUserProfile(UUID publicId) {
        return UserProfileResponseDto.from(userProfileRepository.findByUserId(publicId)
                .orElseThrow(() -> new UserProfileNotFoundException("User profile not found.")));
    }

    @Transactional
    public void saveUserProfile(UserProfileEntity userProfile) {
        userProfileRepository.save(userProfile);
    }
}

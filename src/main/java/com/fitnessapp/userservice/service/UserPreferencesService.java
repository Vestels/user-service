package com.fitnessapp.userservice.service;

import com.fitnessapp.userservice.dto.request.UpdateUserPreferencesRequestDto;
import com.fitnessapp.userservice.dto.response.UserPreferencesAppBehaviourDto;
import com.fitnessapp.userservice.dto.response.UserPreferencesResponseDto;
import com.fitnessapp.userservice.entity.UserPreferencesEntity;
import com.fitnessapp.userservice.exception.UserPreferencesNotFoundException;
import com.fitnessapp.userservice.repository.UserPreferencesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserPreferencesService {

    private final UserPreferencesRepository userPreferencesRepository;

    @Transactional(readOnly = true)
    public UserPreferencesResponseDto getUserPreferences(UUID userId) {
        return UserPreferencesResponseDto.from(userPreferencesRepository.findByUserId(userId)
                .orElseThrow(() -> new UserPreferencesNotFoundException("User Preferences not found."))
        );
    }

    @Transactional(readOnly = true)
    public UserPreferencesAppBehaviourDto getUserAppBehaviourPreferences(UUID userId) {
        return UserPreferencesAppBehaviourDto.from(userPreferencesRepository.findByUserId(userId)
                .orElseThrow(() -> new UserPreferencesNotFoundException("User Preferences not found."))
        );
    }

    @Transactional
    public void saveUserPreferences(UserPreferencesEntity userPreferences) {
        userPreferencesRepository.save(userPreferences);
    }

    @Transactional
    public void updateUserPreferences(UpdateUserPreferencesRequestDto userPreference, UUID userId) {
        this.saveUserPreferences(userPreference.updateEntity(userPreferencesRepository.findByUserId(userId)
                .orElseThrow(() -> new UserPreferencesNotFoundException("User Preferences not found.")))
        );
    }
}

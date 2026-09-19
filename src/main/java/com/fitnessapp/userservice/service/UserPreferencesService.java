package com.fitnessapp.userservice.service;

import com.fitnessapp.userservice.dto.response.UserPreferencesResponseDto;
import com.fitnessapp.userservice.entity.UserPreferencesEntity;
import com.fitnessapp.userservice.exception.UserPreferencesNotFoundException;
import com.fitnessapp.userservice.repository.UserPreferencesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DateTimeException;
import java.time.ZoneId;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserPreferencesService {

    private final UserPreferencesRepository userPreferencesRepository;

    @Transactional(readOnly = true)
    public UserPreferencesResponseDto getUserPreferences(UUID publicId) {
        return UserPreferencesResponseDto.from(userPreferencesRepository.findByUserId(publicId)
                .orElseThrow(() -> new UserPreferencesNotFoundException("User Preferences not found.")));
    }

    @Transactional
    public void saveUserPreferences(UserPreferencesEntity userPreferences) {
        userPreferencesRepository.save(userPreferences);
    }

    @Transactional
    public void updateTimezone(UserPreferencesEntity userPreferences, String timezone) {
        try {
            ZoneId.of(timezone);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException("Invalid timezone: " + timezone, e);
        }

        userPreferences.setTimezone(timezone);
    }
}

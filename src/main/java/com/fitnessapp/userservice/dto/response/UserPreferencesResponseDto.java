package com.fitnessapp.userservice.dto.response;

import com.fitnessapp.userservice.entity.UserPreferencesEntity;
import com.fitnessapp.userservice.enums.Language;
import com.fitnessapp.userservice.enums.Theme;
import com.fitnessapp.userservice.enums.UnitSystem;

import java.util.UUID;

public record UserPreferencesResponseDto(
        UUID userId,
        Language language,
        UnitSystem unitSystem,
        Theme theme,
        String timezone,
        boolean emailNotifications,
        boolean pushNotifications
) {

    public static UserPreferencesResponseDto from(UserPreferencesEntity userPreferences) {
        return new UserPreferencesResponseDto(
                userPreferences.getUserId(),
                userPreferences.getLanguage(),
                userPreferences.getUnitSystem(),
                userPreferences.getTheme(),
                userPreferences.getTimezone(),
                userPreferences.isEmailNotifications(),
                userPreferences.isPushNotifications()
        );
    }
}

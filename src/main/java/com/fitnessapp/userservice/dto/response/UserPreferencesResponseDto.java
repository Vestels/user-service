package com.fitnessapp.userservice.dto.response;

import com.fitnessapp.userservice.entity.UserPreferencesEntity;
import com.fitnessapp.userservice.enums.Language;
import com.fitnessapp.userservice.enums.Theme;
import com.fitnessapp.userservice.enums.UnitSystem;

public record UserPreferencesResponseDto(
        Language language,
        UnitSystem unitSystem,
        Theme theme,
        boolean emailNotifications,
        boolean pushNotifications
) {

    public static UserPreferencesResponseDto from(UserPreferencesEntity userPreferences) {
        return new UserPreferencesResponseDto(
                userPreferences.getLanguage(),
                userPreferences.getUnitSystem(),
                userPreferences.getTheme(),
                userPreferences.isEmailNotifications(),
                userPreferences.isPushNotifications()
        );
    }
}

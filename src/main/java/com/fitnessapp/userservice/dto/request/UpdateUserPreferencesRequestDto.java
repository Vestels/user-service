package com.fitnessapp.userservice.dto.request;

import com.fitnessapp.userservice.entity.UserPreferencesEntity;
import com.fitnessapp.userservice.enums.Language;
import com.fitnessapp.userservice.enums.Theme;
import com.fitnessapp.userservice.enums.UnitSystem;

public record UpdateUserPreferencesRequestDto(
        Language language,
        UnitSystem unitSystem,
        Theme theme,
        Boolean emailNotifications,
        Boolean pushNotifications
) {
    public boolean isEmpty() {
        return language == null &&
                unitSystem == null &&
                theme == null &&
                emailNotifications == null &&
                pushNotifications == null;
    }

    public UserPreferencesEntity updateEntity(UserPreferencesEntity entity) {
        if (language != null) {
            entity.setLanguage(language);
        }
        if (unitSystem != null) {
            entity.setUnitSystem(unitSystem);
        }

        if (theme != null) {
            entity.setTheme(theme);
        }

        if (emailNotifications != null) {
            entity.setEmailNotifications(emailNotifications);
        }

        if (pushNotifications != null) {
            entity.setPushNotifications(pushNotifications);
        }

        return entity;
    }
}

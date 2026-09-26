package com.fitnessapp.userservice.dto.response;

import com.fitnessapp.userservice.entity.UserPreferencesEntity;
import com.fitnessapp.userservice.enums.Language;
import com.fitnessapp.userservice.enums.Theme;

public record UserPreferencesAppBehaviourDto(
        Language language,
        Theme theme
) {

    public static UserPreferencesAppBehaviourDto from(UserPreferencesEntity preferences) {
        return new UserPreferencesAppBehaviourDto(
                preferences.getLanguage(),
                preferences.getTheme()
        );
    }
}

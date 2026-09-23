package com.fitnessapp.userservice.service.support;

import com.fitnessapp.userservice.entity.UserEntity;
import com.fitnessapp.userservice.entity.UserIdentityEntity;
import com.fitnessapp.userservice.enums.UserStatus;
import com.fitnessapp.userservice.exception.UserIdentityNotFoundException;
import com.fitnessapp.userservice.service.UserIdentityService;
import com.fitnessapp.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeletionService {

    private final UserService userService;
    private final UserIdentityService userIdentityService;
    private final Auth0ManagementService auth0ManagementService;
    private final OwnUserDeletionService ownUserDeletionService;

    public void deleteExpiredUsers() {
        Instant now = Instant.now();

        List<UserEntity> users = userService.findAllByStatusInAndScheduledDeletionAtBefore(
                List.of(
                        UserStatus.PENDING_DELETION,
                        UserStatus.DELETING
                ),
                now);

        for (UserEntity user : users) {
            try {
                processDeletion(user);
            } catch (Exception exception) {
//                TODO - logging
            }
        }
    }

    private void processDeletion(UserEntity user) {
        UUID publicId = user.getPublicId();

        user.startDeletion();
        userService.saveUser(user);

        UserIdentityEntity identity = userIdentityService.findByUserId(publicId)
                .orElseThrow(() -> new UserIdentityNotFoundException("User identity not found."));

        auth0ManagementService.deleteUser(identity.getSubject());

        ownUserDeletionService.deleteUser(publicId);
    }
}

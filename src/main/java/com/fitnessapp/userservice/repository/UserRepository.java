package com.fitnessapp.userservice.repository;

import com.fitnessapp.userservice.entity.UserEntity;
import com.fitnessapp.userservice.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByPublicId(UUID publicId);
    Optional<UserEntity> findByEmailIgnoreCase(String email);
    List<UserEntity> findAllByStatusInAndScheduledDeletionAtBefore(List<UserStatus> statuses, Instant timestamp);
    void deleteByPublicId(UUID publicId);
}

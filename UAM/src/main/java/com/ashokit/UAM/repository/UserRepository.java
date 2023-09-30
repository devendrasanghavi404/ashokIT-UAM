package com.ashokit.UAM.repository;

import com.ashokit.UAM.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity save(UserEntity user);

    UserEntity saveAndFlush(UserEntity user);

    void deleteById(Long id);

    Optional<UserEntity> findById(Long id);

    Optional<UserEntity> findByUserNameOrEmail(String userName, String email);

    List<UserEntity> findAll();
}

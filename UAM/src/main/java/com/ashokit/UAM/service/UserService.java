package com.ashokit.UAM.service;

import com.ashokit.UAM.dto.UserDTO;
import com.ashokit.UAM.model.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity saveUserEntity(UserDTO userDTO);

    UserEntity findUserById(Long id);

    List<UserEntity> fetchAllUsers();

    boolean deleteUserAccount(Long id);

    boolean updateUserAccStatus(Long id, String status);

}

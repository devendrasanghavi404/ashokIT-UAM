package com.ashokit.UAM.service;

import com.ashokit.UAM.dto.UserDTO;
import com.ashokit.UAM.model.UserEntity;

public interface UserService {
    UserEntity saveUserEntity(UserDTO userDTO);

    UserEntity findUserById(Long id);
}

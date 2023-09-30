package com.ashokit.UAM.service.impl;

import com.ashokit.UAM.dto.UserDTO;
import com.ashokit.UAM.exceptions.DuplicateUserException;
import com.ashokit.UAM.exceptions.UserNotFoundException;
import com.ashokit.UAM.model.UserEntity;
import com.ashokit.UAM.repository.UserRepository;
import com.ashokit.UAM.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity saveUserEntity(UserDTO userDTO) {
        Optional<UserEntity> existingUser = userRepository.findByUserNameOrEmail(userDTO.getUserName(), userDTO.getEmail());
        if (!existingUser.isPresent()) {
            UserEntity userEntity = UserEntity.builder()
                    .userName(userDTO.getUserName())
                    .firstName(userDTO.getFirstName())
                    .lastName(userDTO.getLastName())
                    .email(userDTO.getEmail())
                    .phoneNumber(userDTO.getPhoneNumber())
                    .birthDate(userDTO.getBirthDate())
                    .isActive(true)
                    .passKey(userDTO.getPassKey())
                    .createdAt(LocalDate.now())
                    .updatedAt(LocalDate.now())
                    .build();
            return userRepository.save(userEntity);
        } else {
            throw new DuplicateUserException("User with " + userDTO.getUserName() + " or " + userDTO.getEmail() + " already exists");
        }
    }

    @Override
    public UserEntity findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
    }

}

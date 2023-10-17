package com.ashokit.UAM.service.impl;

import com.ashokit.UAM.dto.UserDTO;
import com.ashokit.UAM.exceptions.DuplicateUserException;
import com.ashokit.UAM.exceptions.UserNotFoundException;
import com.ashokit.UAM.model.UserEntity;
import com.ashokit.UAM.repository.UserRepository;
import com.ashokit.UAM.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

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

    @Override
    public List<UserEntity> fetchAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean deleteUserAccount(Long id) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(id);
        if (!optionalUserEntity.isPresent())
            return false;

        userRepository.deleteById(optionalUserEntity.get().getId());
        return true;
    }

    @Override
    public boolean updateUserAccStatus(Long id, String status) {
        status = status.toUpperCase();
        Optional<UserEntity> optionalUserEntity = userRepository.findById(id);
        if (!optionalUserEntity.isPresent()) {
            return false;
        }
        boolean isActive = status.contains("Y");
        optionalUserEntity.get().setIsActive(isActive);
        return true;
    }


}

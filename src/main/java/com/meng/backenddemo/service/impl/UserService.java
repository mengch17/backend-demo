package com.meng.backenddemo.service.impl;

import com.meng.backenddemo.dto.UserDto;
import com.meng.backenddemo.entity.UserEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.meng.backenddemo.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    /**
     * @return
     */
    @Transactional
    public UserDto getUserByUuid(String userUuid) {
        // find by uuid from userRepository DAO
        UserEntity userEntity = userRepository.findByUserUuid(userUuid);

        // convert UserEntity to UserDto
        if (userEntity != null) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(userEntity, userDto);
            // if UserDto and UserEntity have different fields，require extra converting process.
            return userDto;
        } else {
            // handle not found case
            throw new EntityNotFoundException("User not found with UUID: " + userUuid);
        }
    }

    @Transactional
    public UserDto createUser(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);

        // 检查 userDto 是否有 userUuid，如果没有则生成一个
        if (userEntity.getUserUuid() == null || userEntity.getUserUuid().isEmpty()) {
            userEntity.setUserUuid(UUID.randomUUID().toString());
        }

        userEntity = userRepository.save(userEntity);

        UserDto savedUserDto = new UserDto();
        BeanUtils.copyProperties(userEntity, savedUserDto);
        return savedUserDto;
    }

    @Transactional
    public void deleteUserByUuid(String userUuid) {
        UserEntity userEntity = userRepository.findByUserUuid(userUuid);
        if (userEntity != null) {
            userRepository.delete(userEntity);
        } else {
            throw new EntityNotFoundException("User not found with UUID: " + userUuid);
        }
    }
}

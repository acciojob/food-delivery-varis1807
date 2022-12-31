package com.driver.service.impl;

import com.driver.Converter.UserConverter;
import com.driver.io.entity.UserEntity;
import com.driver.io.repository.UserRepository;
import com.driver.service.UserService;
import com.driver.shared.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto user) throws Exception {
        UserEntity userEntity = userRepository.save(UserConverter.convertDtoToEntity(user));
        return UserConverter.convertEntityToDto(userEntity);
    }

    @Override
    public UserDto getUser(String email) throws Exception {
        return null;
    }

    @Override
    public UserDto getUserByUserId(String userId) throws Exception {
        UserEntity userEntity = userRepository.findByUserId(userId);
        return UserConverter.convertEntityToDto(userEntity);
    }

    @Override
    public UserDto updateUser(String userId, UserDto user) throws Exception {
        UserEntity userEntity = userRepository.findByUserId(userId);
        userEntity.setEmail(user.getEmail());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());

        userRepository.save(userEntity);
        return UserConverter.convertEntityToDto(userEntity);
    }

    @Override
    public void deleteUser(String userId) throws Exception {
        userRepository.deleteById(Long.valueOf(userId));
    }

    @Override
    public List<UserDto> getUsers() {
        List<UserDto> userDto = new ArrayList<>();
        List<UserEntity> userEntity = (List<UserEntity>) userRepository.findAll();

        for(UserEntity user : userEntity) {
            userDto.add(UserConverter.convertEntityToDto(user));
        }
        return userDto;
    }
}
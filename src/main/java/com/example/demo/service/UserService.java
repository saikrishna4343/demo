package com.example.demo.service;

import com.example.demo.dto.UserVO;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.CustomException;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    UserRepository userRepository;

    public UserVO addUser(UserVO userVO){
        UserEntity savedEntity = userRepository.save(UserEntity.builder()
                .firstName(userVO.getFirstName())
                .lastName(userVO.getLastName())
                .email(userVO.getEmail())
                .userId(UUID.randomUUID().toString())
                .build());

        return UserVO.builder().userId(savedEntity.getUserId()).firstName(savedEntity.getFirstName()).lastName(savedEntity.getLastName()).email(savedEntity.getEmail()).build();
    }

    public List<UserVO> addAllUsers(List<UserVO> userVOList) {
        List<UserEntity> savedEntities = userRepository.saveAll(userVOList.stream().map(userVO -> UserEntity.builder()
                .firstName(userVO.getFirstName())
                .lastName(userVO.getLastName())
                .email(userVO.getEmail())
                .userId(UUID.randomUUID().toString())
                .build()).collect(Collectors.toList()));
        return savedEntities.stream().map(savedEntity -> UserVO.builder().userId(savedEntity.getUserId()).firstName(savedEntity.getFirstName()).lastName(savedEntity.getLastName()).email(savedEntity.getEmail()).build()).collect(Collectors.toList());
    }

    public UserVO getUserByEmail(String email) {
        Optional<UserEntity> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            return UserVO.builder().userId(user.get().getUserId()).firstName(user.get().getFirstName()).lastName(user.get().getLastName()).email(user.get().getEmail()).build();
        }else throw new CustomException("User with given email not found");
    }
}

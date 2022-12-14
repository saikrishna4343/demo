package com.example.demo.controller;

import com.example.demo.dto.UserVO;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v2")
@AllArgsConstructor
public class UserController {

    UserService userService;

    @PostMapping(path = "/adduser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserVO addUser(@RequestBody UserVO userVO){
        return userService.addUser(userVO);
    }

    @PostMapping(path = "/add/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserVO> addAllUsers(@RequestBody List<UserVO> userVOList){
        return userService.addAllUsers(userVOList);
    }

    @GetMapping(path = "/get-user/email", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserVO getUserByEmail(@RequestParam(name = "email", required = true) String email){
        return userService.getUserByEmail(email);
    }
}

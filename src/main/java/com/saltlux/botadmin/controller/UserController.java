package com.saltlux.botadmin.controller;

import com.saltlux.botadmin.dto.user.ListUserDto;
import com.saltlux.botadmin.dto.user.Login;
import com.saltlux.botadmin.dto.user.ResultLogin;
import com.saltlux.botadmin.dto.user.UserDto;
import com.saltlux.botadmin.entity.UserEntity;
import com.saltlux.botadmin.service.IUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "User")
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    IUserService service;

    @GetMapping("/all")
    public List<ListUserDto> getAll() {

        return service.finAll();
    }

    @GetMapping("/detail")
    public UserDto finById(@RequestParam Integer userId) {

        return service.finById(userId);
    }

    @PostMapping("/log_in")
    public ResultLogin Login(@Valid @RequestBody Login login) {
        UserEntity user = service.findByEmailAndPassword(login.getEmail(), login.getPassword());
        if (user != null) {
            ResultLogin resultLogin = new ResultLogin(user.getId(), 1);
            return resultLogin;
        } else {
            ResultLogin resultLogin = new ResultLogin(0, 0);
            return resultLogin;
        }
    }

    @GetMapping("/detail_by_name")
    public List<UserDto> finByName(@RequestParam String userName) {
        List<UserDto> results = service.finByname(userName);

        return results;
    }


}


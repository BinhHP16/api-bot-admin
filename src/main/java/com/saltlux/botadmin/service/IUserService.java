package com.saltlux.botadmin.service;

import com.saltlux.botadmin.dto.user.ListUserDto;
import com.saltlux.botadmin.dto.user.UserDto;
import com.saltlux.botadmin.entity.UserEntity;

import java.util.List;

public interface IUserService {
    List<UserEntity> getAll();
    List<ListUserDto> finAll();

    UserEntity findByUserId(Integer userId);

    UserDto finById(Integer userId);

    UserEntity findByEmailAndPassword(String email, String password);

    List<UserDto> finByname(String userName);


//    PagedResponse<UserEntity> getAll(int page, int size, String orderBy, String sortDirection);

}

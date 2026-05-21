package com.viheakode.api.service;

import com.viheakode.api.dto.request.AssignRoleToUserRequest;
import com.viheakode.api.dto.request.RemoveRoleFromUserRequest;
import com.viheakode.api.dto.request.UserRequest;
import com.viheakode.api.dto.response.UserDto;

import java.util.List;

public interface IUserService {
    UserDto create(UserRequest request);
    UserDto get(Long userId);
    List<UserDto> getAll();
    UserDto update(Long userId, UserRequest request);
    UserDto delete(Long userId);
    UserDto assignRoleToUser(Long userId, AssignRoleToUserRequest request);
    UserDto removeRoleFromUser(Long userId, RemoveRoleFromUserRequest request);
}

package com.viheakode.api.controller;

import com.viheakode.api.dto.request.AssignRoleToUserRequest;
import com.viheakode.api.dto.request.RemoveRoleFromUserRequest;
import com.viheakode.api.dto.request.UserRequest;
import com.viheakode.api.dto.response.UserDto;
import com.viheakode.api.service.serviceImp.UserServiceImp;
import com.viheakode.api.util.ApiResponseStructure;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImp userServiceImp;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody UserRequest request){
        UserDto userDto = userServiceImp.create(request);
        return ApiResponseStructure.responseSuccess("created", userDto, HttpStatus.CREATED);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<Object> get(@PathVariable Long userId){
        UserDto userDto = userServiceImp.get(userId);
        return ApiResponseStructure.responseSuccess("ok", userDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Object> getAll(){
        List<UserDto> userDtoList = userServiceImp.getAll();
        return ApiResponseStructure.responseSuccess("ok", userDtoList, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Object> update(@PathVariable Long userId, @Valid @RequestBody UserRequest request){
        UserDto userDto = userServiceImp.update(userId, request);
        return ApiResponseStructure.responseSuccess("updated", userDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> delete(@PathVariable Long userId){
        UserDto userDto = userServiceImp.delete(userId);
        return ApiResponseStructure.responseSuccess("Deleted", userDto, HttpStatus.OK);
    }

    @PostMapping("/{userId}/roles")
    public ResponseEntity<Object> assignRoleToUser(@PathVariable Long userId, @Valid @RequestBody AssignRoleToUserRequest request){
        UserDto userDto = userServiceImp.assignRoleToUser(userId, request);
        return ApiResponseStructure.responseSuccess("created", userDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}/roles")
    public ResponseEntity<Object> removeRoleFromUser(@PathVariable Long userId, @Valid @RequestBody RemoveRoleFromUserRequest request){
        UserDto userDto = userServiceImp.removeRoleFromUser(userId, request);
        return ApiResponseStructure.responseSuccess("success", userDto, HttpStatus.OK);
    }
}

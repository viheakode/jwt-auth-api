package com.viheakode.api.controller;

import com.viheakode.api.dto.request.AssignPermissionToRoleRequest;
import com.viheakode.api.dto.request.RemovePermissionFromRoleRequest;
import com.viheakode.api.dto.request.RoleRequest;
import com.viheakode.api.dto.response.RoleDto;
import com.viheakode.api.service.serviceImp.RoleServiceImp;
import com.viheakode.api.util.ApiResponseStructure;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleServiceImp roleServiceImp;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody RoleRequest request){
        RoleDto roleDto = roleServiceImp.create(request);
        return ApiResponseStructure.responseSuccess("created", roleDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> getAll(){
        List<RoleDto> roleDtoList = roleServiceImp.getAll();
        return ApiResponseStructure.responseSuccess("ok", roleDtoList, HttpStatus.OK);
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<Object> get(@PathVariable Long roleId){
        RoleDto roleDto = roleServiceImp.get(roleId);
        return ApiResponseStructure.responseSuccess("ok", roleDto, HttpStatus.OK);
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<Object> update(@PathVariable Long roleId, @Valid @RequestBody RoleRequest request){
        RoleDto roleDto = roleServiceImp.update(roleId, request);
        return ApiResponseStructure.responseSuccess("updated", roleDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<Object> delete(@PathVariable Long roleId){
        RoleDto roleDto = roleServiceImp.delete(roleId);
        return ApiResponseStructure.responseSuccess("deleted", roleDto, HttpStatus.OK);
    }

    @PostMapping("/{roleId}/permissions")
    public ResponseEntity<Object> assignPermissionToRole(@PathVariable Long roleId, @Valid @RequestBody AssignPermissionToRoleRequest request){
        RoleDto roleDto = roleServiceImp.assignPermissionToRole(roleId, request);
        return ApiResponseStructure.responseSuccess("created", roleDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{roleId}/permissions")
    public ResponseEntity<Object> removePermissionFromRole(@PathVariable Long roleId, @Valid @RequestBody RemovePermissionFromRoleRequest request){
        RoleDto roleDto = roleServiceImp.removePermissionFromRole(roleId, request);
        return ApiResponseStructure.responseSuccess("removed", roleDto, HttpStatus.OK);
    }

}

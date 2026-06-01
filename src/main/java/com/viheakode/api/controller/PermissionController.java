package com.viheakode.api.controller;

import com.viheakode.api.dto.request.PermissionRequest;
import com.viheakode.api.dto.response.PermissionDto;
import com.viheakode.api.service.serviceImp.PermissionServiceImp;
import com.viheakode.api.util.ApiResponseStructure;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/permissions")
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionServiceImp permissionServiceImp;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody PermissionRequest request){
        PermissionDto permissionDto = permissionServiceImp.create(request);
        return ApiResponseStructure.responseSuccess("created", permissionDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> getAll(){
        List<PermissionDto> permissionDtoList = permissionServiceImp.getAll();
        return ApiResponseStructure.responseSuccess("ok", permissionDtoList, HttpStatus.OK);
    }

    @GetMapping("/{permissionId}")
    public ResponseEntity<Object> get(@PathVariable Long permissionId){
        PermissionDto permissionDto = permissionServiceImp.get(permissionId);
        return ApiResponseStructure.responseSuccess("ok", permissionDto, HttpStatus.OK);
    }

    @PutMapping("/{permissionId}")
    public ResponseEntity<Object> update(@PathVariable Long permissionId, @Valid @RequestBody PermissionRequest request){
        PermissionDto permissionDto = permissionServiceImp.update(permissionId, request);
        return ApiResponseStructure.responseSuccess("updated", permissionDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{permissionId}")
    public ResponseEntity<Object> delete(@PathVariable Long permissionId){
        PermissionDto permissionDto = permissionServiceImp.delete(permissionId);
        return ApiResponseStructure.responseSuccess("deleted", permissionDto, HttpStatus.OK);
    }
}

package com.viheakode.api.controller;

import com.viheakode.api.dto.request.*;
import com.viheakode.api.service.serviceImp.PermissionServiceImp;
import com.viheakode.api.service.serviceImp.RoleServiceImp;
import com.viheakode.api.service.serviceImp.UserServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class HomeController {

    @GetMapping
    public String home(){
        return "System is running";
    }

    @GetMapping("/user")
    public String user(){
        return "User page";
    }

    @GetMapping("/admin")
    public String admin(){
        return "Admin page";
    }

    @GetMapping("/anonymous")
    public String anonymous(){
        return "Anonymous page";
    }

    private final PermissionServiceImp permissionServiceImp;
    private final RoleServiceImp roleServiceImp;
    private final UserServiceImp userServiceImpl;

    private final List<PermissionRequest> permissionRequestList = new ArrayList<>(List.of(
            new PermissionRequest("CREATE_PERMISSION", "CREATE_PERMISSION"),
            new PermissionRequest("READ_PERMISSION", "READ_PERMISSION"),
            new PermissionRequest("UPDATE_PERMISSION", "UPDATE_PERMISSION"),
            new PermissionRequest("DELETE_PERMISSION", "DELETE_PERMISSION"),

            new PermissionRequest("CREATE_ROLE", "CREATE_ROLE"),
            new PermissionRequest("READ_ROLE", "READ_ROLE"),
            new PermissionRequest("UPDATE_ROLE", "UPDATE_ROLE"),
            new PermissionRequest("DELETE_ROLE", "DELETE_ROLE"),
            new PermissionRequest("MODIFY_ROLE", "MODIFY_ROLE"),

            new PermissionRequest("CREATE_USER", "CREATE_USER"),
            new PermissionRequest("READ_USER", "READ_USER"),
            new PermissionRequest("UPDATE_USER", "UPDATE_USER"),
            new PermissionRequest("DELETE_USER", "DELETE_USER"),
            new PermissionRequest("MODIFY_USER", "MODIFY_USER")
    ));

    private final List<RoleRequest> roleRequestList = new ArrayList<>(List.of(
            new RoleRequest("SUPER_ADMIN", "SUPER_ADMIN"),
            new RoleRequest("ADMIN", "ADMIN"),
            new RoleRequest("USER", "USER")
    ));

    private final List<UserRequest> userRequestList = new ArrayList<>(List.of(
            new UserRequest("S.ADMIN", "s.admin@viheakode", "viheakode")
    ));

    private final List<AssignPermissionToRoleRequest> assignPermissionToRoleRequestList = new ArrayList<>(List.of(
            new AssignPermissionToRoleRequest(1L),
            new AssignPermissionToRoleRequest(2L),
            new AssignPermissionToRoleRequest(3L),
            new AssignPermissionToRoleRequest(4L),
            new AssignPermissionToRoleRequest(5L),
            new AssignPermissionToRoleRequest(6L),
            new AssignPermissionToRoleRequest(7L),
            new AssignPermissionToRoleRequest(8L),
            new AssignPermissionToRoleRequest(9L),
            new AssignPermissionToRoleRequest(10L),
            new AssignPermissionToRoleRequest(11L),
            new AssignPermissionToRoleRequest(12L),
            new AssignPermissionToRoleRequest(13L),
            new AssignPermissionToRoleRequest(14L)
    ));

    private final List<AssignRoleToUserRequest> assignRoleToUserRequestList = new ArrayList<>(List.of(
            new AssignRoleToUserRequest(1L)
    ));
    @GetMapping("/start")
    public String start(){

        for (PermissionRequest permissionRequest : permissionRequestList){
            permissionServiceImp.create(permissionRequest);
        }

        for (RoleRequest roleRequest : roleRequestList){
            roleServiceImp.create(roleRequest);
        }

        for (UserRequest userRequest : userRequestList){
            userServiceImpl.create(userRequest);
        }

        for (AssignPermissionToRoleRequest assignPermissionToRoleRequest : assignPermissionToRoleRequestList){
            roleServiceImp.assignPermissionToRole(1L, assignPermissionToRoleRequest);
        }

        for (AssignRoleToUserRequest assignRoleToUserRequest : assignRoleToUserRequestList){
            userServiceImpl.assignRoleToUser(1L, assignRoleToUserRequest);
        }
        return "System started";
    }
}

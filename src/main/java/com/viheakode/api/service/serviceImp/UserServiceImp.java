package com.viheakode.api.service.serviceImp;

import com.viheakode.api.dto.request.AssignRoleToUserRequest;
import com.viheakode.api.dto.request.RemoveRoleFromUserRequest;
import com.viheakode.api.dto.request.UserRequest;
import com.viheakode.api.dto.response.UserDto;
import com.viheakode.api.exception.DuplicateException;
import com.viheakode.api.exception.ResourceNotFoundException;
import com.viheakode.api.mapper.UserMapper;
import com.viheakode.api.model.Role;
import com.viheakode.api.model.User;
import com.viheakode.api.model.UserRole;
import com.viheakode.api.repository.RoleRepository;
import com.viheakode.api.repository.UserRepository;
import com.viheakode.api.repository.UserRoleRepository;
import com.viheakode.api.service.IUserService;
import com.viheakode.api.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    @Override
    @PreAuthorize("hasAuthority('CREATE_USER')")
    public UserDto create(UserRequest request) {
        String username = request.getUsername().toUpperCase();
        String email = request.getEmail().toLowerCase();
        if (userRepository.existsByUsername(username)){
            throw new DuplicateException("Username already exists");
        }

        if (userRepository.existsByEmail(email)){
            throw new DuplicateException("Email already exists");
        }

        User user = new User();
        user.setUuid(UUID.randomUUID().toString());
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPublisher(SecurityUtil.getPublisher());
        userRepository.save(user);

        Role role = roleRepository.findByRoleName("USER")
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        UserRole userRole = new UserRole();
        userRole.setUuid(UUID.randomUUID().toString());
        userRole.setUserId(user.getUserId());
        userRole.setRoleId(role.getRoleId());
        userRole.setPublisher(SecurityUtil.getPublisher());
        userRoleRepository.save(userRole);

        return userMapper.toDto(user);
    }

    @Override
    @PreAuthorize("hasAuthority('READ_USER')")
    public UserDto get(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return userMapper.toDto(user);
    }

    @Override
    @PreAuthorize("hasAuthority('READ_USER')")
    public List<UserDto> getAll() {
        List<User> userList = userRepository.findAll();
        return userList.stream().map(userMapper::toDto).toList();
    }

    @Override
    @PreAuthorize("hasAuthority('UPDATE_USER')")
    public UserDto update(Long userId, UserRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        String username = request.getUsername().toUpperCase();
        String email = request.getEmail().toLowerCase();
        if (userRepository.existsByUsername(username)){
            throw new DuplicateException("Username already exists");
        }

        if (userRepository.existsByEmail(email)){
            throw new DuplicateException("Email already exists");
        }

        user.setUsername(username);
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setModifiedDate(new Date());
        return userMapper.toDto(user);
    }

    @Override
    @PreAuthorize("hasAuthority('DELETE_USER')")
    public UserDto delete(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.delete(user);
        return userMapper.toDto(user);
    }

    @Override
    @PreAuthorize("hasAuthority('MODIFY_USER')")
    public UserDto assignRoleToUser(Long userId, AssignRoleToUserRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        boolean exist = userRoleRepository.existsByUserIdAndRoleId(userId, request.getRoleId());
        if (exist){
            throw new DuplicateException("Role already assigned to user");
        }

        UserRole userRole = new UserRole();
        userRole.setUuid(UUID.randomUUID().toString());
        userRole.setUserId(userId);
        userRole.setRoleId(request.getRoleId());
        userRole.setPublisher(SecurityUtil.getPublisher());
        userRoleRepository.save(userRole);
        return userMapper.toDto(user);
    }

    @Override
    @PreAuthorize("hasAuthority('MODIFY_USER')")
    public UserDto removeRoleFromUser(Long userId, RemoveRoleFromUserRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        boolean exist = userRoleRepository.existsByUserIdAndRoleId(userId, request.getRoleId());
        if (!exist){
            throw new ResourceNotFoundException("Role is not assigned to this user");
        }

        UserRole userRole = userRoleRepository.findByUserIdAndRoleId(userId, request.getRoleId());
        userRoleRepository.delete(userRole);
        return userMapper.toDto(user);
    }


}

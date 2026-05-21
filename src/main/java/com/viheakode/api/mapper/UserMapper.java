package com.viheakode.api.mapper;

import com.viheakode.api.dto.response.UserDto;
import com.viheakode.api.model.Role;
import com.viheakode.api.model.User;
import com.viheakode.api.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final RoleRepository roleRepository;

    public UserDto toDto(User user){

        if (user == null){
            return null;
        }

        UserDto dto = new UserDto();

        dto.setUserId(user.getUserId());
        dto.setUuid(user.getUuid());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());

        Set<String> roles = roleRepository.findRolesByUserId(user.getUserId())
                .stream()
                .map(Role::getRoleName)
                .collect(Collectors.toSet());

        dto.setRoles(roles);

        dto.setStatus(user.getStatus());
        dto.setPublisher(user.getPublisher());
        dto.setPublishedDate(user.getPublishedDate());
        dto.setModifiedDate(user.getModifiedDate());

        return dto;
    }
}

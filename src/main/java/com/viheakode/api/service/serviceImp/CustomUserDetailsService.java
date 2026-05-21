package com.viheakode.api.service.serviceImp;

import com.viheakode.api.model.Permission;
import com.viheakode.api.model.Role;
import com.viheakode.api.model.User;
import com.viheakode.api.repository.PermissionRepository;
import com.viheakode.api.repository.RoleRepository;
import com.viheakode.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        List<Role> roles =roleRepository.findRolesByUserId(user.getUserId());

        Set<GrantedAuthority> authorities = new HashSet<>();

        for (Role role : roles){
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
            List<Permission> permissions = permissionRepository.findPermissionsByRoleId(role.getRoleId());
            for (Permission permission : permissions){
                authorities.add(new SimpleGrantedAuthority(permission.getPermissionName()));
            }
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}

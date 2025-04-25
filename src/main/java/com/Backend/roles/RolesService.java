package com.Backend.roles;

import org.springframework.stereotype.Service;
import javax.management.relation.RoleNotFoundException;
import java.util.Collections;
import java.util.Set;

@Service
public class RolesService {
    private final RoleRepository roleRepository;

    public RolesService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getById(Long id) throws RoleNotFoundException{
        Role role = roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException("Role not found"));
        return role;
    }

    public Set<Role> assignDefaultRole(Long userId) throws RoleNotFoundException{

        Role defaultRole=roleRepository.findByName("Role_USER").orElseThrow(() -> new RoleNotFoundException("Default role (Role_USER) not found"));

        return Collections.singleton(defaultRole);
    }
}

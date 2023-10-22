package de.axone.service;

import de.axone.enums.RoleType;
import de.axone.model.Customer;
import de.axone.model.Role;
import de.axone.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role getRoleByName(RoleType roleType) {
        Optional<Role> role = roleRepository.findRoleByName(roleType);
        return role.orElse(null);
    }

    public Role createRole(Role role) {
        return roleRepository.saveAndFlush(role);
    }
}

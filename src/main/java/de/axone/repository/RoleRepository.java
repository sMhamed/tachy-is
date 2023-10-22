package de.axone.repository;

import de.axone.enums.RoleType;
import de.axone.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByName(RoleType roleType);
}

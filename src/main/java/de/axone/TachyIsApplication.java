package de.axone;

import de.axone.enums.RoleType;
import de.axone.model.Role;
import de.axone.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class TachyIsApplication implements CommandLineRunner {
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        if (roleRepository.count() == 0) {
            List<Role> defaultRoles = new ArrayList<>();

            // Add default roles
            defaultRoles.add(Role.builder()
                    .name(RoleType.ROLE_SUPER_ADMIN)
                    .description("Super Admin Role")
                    .build());

            defaultRoles.add(Role.builder()
                    .name(RoleType.ROLE_ADMIN)
                    .description("Admin Role")
                    .build());

            defaultRoles.add(Role.builder()
                    .name(RoleType.ROLE_OPERATOR)
                    .description("Operator Role")
                    .build());

            defaultRoles.add(Role.builder()
                    .name(RoleType.ROLE_INSTALLER)
                    .description("Installer Role")
                    .build());

            defaultRoles.add(Role.builder()
                    .name(RoleType.ROLE_USER)
                    .description("User Role")
                    .build());

            defaultRoles.add(Role.builder()
                    .name(RoleType.ROLE_VERIFIER)
                    .description("Verifier Role")
                    .build());

            defaultRoles.add(Role.builder()
                    .name(RoleType.ROLE_TECHNICAL_MANAGER)
                    .description("Technical Manager Role")
                    .build());

            // Save all default roles in a single call
            roleRepository.saveAll(defaultRoles);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(TachyIsApplication.class, args);
    }

}
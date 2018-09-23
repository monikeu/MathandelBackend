package mathandel.backend.component;

import mathandel.backend.exception.AppException;
import mathandel.backend.model.*;
import mathandel.backend.repository.EditionStatusRepository;
import mathandel.backend.repository.RoleRepository;
import mathandel.backend.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoaderComponent implements ApplicationRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EditionStatusRepository editionStatusRepository;

    public DataLoaderComponent(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, EditionStatusRepository editionStatusRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.editionStatusRepository = editionStatusRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        insertRolesToDB();
        insertEditionsToDB();
        insertAdminToDB();
    }

    private void insertAdminToDB() {
        User user = new User("admin", "admin", "admin", "admin@admin.admin", "admin");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));
        Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                .orElseThrow(() -> new AppException("Admin Role not set."));
        Role moderatorRole = roleRepository.findByName(RoleName.ROLE_MODERATOR)
                .orElseThrow(() -> new AppException("Moderator Role not set."));

        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        roles.add(adminRole);
        roles.add(moderatorRole);
        user.setRoles(roles);

        userRepository.save(user);
    }

    private void insertEditionsToDB() {
        for (EditionStatusName editionStatusName : EditionStatusName.values()) {
            EditionStatus editionStatus = new EditionStatus();
            editionStatus.setEditionStatusName(editionStatusName);
            editionStatusRepository.save(editionStatus);
        }
    }

    private void insertRolesToDB() {
        for (RoleName roleName : RoleName.values()) {
            Role role = new Role();
            role.setName(roleName);
            roleRepository.save(role);
        }
    }
}


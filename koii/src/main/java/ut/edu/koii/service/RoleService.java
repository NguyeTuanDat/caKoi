package ut.edu.koii.service;

import ut.edu.koii.models.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> getAllRoles();
    Optional<Role> getRoleById(Long id);
    Role saveRole(Role role);
    void deleteRoleById(Long id);
}

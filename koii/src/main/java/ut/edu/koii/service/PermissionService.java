package ut.edu.koii.service;

import ut.edu.koii.models.Permission;

import java.util.List;
import java.util.Optional;

public interface PermissionService {
    List<Permission> getAllPermissions();
    Optional<Permission> getPermissionById(Long id);
    Permission savePermission(Permission permission);
    void deletePermissionById(Long id);
}

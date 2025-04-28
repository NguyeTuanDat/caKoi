package ut.edu.koii.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ut.edu.koii.models.Permission;
import ut.edu.koii.repository.PermissionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    @Override
    public Optional<Permission> getPermissionById(Long id) {
        return permissionRepository.findById(id);
    }

    @Override
    public Permission savePermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public void deletePermissionById(Long id) {
        permissionRepository.deleteById(id);
    }
}

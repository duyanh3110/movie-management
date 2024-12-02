package vn.duynguyen.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import vn.duynguyen.model.Role;
import vn.duynguyen.repository.RoleRepository;

import java.util.List;

@Service
public record RoleService(RoleRepository roleRepository) {

    @PostConstruct
    public List<Role> findAll() {
        List<Role> roles = roleRepository.findAll();

        return roles;
    }
}

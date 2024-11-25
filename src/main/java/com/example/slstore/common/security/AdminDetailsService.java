package com.example.slstore.common.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.slstore.common.entity.Admin;
import com.example.slstore.common.repository.AdminRepository;

@Service
public class AdminDetailsService implements UserDetailsService {
    
    private final AdminRepository adminRepository;

    public AdminDetailsService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("Admin not found"));
        return new AdminDetails(admin);
    }
}

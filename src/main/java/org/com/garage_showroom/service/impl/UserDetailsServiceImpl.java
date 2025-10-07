package org.com.garage_showroom.service.impl;

import lombok.RequiredArgsConstructor;
import org.com.garage_showroom.common.enums.UserRole;
import org.com.garage_showroom.common.enums.UserStatus;
import org.com.garage_showroom.entity.User;


import org.com.garage_showroom.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // username = email
        final String email = username == null ? "" : username.trim();
        if (email.isEmpty()) {
            throw new UsernameNotFoundException("Email trống");
        }

        User user = userRepo.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy tài khoản với email: " + email));

        // Chỉ ADMIN đăng nhập bằng password; các role khác dùng OTP
        if (user.getRole() != UserRole.ADMIN) {
            throw new UsernameNotFoundException("Tài khoản này đăng nhập bằng OTP (không dùng mật khẩu)");
        }

        if (user.getPassword() == null || user.getPassword().isBlank()) {
            throw new UsernameNotFoundException("Tài khoản chưa được đặt mật khẩu");
        }

        boolean enabled = user.getStatus() == UserStatus.ACTIVE;
        List<GrantedAuthority> authorities =
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities(authorities)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(!enabled)
                .build();
    }
}

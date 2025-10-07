package org.com.garage_showroom.service.impl;

import lombok.RequiredArgsConstructor;
import org.com.garage_showroom.common.enums.UserRole;
import org.com.garage_showroom.common.enums.UserStatus;
import org.com.garage_showroom.dto.request.RegisterRequest;
import org.com.garage_showroom.entity.User;
import org.com.garage_showroom.repository.UserRepository;
import org.com.garage_showroom.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void registerCustomer(RegisterRequest req) {
        String email = req.getEmail().trim().toLowerCase();

        if (userRepo.existsByEmailIgnoreCase(email)) {
            throw new IllegalArgumentException("Email đã được sử dụng");
        }

        User user = User.builder()
                .email(email)
                .password(passwordEncoder.encode(req.getPassword()))
                .name(req.getName().trim())
                .phone(req.getPhone())
                .role(UserRole.CUSTOMER)
                .status(UserStatus.ACTIVE)
                .mechanicState(null)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        userRepo.save(user);
    }

}

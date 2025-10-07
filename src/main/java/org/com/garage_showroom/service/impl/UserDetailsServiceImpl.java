//package org.com.garage_showroom.service.impl;
//
//import org.com.garage_showroom.repository.UserRepository;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Collections;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    private final UserRepository userRepo;
//
//    public UserDetailsServiceImpl(UserRepository userRepo) {
//        this.userRepo = userRepo;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserEntity user = userRepo.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("Tài khoản sai"));
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(),
//                user.getPassword(),
//                user.isEnabled(),
//                true, true, true,
//                Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getName()))
//        );
//    }
//}
package org.com.garage_showroom.repository;

import org.com.garage_showroom.entity.User;
import org.com.garage_showroom.entity.UserOtp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.Optional;

public interface UserOtpRepository extends JpaRepository<UserOtp, Long> {

    // OTP mới nhất của user (đăng ký/đăng nhập đều dùng chung vì entity cũ không có "purpose")
    Optional<UserOtp> findTopByUserOrderByCreatedAtDesc(User user);

    // Đếm OTP trong khung thời gian để rate-limit (tuỳ chọn)
    @Query("""
        SELECT COUNT(o) FROM UserOtp o
        WHERE o.user = :user AND o.createdAt > :since
    """)
    long countRecent(@Param("user") User user, @Param("since") Instant since);
}
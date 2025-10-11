package org.com.garage_showroom.service.impl;

import lombok.RequiredArgsConstructor;
import org.com.garage_showroom.common.enums.UserRole;
import org.com.garage_showroom.common.enums.UserStatus;
import org.com.garage_showroom.dto.request.RegisterRequest;
import org.com.garage_showroom.dto.request.VerifyOtpRequest;
import org.com.garage_showroom.entity.User;
import org.com.garage_showroom.entity.UserOtp;
import org.com.garage_showroom.repository.UserOtpRepository;
import org.com.garage_showroom.repository.UserRepository;
import org.com.garage_showroom.service.RegistrationService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository userRepo;
    private final UserOtpRepository otpRepo;
    private final PasswordEncoder encoder;
    private final JavaMailSender mailSender;

    @Override
    public void register(RegisterRequest req) {
        String email = req.getEmail().trim().toLowerCase();

        User user = userRepo.findByEmailIgnoreCase(email).orElse(null);
        if (user == null) {
            user = User.builder()
                    .email(email)
                    .name(req.getName())
                    .phone(req.getPhone())
                    .password(encoder.encode(req.getPassword()))
                    .role(UserRole.CUSTOMER)
                    .status(UserStatus.INACTIVE)
                    .createdAt(Instant.now())
                    .updatedAt(Instant.now())
                    .build();
        } else {
            if (user.getStatus() != UserStatus.PENDING) {
                throw new IllegalArgumentException("Email đã tồn tại");
            }
            user.setName(req.getName());
            user.setPhone(req.getPhone());
            user.setPassword(encoder.encode(req.getPassword()));
            user.setUpdatedAt(Instant.now());
        }
        user = userRepo.save(user);

        // Rate limit (tuỳ chọn): tối đa 5 OTP / 1 giờ
        if (otpRepo.countRecent(user, Instant.now().minus(Duration.ofHours(1))) >= 5) {
            throw new IllegalStateException("Bạn tạo OTP quá nhiều lần. Vui lòng thử lại sau.");
        }

        String code = generate6Digits();
        UserOtp otp = UserOtp.builder()
                .user(user)
                .otpCode(code)
                .expiresAt(Instant.now().plus(Duration.ofMinutes(10)))
                .used(false)
                .createdAt(Instant.now())
                .build();
        otpRepo.save(otp);

        sendOtpEmail(user.getEmail(), user.getName(), code);
    }

    private String generate6Digits() {
        int n = ThreadLocalRandom.current().nextInt(0, 1_000_000);
        return String.format("%06d", n);
    }

    private void sendOtpEmail(String to, String name, String code) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setSubject("Mã OTP xác nhận đăng ký");
        msg.setText("""
            Xin chào %s,

            Mã OTP của bạn là: %s
            Hết hạn sau 10 phút. Không chia sẻ mã cho bất kỳ ai.
            """.formatted(name != null ? name : to, code));
        mailSender.send(msg);
    }

    @Transactional
    public void verifyRegisterOtp(VerifyOtpRequest req) {
        User user = userRepo.findByEmailIgnoreCase(req.email())
                .orElseThrow(() -> new IllegalArgumentException("Không tồn tại tài khoản"));

        UserOtp otp = otpRepo.findTopByUserOrderByCreatedAtDesc(user)
                .orElseThrow(() -> new IllegalArgumentException("Chưa có mã OTP. Hãy gửi lại."));

        if (otp.isUsed()) throw new IllegalArgumentException("Mã OTP đã được sử dụng");
        if (otp.getExpiresAt() != null && Instant.now().isAfter(otp.getExpiresAt()))
            throw new IllegalArgumentException("Mã OTP đã hết hạn");
        if (!otp.getOtpCode().equals(req.code()))
            throw new IllegalArgumentException("Mã OTP không đúng");

        // Kích hoạt tài khoản
        user.setStatus(UserStatus.ACTIVE);
        user.setUpdatedAt(Instant.now());
        userRepo.save(user);

        // Đánh dấu đã dùng
        otp.setUsed(true);
        otpRepo.save(otp);
    }
}

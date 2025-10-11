package org.com.garage_showroom.service;

import org.com.garage_showroom.dto.request.RegisterRequest;
import org.com.garage_showroom.dto.request.VerifyOtpRequest;

public interface  RegistrationService {
    void register(RegisterRequest req);
    void verifyRegisterOtp(VerifyOtpRequest req);
}

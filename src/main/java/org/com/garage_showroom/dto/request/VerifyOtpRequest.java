package org.com.garage_showroom.dto.request;

import lombok.Getter;
import lombok.Setter;


public record VerifyOtpRequest(String email, String code) {

}
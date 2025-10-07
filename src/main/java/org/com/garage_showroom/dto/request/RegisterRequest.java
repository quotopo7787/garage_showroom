package org.com.garage_showroom.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    private String email;

    @NotBlank(message = "Mật khẩu không được để trống")
    @Size(min = 6, max = 64, message = "Mật khẩu 6-64 ký tự")
    private String password;

    @NotBlank(message = "Tên không được để trống")
    private String name;

    @Size(max = 20, message = "Số điện thoại tối đa 20 ký tự")
    private String phone;
}
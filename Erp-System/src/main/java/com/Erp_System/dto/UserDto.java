package com.Erp_System.dto;

import com.Erp_System.validation.ImageValid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {

    private int userId;
    @Size(min = 3, max = 20, message = "Invalid Name !!")
    private String name;

    @Size(min = 5, max = 15, message = "Invalid Employee ID !!")
    private String employeeId;

    // @Email(message = "Invalid User Email..!!")
    @Pattern(regexp = "^[a-z0-9][-a-z0-9._]+@([-a-z0-8-9]+\\.)+[a-z]{2,5}$", message = "Invalid User Email...!!")
    @NotBlank(message = "Email is Required..!!")
    private String email;

    @Size(min = 4, max = 6, message = "Invalid Gender ..!!")
    private String gender;

    @Size(min = 10, max = 12, message = "Invalid Phone Number ..!!")
    private String phoneNumber;

    @NotBlank(message = "Write Something about yourself..!!")
    private String about;

    private Set<RoleDto> roles = new HashSet<>();

    @Size(min = 2, max = 20, message = "Invalid Domain ..!!")
    private String domain;

    @Size(min = 6, max = 100, message = "Invalid Address ..!!")
    private String address;

    @ImageValid
    private String imageName;

    @NotBlank(message = "Password is required..!!")
    private String password;
}

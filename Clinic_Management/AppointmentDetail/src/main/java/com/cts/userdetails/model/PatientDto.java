package com.cts.userdetails.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientDto {
    private long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String confirmPassword;
    private String email;
    private String dob;
    private String gender;
    private MultipartFile image;
}

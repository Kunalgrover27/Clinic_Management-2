package com.cts.userdetails.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="Patient")

public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String confirmPassword;
    @Column(unique = true)
    private String email;
    private String dob;
    private String gender;
    @Lob
    private byte[] image;

}


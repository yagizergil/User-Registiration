package com.registration.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Entity
public class User {

    @Column(name = "user_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name")
    private String name;
    @Column(name = "user_last_name")
    private String lastName;
    @Column(name = "user_password")
    private String password;
    @Column(name = "user_birth_date")
    private String birthDate;
    @Column(name = "user_email")
    private String email;

}

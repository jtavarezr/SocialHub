package com.example.SocialHub.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true, length = 50)
  private String email;

  @Column(nullable = false, length = 64)
  private String password;

  @Column(name = "first_name", nullable = false, length = 20)
  private String firstName;

  @Column(name = "last_name", nullable = false, length = 20)
  private String lastName;

  @Column(name = "verification_code", length = 64)
  private String verificationCode;

  private boolean enabled;
}

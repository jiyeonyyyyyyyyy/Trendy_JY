package com.trendy.login;

import java.security.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Long id;

	@Column(name="username", nullable = false)
	private String nickname;

	@Column(name="email", nullable = false)
	private String email;

	@Column(name="provider")
	private String provider;

	@Column(name="providerId")
	private String providerId;

	@Column(name="profileImageUrl")
	private String profileImageUrl;

	@Column(name="created_at", updatable = false)
	private Timestamp created_At;

	@Column(name="updated_at")
	private java.sql.Timestamp updated_At;
}
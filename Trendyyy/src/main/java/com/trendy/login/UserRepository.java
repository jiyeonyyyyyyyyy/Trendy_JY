package com.trendy.login;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // findById, etc. (기본 제공)
	User findByProviderAndProviderId(String provider, String providerId);
}
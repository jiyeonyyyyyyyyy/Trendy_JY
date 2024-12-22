package com.trendy.login;

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
    private String provider; // naver or kakao
    private String providerId; // 해당 플랫폼의 ID
    private String email;
    private String nickname;
    private String profileImageUrl;
    
}

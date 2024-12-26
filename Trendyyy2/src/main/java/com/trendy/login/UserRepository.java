package com.trendy.login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // 이메일로 사용자를 조회하는 메서드 (중복 체크나 로그인에 사용)
    User findByEmail(String email);


}

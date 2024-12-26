package com.trendy.login;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TimeService {

    private LocalDateTime updated_at;

    // 현재 시간을 '업데이트'에 저장하는 메서드
    public void updateCurrentTime() {
    	
    	// 현재 시간(LocalDateTime 생성)
        this.updated_at = LocalDateTime.now();
    }

    // 현재 '업데이트' 시간 반환
    public Timestamp getUpdateTime() {
    	
    	// LocalDateTime → Timestamp 변환
    	Timestamp timestamp = Timestamp.valueOf(updated_at);
        return timestamp;
    }
}

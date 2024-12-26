package com.trendy.login;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone_number;
    private String profile_image_url;

    @Enumerated(EnumType.STRING)
    private ReceiptType receipt_type = ReceiptType.NONE;

    private String receipt_card_number;
    private String bank_name;
    private String account_number;
    private String account_holder;

    @Column(updatable = false)
    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    @PrePersist
    protected void onCreate() {
        this.created_at = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.setUpdated_at(LocalDateTime.now());
    }

    public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getProfile_image_url() {
		return profile_image_url;
	}

	public void setProfile_image_url(String profile_image_url) {
		this.profile_image_url = profile_image_url;
	}

	public String getReceipt_card_number() {
		return receipt_card_number;
	}

	public void setReceipt_card_number(String receipt_card_number) {
		this.receipt_card_number = receipt_card_number;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public String getAccount_holder() {
		return account_holder;
	}

	public void setAccount_holder(String account_holder) {
		this.account_holder = account_holder;
	}

	public LocalDateTime getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}

	public enum ReceiptType {
        PERSONAL_DEDUCTION,
        BUSINESS_DEDUCTION,
        NONE
    }

	public void setUsername(String username2) {
		this.username=username2;		
	}

	public void setEmail(String email2) {
		this.email=email2;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profile_image_url=profileImageUrl;
	}
}

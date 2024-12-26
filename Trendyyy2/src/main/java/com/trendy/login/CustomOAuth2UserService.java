package com.trendy.login;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        Map<String, Object> attributes = oAuth2User.getAttributes();

        String email = null;
        String name = null;
        String profileImage = null;

        if ("kakao".equals(registrationId)) {
            // Kakao 사용자 정보 처리
            Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
            Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
            email = (String) kakaoAccount.get("email");
            name = (String) profile.get("nickname");
            profileImage = (String) profile.get("profile_image_url");
        } else if ("naver".equals(registrationId)) {
            // Naver 사용자 정보 처리
            Map<String, Object> response = (Map<String, Object>) attributes.get("response");

            // 정보가 없는 경우 null로 처리
            email = response.containsKey("email") ? (String) response.get("email") : null;
            name = response.containsKey("nickname") ? (String) response.get("nickname") : null;
            profileImage = response.containsKey("profile_image") ? (String) response.get("profile_image") : null;
        }

        // 사용자 정보 저장/업데이트
        saveOrUpdateUser(email, name, profileImage);
        return oAuth2User;
    }

    private void saveOrUpdateUser(String email, String name, String profileImage) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            user = new User();
            user.setEmail(email);
            user.setUsername(name);
            user.setProfileImageUrl(profileImage);
            userRepository.save(user);
        } else {
            // 기존 사용자 정보 업데이트 (필요 시)
            user.setUsername(name);
            user.setProfileImageUrl(profileImage);
            userRepository.save(user);
        }
    }
}

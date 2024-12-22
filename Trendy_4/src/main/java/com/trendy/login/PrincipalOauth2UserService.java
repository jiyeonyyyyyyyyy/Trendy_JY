package com.trendy.login;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;



@Component
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
	int option = 0; // 1이면 kakaotalk / 2면 naver
	
	
    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        Map<String, Object> attributes = oAuth2User.getAttributes();

        String provider = registrationId;
        String providerId;
        String nickname;
        String email;
        String profileImageUrl;

        if (provider.equals("naver")) {
            Map<String, Object> response = (Map<String, Object>) attributes.get("response");
            providerId = (String) response.get("id");
            nickname = (String) response.get("nickname");
            email = (String) response.get("email");
            profileImageUrl = (String) response.get("profile_image");
        } else { // kakao
            providerId = String.valueOf(attributes.get("id"));
            Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
            Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
            nickname = (String) profile.get("nickname");
            email = (String) kakaoAccount.get("email");
            profileImageUrl = (String) profile.get("profile_image_url");
        }

        User user = userRepository.findByProviderAndProviderId(provider, providerId);
        if (user == null) {
            user = new User();
            user.setProvider(provider);
            user.setProviderId(providerId);
            user.setNickname(nickname);
            user.setEmail(email);
            user.setProfileImageUrl(profileImageUrl);
            userRepository.save(user);
        }

        return oAuth2User;
    }
}

package com.example.dayte.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;


import java.util.Collection;
import java.util.Map;

@Getter
@Setter
@ToString
public class UserSecurityDTO extends User implements OAuth2User {

    private String userEmail;
    private String password;
    private String userName;
    private String nickName;
    private String phone;
    private String birthDate;
    private String gender;
    private String profileImagePath;
    private boolean del;

    // 소셜 로그인 시 넘어오는 정보들이 담겨있는 맵 객체
    private Map<String, Object> props;

    public UserSecurityDTO(String userEmail, String password, String userName,
                           String nickName, String phone, String birthDate,
                           String gender, boolean del,String profileImagePath,
                           Collection<? extends GrantedAuthority> authorities) {

        super(userEmail,password,authorities);

        this.userEmail =userEmail;
        this.password = password;
        this.nickName = nickName;
        this.userName = userName;
        this.phone = phone;
        this.birthDate = birthDate;
        this.gender = gender;
        this.del = del;
        this.profileImagePath = profileImagePath;


    }

    public Map<String, Object> getAttributes() {
        return this.props;
    }

    @Override
    public String getName() {
        return this.userName;
    }
}

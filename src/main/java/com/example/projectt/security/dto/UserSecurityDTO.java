package com.example.projectt.security.dto;

import com.example.projectt.members.domain.RoleType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;


import java.util.Collection;

@Getter
@Setter
@ToString
public class UserSecurityDTO extends User{

    private String userEmail;
    private String password;
    private String nickName;
    private String userName;
    private String phone;
    private String birthDate;
    private String gender;
    private boolean del;
    private RoleType role;


    public UserSecurityDTO(String userEmail, String password, String nickName, String userName, String phone,
                           String birthDate, String gender, boolean del, Collection<? extends GrantedAuthority> authorities) {
        super(userEmail,password,authorities);


        this.userEmail =userEmail;
        this.password = password;
        this.nickName = nickName;
        this.userName = userName;
        this.phone = phone;
        this.birthDate = birthDate;
        this.gender = gender;
        this.del = del;


    }


}

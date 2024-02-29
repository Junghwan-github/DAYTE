package com.example.dayte.security.service;


import com.example.dayte.members.domain.RoleType;
import com.example.dayte.members.domain.User;
import com.example.dayte.members.dto.UserDTO;
import com.example.dayte.members.persistence.DeleteUserRepository;
import com.example.dayte.members.persistence.DormancyRepository;
import com.example.dayte.members.persistence.UserRepository;
import com.example.dayte.security.dto.UserSecurityDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final DormancyRepository dormancyRepository;
    private final DeleteUserRepository deleteUserRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 요청 아이디에 해당하는 회원이 있는지 조회 (DB => domain.User)
        Optional<User> result = userRepository.findById(username);

        if (result.isEmpty())  // 해당 아이디를 가진 사용자가 없다면
            throw new UsernameNotFoundException("아이디 또는 비밀번호가 맞지 않습니다.");

        // user = DB 에서 조회해 온 회원의 엔티티
        User user = result.get();
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        if (user.getRole() == RoleType.DORMANCY)
            throw new DisabledException("귀하의 계정은 " +
                    dormancyRepository.findById(user.getUserEmail()).get().getDormancyDate() +
                    " 부로 휴면 상태로 전환된 계정입니다."); // 계정 비활성화
        else if (user.getRole() == RoleType.BLOCK) {
            if(user.getBlockDate().toLocalDateTime().isAfter(LocalDateTime.now())) {
                throw new LockedException("귀하의 계정은 사용 정지되어 " +
                        user.getBlockDate().toLocalDateTime().getYear() + "년 " +
                        user.getBlockDate().toLocalDateTime().getMonthValue() + "월 " +
                        user.getBlockDate().toLocalDateTime().getDayOfMonth() + "일 " +
                        user.getBlockDate().toLocalDateTime().getHour() + "시 " +
                        user.getBlockDate().toLocalDateTime().getMinute() + "분" +
                        " 이후부터 사용 가능한 계정입니다."); // 계정 잠김
            } else {
                userDTO.setRole(RoleType.USER);
                userDTO.setBlockDate(null);
            }
        }
        else if (user.isDel())
            throw new AccountExpiredException("귀하의 계정은 " +
                    deleteUserRepository.findByUserEmail(user.getUserEmail()).get().getDeleteDate() +
                    " 부로 삭제된 계정입니다."); // 계정 만료

        userDTO.setLoginDate(LocalDate.now());
        if (user.isNotification())
            userDTO.setNotification(false);
        
        userRepository.save(modelMapper.map(userDTO, User.class));
        // 전송 객체인 DTO 를 이용하여 회원 정보 반환
        return new UserSecurityDTO(
                user.getUserEmail(),
                user.getPassword(),
                user.getUserName(),
                user.getNickName(),
                user.getPhone(),
                user.getBirthDate(),
                user.getGender(),
                user.isDel(),
                user.getProfileImagePath(),
                user.isSocial(),
                Arrays.asList(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))

        );
    }

}




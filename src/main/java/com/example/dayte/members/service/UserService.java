package com.example.dayte.members.service;

import com.example.dayte.members.domain.DeleteUser;
import com.example.dayte.members.domain.RoleType;
import com.example.dayte.members.domain.User;
import com.example.dayte.members.dto.UserDTO;
import com.example.dayte.members.persistence.DeleteUserRepository;
import com.example.dayte.members.persistence.UserRepository;
import com.example.dayte.security.dto.UserSecurityDTO;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DeleteUserRepository deleteUserRepository;

    private static final String contentsImageUploadPath = "/temp/images/user/profileImages/";

    @Transactional
    public void insertUser(User user) {
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User newUser(String userEmail) {
        // 회원가입 시 회원이 없으면 새로 등록하게
        User findUser = userRepository.findByUserEmail(userEmail).orElseGet(User::new);
        return findUser;
    }

    @Transactional(readOnly = true)
    public User getUser(String userEmail) {
        // 회원 불러올 때 없으면 에러메시지
        User findUser = userRepository.findByUserEmail(userEmail).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        return findUser;
    }

    @Transactional
    public Page<User> userList(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
    // 탈퇴 회원 수

    @Transactional
    public Page<User> delList(Pageable pageable) {
        boolean delCheck = true;
        return userRepository.findByDel(delCheck, pageable);
    }

    @Transactional
    public Page<User> userListByUserName(String userName, Pageable pageable) {
        return userRepository.findByUserName(userName, pageable);
    }

    @Transactional
    public Page<User> userListByUserEmail(String userEmail, Pageable pageable) {
        return userRepository.findByUserEmail(userEmail, pageable);
    }

    @Transactional
    public Page<User> userListByNickName(String nickName, Pageable pageable) {
        return userRepository.findByNickName(nickName, pageable);
    }

    @Transactional
    public Page<User> userListByRole(String role, Pageable pageable) {
        RoleType roleType = convertRole(role);
        return userRepository.findByRole(roleType, pageable);
    }

    @Transactional
    public Page<User> userListByPhone(String phone, Pageable pageable) {
        return userRepository.findByPhone(phone, pageable);
    }

    // 회원 수정
    @Transactional
    public boolean updateUser(UserDTO userDTO) {
        User findUser = userRepository.findByUserEmail(userDTO.getUserEmail()).get();

        userDTO.setUserEmail(findUser.getUserEmail());
        userDTO.setBirthDate(findUser.getBirthDate());
        userDTO.setJoinDate(findUser.getJoinDate());
        if(userDTO.getProfileImagePath() == null){
            userDTO.setProfileImageName(findUser.getProfileImageName());
            userDTO.setProfileImagePath(findUser.getProfileImagePath());
        }
        // 비밀번호란이 비어있다면 그대로, 입력되어있으면 변경
        if ("".equals(userDTO.getPassword()))
            userDTO.setPassword(findUser.getPassword());
        else
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        if (!userDTO.getNickName().equals(findUser.getNickName())) {
            // 새 닉네임 입력시
            if (!userDTO.getNickName().isEmpty() && !nickNameChk(userDTO.getNickName())) {
                userDTO.setNickName(userDTO.getNickName());
                userRepository.save(modelMapper.map(userDTO, User.class));
                return true;
            } else {
                return false;
            }
        } else {
            userDTO.setNickName(userDTO.getNickName());
            userRepository.save(modelMapper.map(userDTO, User.class));
            return true;
        }

    }

    // 관리자페이지 권한 검색
    public static RoleType convertRole(String role) {
        try {
            String roleName = "";
            if (role.equals("유저") || role.toUpperCase().equals("USER")) {
                roleName = "USER";
            } else if (role.equals("관리자") || role.toUpperCase().equals("ADMIN")) {
                roleName = "ADMIN";
            } else if (role.equals("휴면") || role.toUpperCase().equals("DORMANCY")) {
                roleName = "DORMANCY";
            } else if (role.equals("정지") || role.toUpperCase().equals("BLOCK")) {
                roleName = "BLOCK";
            }
            return RoleType.valueOf(roleName.toUpperCase());

        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IllegalArgumentException("Invalid RoleType value");
        }
    }

    public void profileImage(UserDTO userDTO) {
        try {
            String uuid = UUID.randomUUID().toString();
            // 이미지 파일을 저장할 디렉토리 경로 설정
            Path path = Path.of("\\\\192.168.10.203" + contentsImageUploadPath);
            // 디렉토리가 존재하지 않으면 생성
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            String encodedFileName = UriUtils.encode(Objects.requireNonNull(userDTO.getImage().getOriginalFilename()), StandardCharsets.UTF_8);
            String fileName = uuid + "_" + encodedFileName;

            Path targetPath = Path.of(path + "/" + (uuid + "_" + userDTO.getImage().getOriginalFilename()));
            Files.copy(userDTO.getImage().getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            // UserDTO에 파일명과 경로 설정
            userDTO.setProfileImageName(fileName);
            userDTO.setProfileImagePath(contentsImageUploadPath + fileName);
        } catch (IOException e) {
            ;
            ;
        }
    }

    @Transactional
    public User modifyUser(UserSecurityDTO userSecurityDTO, UserDTO userDTO) {
        User findUser = userRepository.findByUserEmail(userSecurityDTO.getUserEmail()).get();

        if (userDTO.getNickName() != null && !nickNameChk(userDTO.getNickName()))
            findUser.setNickName(userDTO.getNickName());


        // 전화번호가 존재하지 않는 경우 사용자 정보에서 가져오도록 함
        String phone = userDTO.getPhone() != null ? userDTO.getPhone() : userSecurityDTO.getPhone();

        findUser.setPhone(phone);
        if (userDTO.getImage() != null) {
            findUser.setProfileImagePath(userDTO.getProfileImagePath());
            findUser.setProfileImageName(userDTO.getProfileImageName());
        }

        return findUser;
    }

    public boolean nickNameChk(String nickName) {
        return userRepository.existsByNickName(nickName);
    }

    // 사용자 - 비밀번호 변경
    @Transactional
    public User checkPassword(UserSecurityDTO principal, String rawNewPwd) {
        User findUser = userRepository.findByUserEmail(principal.getUserEmail()).orElseThrow(() -> {
            return new IllegalArgumentException("회원 찾기 실패");
        });

        findUser.setPassword(passwordEncoder.encode(rawNewPwd));

        return findUser;
    }

    // 회원 정보 삭제 ( 이메일, 탈퇴여부 외 정보 삭제)
    @Transactional
    public boolean testDelUser(String userEmail) {
        User findUser = userRepository.findByUserEmail(userEmail).orElseThrow(() -> {
            return new IllegalArgumentException("회원 찾기 실패");
        });

        UserDTO userDTO = new UserDTO();
        userDTO.setUserEmail(findUser.getUserEmail());
        userDTO.setPassword(passwordEncoder.encode("1111"));
        userDTO.setUserName(RandomStringUtils.random(5, true, true));
        userDTO.setNickName(RandomStringUtils.random(10, true, true));
        userDTO.setPhone("010-9999-9999");
        userDTO.setBirthDate("00000000");
        userDTO.setRole(RoleType.USER);
        userDTO.setGender("other");
        userDTO.setDel(true);
        userRepository.save(modelMapper.map(userDTO, User.class));

        DeleteUser deleteUser = DeleteUser.builder()
                .userEmail(userEmail)
                .deleteDate(LocalDate.now())
                .build();
        deleteUserRepository.save(deleteUser);

        return true;

    }

    @Transactional
    public List<User> getRecentUsers(int count) {
        return userRepository.findTopByOrderByJoinDateDesc(count);
    }
}

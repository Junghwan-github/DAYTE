package com.example.dayte.members.service;

import com.example.dayte.members.domain.RoleType;
import com.example.dayte.members.domain.User;
import com.example.dayte.members.dto.UserDTO;
import com.example.dayte.members.persistence.UserRepository;
import com.example.dayte.security.dto.UserSecurityDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public void insertUser(User user) {
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User getUser(String userEmail) {
        User findUser = userRepository.findByUserEmail(userEmail).orElseGet(User::new);
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

    //    회원 수정
    @Transactional
    public void updateUser(UserDTO userDTO) {
        User findUser = userRepository.findByUserEmail(userDTO.getUserEmail()).get();

        userDTO.setUserEmail(findUser.getUserEmail());
        userDTO.setRole(findUser.getRole());
        userDTO.setBirthDate(findUser.getBirthDate());
        userDTO.setBirthDate(findUser.getBirthDate());
        userDTO.setJoinDate(findUser.getJoinDate());

        User user = modelMapper.map(userDTO, User.class);

        userRepository.save(user);
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

    private static final String contentsImageUploadPath = "/temp/images/user/profileImages/";

    public void profileImage(UserDTO userDTO) {
        try {
            String uuid = UUID.randomUUID().toString();
            // 이미지 파일을 저장할 디렉토리 경로 설정
            Path path = Path.of("\\\\192.168.10.75" + contentsImageUploadPath);
            // 디렉토리가 존재하지 않으면 생성
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            String encodedFileName = UriUtils.encode(Objects.requireNonNull(userDTO.getImage().getOriginalFilename()), StandardCharsets.UTF_8);
            String fileName = uuid + "_" + encodedFileName;

            Path targetPath = Path.of(path + "/" + (uuid + "_" + userDTO.getImage().getOriginalFilename()));
            System.out.println(targetPath);
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
        if (userDTO.getNickName() != null && !nickNameChk(userDTO.getNickName())) {
            findUser.setNickName(userDTO.getNickName());
        }

        // 전화번호가 존재하지 않는 경우 사용자 정보에서 가져오도록 함
        String phone = userDTO.getPhone() != null ? userDTO.getPhone() : userSecurityDTO.getPhone();

        findUser.setPhone(phone);
        if (userDTO.getImage() != null && !userDTO.getImage().isEmpty()) {
            findUser.setProfileImagePath(userDTO.getProfileImagePath());
            findUser.setProfileImageName(userDTO.getProfileImageName());
        }

        return findUser;
    }

    @Transactional
    public void deleteUser(String userEmail) {
        userRepository.deleteById(userEmail);
    }

    public boolean nickNameChk(String nickName) {
        return userRepository.existsByNickName(nickName);
    }
}

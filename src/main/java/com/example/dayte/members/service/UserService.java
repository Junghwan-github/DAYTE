package com.example.dayte.members.service;

import com.example.dayte.members.domain.RoleType;
import com.example.dayte.members.domain.User;
import com.example.dayte.members.dto.UserDTO;
import com.example.dayte.members.persistence.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public void insertUser(User user){
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


    public static RoleType convertRole(String role) {
        try {
            String roleName = "";
            if (role.equals("유저") || role.toUpperCase().equals("USER")) {
                roleName = "USER";
            } else if (role.equals("관리자") || role.toUpperCase().equals("ADMIN")) {
                roleName = "ADMIN";
            } else if (role.equals("휴면") || role.toUpperCase().equals("DORMANCY")) {
                roleName = "DORMANCY";
            }  else if (role.equals("정지") || role.toUpperCase().equals("BLOCK")) {
                roleName = "BLOCK";
            }
            return RoleType.valueOf(roleName.toUpperCase());

        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IllegalArgumentException("Invalid RoleType value");
        }
    }
    private static final String contentsImageUploadPath = "/temp/images/user/profileImages/";

    public void profileImage(MultipartFile image, UserDTO userDTO) throws IOException {
        // 이미지 파일을 저장할 디렉토리 경로 설정
        Path path = Path.of("\\\\192.168.10.75"+this.contentsImageUploadPath);
        System.out.println("===============" + path);
        // 디렉토리가 존재하지 않으면 생성
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        // 이미지 파일을 서버에 저장
        String fileName = userDTO.getUserEmail() + "_" + System.currentTimeMillis() + ".jpg"; // 파일명을 고유하게 설정
        Path filePath = path.resolve(fileName);
        Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        // UserDTO에 파일명과 경로 설정
        userDTO.setProfileImageName(fileName);
        userDTO.setProfileImagePath(contentsImageUploadPath + fileName);
    }

    @Transactional
    public void modifyUser(UserDTO userDTO){
        User findUser = userRepository.findByUserEmail(userDTO.getUserEmail()).get();
        findUser.setNickName(userDTO.getNickName());
        findUser.setProfileImagePath(userDTO.getProfileImagePath());
        findUser.setProfileImageName(userDTO.getProfileImageName());
    }
}

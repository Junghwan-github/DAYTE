
package com.example.dayte.members.service;

import com.example.dayte.members.domain.DeleteUser;
import com.example.dayte.members.domain.Dormancy;
import com.example.dayte.members.domain.RoleType;
import com.example.dayte.members.domain.User;
import com.example.dayte.members.dto.UserDTO;
import com.example.dayte.members.persistence.DeleteUserRepository;
import com.example.dayte.members.persistence.DormancyRepository;
import com.example.dayte.members.persistence.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SchedulerService {

    private final UserRepository userRepository;
    private final DormancyRepository dormancyRepository;
    private final DeleteUserRepository deleteUserRepository;
    private final JavaMailSender javaMailSender;
    private final ModelMapper modelMapper;
    private LocalDate now = LocalDate.now();

    @Scheduled(cron = "0 0 0 * * *", zone="Asia/Seoul")
    public void dormancyTest() {

        LocalDate oneYearAgo = this.now.minusYears(1L); // 일년 뒤 날짜와 마지막 로그인 날짜가 같으면 이메일 발송하는 메서드를 위한 값
        LocalDate elevenMonthAgo = oneYearAgo.plusMonths(1L); // 현재 날짜보다 1달 이전에 이메일을 보내기 위한 값

        List<User> dormancyWarningUserList = userRepository.findByLoginDateBetweenAndRoleAndNotification(oneYearAgo, elevenMonthAgo, RoleType.USER, false);
        // 휴면계정 전환 1달 전 메세지 고지
        if (!dormancyWarningUserList.isEmpty())
            dormancyWarningUserSendEmail(dormancyWarningUserList);


        // 휴면계정 전환
        List<User> dormancyUserList = userRepository.findByLoginDateLessThanEqualAndRole(oneYearAgo, RoleType.USER);
        if (!dormancyUserList.isEmpty())
            changeDormancyUser(dormancyUserList);

        // 휴면계정 전환 후 1년이 지나면 데이터 삭제
        List<Dormancy> deleteDormancyUserList = dormancyRepository.findByDormancyDateLessThanEqual(oneYearAgo);
        if (!deleteDormancyUserList.isEmpty())
            deleteDormancyUser(deleteDormancyUserList);

        // 계정 삭제 이후 6개월 지나면 DeleteUser, User 테이블에서 삭제
        List<DeleteUser> deleteUserList = deleteUserRepository.findAllByDeleteDateLessThanEqual(this.now.minusDays(7L));
        if (deleteUserList.isEmpty())
            deleteUser(deleteUserList);
    }

    // 계정 삭제 이후 6개월 지나면 DeleteUser, User 테이블에서 삭제하는 메서드
    private void deleteUser(List<DeleteUser> deleteUserList) {
        deleteUserList.forEach(deleteUser -> userRepository.deleteById(deleteUser.getUserEmail()));
        deleteUserRepository.deleteAll(deleteUserList);

    }

    // 휴면계정 전환 후 1년이상 지난 User 데이터 삭제
    @Transactional
    public void deleteDormancyUser(List<Dormancy> deleteDormancyUserList) {

        deleteDormancyUserList.forEach(deleteDormancyUser ->
                userRepository.deleteById(deleteDormancyUser.getUserEmail())
        );
        dormancyRepository.deleteAll(deleteDormancyUserList);

    }

    // 휴면계정 전환
    @Transactional
    public void changeDormancyUser(List<User> dormancyUserList) {

        List<String> dormancyUserEmailList = new ArrayList<>();
        for (int i = 0; i < dormancyUserList.size(); i++)
            dormancyUserEmailList.add(dormancyUserList.get(i).getUserEmail());

        // 휴면계정 전환 이메일 발송
        dormancyUserSendEmail(dormancyUserEmailList);

        // 휴면계정 전환
        dormancyUserList.forEach(dormancyUser -> {
            Dormancy dormancy = Dormancy.builder()
                    .userEmail(dormancyUser.getUserEmail())
                    .password(dormancyUser.getPassword())
                    .userName(dormancyUser.getUserName())
                    .nickName(dormancyUser.getNickName())
                    .phone(dormancyUser.getPhone())
                    .birthDate(dormancyUser.getBirthDate())
                    .gender(dormancyUser.getGender())
                    .role(RoleType.DORMANCY)
                    .del(dormancyUser.isDel())
                    .social(dormancyUser.isSocial())
                    .joinDate(dormancyUser.getJoinDate())
                    .profileImageName(dormancyUser.getProfileImageName())
                    .profileImagePath(dormancyUser.getProfileImagePath())
                    .dormancyDate(this.now)
                    .build();
            dormancyRepository.save(dormancy);

            UserDTO userDTO = new UserDTO();
            userDTO.setUserEmail(dormancyUser.getUserEmail());
            userDTO.setPassword(dormancyUser.getPassword());
            userDTO.setUserName("Dormancy User");
            userDTO.setNickName(RandomStringUtils.random(10, true, true));
            userDTO.setPhone("010-9999-9999");
            userDTO.setBirthDate("0000.00.00");
            userDTO.setRole(RoleType.DORMANCY);
            userRepository.save(modelMapper.map(userDTO, User.class));
        });
    }

    // 휴면계정 전환 예정 이메일 발송
    private void dormancyWarningUserSendEmail(List<User> userList) {

        List<String> userEmailList = new ArrayList<>();

        userList.forEach(user -> {
            user.setNotification(true);
            userEmailList.add(user.getUserEmail());
        });

        try {
            for (int i = 0; i < userEmailList.size(); i++) {
                MimeMessage message = javaMailSender.createMimeMessage();
                message.setRecipients(MimeMessage.RecipientType.TO, userEmailList.get(i));// userEmailList
                message.setSubject("휴면계정 전환 예정 안내");
                String sendDormancyMail =
                        "<h1>[DAYTE] 휴면계정 전환 예정 안내</h1> <br> " +
                                "<h3> 고객님에게 좋은 여행일정을 제공할 수 있도록 노력하는 DAYTE입니다. <br> " +
                                userEmailList.get(i) + "님은 최근 1년간 DAYTE 이용이력이 없어 " +
                                this.now.getYear() + "년" +
                                this.now.getMonthValue() + "월" +
                                this.now.getDayOfMonth() + "일 " +
                                "이후 휴면계정으로 전환될 예정입니다. <br>" +
                                "휴면상태를 원하지 않으시면 " +
                                this.now.getYear() + "년" +
                                this.now.getMonthValue() + "월" +
                                this.now.getDayOfMonth() + "일 " +
                                "이전까지 DAYTE 홈페이지에 로그인을 해 주시기 바랍니다.<h3> <br>" +
                                "<h3>또한 휴면계정 전환 후 1년이 경과될 시 "+
                                "고객님의 계정이 삭제되니 주의하시길 바랍니다.</h3>";
                message.setText(sendDormancyMail, "UTF-8", "html");
                javaMailSender.send(message);
            }
        } catch (MessagingException e) {
            ;;
        }
    }

    // 휴면계정 전환 이메일 발송
    private void dormancyUserSendEmail(List<String> userEmailList) {
        MimeMessage message = javaMailSender.createMimeMessage();
        userEmailList.forEach(userEmail -> {
            try {
                message.setRecipients(MimeMessage.RecipientType.TO, userEmail);
                message.setSubject("휴면계정 전환 안내");
                String sendDormancyMail =
                        "<h1>[DAYTE] 휴면계정 전환 안내</h1> <br> " +
                                "<h3> 고객님에게 좋은 여행일정을 제공할 수 있도록 노력하는 DAYTE입니다. <br> " +
                                userEmail + "님은 최근 1년간 DAYTE 이용이력이 없어 " +
                                this.now.getYear() + "년" +
                                this.now.getMonthValue() + "월" +
                                this.now.getDayOfMonth() + "일 " +
                                "부로 휴면계정으로 전환되었습니다. <br>" +
                                "개인정보 보호를 위해 고객님의 계정은 휴면계정으로 전환될 예정임을 안내드립니다. <br>" +
                                "관련 법령에 따라 고객님의 개인정보는 다른 이용자의 개인정보와 분리하여 <br>" +
                                "1년간 별도 저장, 관리 되고 1년 이후 분리된 고객님의 데이터는 폐기될 예정입니다.";
                message.setText(sendDormancyMail, "UTF-8", "html");
                javaMailSender.send(message);
            } catch (MessagingException e) {
                ;;
            }
        });
    }

}

package com.example.dayte.notice.controller;


import com.example.dayte.members.domain.RoleType;
import com.example.dayte.members.domain.User;
import com.example.dayte.notice.controller.advice.FileUtils;
import com.example.dayte.notice.domain.FilesInfo;
import com.example.dayte.notice.domain.Notice;
import com.example.dayte.notice.dto.NoticeDTO;
import com.example.dayte.notice.dto.ResponseDTO;
import com.example.dayte.notice.service.NoticeService;
import com.example.dayte.security.service.CustomUserDetailsService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.file.ConfigurationSource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Log4j2
@Controller
public class NoticeController {

    @Autowired
    private FileUtils fileUtils;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @GetMapping("/notice")
    public String getNoticeList(Model model, @PageableDefault(size = 10, sort = "no", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<Notice> noticeListPage = noticeService.getNoticeList(pageable);
        int totalPages = noticeListPage.getTotalPages();

        // 목록 하단 페이지 번호의 노출 개수
        int pageSize = 5;

        int startPage = Math.max(0, (pageable.getPageNumber() / pageSize) * pageSize);
        int endPage = Math.min(startPage + pageSize - 1, totalPages - 1);


        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        model.addAttribute("noticeList", noticeService.getNoticeList(pageable));


        return "notice/notice";
    }

    @GetMapping("/notice/modAll")
    public String modAllNotice(Model model, @PageableDefault(size = 5, sort = "no", direction = Sort.Direction.DESC) Pageable pageable) {

        List<Notice> trueNotices = noticeService.findTrueNotice();
        model.addAttribute("trueNotices", trueNotices);

        Page<Notice> defaultNotices = noticeService.findDefaultNotice(pageable);
        model.addAttribute("defaultNotices", noticeService.findDefaultNotice(pageable));


        int totalPages = defaultNotices.getTotalPages();

        // 목록 하단 페이지 번호의 노출 개수
        int pageSize = 5;

        int startPage = Math.max(0, (pageable.getPageNumber() / pageSize) * pageSize);
        int endPage = Math.min(startPage + pageSize - 1, totalPages - 1);


        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);


        return "notice/modNotices";
    }


    @GetMapping("/notice/createNotice")
    public String createNotice(Model model) {

        List<Notice> trueNotices = noticeService.findTrueNotice();
        model.addAttribute("trueNotices", trueNotices);

        return "notice/createNotice";
    }

    //공지사항 추가
//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/notice/createNotice")
    public @ResponseBody ResponseDTO<?> createNotice(
            @RequestPart("title") String title,
            @RequestPart("content") String content,
            @RequestPart("files")List<MultipartFile> files) {

       /* System.out.println("Title: " + title);
        System.out.println("Content: " + content);*/

        for (MultipartFile file : files) {
            // 각 파일에 대한 처리 로직 수행
            if(!file.isEmpty()) {
                   /*System.out.println("Received file: " + file.getOriginalFilename());
                System.out.println("파일 크기 : " + (double)file.getSize()/(1024*1024));*/
            }
        }

        List<FilesInfo> filesInfos = fileUtils.uploadFiles(files);
        NoticeDTO noticeDTO = new NoticeDTO(title, content);

        Notice notice = modelMapper.map(noticeDTO, Notice.class);

        noticeService.createNotice(notice, filesInfos);





        /*List<FilesInfo> fileCollection = fileUtils.uploadFiles(files);*/



        /*if (bindingResult.hasErrors()) {
            Map<String, String> errMap = new HashMap<>();

            for (FieldError err : bindingResult.getFieldErrors())
                errMap.put(err.getField(), err.getDefaultMessage());

            return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), errMap);
        }

        Notice notice = modelMapper.map(noticeDTO, Notice.class);

        noticeService.createNotice(notice);*/

        return new ResponseDTO<>(HttpStatus.OK.value(), "새로운 공지사항을 등록했습니다.");

    }

    //공지사항 상세보기
    @Transactional(readOnly = true)
    @GetMapping("/notice/{id}")
    public String getNotice(@PathVariable int id, Model model, Pageable pageable, HttpSession session) {
        model.addAttribute("notice", noticeService.getNotice(id));




        model.addAttribute("nowId", id);

        Optional<Notice> nextNotice = noticeService.getNextNotice(id);
        model.addAttribute("nextId", nextNotice.map(Notice::getNo).orElse(null));
        model.addAttribute("nextTitle", nextNotice.map(Notice::getTitle).orElse(null));


        Optional<Notice> prevNotice = noticeService.getPrevNotice(id);
        model.addAttribute("prevId", prevNotice.map(Notice::getNo).orElse(null));
        model.addAttribute("prevTitle", prevNotice.map(Notice::getTitle).orElse(null));

        // log.info(prevNotice.map(Notice::getNo));


        User principal = (User) session.getAttribute("principal");
        if (principal != null) {
            if (principal.getRole().equals(RoleType.ADMIN)) {
                model.addAttribute("isAdmin", true);
                log.info("isAdmin: true");

            } else {
                model.addAttribute("isAdmin", false);
                log.info("isAdmin: false");
            }
        }

        return "notice/detailNotice";
    }



    //공지사항 수정페이지 이동
    @GetMapping("/update/{id}")
    public String updateNotice(@PathVariable int id, Model model) {
        model.addAttribute("notice", noticeService.getNotice(id));
        return "notice/updateNotice";
    }

    //공지사항 수정
    @PostMapping(value = "/update/all", consumes = "multipart/form-data")
    public @ResponseBody ResponseDTO<?> updateNotice(@RequestParam("no") int no,
                                                     @RequestPart("title") String title,
                                                     @RequestPart("content") String content,
                                                     @RequestParam("savedFile") Optional<List<String>> savedFile,
                                                     @RequestPart("files")List<MultipartFile> files) {

/*log.info("^^^^^^^^^^^^매개변수 값^^^^^^^^^^^");

log.info(no);
log.info(title);
log.info(content);
log.info(savedFile);
for(MultipartFile file: files) {
    log.info(file.getOriginalFilename());
    log.info(file.getSize());
    log.info(file.getContentType());
}*/
   List<FilesInfo> filesInfos = fileUtils.uploadFiles(files);
    NoticeDTO noticeDTO = new NoticeDTO(title, content);
    Notice notice = modelMapper.map(noticeDTO, Notice.class);


    if(savedFile.isPresent()){
        noticeService.updateNotice(no, notice, savedFile.get(), filesInfos);
    } else{
        noticeService.updateNotice(no, notice, filesInfos);
    }







        return new ResponseDTO<>(HttpStatus.OK.value(), /*notice.getNo() + */"번 게시글이 수정되었습니다.");
    }


    //공지사항 삭제

    @DeleteMapping("/notice/delete")
    public @ResponseBody ResponseDTO<?> deleteNotice(@RequestBody List<Integer> noticeIds) {
        for (int noticeId : noticeIds) {
            noticeService.deleteNotice(noticeId);
        }
        return new ResponseDTO<>(HttpStatus.OK.value(), "삭제되었습니다.");
    }

    //필독 공지사항 전체 우선순위 변경
    @PostMapping("/notice/fixPriority")
    public @ResponseBody ResponseDTO<?> fixPriority(@RequestBody List<Integer> noticeIds) {
        log.info("^^^^^^^^^^^^^^^^^^^^^^");
        log.info(noticeIds);
        noticeService.fixPriority(noticeIds);

        return new ResponseDTO<>(HttpStatus.OK.value(), "필독 우선순위가 저장되었습니다.");
    }

    //필독 공지사항을 일반 공지사항으로 변경(priority 값을 0으로)
    @PutMapping("/notice/resetPriority{no}")
    public @ResponseBody ResponseDTO<?> resetPriority(@PathVariable int no) {
        noticeService.resetPriority(no);

        return new ResponseDTO<>(HttpStatus.OK.value(), "필독공지사항이 일반 공지사항으로 변경되었습니다.");

    }

    //일반 공지사항을 필독 공지사항으로 변경(priority 값을 부여된 값 중 가장 높은 값으로)
    @PutMapping("/notice/grantPriority{no}")
    public @ResponseBody ResponseDTO<?> grantPriority(@PathVariable int no) {
        noticeService.grantPriority(no);

        return new ResponseDTO<>(HttpStatus.OK.value(), "일반 공지사항을 필독 공지사항으로 변경했습니다.");

    }

    // 페이지 벗어날 때 마다 체크박스 상태 저장
    @PostMapping("/notice/ViewCheck")
    public @ResponseBody ResponseDTO<?> ViewCheck(@RequestBody Map<String, Boolean> checkboxIdState) {

        // log.info("^^^^^^^^^^^^^^^^^^^^^^^^^");
        // log.info(checkboxIdState);

        noticeService.saveCheckboxStates(checkboxIdState);

        return new ResponseDTO<>(HttpStatus.OK.value(), "checkbox상태를 확인하고 변경값이 생겼을 경우 DB에 반영했습니다.");

    }

    /*@GetMapping("/notice/searchedNotices")
    public String goToSearchedPage(){


        return "notice/searchedNotices";
    }*/


    //관리자 페이지 검색
    @GetMapping("/notice/searchNoticesAdmin")
    public String searchNoticesAdmin(String searchWord, String searchOption, Model model, @PageableDefault(size = 5, sort = "no", direction = Sort.Direction.DESC) Pageable pageable) {

        log.info("폼에서 넘어오는 문자열 : " + searchWord);
        log.info("폼에서 넘어오는 드롭다운 : " + searchOption);


        Page<Notice> searchNotices = noticeService.searchNoticesAdmin(pageable, searchWord, searchOption);
        model.addAttribute("searchNotices", searchNotices);


        List<Notice> trueNotices = noticeService.findTrueNotice();
        model.addAttribute("trueNotices", trueNotices);


        int totalPages = searchNotices.getTotalPages();

        // 목록 하단 페이지 번호의 노출 개수
        int pageSize = 5;

        int startPage = Math.max(0, (pageable.getPageNumber() / pageSize) * pageSize);
        model.addAttribute("startPage", startPage);

        int endPage = Math.min(startPage + pageSize - 1, totalPages - 1);
        if(endPage >=0){
            model.addAttribute("endPage", endPage);
        }

        return "notice/searchedNoticesAdmin";

    }

    //사용자 페이지 검색
    @GetMapping("/notice/searchNotices")
    public String searchNotices(String searchWord, String searchOption, Model model, @PageableDefault(size = 5, sort = "no", direction = Sort.Direction.DESC) Pageable pageable) {

        log.info("폼에서 넘어오는 문자열 : " + searchWord);
        log.info("폼에서 넘어오는 드롭다운 : " + searchOption);


        Page<Notice> searchNotices = noticeService.searchNotices(pageable, searchWord, searchOption);
        model.addAttribute("searchNotices", searchNotices);


        List<Notice> trueNotices = noticeService.findTrueNotice();
        model.addAttribute("trueNotices", trueNotices);


        int totalPages = searchNotices.getTotalPages();

        // 목록 하단 페이지 번호의 노출 개수
        int pageSize = 5;

        int startPage = Math.max(0, (pageable.getPageNumber() / pageSize) * pageSize);
        model.addAttribute("startPage", startPage);

        int endPage = Math.min(startPage + pageSize - 1, totalPages - 1);
        if(endPage >=0){
            model.addAttribute("endPage", endPage);
        }


        return "notice/searchedNotices";

    }


}












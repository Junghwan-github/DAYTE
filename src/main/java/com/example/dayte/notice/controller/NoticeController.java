package com.example.dayte.notice.controller;

import com.example.dayte.members.domain.RoleType;
import com.example.dayte.members.domain.User;
import com.example.dayte.members.dto.ResponseDTO;
import com.example.dayte.notice.controller.advice.FileUtil;
import com.example.dayte.notice.domain.FilesInfo;
import com.example.dayte.notice.domain.Notice;
import com.example.dayte.notice.dto.NoticeDTO;
import com.example.dayte.notice.service.NoticeService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private FileUtil fileUtils;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/notice")
    public String getNoticeList(Model model, @PageableDefault(size = 10, sort = "no", direction = Sort.Direction.DESC) Pageable pageable,
                                @RequestParam(value = "page", required = false) Integer page,
                                @RequestParam(required = false, defaultValue = "") String searchOption,
                                @RequestParam(required = false, defaultValue = "") String searchWord) {

        Page<Notice> noticeList = noticeService.getNoticeList(pageable);
        String msg = "default";

        if (searchOption.equals("all")) {
            noticeList = noticeService.AllBySearchWord(searchWord, pageable);
            msg = "searched";
        } else if (searchOption.equals("title")) {
            noticeList = noticeService.TitleBySearchWord(searchWord, pageable);
            msg = "searched";
        } else if (searchOption.equals("content")) {
            noticeList = noticeService.ContentBySearchWord(searchWord, pageable);
            msg = "searched";
        }

        model.addAttribute("noticeList", noticeList);
        model.addAttribute("msg", msg);

        int currentPage = noticeList.getPageable().getPageNumber();
        int totalPages = noticeList.getTotalPages();
        int pageSize = 5;
        int startPage = Math.max(0, (pageable.getPageNumber() / pageSize) * pageSize);
        int endPage = Math.min(startPage + pageSize - 1, totalPages - 1);

        model.addAttribute("startPage", startPage);
        if (endPage >= 0) {
            model.addAttribute("endPage", endPage);
        }

        return "notice/notice";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/notice/modAll")
    public String modAllNotice(Model model,
                               @RequestParam(required = false, defaultValue = "") String searchOption,
                               @RequestParam(required = false, defaultValue = "") String searchWord,
                               @PageableDefault(size = 10, sort = "no", direction = Sort.Direction.DESC) Pageable pageable) {
        // 필독 공지사항들
        List<Notice> trueNotices = noticeService.findTrueNotice();
        model.addAttribute("trueNotices", trueNotices);

        // 일반 공지사항들
        Page<Notice> defaultNotices = noticeService.findDefaultNotice(pageable);
        String msg = "default";

        // 일반 공지사항 검색
        if (searchOption.equals("all")) {
            defaultNotices = noticeService.AllBySearchWord(searchWord, pageable);
            msg = "searched";
        } else if (searchOption.equals("title")) {
            defaultNotices = noticeService.TitleBySearchWord(searchWord, pageable);
            msg = "searched";
        } else if (searchOption.equals("content")) {
            defaultNotices = noticeService.ContentBySearchWord(searchWord, pageable);
            msg = "searched";
        }

        model.addAttribute("defaultNotices", defaultNotices);
        model.addAttribute("msg", msg);

        // 페이지네이션
        int currentPage = defaultNotices.getPageable().getPageNumber();
        int totalPages = defaultNotices.getTotalPages();

        // 목록 하단 페이지 번호의 노출 개수
        int pageSize = 5;

        int startPage = Math.max(0, (pageable.getPageNumber() / pageSize) * pageSize);
        int endPage = Math.min(startPage + pageSize - 1, totalPages - 1);

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "notice/modNotices";
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/notice/createNotice")
    public String createNotice(Model model) {

        List<Notice> trueNotices = noticeService.findTrueNotice();
        model.addAttribute("trueNotices", trueNotices);

        return "notice/createNotice";
    }

    //공지사항 추가
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/notice/createNotice")
    public @ResponseBody ResponseDTO<?> createNotice(
            @RequestPart("title") String title,
            @RequestPart("content") String content,
            @RequestPart("inputFiles") List<MultipartFile> files) {

        List<FilesInfo> filesInfos = fileUtils.uploadFiles(files);
        NoticeDTO noticeDTO = new NoticeDTO(title, content);

        Notice notice = modelMapper.map(noticeDTO, Notice.class);

        noticeService.createNotice(notice, filesInfos);

        return new ResponseDTO<>(HttpStatus.OK.value(), "새로운 공지사항을 등록했습니다.");

    }

    //공지사항 상세보기
    @Transactional(readOnly = true)
    @GetMapping("/viewNotice/{id}")
    public String getNotice(@PathVariable int id, Model model, Pageable pageable,
                            @RequestParam(value = "page", required = false) String currentPage,
                            HttpSession session) {
        model.addAttribute("notice", noticeService.getNotice(id));

        model.addAttribute("page", currentPage);

        model.addAttribute("nowId", id);

        Optional<Notice> nextNotice = noticeService.getNextNotice(id);
        model.addAttribute("nextId", nextNotice.map(Notice::getNo).orElse(null));
        model.addAttribute("nextTitle", nextNotice.map(Notice::getTitle).orElse(null));

        Optional<Notice> prevNotice = noticeService.getPrevNotice(id);
        model.addAttribute("prevId", prevNotice.map(Notice::getNo).orElse(null));
        model.addAttribute("prevTitle", prevNotice.map(Notice::getTitle).orElse(null));

        User principal = (User) session.getAttribute("principal");
        if (principal != null) {
            if (principal.getRole().equals(RoleType.ADMIN)) {
                model.addAttribute("isAdmin", true);

            } else {
                model.addAttribute("isAdmin", false);
            }
        }

        return "notice/detailNotice";
    }


    //공지사항 수정페이지 이동
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/update/{id}")
    public String updateNotice(@PathVariable int id, Model model) {
        model.addAttribute("notice", noticeService.getNotice(id));
        return "notice/updateNotice";
    }

    //공지사항 수정
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/update/all", consumes = "multipart/form-data")
    public @ResponseBody ResponseDTO<?> updateNotice(@RequestParam("no") int no,
                                                     @RequestPart("title") String title,
                                                     @RequestPart("content") String content,
                                                     @RequestParam("savedFile") Optional<List<String>> savedFile,
                                                     @RequestPart("inputFiles") List<MultipartFile> files) {

        List<FilesInfo> filesInfos = fileUtils.uploadFiles(files);
        NoticeDTO noticeDTO = new NoticeDTO(title, content);
        Notice notice = modelMapper.map(noticeDTO, Notice.class);

        if (savedFile.isPresent()) {
            noticeService.updateNotice(no, notice, savedFile.get(), filesInfos);
        } else {
            noticeService.updateNotice(no, notice, filesInfos);
        }

        return new ResponseDTO<>(HttpStatus.OK.value(), /*notice.getNo() + */"번 게시글이 수정되었습니다.");
    }


    //공지사항 삭제
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/notice/delete")
    public @ResponseBody ResponseDTO<?> deleteNotice(@RequestBody List<Integer> noticeIds) {

        for (int noticeId : noticeIds) {
            noticeService.deleteNotice(noticeId);
        }
        return new ResponseDTO<>(HttpStatus.OK.value(), "삭제되었습니다.");
    }

    //필독 공지사항 전체 우선순위 변경
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/notice/fixPriority")
    public @ResponseBody ResponseDTO<?> fixPriority(@RequestBody List<Integer> noticeIds) {

        noticeService.fixPriority(noticeIds);

        return new ResponseDTO<>(HttpStatus.OK.value(), "필독 우선순위가 저장되었습니다.");
    }

    //필독 공지사항을 일반 공지사항으로 변경(priority 값을 0으로)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/notice/resetPriority{no}")
    public @ResponseBody ResponseDTO<?> resetPriority(@PathVariable int no) {
        noticeService.resetPriority(no);

        return new ResponseDTO<>(HttpStatus.OK.value(), "필독공지사항이 일반 공지사항으로 변경되었습니다.");
    }

    //일반 공지사항을 필독 공지사항으로 변경(priority 값을 부여된 값 중 가장 높은 값으로)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/notice/grantPriority{no}")
    public @ResponseBody ResponseDTO<?> grantPriority(@PathVariable int no) {
        noticeService.grantPriority(no);

        return new ResponseDTO<>(HttpStatus.OK.value(), "일반 공지사항을 필독 공지사항으로 변경했습니다.");
    }

    // 페이지 벗어날 때 마다 체크박스 상태 저장
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/notice/ViewCheck")
    public @ResponseBody ResponseDTO<?> ViewCheck(@RequestBody Map<String, Boolean> checkboxIdState) {

        noticeService.saveCheckboxStates(checkboxIdState);

        return new ResponseDTO<>(HttpStatus.OK.value(), "checkbox상태를 확인하고 변경값이 생겼을 경우 DB에 반영했습니다.");
    }

}












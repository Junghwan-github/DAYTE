package com.example.dayte.notice.service;


import com.example.dayte.notice.domain.FilesInfo;
import com.example.dayte.notice.domain.Notice;
import com.example.dayte.notice.persistence.FileRepository;
import com.example.dayte.notice.persistence.NoticeRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Log4j2
public class NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private FileRepository fileRepository;


    @Transactional
    public void createNotice(Notice notice, List<FilesInfo> filesInfos){
        noticeRepository.save(notice);

        filesInfos.forEach(filesInfo -> filesInfo.setNotice(notice));

        fileRepository.saveAll(filesInfos);
    }

    @Transactional(readOnly = true)
    public List<Notice> findTrueNotice(){
        List<Notice> notices = noticeRepository.findTruePriority();

        if(notices.isEmpty()){
            return Collections.emptyList();

        } else{
            return notices;
        }
    }

    @Transactional(readOnly = true)
    public Page<Notice> findDefaultNotice(Pageable pageable) {
        return noticeRepository.findDefaultPriority(pageable);


    }


//사용자에게 보여질 공지사항 데이터 불러오기
    @Transactional(readOnly = true)
    public Page<Notice> getNoticeList(Pageable pageable) {
        return noticeRepository.findAllByPriority(pageable);

    }


    //이전글 정보(id번호, 타이틀 뽑아내기 위해 사용)
    @Transactional(readOnly = true)
    public Optional<Notice> getPrevNotice(int currentId){
        return noticeRepository.findPrevNotice(currentId);
    }



//다음글 정보(id번호, 타이틀 뽑아내기 위해 사용)
    @Transactional(readOnly = true)
    public Optional<Notice> getNextNotice(int currentId) {
        return noticeRepository.findNextNotice(currentId);
    }


    @Transactional(readOnly = true)
    public Notice getNotice(int id){
        return noticeRepository.findById(id).get();
    }

    public void deleteNotice(int noticeId) {

        noticeRepository.deleteById(noticeId);
    }

    //기존 저장된 파일이 지워진 후(savedFile 안에 값이 존재) 업데이트
    @Transactional
    public void updateNotice(int no, Notice notice, List<String> savedFile, List<FilesInfo> filesInfos) {

        log.info("기존 파일 지워짐");
        Notice findNotice = noticeRepository.findById(no).get();
        findNotice.setTitle(notice.getTitle());
        findNotice.setContent(notice.getContent());

        noticeRepository.save(findNotice);


        List<FilesInfo> filesNotInSavedFile = fileRepository.findFilesNotInSavedFile(savedFile ,no);

        if(!filesNotInSavedFile.isEmpty()){
            for (FilesInfo deletedFile : filesNotInSavedFile) {
                log.info("^^^^^^^^^^^^^");
                fileRepository.delete(deletedFile);
            }
}
        //지워질거 지워진 기존의 파일 리스트 가져옴
        List<FilesInfo> originalFileList = fileRepository.findByPostNo(no);
        filesInfos.forEach(filesInfo -> filesInfo.setNotice(findNotice));
        originalFileList.addAll(filesInfos);

        /*for(FilesInfo ffi : originalFileList){
            log.info("저장되어야 할 리스트");
            log.info(ffi.getOriginalName());
        }*/

        fileRepository.saveAll(originalFileList);


    }

    // 기존 파일이 싹 다 지워지고 파일 추가 있거나 없거나 업데이트
    @Transactional
    public void updateNotice(int no, Notice notice, List<FilesInfo> filesInfos) {

        log.info("기존 파일 싹다 지워짐");

        Notice findNotice = noticeRepository.findById(no).get();
        findNotice.setTitle(notice.getTitle());
        findNotice.setContent(notice.getContent());

        noticeRepository.save(findNotice);

        //기존 파일 어차피 다 지워졌으니 해당 포스트 아이디를 가진 file들 싹다 지움
        fileRepository.deleteByPostID(no);


        //지워질거 지워진 기존의 파일 리스트 가져옴
        List<FilesInfo> originalFileList = fileRepository.findByPostNo(no);
        filesInfos.forEach(filesInfo -> filesInfo.setNotice(findNotice));
        originalFileList.addAll(filesInfos);



        fileRepository.saveAll(originalFileList);



    }


    public int modPriority(Notice notice) {
        Notice findNotice = noticeRepository.findById(notice.getNo()).get();
        findNotice.setPriority(notice.getPriority());

        noticeRepository.save(findNotice);

        return HttpStatus.OK.value();

    }

    //필독 공지사항을 일반 공지사항으로 변경(priority 값을 0으로)

    public void resetPriority(int no) {
        Notice findNotice = noticeRepository.findById(no).get();
        findNotice.setPriority(0);

        noticeRepository.save(findNotice);
    }

    //일반 공지사항을 필독 공지사항으로 변경(priority 값이 부여된 값 중 가장 큰 값보다 +1한 값을 부여)

    @Transactional
    public void grantPriority(int no) {

        Notice findMaxPriorityNotice = noticeRepository.findMaxPriorityNotice();
        int MaxPriority = findMaxPriorityNotice.getPriority();

        Notice findNotice = noticeRepository.findById(no).get();

        findNotice.setPriority(MaxPriority+1);
        log.info("^^^^^^^^^^^^^^^^^^");
        log.info(findNotice.isViewCheck());

        if (!findNotice.isViewCheck()) { // isViewCheck()가 false일 때만 설정
            findNotice.setViewCheck(true);

            log.info(findNotice.isViewCheck());
            log.info(findNotice);

        }


        noticeRepository.save(findNotice);

    }

    public void fixPriority(List<Integer> noticeIds) {

        for(int i =0; i<noticeIds.size();i++){

            Notice findNotice = noticeRepository.findById(noticeIds.get(i)).get();
            findNotice.setPriority(i+1);

            noticeRepository.save(findNotice);

        }
    }

    public void saveCheckboxStates(Map<String, Boolean> checkboxIdState) {

        for (Map.Entry<String, Boolean> entry : checkboxIdState.entrySet()) {
            int checkboxId = Integer.parseInt(entry.getKey());
            Boolean isChecked = entry.getValue();

            int priority = noticeRepository.findById(checkboxId).get().getPriority();
            boolean checkViewValue = noticeRepository.findById(checkboxId).get().isViewCheck();

            if(isChecked != checkViewValue && priority==0){
                Notice findNotice = noticeRepository.findById(checkboxId).get();

                findNotice.setViewCheck(isChecked);

                noticeRepository.save(findNotice);

            }

        }

    }

    // 공지사항 검색
    @Transactional
    public Page<Notice> noticeList(Pageable pageable) { return noticeRepository.findAll(pageable);}

    @Transactional
    public Page<Notice> AllBySearchWord(String searchWord, Pageable pageable){
        return noticeRepository.findAllBySearchWord(searchWord, pageable);
    }
    @Transactional
    public Page<Notice> TitleBySearchWord(String searchWord, Pageable pageable){
        return noticeRepository.findTitleBySearchWord(searchWord, pageable);
    }

    @Transactional
    public Page<Notice> ContentBySearchWord(String searchWord, Pageable pageable){
        return noticeRepository.findContentBySearchWord(searchWord, pageable);
    }
}


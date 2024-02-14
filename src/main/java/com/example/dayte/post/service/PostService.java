package com.example.dayte.post.service;


import com.example.dayte.post.domin.Post;
import com.example.dayte.post.domin.PostImages;
import com.example.dayte.post.repository.PostImagesRepository;
import com.example.dayte.post.repository.PostRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.tuple.Pair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@Log4j2
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostImagesRepository postImagesRepository;

    @Transactional
    public void insertPost(Post post) {
        System.out.println("service의 post : " + post);
        postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public List<Post> getPostList() {
        return postRepository.findAll();
    }

    @Transactional
    public Post getPost(Long id) {
        return postRepository.findById(id).get();
    }


    @Transactional
    public void updatePost(Post post) {
        // 수정하기 전 해당 게시글 데이터 조회
        Post findPost = postRepository.findById(post.getId()).get();
        findPost.setTitle(post.getTitle());
        findPost.setContent(post.getContent());
    }

    // 삭제
    @Transactional
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Transactional
    public void deletePostImage(Post post) {
        postImagesRepository.deleteAllByPost(post);
    }


    // 페이지네이션
    @Transactional(readOnly = true)
    public Page<Post> getPostList(Pageable pageable) {

        return postRepository.findAll(pageable);
        // 모든 게시물을 페이지네이션화하여 pageable 객체를 매개변수로 받아와서 페이지네이션 정보를 적용하여 컨트롤러단에 반환
    }

    // ------------------------- 검색한 타이틀에 따라 작동하게 될 메서드 -------------------------

    // 제목으로 검색했을 경우
    @Transactional
    public Page<Post> getPostSearchToTitleList(Pageable pageable, String postWord) {

        return postRepository.postSearchToPostTitle(pageable, postWord);
    }

    // 내용으로 검색했을 경우
    @Transactional
    public Page<Post> getPostSearchToContentList(Pageable pageable, String postWord) {

        return postRepository.postSearchToPostContent(pageable, postWord);
    }

    // 전체로 검색했을 경우
    @Transactional
    public Page<Post> getPostSearchToAllList(Pageable pageable, String postWord) {

        return postRepository.postSearchToPostAll(pageable, postWord);
    }

    // --------------------------------------------------------------------------------------

    public ResponseEntity<Map<String, String>> uploadImage(MultipartFile multipartFile) {
        Map<String, String> resultMap = new HashMap<>();
        String fileRoot = "\\\\192.168.10.75/temp/images/post/";
        // 저장될 경로 학원에서 서버로 수정해야되고
        try {
            String originalFileName = multipartFile.getOriginalFilename();
            String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
            String savedFileName = UUID.randomUUID() + extension;

            File targetFile = new File(fileRoot + savedFileName);
            multipartFile.transferTo(targetFile);

            String imageUrl = "/temp/images/post/" + savedFileName;
            // url 로 접속했을시 나올 경로
            resultMap.put("url", imageUrl);
            resultMap.put("responseCode", "success");
            return ResponseEntity.ok(resultMap);
        } catch (IOException e) {
            e.printStackTrace();
            resultMap.put("responseCode", "error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultMap);
        }
    }

    public void extractPostContentImages(Post post) {
        extractPostContentHTML(post);
    }

    private void extractPostContentHTML(Post post) {


        Document doc = Jsoup.parse(post.getContent());

        Elements imgElements = doc.select("img");

        for (Element imgElement : imgElements) {
            String src = imgElement.attr("src");
            PostImages postImages = PostImages.builder()
                    .imageUrl(src)
                    .post(post)
                    .build();
            postImagesRepository.save(postImages);

        }

    }

    public List<Pair<Long, String>> extractPostContentText() {

        List<Post> postContentList = postRepository.findAll();
        List<Pair<Long, String>> contentTextList = new ArrayList<>();

        for (Post post : postContentList) {
            Long postId = post.getId();
            String contentText = post.getContent();
            Document doc = Jsoup.parse(contentText);
            Elements pTagElements = doc.select("p");
            contentTextList.add(Pair.of(postId, pTagElements.text()));
        }

        return contentTextList;
    }
}


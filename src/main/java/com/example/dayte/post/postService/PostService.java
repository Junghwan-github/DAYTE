package com.example.dayte.post.postService;


import com.example.dayte.post.domin.Post;
import com.example.dayte.post.postRepository.PostRepository;
import org.jsoup.Jsoup;
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
public class PostService {

    @Autowired
    private PostRepository postRepository;

    /*
        기능 : 트랜잭션이 시작되고, 메서드가 성공적으로 완료되면 트랜잭션이 커밋, 예외가 발생하면 트랜잭션이 롤백
     */
    @Transactional // 해당 어노테이션이 붙은 메서드 (혹은 클래스)는 트랜잭션의 일부로 실행된다.
    public void insertPost(Post post) {
        System.out.println("service의 post : " + post);
        postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public List<Post> getPostList() {
        return postRepository.findAll();
    }

    @Transactional
    public Post getPost(int id) {
        return postRepository.findById(id).get();
    }

    // update 로직
    /*
        검색한 findPost 엔티티의 title과 content를 사용자가 입력한(post) 값으로 변경하고, 트랜잭션이 종료되면 JPA의 더티 체킹에 의해 UPDATE 구문이 실행된다.
        - 더티 체킹?
        - JPA는 영속성 컨텍스트를 이용하여 이전의 데이터를 스냅샷으로 기록해 두고 있는다.
        - @Transactional 어노테이션에 의해 더티 체킹을 수행하면 JPA는 자신이 가지고 있던 스냅샷과 변경된 엔티티 데이터가 있는지 확인
        - 트랜잭션이 종료되는 시점에 해당 UPDATE 구문을 작성하여 데이터베이스에게 전송한다.
     */
    @Transactional
    public void updatePost(Post post) {
        // 수정하기 전 해당 게시글 데이터 조회
        Post findPost = postRepository.findById(post.getId()).get();
        findPost.setTitle(post.getTitle());
        findPost.setContent(post.getContent());
    }

    // 삭제
    @Transactional
    public void deletePost(int id) {
        postRepository.deleteById(id);
    }



    // 페이지네이션
    @Transactional(readOnly = true)
    public Page<Post> getPostList(Pageable pageable) {
        // Pageable -> 페이징 처리를 위한 매개변수 (클라이언트가 요청한 페이지 번호, 페이지 크기 등을 포함하는 정보)
        return postRepository.findAll(pageable);

        // pageable = 페이지 정보 (findAll 실행 시 페이지 정보 값을 이용하여 Post 객체 목로가을 조회해 Page<Post>형태로 반환한다)
        // Page -> Spring Data의 일부로 Spring Data JPA에서 제공하는 페이징과 정렬 기능을 지원하는 인터페이스
        // 데이터베이스에서 조회된 데이터의 한 부분(즉, 한 페이지)을 나타내며, 추가적으로 페이징 처리에 관련된 정보를 제공한다.
    }

    public ResponseEntity<Map<String, String>> uploadImage(MultipartFile multipartFile) {
        Map<String, String> resultMap = new HashMap<>();
        String fileRoot = "E:/temp/images/post/";
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

    @Transactional
    public List<String> extractImageUrlsById(int id) {
        // 데이터베이스에서 해당 ID의 포스트를 가져옵니다.
        Post post = postRepository.findById(id).orElse(null);

        if (post != null) {
            // 가져온 서머노트 본문 내용에서 이미지의 src 속성을 추출합니다.
            return extractImageUrlsFromSummernoteContent(post.getContent());
        } else {
            return Collections.emptyList();
        }
    }

    private List<String> extractImageUrlsFromSummernoteContent(String summernoteContent) {
        List<String> imageUrls = new ArrayList<>();

        // 서머노트 본문 내용을 jQuery로 파싱합니다.
        var $content = Jsoup.parse(summernoteContent);

        // 각 이미지를 찾아서 배열에 추가합니다.
        $content.select("img").forEach(element -> {
            String imageUrl = element.attr("src");
            imageUrls.add(imageUrl);
        });

        return imageUrls;
    }
}

package com.example.dayte.post.postService;


import com.example.dayte.post.domin.Post;
import com.example.dayte.post.postRepository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional(readOnly = true)
    public Page<Post> getPostList(Pageable pageable) {
        // Pageable -> 페이징 처리를 위한 매개변수 (클라이언트가 요청한 페이지 번호, 페이지 크기 등을 포함하는 정보)
        return postRepository.findAll(pageable);

        // pageable = 페이지 정보 (findAll 실행 시 페이지 정보 값을 이용하여 Post 객체 목로가을 조회해 Page<Post>형태로 반환한다)
        // Page -> Spring Data의 일부로 Spring Data JPA에서 제공하는 페이징과 정렬 기능을 지원하는 인터페이스
        // 데이터베이스에서 조회된 데이터의 한 부분(즉, 한 페이지)을 나타내며, 추가적으로 페이징 처리에 관련된 정보를 제공한다.
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

}

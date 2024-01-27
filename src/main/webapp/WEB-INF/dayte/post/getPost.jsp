<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../layout/head.jsp"%>

<main class="contentArea">
    <%@include file="../layout/head.jsp"%>
<div >

    <div class="PostItems">
        <div class="informationBox">
            포스트 번호 : <span id="id"><i>${post.id}</i></span> <br>
            작성자 : <span><i>${post.user.userEmail}</i></span>
        </div>
        <div>
            <c:if test="${post.user.userEmail eq principal.userEmail}">
                <a href="/post/updatePost/${post.id}" class="btn btn-warning">수정하기</a>
                <button id="btn-delete" class="btn btn-danger">삭제하기</button>
            </c:if>
        </div>
    </div>

    <div class="title">
        <h1>
<%--            ${post.title}--%>
            대구 광역시에서 가볼만한 곳 BEST 12
        </h1>
    </div>
    <div>
        ${post.content}
    </div>
    <p class="s2"><img src="/images/PostTestImg/Taegeukgi.jpg"></p>
    <h2>태극기</h2>
    <p class="s1"><b>1절</b></p>
    <p class="s1"><strong>동</strong> 해물과 백두산이 마르고 닳도록</p>
    <p class="s1">하느님이 보우하사 우리나라만세</p>
    <p class="s1">무궁화 삼천리 화려강산</p>
    <p class="s1">대한사람 대한으로 길이 보전하세</p>

    <p class="s2"><img src="/images/PostTestImg/Mugunghwa.jpg"></p>
    <h2>무궁화</h2>
    <p class="s1"><b>2절</b></p>
    <p class="s1"><strong>남</strong> 산 위에 저소나무 철갑을 두른 듯</p>
    <p class="s1">바람 서리 불변함은 우리 기상일세</p>
    <p class="s1">무궁화 삼천리 화려강산</p>
    <p class="s1">대한사람 대한으로 길이 보전하세</p>

    <p class="s2"><img src="/images/PostTestImg/TaebaekMountains.jpg"></p>
    <h2>태백산맥</h2>
    <p class="s1"><b>3절</b></p>
    <p class="s1"><strong>가</strong> 을 하늘 공활한데 높고 구름없이</p>
    <p class="s1">밝은 달은 우리 가슴 일편담심 일세</p>
    <p class="s1">무궁화 삼천리 화려강산</p>
    <p class="s1">대한사람 대한으로 길이 보전하세</p>

    <p class="s2"><img src="/images/PostTestImg/Namdaemun.jpg"></p>
    <h2>남대문</h2>
    <p class="s1"><b>4절</b></p>
    <p class="s1"><strong>이</strong> 기상과 이맘으로 충성을 다하여</p>
    <p class="s1">괴로우나 즐거우나 나라 사랑하세</p>
    <p class="s1">무궁화 삼천리 화려강산</p>
    <p class="s1">대한사람 대한으로 길이 보전하세</p>
    <p class="s1"><a href="https://m.blog.naver.com/angelgirl4/221856043858" target="self">여기서 애국가 악보
        볼수있어요!</a></p>


    <p class="s2"><img src="/images/PostTestImg/img3.jpg"><img src="/images/PostTestImg/img2.jpg"></p>

    <h2>상세 설명</h2>
    <p class="s1">
        p태그에 한줄이 아닌 p 태그안에서 택스트를 전부 작성하고
        크기 조정으로 줄 변경 할수있도록한다.
        p 태그 밑으로 마진바텀 30px 로 여백을 주어 보기편하게 수정
        이미지는 마진 20 패딩 0으로 깔끔하게 수정
        aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
        aaaaaaaaaaaaaaaaaaaaaaaaa
        aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
        aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
        aaaaaaaaaaaaaaaaaaaaaaaaaaaa
    </p>
</div>
<%-- 포스트 내용 끝 --%>

<%-- 댓글 부분 --%>
<hr>

<%--    <div class="container mb-3">--%>
<%--        <table class="table1">--%>
<%--            <tbody>--%>
<%--            <c:forEach var="reply" items="${postReplyList}">--%>
<%--                <tr>--%>
<%--                    <td><textarea readonly required>${reply.content}</textarea></td>--%>
<%--                    <td>${reply.user.nickName}</td>--%>
<%--                    <td>${reply.formatDate}</td>--%>
<%--                    <c:if test="${principal.userEmail eq reply.user.userEmail}">--%>
<%--                        <td>--%>
<%--                            <button id="btn-delete-reply" onclick="replyObject.deleteReply(this.value)"--%>
<%--                                    value="${reply.num}">삭제--%>
<%--                            </button>--%>

<%--                            <button id="btn-update-reply" class="replyBtnShow"--%>
<%--                                    onclick="replyObject.updateReply(this, this.value)" value="${reply.num}">수정--%>
<%--                            </button>--%>

<%--                            <button type="button" id="updateComplete" class="replyBtnHidden"--%>
<%--                                    onclick="replyObject.completeUpdateReply(this)" value="${reply.num}">확인--%>
<%--                            </button>--%>
<%--                        </td>--%>
<%--                    </c:if>--%>
<%--                </tr>--%>
<%--            </c:forEach>--%>
<%--            </tbody>--%>
<%--        </table>--%>
<%--    </div>--%>

<%-- 댓글 등록 폼 --%>
<div class="container mt-3">
    <input type="hidden" id="postId" value="${post.id}">
    <table class="table">
        <thead>
        <tr>
            <th>
                <h3>Comment</h3>
            </th>
        </tr>
        </thead>
        <tbody>
        <tr align="right">
            <td class="ContainerForm">
                <textarea id="reply-content" rows="1" class="form-control" placeholder="댓글을 입력하세요."></textarea>
            </td>
            <td class="ContainerForm2">
                <button id="btn-save-reply" class="btn btn-secondary">댓글 등록</button>
            </td>
        </tr>
        </tbody>
    </table>
</div>

    <%-- 댓글 목록 불러오기 구현 --%>
    <c:if test="${!empty postReplyList}">
        <ul id="postReplyView">
            <c:forEach var="reply" items="${postReplyList}">
                <li>
                    <ul>
                        <li>
                            <span>프로필 사진</span>
                            <span>${reply.user.nickName}</span>
                            <span>${reply.formatDate}</span>
                        </li>
                        <li> <%-- 햄버거바 --%>
                            <div id="mobile_rnb" >
                                <button type="button" onclick="postReplyBtnListOpen(this)" id="postReplyHamburger" class=""></button>
                                <label for="postReplyHamburger">
                                    <span></span>
                                    <span></span>
                                    <span></span>
                                </label>
                            </div>

                            <div class="postReplyBtnList">
                                <ul>
                                    <li>
                                        <button id="btn-update-reply" class="replyBtnShow"
                                                onclick="replyObject.updateReply(this, this.value)" value="${reply.num}">수정
                                        </button>
                                    </li>
                                    <li>
                                        <button id="btn-delete-reply" onclick="replyObject.deleteReply(this.value)"
                                                value="${reply.num}">삭제
                                        </button>
                                    </li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                    <ul>
                        <li><p class="replyContent">${reply.content}</p></li>
                    </ul>
                </li>
            </c:forEach>
        </ul>
    </c:if>

</main>
<script src="/js/post.js"></script>
<script src="/js/postReply.js"></script>
<%@include file="../layout/footer.jsp" %>
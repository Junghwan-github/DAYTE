<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../layout/head.jsp" %>
<link rel="stylesheet" href="/css/main/customer.css">


<title>DAYTE | 고객센터</title>

</head>

<body>
<%@include file="../layout/header.jsp" %>
<main>
    <div id="customer-container">
        <h1>고객센터</h1>
        <span>궁금한점이 있으신가요?</span>
        <a href="http://pf.kakao.com/_MXjSG/chat" class="ka-ka-o"><img src="/images/kakaologo.png">카카오 문의</a>
        <div id="customer-nav">
            <ul>
                <li class="tab" onclick="openTab('customer-best-question')"><i class="xi-help-o"></i>자주 묻는 질문</li>
                <li class="tab" onclick="openTab('customer-n-inquiry')"><i class="xi-mail-o"></i>1:1 이메일 문의</li>
            </ul>
        </div>
        <div id="customer-best-question" class="customer-best-question tab-content active">
            <ul>
                <li>
                    <label class="question" for="question1">
                        <span class="icon-quest">Q</span>
                        <span class="question-text">
                            계정을 잊어버렸어요
                        </span>
                        <span class="icon-r"><i class="xi-angle-down-min"></i></span>
                    </label>
                    <input type="radio" name="question" id="question1">
                    <div>
                        <p>카카오톡, 네이버 등 SNS 간편 로그인을 통해 트리플에 가입한 경우, 해당 서비스의 계정/보안 관리 메뉴를 통해 '연동' 현황을 확인하실 수 있습니다.
                            계정을 연동하여 가입한 것으로 추정되는 서비스에서 '연동 현황'을 우선 확인해 주세요.


                            [ 간편로그인 - 계정 연결 서비e스 확인 방법 ]

                            색깔이 별로다 진짜 거지같다.. 키보드 키감은 괯낳은거 같은데


                            - 카카오톡
                            카카오톡 앱 > 더보기 > 설정 > 개인/보안 > 카카오 계정 > 연결된 서비스 관리


                            - 네이버
                            네이버 앱/웹 좌측 상단 [ ≡ ] > 로그인 > 좌측 OOO님 터치 > 이력관리 > 연결된 서비스 관리


                            - 페이스북
                            페이스북 앱 우측 상단 또는 하단 [ ≡ ] > 설정 및 개인정보 > 설정 > 앱 및 웹사이트


                            - 애플
                            휴대폰 설정 > 사용자 이름 (Apple ID) > 암호 및 보안 > Apple ID를 사용하는 앱


                            이메일로 가입 하셨거나, 간편로그인 서비스에서 데이트 '연동' 계정 확인이 어려운 경우, 아래의 내용을 고객센터로 보내주세요. 참고하여 사용 기록이 있는 계정을
                            확인해드리겠습니다.


                            1. 사용한 것으로 예상되는 이메일 주소
                            2. 로그인 수단
                            (카카오 / 네이버 / 페이스북 / 애플 / 이메일)
                            3. 닉네임


                            단, 이메일 인증이 되지 않은 계정은 확인 및 안내가 어려울 수 있습니다. 참고 부탁드립니다.
                        </p>
                    </div>
                </li>
                <li>
                    <label class="question" for="question2">
                        <span class="icon-quest">Q</span>
                        <span class="question-text">
                            비밀번호를 잊어버렸어요
                        </span>
                        <span class="icon-r"><i class="xi-angle-down-min"></i></span>
                    </label>
                    <input type="radio" name="question" id="question2">
                    <div>
                        <p>상단 메뉴 로그인 버튼 클릭후 하단 맨 아래쪽 '비밀번호를 잊으셨나요?' 클릭후

                            이메일 인증 > 새 비밀번호 변경 으로 진행하시면 됩니다.

                            [이메일 인증 불가하신 경우]

                            아래 내용 을 작성하여 1:1 이메일 문의를 통해 최대한 빠르게 도움을 드릴수있도록
                            노력하겠습니다.

                            1. 사용한 것으로 예상되는 이메일 주소
                            2. 로그인 수단
                            (카카오 / 네이버 / 페이스북 / 애플 / 이메일)
                            3. 닉네임
                        </p>
                    </div>
                </li>
                <li>
                    <label class="question" for="question3">
                        <span class="icon-quest">Q</span>
                        <span class="question-text">
                            회원 가입시 이메일 인증번호가 오지않아요!
                        </span>
                        <span class="icon-r"><i class="xi-angle-down-min"></i></span>
                    </label>
                    <input type="radio" name="question" id="question3">
                    <div>
                        <p>회원가입시 이메일 인증번호가 오지 않는경우 이메일 주소 작성중 오타가 없는지 확인해주세요
                            만약 오타가 없을시에도 인증번호가 오지않는 경우는 1:1 이메일 문의를 통해 고객센터에 연락주세요

                            최대한 빠르게 처리될수 있도록 도와드리겠습니다.
                        </p>
                    </div>
                </li>
                <li>
                    <label class="question" for="question4">
                        <span class="icon-quest">Q</span>
                        <span class="question-text">
                            회원 탈퇴는 어떻게 하나요?
                        </span>
                        <span class="icon-r"><i class="xi-angle-down-min"></i></span>
                    </label>
                    <input type="radio" name="question" id="question4">
                    <div>
                        <p>상단 우측 로그인 하신후
                            내 정보 > 회원 탈퇴 로 진행하시면됩니다.

                            [탈퇴시 안내 사항]

                            회원 탈퇴 후 1개월 동안은 회원가입이 불가능합니다. 소셜계정을 연동하여 가입한 회원의 경우, 탈퇴시 소셜계정도 자동으로 연동해지 됩니다.

                            탈퇴시 삭제/유지 되는 정보를 확인하세요.

                            계정 및 프로필 정보 삭제 일정 및 리뷰, 사진 유지
                        </p>
                    </div>
                </li>
                <li>
                    <label class="question" for="question5">
                        <span class="icon-quest">Q</span>
                        <span class="question-text">
                            내 일정 관리 수정은 어떻게 하나요?
                        </span>
                        <span class="icon-r"><i class="xi-angle-down-min"></i></span>
                    </label>
                    <input type="radio" name="question" id="question5">
                    <div>
                        <p>상단 우측 로그인 하신후
                            내 일정 관리 메뉴에서 각 일정 테이블 우측 상단에 '<i class="xi-ellipsis-v"></i>' 클릭
                            수정 메뉴에서 수정을 하실수 있습니다.

                            다른 문제점이 생기셨다면 1:1 이메일 문의를 통해 연락 주시면 최대한 빠르게 도움을 드릴수 있도록 노력하겠습니다.
                        </p>
                    </div>
                </li>
                <li>
                    <label class="question" for="question6">
                        <span class="icon-quest">Q</span>
                        <span class="question-text">
                            내 일정 관리 삭제는 어떻게 하나요?
                        </span>
                        <span class="icon-r"><i class="xi-angle-down-min"></i></span>
                    </label>
                    <input type="radio" name="question" id="question6">
                    <div>
                        <p>상단 우측 로그인 하신후
                            내 일정 관리 메뉴에서 각 일정 테이블 우측 상단에 '<i class="xi-ellipsis-v"></i>' 클릭
                            삭제 메뉴에서 삭제를 하실수 있습니다.

                            [삭제시 주의사항]
                            한번 삭제된 일정은 복원이 불가하며, 삭제시 신중을 기울여 진행해 주시길 부탁드립니다.

                            다른 문제점이 생기셨다면 1:1 이메일 문의를 통해 연락 주시면 최대한 빠르게 도움을 드릴수 있도록 노력하겠습니다.
                        </p>
                    </div>
                </li>
                <li>
                    <label class="question" for="question7">
                        <span class="icon-quest">Q</span>
                        <span class="question-text">
                            등록된 일정에서 예약이나 결제를 할수는 없나요?
                        </span>
                        <span class="icon-r"><i class="xi-angle-down-min"></i></span>
                    </label>
                    <input type="radio" name="question" id="question7">
                    <div>
                        <p>저희 '데이트' 현재 고객님의 일정관리 및 정보공유의 목적으로만 역활을 하고있을뿐,
                            예약이나 결제를 지원하지 않고있습니다.

                            추후 예약/결제 관련해서 업데이트 예정은 있으나 아직 정확한 일정은 잡혀있지 않으며,
                            만약 정확한 업데이트 일정이 나온다면 공지사항 게시판에 공지 하도록 하겠습니다.


                            고객님들의 불편함이 없으시도록 최대한 빠르게 많은 개선을 하도록 노력하겠습니다.
                        </p>
                    </div>
                </li>
            </ul>
        </div>
        <div id="customer-n-inquiry" class="customer-n-inquiry tab-content">
            <form enctype="multipart/form-data">
                <ul>
                    <li>
                        <sec:authorize access="isAuthenticated()">
                            <label for="inquiry-email">이메일</label>
                            <input type="text" id="inquiry-email" value="${principal.userEmail}">
                        </sec:authorize>
                        <sec:authorize access="!isAuthenticated()">
                            <label for="inquiry-email">이메일</label>
                            <input type="text" id="inquiry-email" value="" placeholder="example@dayte.com">
                        </sec:authorize>
                    </li>
                    <li>
                        <label for="mainCategory">문의 분류</label>
                        <select id="mainCategory">
                        </select>
                        <select id="subCategory">
                        </select>
                    </li>
                    <li>
                        <label for="inquiry-title">제목</label>
                        <input type="text" id="inquiry-title" placeholder="제목을 적어주세요(20자 이내)">
                    </li>
                    <li>
                        <label for="inquiry-content">문의 내용</label>
                        <textarea id="inquiry-content" class="inquiry-content" placeholder="문의하실 내용을 적어주세요"></textarea>
                    </li>
                </ul>
                <div class="fileList">
                    <div id="file-input-Div-parentNode">
                        <input type="file" accept=".pdf, .hwp, .docx, .xlsx, .xls, .jpg, .png, .jpeg, .zip "
                               class="fileInput"
                               name="inputFiles" id="file-input" multiple="multiple" onchange="selectFile(this);"/>
                    </div>
                    <div>
                        <div id="preview"></div>
                    </div>
                </div>

            </form>
            <button type="button" id="sendEmail" class="sendEmail">문의하기</button>
        </div>
    </div>
</main>


<script src="/js/main/customer.js"></script>
<script src="/js/main/emailQuestion.js"></script>
<script src="/js/schedule/contentInfo.js"></script>
<script src="/js/notice/fileFunction.js"></script>


<%@include file="../layout/footer.jsp" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../layout/head.jsp" %>
<link rel="stylesheet" href="/css/main/usingTerms.css"/>
<title>회원가입 | 개인정보 동의</title>
</head>
<body>
<%@include file="../layout/header.jsp" %>
<main>

    <div class="container">
        <form action="/members/joinForm" method="get" class="joinForm">
            <div class="termInfo">
                <h1>이용 약관 안내</h1>
                <ul>
                    <li><img src="/images/selected1.png" alt="1"/></li>
                    <li><img src="/images/num2.png" alt="2"/></li>

                </ul>

                <div class="privacyInfo">
                    <div id="checkTerm1">
                        <div class="termsBox">
                            <div>
                                <h3 class="articleTitle">
                                    개인정보 수집 및 이용
                                </h3>
                                <p class="articleText">
                                    개인정보보호법에 따라 DAYTE에 회원가입 신청하시는 분께 수집하는 개인정보의 항목, 개인정보의 수집 및 이용목적, 개인정보의 보유 및 이용기간, 동의
                                    거부권 및 동의 거부 시 불이익에 관한 사항을 안내 드리오니 자세히 읽은 후 동의하여 주시기 바랍니다.

                                </p>
                                <h3 class="articleTitle">
                                    1. 수집하는 개인정보
                                </h3>
                                <p class="articleText">
                                    이용자는 회원가입을 하지 않아도 정보 검색 등 대부분의 DAYTE 서비스를 회원과 동일하게 이용할 수 있습니다. 이용자가 여행 일정 관리, 내 리뷰보기 등과 같이 개인화 혹은 회원제 서비스를 이용하기 위해
                                    회원가입을 할 경우, DAYTE는 서비스 이용을 위해 필요한 최소한의 개인정보를 수집합니다.
                                </p>
                                <h3 class="articleTitle">
                                    회원가입 시점에 DAYTE가 이용자로부터 수집하는 개인정보는 아래와 같습니다.
                                </h3>
                                <p class="articleText">
                                    - 회원 가입 시 필수항목으로 이메일주소, 비밀번호, 이름, 생년월일, 휴대전화번호를, 선택항목으로 성별을 수집합니다.

                                </p>
                                <h3 class="articleTitle">
                                    서비스 이용 과정에서 이용자로부터 수집하는 개인정보는 아래와 같습니다.
                                </h3>
                                <p class="articleText">
                                    - 회원정보 또는 개별 서비스에서 프로필 정보(별명, 프로필 사진)를 설정할 수 있습니다. 간편 로그인으로 가입 시 별명을 입력하지 않은 경우에는 이메일주소가 별명으로 자동 입력됩니다.
                                    - DAYTE 내의 개별 서비스 이용, 이벤트 응모 및 경품 신청 과정에서 해당 서비스의 이용자에 한해 추가 개인정보 수집이 발생할 수 있습니다. 추가로 개인정보를 수집할 경우에는 해당 개인정보 수집 시점에서
                                    이용자에게 ‘수집하는 개인정보 항목, 개인정보의 수집 및 이용목적, 개인정보의 보관기간’에 대해 안내 드리고 동의를 받습니다.

                                </p>
                                <h3 class="articleTitle">
                                    서비스 이용 과정에서 IP 주소, 쿠키, 서비스 이용 기록, 기기정보, 위치정보가 생성되어 수집될 수 있습니다.
                                </h3>
                                <p class="articleText">
                                    구체적으로 1) 서비스 이용 과정에서 이용자에 관한 정보를 자동화된 방법으로 생성하여 이를 저장(수집)하거나, 2) 이용자 기기의 고유한 정보를 원래의 값을 확인하지 못 하도록 안전하게 변환하여 수집합니다.
                                    서비스 이용 과정에서 위치정보가 수집될 수 있으며, DAYTE에서 제공하는 위치기반 서비스에 대해서는 'DAYTE 위치기반서비스 이용약관'에서 자세하게 규정하고 있습니다. 이와 같이 수집된 정보는 개인정보와의
                                    연계 여부 등에 따라 개인정보에 해당할 수 있고, 개인정보에 해당하지 않을 수도 있습니다.

                                </p>
                                <h3 class="articleTitle">
                                    2. 수집한 개인정보의 이용
                                </h3>
                                <p class="articleText">
                                    DAYTE 및 DAYTE 관련 제반 서비스(모바일 웹/앱 포함)의 회원관리, 서비스 개발・제공 및 향상, 안전한 인터넷 이용환경 구축 등 아래의 목적으로만 개인정보를 이용합니다.
                                    - 회원 가입 의사의 확인, 연령 확인 및 법정대리인 동의 진행, 이용자 및 법정대리인의 본인 확인, 이용자 식별, 회원탈퇴 의사의 확인 등 회원관리를 위하여 개인정보를 이용합니다.
                                    - 콘텐츠 등 기존 서비스 제공(광고 포함)에 더하여, 인구통계학적 분석, 서비스 방문 및 이용기록의 분석, 개인정보 및 관심에 기반한 이용자간 관계의 형성, 지인 및 관심사 등에 기반한 맞춤형 서비스 제공 등
                                    신규 서비스 요소의 발굴 및 기존 서비스 개선 등을 위하여 개인정보를 이용합니다. 신규 서비스 요소의 발굴 및 기존 서비스 개선 등에는 정보 검색, 다른 이용자와의 커뮤니케이션, 콘텐츠 생성·제공·추천이
                                    포함됩니다.
                                    - 법령 및 DAYTE 이용약관을 위반하는 회원에 대한 이용 제한 조치, 부정 이용 행위를 포함하여 서비스의 원활한 운영에 지장을 주는 행위에 대한 방지 및 제재, 계정도용 및 부정거래 방지, 약관 개정 등의
                                    고지사항 전달, 분쟁조정을 위한 기록 보존, 민원처리 등 이용자 보호 및 서비스 운영을 위하여 개인정보를 이용합니다.
                                    - 이벤트 정보 및 참여기회 제공, 광고성 정보 제공 등 마케팅 및 프로모션 목적으로 개인정보를 이용합니다.
                                    - 서비스 이용기록과 접속 빈도 분석, 서비스 이용에 대한 통계, 서비스 분석 및 통계에 따른 맞춤 서비스 제공 및 광고 게재 등에 개인정보를 이용합니다.
                                    - 보안, 프라이버시, 안전 측면에서 이용자가 안심하고 이용할 수 있는 서비스 이용환경 구축을 위해 개인정보를 이용합니다.

                                </p>
                                <h3 class="articleTitle">
                                    3. 개인정보의 보관기간
                                </h3>
                                <p class="articleText">
                                    회사는 원칙적으로 이용자의 개인정보를 회원 탈퇴 시 지체없이 파기하고 있습니다.
                                    단, 이용자에게 개인정보 보관기간에 대해 별도의 동의를 얻은 경우, 또는 법령에서 일정 기간 정보보관 의무를 부과하는 경우에는 해당 기간 동안 개인정보를 안전하게 보관합니다.
                                    이용자에게 개인정보 보관기간에 대해 회원가입 시 또는 서비스 가입 시 동의를 얻은 경우는 아래와 같습니다.
                                    - 부정 가입 및 이용 방지
                                    부정 이용자의 가입인증 이메일 : 탈퇴일로부터 6개월 보관
                                    탈퇴한 이용자의 이메일 : 탈퇴일로부터 6개월 보관
                                    - 통신비밀보호법
                                    로그인 기록: 3개월
                                    참고로 DAYTE는 ‘개인정보 유효기간제’에 따라 1년간 서비스를 이용하지 않은 회원의 개인정보를 별도로 분리 보관하여 관리하고 있습니다.

                                </p>
                                <h3 class="articleTitle">
                                    4. 개인정보 수집 및 이용 동의를 거부할 권리
                                </h3>
                                <p class="articleText">
                                    이용자는 개인정보의 수집 및 이용 동의를 거부할 권리가 있습니다. 회원가입 시 수집하는 최소한의 개인정보, 즉, 필수 항목에 대한 수집 및 이용 동의를 거부하실 경우,
                                    회원가입이 어려울 수 있습니다.

                                </p>
                                <h3 class="articleTitle">
                                    위치기반서비스 이용약관
                                </h3>
                                <p class="articleText">
                                    위치기반서비스 이용약관에 동의하시면, 위치를 활용한 광고 정보 수신 등을 포함하는 DAYTE 위치기반 서비스를 이용할 수 있습니다.

                                    제 1 조 (목적)
                                    이 약관은 DAYTE 주식회사 (이하 “회사”)가 제공하는 위치기반서비스와 관련하여 회사와 개인위치정보주체와의 권리, 의무 및 책임사항, 기타 필요한 사항을 규정함을
                                    목적으로 합니다.

                                    제 2 조 (약관 외 준칙)
                                    이 약관에 명시되지 않은 사항은 위치정보의 보호 및 이용 등에 관한 법률, 개인정보보호법, 정보통신망 이용촉진 및 정보보호 등에 관한 법률, 전기통신기본법,
                                    전기통신사업법 등 관계법령과 회사의 이용약관 및 개인정보처리방침, 회사가 별도로 정한 지침 등에 의합니다.

                                    제 3 조 (서비스 내용 및 요금)
                                    ① 회사는 위치정보사업자로부터 위치정보를 전달받아 아래와 같은 위치기반서비스를 제공합니다.

                                    1. GeoTagging 서비스: 게시물 또는 이용자가 저장하는 콘텐츠에 포함된 개인위치정보주체 또는 이동성 있는 기기의 위치정보가 게시물과 함께 저장됩니다.
                                    저장된 위치정보는 별도의 활용없이 보관되거나, 또는 장소를 기반으로 콘텐츠를 분류하거나 검색할 수 있는 기능이 제공될 수도 있습니다.
                                    2. 위치정보를 활용한 검색결과 및 콘텐츠 제공 : 정보 검색을 요청하거나 개인위치정보주체 또는 이동성 있는 기기의 위치정보를 제공 시 본 위치정보를 이용한 검색결과,
                                    주변결과(맛집, 주변업체, 교통수단 등), 번역결과를 제시합니다.
                                    3. 이용자 위치를 활용한 광고정보 제공: 검색결과 또는 기타 서비스 이용 과정에서 개인위치정보주체 또는 이동성 있는 기기의 위치를 이용하여 광고소재를 제시합니다.
                                    4. 이용자 보호 및 부정 이용 방지: 개인위치정보주체 또는 이동성 있는 기기의 위치를 이용하여 권한없는 자의 비정상적인 서비스 이용 시도 등을 차단합니다.
                                    5. 길 안내 등 생활편의 서비스 제공: 교통정보와 길 안내 등 최적의 경로를 지도로 제공하며, 주변 시설물 찾기, 뉴스/날씨 등 생활정보, 긴급구조 서비스, 주소 자동 입력 등 다양한 운전 및
                                    생활 편의 서비스를 제공합니다.
                                    ② 제1항 위치기반서비스의 이용요금은 무료입니다.
                                    제 4 조 (개인위치정보주체의 권리)
                                    ① 개인위치정보주체는 개인위치정보 수집 범위 및 이용약관의 내용 중 일부 또는 개인위치정보의 이용ㆍ제공 목적, 제공받는 자의 범위 및 위치기반서비스의 일부에 대하여 동의를 유보할 수 있습니다.
                                    ② 개인위치정보주체는 개인위치정보의 수집ㆍ이용ㆍ제공에 대한 동의의 전부 또는 일부를 철회할 수 있습니다.
                                    ③ 개인위치정보주체는 언제든지 개인위치정보의 수집ㆍ이용ㆍ제공의 일시적인 중지를 요구할 수 있습니다. 이 경우 회사는 요구를 거절하지 아니하며, 이를 위한 기술적 수단을 갖추고 있습니다.
                                    ④ 개인위치정보주체는 회사에 대하여 아래 자료의 열람 또는 고지를 요구할 수 있고, 당해 자료에 오류가 있는 경우에는 그 정정을 요구할 수 있습니다.
                                    이 경우 회사는 정당한 이유 없이 요구를 거절하지 아니합니다.

                                    1. 개인위치정보주체에 대한 위치정보 수집ㆍ이용ㆍ제공사실 확인자료
                                    2. 개인위치정보주체의 개인위치정보가 위치정보의 보호 및 이용 등에 관한 법률 또는 다른 법령의 규정에 의하여 제3자에게 제공된 이유 및 내용
                                    ⑤ 회사는 개인위치정보주체가 동의의 전부 또는 일부를 철회한 경우에는 지체 없이 수집된 개인위치정보 및 위치정보 수집ㆍ이용ㆍ제공사실 확인자료를 파기합니다.단, 동의의 일부를 철회하는 경우에는
                                    철회하는 부분의 개인위치정보 및 위치정보 수집ㆍ이용ㆍ제공사실 확인자료에 한합니다.
                                    ⑥ 개인위치정보주체는 제1항 내지 제4항의 권리행사를 위하여 이 약관 제13조의 연락처를 이용하여 회사에 요구할 수 있습니다.
                                    제 5 조 (법정대리인의 권리)
                                    ① 회사는 만14세 미만 아동으로부터 개인위치정보를 수집ㆍ이용 또는 제공하고자 하는 경우에는 만14세 미만 아동과 그 법정대리인의 동의를 받아야 합니다.
                                    ② 법정대리인은 만14세 미만 아동의 개인위치정보를 수집ㆍ이용ㆍ제공에 동의하는 경우 동의유보권, 동의철회권 및 일시중지권, 열람ㆍ고지요구권을 행사할 수 있습니다.
                                    제 6 조 (위치정보 이용·제공사실 확인자료 보유근거 및 보유기간)
                                    회사는 위치정보의 보호 및 이용 등에 관한 법률 제16조 제2항에 근거하여 개인위치정보주체에 대한 위치정보 수집·이용·제공사실 확인자료를 위치정보시스템에 자동으로 기록하며, 6개월 이상 보관합니다.
                                    제 7 조 (서비스의 변경 및 중지)
                                    ① 회사는 위치기반서비스사업자의 정책변경 등과 같이 회사의 제반 사정 또는 법률상의 장애 등으로 서비스를 유지할 수 없는 경우, 서비스의 전부 또는
                                    일부를 제한, 변경하거나 중지할 수 있습니다.
                                    ② 제1항에 의한 서비스 중단의 경우에는 회사는 사전에 인터넷 등에 공지하거나 개인위치정보주체에게 통지합니다.
                                    제 8 조 (개인위치정보 제3자 제공 시 즉시 통보)
                                    ① 회사는 개인위치정보주체의 동의 없이 당해 개인위치정보주체의 개인위치정보를 제3자에게 제공하지 아니하며, 제3자 제공 서비스를 제공하는 경우에는 제공받는 자 및 제공목적을 사전에
                                    개인위치정보주체에게 고지하고 동의를 받습니다.
                                    ② 회사는 개인위치정보를 개인위치정보주체가 지정하는 제3자에게 제공하는 경우에는 개인위치정보를 수집한 당해 통신단말장치로 매회 개인위치정보주체에게 제공받는 자,
                                    제공일시 및 제공목적을 즉시 통보합니다.
                                    ③ 다만, 아래에 해당하는 경우에는 개인위치정보주체가 미리 특정하여 지정한 통신단말장치 또는 전자우편주소 등으로 통보합니다.

                                    1.개인위치정보를 수집한 당해 통신단말장치가 문자, 음성 또는 영상의 수신기능을 갖추지 아니한 경우
                                    2.개인위치정보주체가 개인위치정보를 수집한 당해 통신단말장치 외의 통신단말장치 또는 전자우편주소 등으로 통보할 것을 미리 요청한 경우
                                    제 9 조 (8세 이하의 아동 등의 보호의무자의 권리)
                                    ① 회사는 아래의 경우에 해당하는 자(이하 “8세 이하의 아동 등"이라 함)의 보호의무자가 8세 이하의 아동 등의 생명 또는 신체보호를 위하여 개인위치정보의 이용 또는 제공에 동의하는
                                    경우에는 본인의 동의가 있는 것으로 봅니다.

                                </p>
                                <h3 class="articleTitle">
                                    개인정보 수집 및 이용 동의
                                </h3>
                                <p class="articleText">
                                    DAYTE 및 제휴 서비스의 이벤트・혜택 등의 정보 발송을 위해 이메일주소, 휴대전화번호를 수집합니다.
                                    이메일주소와 휴대전화번호는 DAYTE 서비스 제공을 위한 필수 수집항목으로서 DAYTE 회원 가입 기간 동안 보관하나, 이벤트 혜택 정보 수신 동의를
                                    철회하시면 본 목적으로의 개인정보 처리는 중지됩니다.
                                    정보주체는 개인정보 수집 및 이용 동의를 거부하실 수 있으며, 미동의 시에도 서비스 이용은 가능합니다.
                                </p>
                            </div>

                        </div>
                        <br>
                        <div>
                            <input
                                    type="radio"
                                    id="disagreeCheckbox"
                                    value="N"
                                    name="checkTerm1"
                            />
                            <label for="disagreeCheckbox">동의하지 않습니다.</label>
                        </div>
                        <div>
                            <input type="radio" id="agreeCheckbox" value="Y" name="checkTerm1"/>
                            <label for="agreeCheckbox">동의합니다.</label>
                        </div>

                    </div>
                </div>
                <div class="termCheckBttn">
                    <button type="button" value="약관 동의">회원 정보 등록</button>
                </div>
            </div>
        </form>
    </div>
    <script>
        /* 이용약관 동의 시 -> 회원정보 기입페이지 연결 */
        $(document).ready(function () {
        $("button[type='button']").click(function () {
                    if ($("#agreeCheckbox").is(":checked")) {
                        $("button[type='button']").prop("type", "submit");
                    } else {
                        alert("약관에 동의 하지 않으셨습니다.");
                    }
                })
            });
    </script>
</main>
<%@include file="../layout/footer.jsp" %>
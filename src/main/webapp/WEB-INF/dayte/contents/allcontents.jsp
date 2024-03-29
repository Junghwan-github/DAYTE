<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- head --%>
<%@include file="../layout/head.jsp" %>

<%-- css --%>
<link rel="stylesheet" href="/css/contents/allcontents.css">
<title>DAYTE | ${contentsListCategory[0].category}</title>
</head><%-- /head --%>

<body> <%-- body --%>

<%@include file="../layout/header.jsp" %> <%-- header --%>
<%@include file="../layout/subnav.jsp" %>


<main id="main-max-top"> <%-- main --%>
    <h1 id="contents-list-category-name">${contentsListCategory[0].category} ${contentsListCategory.size()}개</h1>
    <div id ="contents-category-list-container">
        <div class="bottomModalWraper">
            <div class="leftModalLayout">
                <div><input type="text" id="leftModalSearchBar" placeholder="검색어를 입력하세요">
                    <button type="button" id="leftModalSearchBarBtn"
                            onclick="searchContentsCategory(leftModalSearchBar.value)">검색
                    </button>
                </div>
                <div>
                    <h2>구/군</h2>
                    <ul id="guList">
                        <li>중구</li>
                        <li>수성구</li>
                        <li>북구</li>
                        <li>동구</li>
                        <li>남구</li>
                        <li>서구</li>
                        <li>달서구</li>
                        <li>달성군</li>
                        <li>군위군</li>
                    </ul>
                </div>
                <div>
                    <h2>키워드</h2>
                    <ul id="keywordList">
                        <c:forEach var="content" items="${contentsListKeyword}">
                        <li>${content}</li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
            <div class="centerModalLayout">
                <ul class="contentListViewer">
                    <c:forEach var="content" items="${contentsListCategory}">
                        <li>
                            <div class="contentListItems">
                                <span class="contentListItemPoint-x">${content.positionX}</span>
                                <span class="contentListItemPoint-y">${content.positionY}</span>
                                <div class="contentListItemsImages">
                                    <img src="${content.adminContentsImageList[0].imageURL}">
                                </div>
                                <ul class="contentListItemText">
                                    <li>
                                        <div class="contents-title-wrapper">
                                            <h2>${content.businessName}</h2>
                                            <span>${content.category}</span>
                                            <span>${content.keyword}</span>
                                        </div>
                                    </li>
                                    <li>
                                        <span>${content.detailedAddress}</span>
                                    </li>
                                    <li>
                                        <p>영업시간 : ${content.opening} ~ ${content.closed}</p>
                                        <p>기간 : 없음</p>
                                        <p>문의 : ${content.contactInfo}</p>
                                    </li>
                                    <li>
                                        <c:set var="hasMatch" value="false" />
                                        <c:forEach var="star" items="${starList}">
                                            <c:if test="${star.uuid eq contents.uuid}">
                                                <span class="star">★${star.starAVG}</span>
                                                <c:set var="hasMatch" value="true" />
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${not hasMatch}">
                                            <span class="star">★0.0</span>
                                        </c:if>
                                    </li>
                                </ul>
                                <div class="contentListItemButton">
                                    <ul>
                                        <li>
                                            <button class="contentListItemdetailViewBtn" value="${content.uuid}">
                                                상세보기
                                            </button>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</main>
<a href="#main-max-top" id="max-top-bottom"><i class="xi-arrow-up"></i></a>
<script src="/js/contents/allcontents.js"></script>
<jsp:include page="../layout/footer.jsp"/>
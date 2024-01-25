<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="layout/adminHead.jsp"%>
        <!-- 콘텐츠 -->
        <div class="h-100 bg-body-tertiary px-3" style="padding-top: 70px; min-width:375px;">
            <p class="h4 fw-bold" >회원 관리</p>

            <!-- 회원 요약 통계 -->
            <div class="d-flex flex-row">
                <ul class="list-unstyled border rounded d-flex flex-row me-2">
                    <li class="border-end bg-secondary-subtle p-1 rounded-start fw-semibold ">총회원수</li>
                    <li  class=" px-2 py-1 bg-light rounded-end fw-semibold">00</li>
                </ul>
                <ul class="list-unstyled border rounded d-flex flex-row me-2">
                    <li class="border-end bg-secondary-subtle p-1 rounded-start fw-semibold">탈퇴</li>
                    <li  class=" px-2 py-1 bg-light rounded-end fw-semibold">00</li>
                </ul>
                <ul class="list-unstyled border rounded d-flex flex-row me-2">
                    <li class="border-end bg-secondary-subtle p-1 rounded-start fw-semibold">정지</li>
                    <li  class=" px-2 py-1 bg-light rounded-end fw-semibold">00</li>
                </ul>
                <ul class="list-unstyled border rounded d-flex flex-row me-2">
                    <li class="border-end bg-secondary-subtle p-1 rounded-start fw-semibold">휴면</li>
                    <li  class=" px-2 py-1 bg-light rounded-end fw-semibold">00</li>
                </ul>
            </div>

            <!-- 회원 검색 -->
            <div class="mb-2">
                <form id="uSearch" name="uSearch" >
                    <div class="list-group d-flex flex-row">
                        <select name="us1" id="us1" class="list-item me-1">
                            <option value="userEmail">아이디</option>
                            <option value="nickName">닉네임</option>
                            <option value="userName">이름</option>
                            <option value="roleType">권한</option>
                            <option value="phone">휴대전화번호</option>
                            <option value="joinDate">가입일</option>
                        </select>
                        <input type="text" name="uSearchInput" id="uSearchInput" class="list-item me-1">
                        <input type="submit" class="btn-submit btn btn-dark py-1" value="검색">
                    </div>
                </form>
            </div>

            <!-- 회원 조회 -->
            <div class="table-responsive">
                <table class="table table-striped border" >
                    <thead class="align-middle">
                    <tr >
                        <th scope="col" class="text-center bg-secondary-subtle">No</th>
                        <th scope="col" class="text-center bg-secondary-subtle">아이디</th>
                        <th scope="col" class="text-center bg-secondary-subtle">이름</th>
                        <th scope="col" class="text-center bg-secondary-subtle">닉네임</th>
                        <th scope="col" class="text-center bg-secondary-subtle">휴대전화번호</th>
                        <th scope="col" class="text-center bg-secondary-subtle">소셜</th>
                        <th scope="col" class="text-center bg-secondary-subtle">성별</th>
                        <th scope="col" class="text-center bg-secondary-subtle">권한</th>
                    </tr>
                    </thead>
                    <tbody class="align-middle">
                    <tr>
                        <td class="text-center">1</td>
                        <td class="text-center">test@test.com</td>
                        <td class="text-center">test</td>
                        <td class="text-center">test</td>
                        <td class="text-center">010-1234-1234</td>
                        <td class="text-center">test</td>
                        <td class="text-center">test</td>
                        <td>
                            <div class="d-flex flex-row justify-content-center">
                                <div class="border col-6">
                                    <button type="button" class="btn dropdown-toggle w-100" data-bs-toggle="dropdown" aria-expanded="false">권한</button>
                                    <ul class="dropdown-menu">
                                        <li class="dropdown-item">일반</li>
                                        <li class="dropdown-item">관리자</li>
                                        <li class="dropdown-item">탈퇴</li>
                                        <li class="dropdown-item">정지</li>
                                        <li class="dropdown-item">휴면</li>
                                    </ul>
                                </div>
                                <button class="btn btn-dark ms-1 d-block">저장</button>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="text-center">2</td>
                        <td class="text-center">test@test.com</td>
                        <td class="text-center">test</td>
                        <td class="text-center">test</td>
                        <td class="text-center">010-1234-1234</td>
                        <td class="text-center">test</td>
                        <td class="text-center">test</td>
                        <td>
                            <div class="d-flex flex-row justify-content-center">
                                <div class="border col-6">
                                    <button type="button" class="btn dropdown-toggle w-100" data-bs-toggle="dropdown" aria-expanded="false">권한</button>
                                    <ul class="dropdown-menu">
                                        <li class="dropdown-item">일반</li>
                                        <li class="dropdown-item">관리자</li>
                                        <li class="dropdown-item">탈퇴</li>
                                        <li class="dropdown-item">정지</li>
                                        <li class="dropdown-item">휴면</li>
                                    </ul>
                                </div>
                                <button class="btn btn-dark ms-1 d-block">저장</button>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="text-center">3</td>
                        <td class="text-center">test@test.com</td>
                        <td class="text-center">test</td>
                        <td class="text-center">test</td>
                        <td class="text-center">010-1234-1234</td>
                        <td class="text-center">test</td>
                        <td class="text-center">test</td>
                        <td>
                            <div class="d-flex flex-row justify-content-center">
                                <div class="border col-6">
                                    <button type="button" class="btn dropdown-toggle w-100" data-bs-toggle="dropdown" aria-expanded="false">권한</button>
                                    <ul class="dropdown-menu">
                                        <li class="dropdown-item">일반</li>
                                        <li class="dropdown-item">관리자</li>
                                        <li class="dropdown-item">탈퇴</li>
                                        <li class="dropdown-item">정지</li>
                                        <li class="dropdown-item">휴면</li>
                                    </ul>
                                </div>
                                <button class="btn btn-dark ms-1 d-block">저장</button>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="text-center">4</td>
                        <td class="text-center">test@test.com</td>
                        <td class="text-center">test</td>
                        <td class="text-center">test</td>
                        <td class="text-center">010-1234-1234</td>
                        <td class="text-center">test</td>
                        <td class="text-center">test</td>
                        <td>
                            <div class="d-flex flex-row justify-content-center">
                                <div class="border col-6">
                                    <button type="button" class="btn dropdown-toggle w-100" data-bs-toggle="dropdown" aria-expanded="false">권한</button>
                                    <ul class="dropdown-menu">
                                        <li class="dropdown-item">일반</li>
                                        <li class="dropdown-item">관리자</li>
                                        <li class="dropdown-item">탈퇴</li>
                                        <li class="dropdown-item">정지</li>
                                        <li class="dropdown-item">휴면</li>
                                    </ul>
                                </div>
                                <button class="btn btn-dark ms-1 d-block">저장</button>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <!-- 페이지네이션 -->
            <div class="mt-3">
                <nav aria-label="Page navigation ">
                    <ul class="pagination justify-content-center">
                        <li class="page-item">
                            <a class="page-link text-dark" href="#" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                        <li class="page-item"><a class="page-link text-dark" href="#">1</a></li>
                        <li class="page-item"><a class="page-link text-dark" href="#">2</a></li>
                        <li class="page-item"><a class="page-link text-dark" href="#">3</a></li>
                        <li class="page-item">
                            <a class="page-link text-dark" href="#" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
<%-- div.wrapper의 닫힘 태그는 푸터 안에 있음 --%>
<%@include file="layout/adminFooter.jsp"%>
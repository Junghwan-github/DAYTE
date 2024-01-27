<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="principal" />
</sec:authorize>


<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <script src="/webjars/bootstrap/5.3.2/js/bootstrap.min.js"></script>
    <script src="/webjars/jquery/3.7.1/jquery.min.js"></script>
    <script src="/webjars/summernote/0.8.10/summernote-bs4.min.js"></script>
    <title>Project T</title>

    <link href="/webjars/bootstrap/5.3.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="/webjars/summernote/0.8.10/summernote-bs4.css" rel="stylesheet">

</head>
<body>
<div class="container mt-3">
    <form>
        <div class="mb-3">
            <label for="title">Title:</label>
            <input type="text" class="form-control" id="title" name="title">
        </div>
        <div class="mb-3">
            <label for="content">Content:</label>
            <textarea rows="5" class="form-control summernote" id="content" name="content"></textarea>
        </div>
    </form>
    <button id="save-post" class="btn btn-secondary">등록</button>
</div>

<script src="/js/post.js"></script>
<script>
    $(document).ready(function() {
        $("#content").summernote();
    });
</script>

</body>
</html>
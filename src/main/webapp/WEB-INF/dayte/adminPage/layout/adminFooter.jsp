<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<footer class="w-100 mt-3 d-flex align-items-center justify-content-center" >
  <p>Copyright ⓒ 2024 RIRI-Child Team. All rights reserved.</p>
</footer>
</div>
<%--wrapper 닫힘--%>

<!-- 부트스트랩 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
<script>
    $(document).ready(function() {
        $("#nowPageTitle").text($(".titleName").text());
    });
</script>

</body>
</html>
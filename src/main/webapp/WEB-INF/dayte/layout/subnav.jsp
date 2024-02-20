<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<section id="searchContainer">
    <div class="searchArea">
        <form action="/contents/indexSearch" method="get">
            <input type="text" name="indexSearch" placeholder="검색어를 입력하세요" />
            <button type="submit"><i class="xi-search"></i></button>
        </form>
        <ul class="subNavIcon">
            <li onclick="indexContentsListLink('hotels')"><img src="/images/bedicon.png"></li>
            <li onclick="indexContentsListLink('restaurants')"><img src="/images/foodicon.png"></li>
            <li onclick="indexContentsListLink('cafes')"><img src="/images/coffeicon1.png"></li>
            <li onclick="indexContentsListLink('events')"><img src="/images/eventicon2.png"></li>
        </ul>
    </div>
    <script>
        function indexContentsListLink (category) {
            location.href = "/contents/category/" + category
        }
    </script>
</section>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<section id="searchContainer">
    <div class="searchArea">
        <form action="#" method="get">
            <input type="text" name="searchArea" placeholder="검색어를 입력하세요" />
            <button type="submit"><i class="xi-search"></i></button>
        </form>
        <ul class="subNavIcon">
            <li onclick="indexContentsListLink('hotels')"><img src="/images/hotel-i.png"></li>
            <li onclick="indexContentsListLink('restaurants')"><img src="/images/restaurant-i.png"></li>
            <li onclick="indexContentsListLink('cafes')"><img src="/images/cafe-i.png"></li>
            <li onclick="indexContentsListLink('events')"><img src="/images/events.png"></li>
        </ul>
    </div>
    <script>
        function indexContentsListLink (category) {
            location.href = "/contents/category/" + category
        }
    </script>
</section>
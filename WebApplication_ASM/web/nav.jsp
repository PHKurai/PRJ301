<%-- 
    Document   : nav
    Created on : Feb 19, 2025, 10:10:47 PM
    Author     : phucl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }

    .navbar {
        background-color: #fff;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        padding: 10px 40px;
    }

    .nav-links {
        list-style: none;
        display: flex;
        justify-content: space-around;
        align-items: center;
    }

    .nav-links li {
        position: relative;
    }

    .nav-links a {
        text-decoration: none;
        color: #333;
        padding: 10px 15px;
        display: block;
        transition: all 0.3s ease;
        font-weight: 500;
        border-bottom: 2px solid white;
    }

    .nav-links a:hover {
        background-color: #e0f7fa;
        border-bottom: 2px solid #00acc1;
    }

    .dropdown-content {
        display: none;
        position: absolute;
        top: 100%;
        left: 0;
        background-color: #fff;
        padding: 15px;
        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        display: grid;
        grid-template-columns: repeat(auto-fill, minmax(120px, 1fr));
        gap: 10px;
        z-index: 100;
        width: max-content;
    }

    .dropdown-content a {
        padding: 5px;
        border-radius: 5px;
        transition: background-color 0.3s;
    }

    .dropdown-content a:hover {
        background-color: #e0f7fa;
        border-bottom: 2px solid #e0f7fa;
    }

    .dropdown:hover .dropdown-content {
        display: grid;
    }

    .dropdown > a::after {
        content: " ▼";
        font-size: 0.8em;
    }
</style>
<nav class="navbar">
    
    <!-- List navigation -->
    <ul class="nav-links">
        
        <!-- Home -->
        <li><a href="#">Trang Chủ</a></li>
        
        <!-- Type -->
        <li class="dropdown">
            <a href="#">Thể Loại</a>
            <div class="dropdown-content">
                <a href="#">Action</a>
                <a href="#">Adventure</a>
                <a href="#">Anime</a>
                <a href="#">Chuyển Sinh</a>
                <a href="#">Cổ Đại</a>
                <a href="#">Comedy</a>
                <a href="#">Drama</a>
                <a href="#">Fantasy</a>
                <a href="#">Manga</a>
                <a href="#">Manhwa</a>
            </div>
        </li>
        
        <!-- Ranking -->
        <li class="dropdown">
            <a href="#">Xếp Hạng</a>
            <div class="dropdown-content">
                <a href="#">Mới nhất</a>
                <a href="#">Đọc nhiều</a>
                <a href="#">Yêu thích</a>
            </div>
        </li>
        
        <!-- History -->
        <li><a href="#">Lịch Sử</a></li>
        
        <!-- Following -->
        <li><a href="#">Theo Dõi</a></li>
    </ul>
</nav>

<%-- 
    Document   : header
    Created on : Feb 17, 2025, 2:18:54 PM
    Author     : phucl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }
    
    .header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 15px 40px;
        background-color: #007bff;
    }

    .logo {
        display: flex;
        align-items: center;
        color: #fff;
        font-size: 24px;
        font-weight: bold;
    }

    .logo img {
        width: 40px;
        height: 40px;
        margin-right: 10px;
    }

    .search-container {
        display: flex;
        align-items: center;
        width: 40%;
        background-color: #ffffff;
        border-radius: 30px;
        padding: 5px;
    }

    .search-container input {
        width: 100%;
        padding: 10px;
        border: none;
        outline: none;
        background: transparent;
        color: #000000;
        font-size: 16px;
    }

    .search-container input::placeholder {
        color: #bbb;
    }

    .search-btn {
        background: none;
        border: none;
        color: #fff;
        font-size: 20px;
        cursor: pointer;
    }

    .auth-buttons {
        display: flex;
        gap: 10px;
    }

    .btn {
        padding: 10px 20px;
        border: none;
        border-radius: 6px;
        cursor: pointer;
        font-weight: bold;
        transition: background-color 0.3s;
        background-color: #ffffff;
        color: #007bff;
    }

    .btn:hover {
        background-color: #e0f7fa;
    }
</style>
<header class="header">
    
    <!-- Logo -->
    <div class="logo">
        <!-- <img src="logo.png" alt="Logo"> -->
        <span>TENWEB</span>
    </div>

    <!-- Searching bar -->
    <div class="search-container">
        <input type="text" placeholder="Bạn muốn tìm truyện gì">
        <button class="search-btn">
            
        </button>
    </div>

    <!-- Auth buttons -->
    <div class="auth-buttons">
        <button class="btn signup">Đăng ký</button>
        <button class="btn signin">Đăng nhập</button>
    </div>
</header>

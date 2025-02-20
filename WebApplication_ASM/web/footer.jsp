<%-- 
    Document   : footer
    Created on : Feb 17, 2025, 2:19:30 PM
    Author     : phucl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }

    .footer {
        display: flex;
        justify-content: space-between;
        align-items: center;
        flex-wrap: wrap;
        padding: 20px 40px;
        background-color: #007bff;
        color: white;
        border-top: 1px solid #ddd;
    }

    .footer-left,
    .footer-center,
    .footer-right {
        display: flex;
        flex-direction: column;
        align-items: center;
        gap: 10px;
    }

    .footer-center {
        flex-direction: row;
        gap: 20px;
    }

    .footer-center a {
        color: white;
        font-size: 24px;
        transition: color 0.3s;
    }

    .footer-center a:hover {
        color: #00acc1;
    }

    @media (max-width: 768px) {
        .footer {
            flex-direction: column;
            text-align: center;
            gap: 20px;
        }

        .footer-right {
            text-align: center;
        }
    }

</style>

<footer class="footer">

    <!-- Left section -->
    <div class="footer-left">
        <p>Trang web đọc truyện tranh online</p>
        <h3>TenWeb</h3>
        <p>Email hỗ trợ: phuclamhong05@gmail.com</p>
    </div>

    <!-- Center section -->
    <div class="footer-center">
        <a href="#"><i class="fb"></i></a>
        <a href="#"><i class="ig"></i></a>
        <a href="#"><i class="gh"></i></a>
    </div>

    <!-- Right section -->
    <div class="footer-right">
        <p>Copyright @ 2025</p>
        <p>Không sao chép</p>
    </div>
</footer>
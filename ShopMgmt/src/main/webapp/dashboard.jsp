<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<!DOCTYPE html>  
<html lang="en">  
<head>  
    <meta charset="UTF-8">  
    <meta name="viewport" content="width=device-width, initial-scale=1">  
    <title>Shop Management - Dashboard</title>  
    <!-- Bootstrap CSS -->  
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">  
    <style>  
        /* Nền gradient sang trọng */
        body {  
            background: linear-gradient(135deg, #1f4037, #99f2c8);  
            font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;  
            height: 100vh;  
            margin: 0;  
            display: flex;  
            justify-content: center;  
            align-items: center;  
        }  
        /* Container chính */  
        .dashboard-container {  
            background: rgba(255, 255, 255, 0.95);  
            border-radius: 15px;  
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);  
            padding: 40px;  
            width: 90%;  
            max-width: 900px;  
        }  
        /* Tiêu đề */  
        .dashboard-header {  
            text-align: center;  
            color: #ff9900;  
            font-size: 28px;  
            font-weight: bold;  
            text-transform: uppercase;  
            letter-spacing: 1px;  
            margin-bottom: 20px;  
        }  
        /* Thanh navigation */  
        .nav-link {  
            color: #333;  
            font-weight: bold;  
            transition: color 0.3s ease;  
        }  
        .nav-link:hover {  
            color: #ff9900;  
        }  
        /* Nút Logout */  
        .btn-logout {  
            background-color: #ff9900;  
            border: none;  
            border-radius: 50px;  
            padding: 10px 20px;  
            font-weight: bold;  
            transition: background-color 0.3s ease;  
        }  
        .btn-logout:hover {  
            background-color: #e68a00;  
        }  
    </style>  
</head>  
<body>  
    <div class="dashboard-container">  
        <div class="dashboard-header">
    Welcome, 
    <a href="profile.jsp" class="username-link">
        <%= request.getAttribute("username") != null ? request.getAttribute("username") : "Guest" %>
    </a>!
</div>
<p class="text-center">
    Last login: <%= request.getAttribute("loginDate") != null ? request.getAttribute("loginDate") : "Unknown" %>
</p>
        <ul class="nav nav-pills mb-3 justify-content-center">  
            <li class="nav-item">  
                <a class="nav-link active" href="#">Dashboard</a>  
            </li>  
            <li class="nav-item">  
                <a class="nav-link" href="#">Orders</a>  
            </li>  
            <li class="nav-item">  
                <a class="nav-link" href="./products">Products</a>  
            </li>  
            <li class="nav-item">  
                <a class="nav-link" href="#">Customers</a>  
            </li>  
            <li class="nav-item">  
                <a class="nav-link" href="#">Reports</a>  
            </li>  
        </ul>  

        <div class="text-center mt-4">  
            <p>Manage your shop effectively with our dashboard!</p>  
            <a href="logout" class="btn btn-logout">Logout</a>  
        </div>  
    </div>  

    <!-- Bootstrap JS -->  
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>  
</body>  
</html>

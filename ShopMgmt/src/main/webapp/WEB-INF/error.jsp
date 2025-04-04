<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<!DOCTYPE html>  
<html lang="en">  
<head>  
    <meta charset="UTF-8">  
    <meta name="viewport" content="width=device-width, initial-scale=1">  
    <title>Error - Shop Management</title>  
    <!-- Bootstrap CSS -->  
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">  
    <style>  
        /* Nền gradient tạo cảm giác sang trọng */  
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
        .error-container {  
            background: rgba(255, 255, 255, 0.95);  
            border-radius: 15px;  
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);  
            padding: 40px;  
            text-align: center;  
            max-width: 500px;  
            width: 100%;  
        }  
        /* Tiêu đề lỗi */  
        .error-header {  
            color: #ff0000;  
            font-size: 28px;  
            font-weight: bold;  
            text-transform: uppercase;  
            margin-bottom: 20px;  
        }  
        /* Mô tả lỗi */  
        .error-message {  
            font-size: 18px;  
            color: #333;  
            margin-bottom: 20px;  
        }  
        /* Nút điều hướng */  
        .btn-custom {  
            background-color: #ff9900;  
            border: none;  
            border-radius: 50px;  
            padding: 10px 20px;  
            font-weight: bold;  
            font-size: 16px;  
            transition: background-color 0.3s ease;  
        }  
        .btn-custom:hover {  
            background-color: #e68a00;  
        }  
    </style>  
</head>  
<body>  
    <div class="error-container">  
        <div class="error-header">Error</div>  
        <p class="error-message">Oops! Something went wrong. Please try again later.</p>  
        <a href="index.jsp" class="btn btn-custom">Back to Home</a>  
    </div>  

    <!-- Bootstrap JS -->  
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>  
</body>  
</html>

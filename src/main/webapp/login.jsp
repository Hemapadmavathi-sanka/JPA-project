<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login page</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f2f2f2;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
    }

    form {
        background-color: white;
        padding: 25px;
        border-radius: 10px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        width: 300px;
    }

    h2 {
        text-align: center;
        margin-bottom: 20px;
    }

    input {
        width: 100%;
        padding: 10px;
        margin: 10px 0;
        border: 1px solid #ccc;
        border-radius: 5px;
    }

    button {
        width: 100%;
        padding: 10px;
        background-color: #4CAF50;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }

    button:hover {
        background-color: #45a049;
    }

    a {
        display: block;
        text-align: center;
        margin-top: 15px;
        color: #333;
        text-decoration: none;
    }

    a:hover {
        text-decoration: underline;
    }
</style>
</head>
<body>
       
        
    <form action="login" method="post">
         <% 
            String msg = (String) request.getAttribute("message");
            if (msg != null) {
        %>
            <p class="error" style="color:green"><%= msg %></p>
        <% } %>
        <h2>Login</h2>
        <input type="text" name="name" placeholder="name" required>
        <input type="password" name="password" placeholder="Password" required>
        <button type="submit">Login</button><br><br>
     
   <a href="register">Don't have an account? Register here</a>
    
    </form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register page</title>
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
   .login-link {
        margin-top: 15px;
        text-align: center;
    }

    .login-link a {
        color: #333;
        text-decoration: none;
    }

    .login-link a:hover {
        text-decoration: underline;
    }

   
</style>
</head>
<body>
<form action="register" method="post">
         <h2>Register</h2>
           <% 
        String msg = (String) request.getAttribute("message");
        String status = (String) request.getAttribute("status");
        if (msg != null) {
    %>
        <p class="message <%= (status != null && status.equals("success")) ? "success" : "" %>">
            <%= msg %>
        </p>
    <% } %>
         
         
         <label>Name:</label><input type="text" name="name" placeholder="enter name" required>
        <label>Email:</label><input type="email" name="mail_id" placeholder=" enter email" required>
        <label>PhoneNumber:</label><input type="text" name="phonenumber" placeholder=" enter phonenumber" required>
        <label>Role:</label><input type="text" name="role" placeholder=" enter role" required>
        <label>company:</label><input type="text" name="company" placeholder=" enter company" required>
        <label>Password:</label><input type="password" name="password" placeholder=" enter Password" required><br><br>
     
        <button type="submit">Register</button>
        <a href="login">Already have an account? Login here</a>
    </form>
   
</body>
</html>
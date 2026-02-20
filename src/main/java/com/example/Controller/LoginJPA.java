package com.example.Controller;

import java.io.IOException;
import com.example.Service.Service;
import com.example.ServiceImplementation.ServiceImp;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginJPA extends HttpServlet {
	Service service=new ServiceImp();
	 protected void doGet(HttpServletRequest req, HttpServletResponse res)
	            throws ServletException, IOException {
		 req.getRequestDispatcher("login.jsp").forward(req, res);
	 
	 }
	 @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	try {
	       
	     String name = req.getParameter("name");
	       String password = req.getParameter("password");
	       
	       System.out.println(name+"---"+password);
	       
	       boolean result=service.findloginData(name,password);
	       System.out.println(result);
	       if(result) {
	    	   HttpSession session=req.getSession();
	    	   session.setAttribute("name",name);
	    	   res.sendRedirect("home");
	    	  
	       }else {
	           req.setAttribute("error", "Invalid username or password");
	           RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
	           rd.forward(req, res);
	       }
	          
	      }
	         catch(Exception e){
		        e.printStackTrace();
	            }
}}

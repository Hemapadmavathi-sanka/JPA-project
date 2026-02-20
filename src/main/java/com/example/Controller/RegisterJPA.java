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

@WebServlet("/register")
public class RegisterJPA extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
            throws ServletException, IOException {
        try {
        
        RequestDispatcher rd = req.getRequestDispatcher("register.jsp");
        rd.forward(req, res);
        }
        catch(Exception e){
        	e.printStackTrace();
        }
    }
    public void doPost(HttpServletRequest req, HttpServletResponse res) {
    	try {
    		//String id=req.getParameter("id");
    		String name=req.getParameter("name");
    		String mail_id=req.getParameter("mail_id");
    		String phonenumber=req.getParameter("phonenumber");
    		String role=req.getParameter("role");
    		String company=req.getParameter("company");
    		String password=req.getParameter("password");
    		
    		//System.out.println("ID:"+id);	
    	     System.out.println("Name:"+name);
    	     System.out.println("mail_id:"+mail_id);
    	     System.out.println("phonenumber:"+phonenumber);
    	     System.out.println("role:"+role);
    	     System.out.println("company:"+company);
    	     System.out.println("password:"+password);
    	
     Service service=new ServiceImp();
     boolean result=service.registerEmployeeData(name,mail_id,phonenumber,role,company,password);
     if(result) {
    	 req.setAttribute("message", "registerd Successfully !!!");
    	 req.getRequestDispatcher("login.jsp").forward(req, res);
    	 
     }
     else {
    	 req.setAttribute("message", "invalid values !!!");
    	 req.getRequestDispatcher("register.jsp").forward(req, res);
     }
    }
    	catch(Exception e) {
    		e.printStackTrace();
    }
    }}

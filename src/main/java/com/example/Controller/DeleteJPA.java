package com.example.Controller;

import java.io.IOException;
import com.example.Service.Service;
import com.example.ServiceImplementation.ServiceImp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/delete")
public class DeleteJPA extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			String id = req.getParameter("id");
		     String name = req.getParameter("name");
		     String mail = req.getParameter("mail");
		     String phonenumber = req.getParameter("phonenumber");
		     String role = req.getParameter("role");
		     String company = req.getParameter("company");
		       String password = req.getParameter("password");
		       
		       Service service=new ServiceImp();
		       boolean result=service.deleteEmployeeData(id,name,mail,phonenumber,role,company,password);
		       res.sendRedirect("home");
		}catch(Exception e){
	        e.printStackTrace();
        }

}
}

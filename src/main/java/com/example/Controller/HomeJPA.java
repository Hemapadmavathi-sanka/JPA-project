package com.example.Controller;

import java.io.IOException;
import java.util.List;

import com.example.Entity.Employee;
import com.example.Service.Service;
import com.example.ServiceImplementation.ServiceImp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/home")
public class HomeJPA extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		try {
			
			HttpSession session = req.getSession(false);

			if(session == null || session.getAttribute("name") == null){
			    res.sendRedirect("login");
			    return;
			}

			String name = (String) session.getAttribute("name");
			Service service=new ServiceImp();
			List<Employee> list=service.findAll();
			
			req.setAttribute("message", "Successfully logged");
			req.setAttribute("name", name);
			req.setAttribute("list", list);
			
			req.getRequestDispatcher("home.jsp").forward(req, res);
		    }
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}

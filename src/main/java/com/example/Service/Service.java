package com.example.Service;

import java.util.List;
import com.example.Entity.Employee;

public interface Service {
	 public boolean registerEmployeeData( String name, String mail_id,
             String phonenumber, String role,
             String company, String password);
public boolean findloginData(String name,String password);
public List<Employee> findAll();
public boolean updateEmployeeData(String id,String name,String mail,String phonenumber,
String role,String comapany,String password);
public boolean deleteEmployeeData(String id,String name,String mail,String phonenumber,
String role,String comapany,String password);
}

package com.example.dao;

import java.util.List;
import com.example.Entity.Employee;


public interface DataBaseOperation {
	public int save(Employee emp);
    public boolean findloginData(String name,String password);
    public List<Employee> findAll();
    public boolean updateEmployeeData(Employee emp);
    public boolean deleteEmployeeData(Employee emp);
    
}

package com.example.ServiceImplementation;
import java.util.List;
import com.example.Entity.Employee;
import com.example.Service.Service;
import com.example.dao.DataBaseOperation;
import com.example.daoimplementation.DataBaseOperationImp;

public class ServiceImp implements Service {
	DataBaseOperation db = new DataBaseOperationImp();
    @Override
    public boolean registerEmployeeData( String name, String mail_id,
                                       String phonenumber, String role,
                                       String company, String password) {

        Employee emp = new Employee();
        emp.setName(name);
        emp.setMail_id(mail_id);
        emp.setPhonenumber(phonenumber);
        emp.setRole(role);
        emp.setCompany(company);
        emp.setPassword(password);

        DataBaseOperation db = new DataBaseOperationImp();
        int result = db.save(emp);

        if( result >= 1) {
        	return true;
        }
        else
        	return false;
    }
    @Override
    public boolean findloginData(String name,String password) {
   
     return db.findloginData(name,password);
   
 
}
    public List<Employee> findAll(){
    	DataBaseOperation db=new DataBaseOperationImp();
    	List<Employee> list=db.findAll();
    	return list;
    }
    public boolean updateEmployeeData(String id,String name,String mail,String phonenumber,
    		String role,String company,String password) {
    	Employee emp=new Employee();
    	int ids=Integer.parseInt(id);
    	emp.setId(ids);
    	emp.setName(name);
    	emp.setMail_id(mail);
    	emp.setPhonenumber(phonenumber);
    	emp.setRole(role);
    	emp.setCompany(company);
    	emp.setPassword(password);
        
        DataBaseOperation db = new DataBaseOperationImp();
        boolean result = db.updateEmployeeData(emp);
    	return result;
    	
    }
    public boolean deleteEmployeeData(String id,String name,String mail,String phonenumber,
    		String role,String company,String password) {
    	Employee emp=new Employee();
    	int ids=Integer.parseInt(id);
    	emp.setId(ids);
    	emp.setName(name);
    	emp.setMail_id(mail);
    	emp.setPhonenumber(phonenumber);
    	emp.setRole(role);
    	emp.setCompany(company);
    	emp.setPassword(password);
        
        DataBaseOperation db = new DataBaseOperationImp();
        boolean result = db.deleteEmployeeData(emp);
    	return result;
    	
    }
}

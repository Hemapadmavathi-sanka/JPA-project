package com.example.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
@NamedQuery(
	name="Employee.login",
	query="select e from Employee e where e.name=:name AND e.password=:password"
)
@NamedQuery(
		name="Employee.findAll",
		query="SELECT e FROM Employee e"
	)
@NamedQuery(
	    name = "Employee.updateById",
	    query = "UPDATE Employee e SET " +
	            "e.name = :name, " +
	            "e.mail_id = :mail, " +
	            "e.phonenumber = :phone, " +
	            "e.role = :role, " +
	            "e.company = :company, " +
	            "e.password = :password " +
	            "WHERE e.id = :id"
	)

@NamedQuery(
	    name = "Employee.deleteById",
	    query = "DELETE FROM Employee e WHERE e.id = :id"
	)

@Entity
@Table (name="Employee")
public class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="mail_id")
	private String mail_id;
	
	 @Column(name="phonenumber")
  private String phonenumber;
	 
	 @Column(name="role")
	private String role;
	 @Column(name="company")
	
	private String company;
	 
	 @Column(name="password")
	private String password;
	
   public void setId(int id) {
		this.id=id;
	}
   
   public void setName(String name) {
		this.name=name;
	}
   
   public void setMail_id(String mail_id) {
		this.mail_id=mail_id;
	}
     public void setPhonenumber(String phonenumber) {
		this.phonenumber=phonenumber;
	}
  
   public void setRole(String role) {
		this.role=role;
	}
  
   public void setCompany(String company) {
		this.company=company;
	}
  
   public void setPassword(String password) {
	  this.password= password;
	  }
   
  public int getId() {
	  return id;
  }
  public String getName() {
	  return name;
  }
  public String getMail() {
	  return mail_id;
  }
  public String getPhonenumber() {
	  return phonenumber;
  }
  public String getRole() {
	  return role;
  }
  public String getCompany() {
	  return company;
  }
  public String getPassword() {
	  return password;
  }

 
 
  }
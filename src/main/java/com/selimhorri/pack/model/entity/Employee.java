package com.selimhorri.pack.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "employees")
public final class Employee implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "fname")
	private String fname;
	
	@Column(name = "lname")
	private String lname;
	
	@Email
	@Column(name = "email")
	private String email;
	
	@Column(name = "image_url")
	private String imageUrl;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "hiredate")
	private LocalDate hiredate;
	
	public Employee() {
		
	}
	
	public Employee(String fname, String lname, String email, String imageUrl, LocalDate hiredate) {
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.imageUrl = imageUrl;
		this.hiredate = hiredate;
	}
	
	public Employee(Integer id, String fname, String lname, String email, String imageUrl, LocalDate hiredate) {
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.imageUrl = imageUrl;
		this.hiredate = hiredate;
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", fname=" + fname + ", lname=" + lname + ", email=" + email + ", imageUrl="
				+ imageUrl + ", hiredate=" + hiredate + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public LocalDate getHiredate() {
		return hiredate;
	}

	public void setHiredate(LocalDate hiredate) {
		this.hiredate = hiredate;
	}
	
	
	
}






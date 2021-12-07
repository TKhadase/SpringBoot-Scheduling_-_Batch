package com.tushar.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class IStudents implements Serializable {

	private int sid;
	private String fname;
	private String lname;
	private int age;
	private String sex;
	
}

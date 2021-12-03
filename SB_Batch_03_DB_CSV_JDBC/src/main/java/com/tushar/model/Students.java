package com.tushar.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Students implements Serializable {

	//create table Batch_02_CSV_DB_JDBC (SID Number, fname varchar2(20), lname varchar2(20), age Number, sex varchar2(10), rank Number);
	private int sid;
	private String fname;
	private String lname;
	private int age;
	private String sex;
	private int  rank;
	
	
	
}

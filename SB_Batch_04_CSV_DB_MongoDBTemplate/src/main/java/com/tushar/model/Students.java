package com.tushar.model;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Students implements Serializable {

	private int sid;
	private String fname;
	private String lname;
	private int age;
	private String sex;
	private int  rank;
	
}

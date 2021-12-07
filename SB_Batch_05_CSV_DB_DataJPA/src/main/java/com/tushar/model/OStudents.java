package com.tushar.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="SB_Batch_05_CSV")
public class OStudents implements Serializable {

	@Id
	private int sid;
	private String fname;
	private String lname;
	private int age;
	private String sex;
	private int rank;
}

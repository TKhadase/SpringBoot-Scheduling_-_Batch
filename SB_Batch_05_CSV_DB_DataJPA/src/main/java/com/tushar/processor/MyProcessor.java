package com.tushar.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.tushar.model.*;

//@Component("myProcessorBean")
public class MyProcessor implements ItemProcessor<IStudents, OStudents> {

	OStudents studentO =null;
	
	public MyProcessor() {
		System.out.println("myProcessor.myProcessor()");
	}
	
	@Override
	public OStudents process(IStudents student) throws Exception {
		
		 studentO =new OStudents();
		 
		  	studentO.setSid(student.getSid());
			studentO.setFname(student.getFname());
			studentO.setLname(student.getLname());
			studentO.setAge(student.getAge());
			studentO.setSex(student.getSex());
			
		if(student.getAge()>18)
		{
			studentO.setRank(student.getSid()+2);
			return studentO;
		}
		else {
			studentO.setRank(student.getSid()+1);
			return studentO;
		}
		
	}//process

}//MyProcessor

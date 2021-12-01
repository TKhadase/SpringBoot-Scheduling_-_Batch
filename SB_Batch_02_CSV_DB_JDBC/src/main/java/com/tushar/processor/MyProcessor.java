package com.tushar.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.tushar.model.Students;

//@Component("myProcessorBean")
public class MyProcessor implements ItemProcessor<Students, Students> {

	public MyProcessor() {
		System.out.println("myProcessor.myProcessor()");
	}
	
	@Override
	public Students process(Students student) throws Exception {
		
		if(student.getAge()>18)
		{
			student.setRank(student.getSid());
			return student;
		}
		else {
			student.setRank(student.getSid());
			return student;
		}
		
	}//process

}//MyProcessor

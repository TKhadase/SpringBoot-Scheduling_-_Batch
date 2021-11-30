package com.tushar.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

@Component("myReaderBean")
public class MyReader implements ItemReader<String> {

	String students[] = new String[]{"JOHN","ROCK", "MIZ", "EDGE"};
	int count= 0;
	
	public MyReader() {
		System.out.println("MyReader.MyReader()");
	}
	
	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		System.out.println("MyReader.read()");
		if(count< students.length) {
			return students[count++];
		}else
		{
		return null;
		}
	}

}

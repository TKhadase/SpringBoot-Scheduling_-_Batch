package com.tushar.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component("myWriterBean")
public class MyWriter implements ItemWriter<String> {
 
	public MyWriter() {
		System.out.println("MyWriter.MyWriter()");
	}
	
	@Override
	public void write(List<? extends String> items) throws Exception {
		System.out.println("MyWriter.write()");
		items.forEach(System.out::println);
	}

}

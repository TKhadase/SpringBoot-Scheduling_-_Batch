package com.tushar.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component("myProcessorBean")
public class MyProcessor implements ItemProcessor<String, String> {

	public MyProcessor() {
		System.out.println("myProcessor.myProcessor()");
	}
	
	@Override
	public String process(String item) throws Exception {
		System.out.println("myProcessor.process()");
		if(item.length()>=4)
		{
			return "WWE UNIVERSAL CHAMPION " +item ;
		}
		else{
			return "WWE CHAMPION "  +item ;
		}
	}

}

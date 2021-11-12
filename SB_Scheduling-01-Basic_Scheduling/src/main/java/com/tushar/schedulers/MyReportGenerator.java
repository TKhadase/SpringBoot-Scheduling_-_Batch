package com.tushar.schedulers;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyReportGenerator {

	@Scheduled(initialDelay = 5*1000, fixedDelay = 5*1000)//1000=1sec
	private void generateCustomerReport() {
		System.out.println(Thread.currentThread().getName()+"-"+Thread.currentThread().hashCode()+"-Customer Report  Generation starts : "+new Date());
	}
	
	/*
	cron exp:  sec minute hour dateOfMonth month  weekDayName
	every 15sec of every minute:: 15 * * * * *
	everyday 9am:: 0 0 9 * * * 
	everyday 8:27:01PM :: 1 27 20 * * *
	everyday 8:27:01PM & 10:27:01PM:: 1 27 20,22 * * *
	everyday 8:27:01PM & 9:27:01PM &10:27:01PM:: 1 27 20-22 * * *
	
	*/
	@Scheduled(cron="1 27 20 * * *")
	private void generateSOAReport() {
		System.out.println(Thread.currentThread().getName()+"-"+Thread.currentThread().hashCode()+"-SOA Report  Generation starts : "+new Date());
	}
	
}

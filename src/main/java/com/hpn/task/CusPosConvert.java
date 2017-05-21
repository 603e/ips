package com.hpn.task;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CusPosConvert {

	@Scheduled(cron="0/10 * *  * * ? ")
	public void convertGauss2Geodetic(){
		//System.out.println(new Date());		
	}
}

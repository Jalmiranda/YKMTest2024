package com.example.ykm.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.math.NumberUtils;

import lombok.extern.log4j.Log4j2;
 
@Aspect
@Component
@Log4j2
public class ShipAspect {
 
	@After("execution(* com.example.ykm.service.ShipService.getShipById(..))")
	public void logIdNegativo(JoinPoint point) {
		if(NumberUtils.isParsable(point.getArgs()[0].toString())) {
			int id = Integer.parseInt(point.getArgs()[0].toString());
			if(id < 0) {
				log.error("DETECTADO ID NEGATIVO");
			}
		} else {
			log.error("ID UTILIZADO NO ES NUMERICO");
		}
	}
	
}


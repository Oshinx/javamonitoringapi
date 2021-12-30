package com.servicemonitor.service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.codehaus.jettison.json.JSONObject;
import org.codehaus.jettison.json.JSONWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonFactoryBuilder;
import com.servicemonitor.model.IPThreshold;
import com.servicemonitor.model.ServiceLog;
import com.servicemonitor.model.ServiceThreshold;
import com.servicemonitor.repository.ServiceLogRepository;
import com.servicemonitor.repository.ServiceThresholdRepository;

@Service
public class ServiceThresholdService {
   

	final ServiceThresholdRepository serviceThresholdRepository;
	
	@Autowired
    public ServiceThresholdService(ServiceThresholdRepository serviceThresholdRepository) {
		this.serviceThresholdRepository = serviceThresholdRepository;
	}
	
	
	
	public ResponseEntity<?> saveServiceLogs(ServiceThreshold serviceThreshold) {

		if ((!serviceThreshold.getDuration().equalsIgnoreCase("hourly")
				|| !serviceThreshold.getDuration().equalsIgnoreCase("daily"))
				&& serviceThreshold.getStartDate() == null) {
		    
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	  }
		System.out.println(serviceThreshold.getStartDate() + " ff");
		String formatStartDate = formatStartDate(serviceThreshold.getStartDate());
		
		System.out.println(formatStartDate + " heello");
	    try {

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
            System.out.println("before");
           if(serviceThreshold.getDuration().equalsIgnoreCase("hourly")) {
        	   LocalDateTime startDate  = LocalDateTime.parse(formatStartDate, formatter);
        	   System.out.println(startDate + " start Date");
   			LocalDateTime hour = startDate.plusHours(1L);
   			   System.out.println(hour + " end date" );
   			  String tempDate = hour.format(formatter);
   			LocalDateTime endDate  = LocalDateTime.parse(tempDate, formatter);

  			System.out.println(startDate.format(formatter) + " =======  " + endDate.format(formatter));
  			String startDateFormatted = startDate.format(formatter);
  			String endDateFormatted = endDate.format(formatter);
  			System.out.println(startDateFormatted + " =======  " + endDateFormatted);
  			
   		    return ResponseEntity.ok(this.serviceThresholdRepository.getThreshold(startDateFormatted, endDateFormatted)) ;
   			
           }
           
           
           if(serviceThreshold.getDuration().equalsIgnoreCase("daily")) {   			  
        	    LocalDateTime startDate  = LocalDateTime.parse(formatStartDate, formatter);
      			LocalDateTime day = startDate.plusDays(1L);
      			LocalDateTime endDate  = LocalDateTime.parse(day.format(formatter), formatter);
      			//this.serviceThresholdRepository.getThreshold(startDate.format(formatter), endDate.format(formatter));
        	  // LocalDateTime day = 
      			
      			System.out.println(startDate + " ====daily===  " + endDate);
      			String startDateFormatted = startDate.format(formatter);
      			String endDateFormatted = endDate.format(formatter);
      			System.out.println(startDateFormatted + " =======  " + endDateFormatted);
      			List<Object[]> objs = this.serviceThresholdRepository.getThreshold(startDateFormatted, endDateFormatted);
      			List<IPThreshold> ipThresholds = new ArrayList<>();
      			for(Object[] obj : objs) {
      				IPThreshold ipThreshold = new IPThreshold((String)obj[0], (BigInteger) obj[1]);
      				ipThresholds.add(ipThreshold);
      				
      			}
      			return ResponseEntity.ok(ipThresholds) ;
   		 // System.out.println(day +	  "   <==========   ");
           }
	    }
	    catch (Exception e) {
			// TODO: handle exception
	    	System.out.println(e);
	    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.notFound().build();
	
		
		//this.serviceThresholdRepository.getThreshold(null, null);
		
	   // this.serviceLogRepository.finby
		}
	
	
	 public String formatStartDate(String startDate) {
		 String splittedDate[] = startDate.split("\\.");
		 System.out.println(Arrays.toString(splittedDate));
		 String formattedJoined = splittedDate[0] + " " +splittedDate[1]+".000";
		 
		 
		 return formattedJoined;
	 }
	
	
	
}

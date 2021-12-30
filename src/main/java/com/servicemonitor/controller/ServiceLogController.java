package com.servicemonitor.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.servicemonitor.model.ServiceLog;
import com.servicemonitor.service.ServiceLogService;


@RestController
@RequestMapping(path = "api/v1/servicerequest")
public class ServiceLogController {
	/*
	 * @Autowired JobLauncher jobLauncher;
	 * 
	 * 
	 * Job job;
	 * 
	 * private
	 */ final ServiceLogService serviceLogService;
	
	@Autowired
	public ServiceLogController(ServiceLogService serviceLogService) {
		this.serviceLogService = serviceLogService;
	}
	
	
	 @GetMapping("/{ip}")
     public List<ServiceLog> getServiceRequestByIP(@PathVariable String ip,
    		 @RequestParam(defaultValue = "20") int pageSize,
    		 @RequestParam(defaultValue = "1") int pageNumber ) {
		 
		   
	
		 
		 pageNumber = pageNumber -1;
		 return this.serviceLogService.getServiceLogByIP(ip, pageNumber, pageSize);
	 }
	
	 
	 
	 
	
	 
	 
	 
	@PostMapping("/upload")
	public ResponseEntity<?> handlesAccessLogUpload(@RequestParam("file") MultipartFile file){
		Long size = file.getSize() / (1024 * 1024);
		
		if(size > 20) {
			return new ResponseEntity<>(
			          "File Size Cannot be Greater than 20Mb", 
			          HttpStatus.BAD_REQUEST);
		}
		
		
		String fileName =  file.getOriginalFilename();
		System.out.println(fileName);
	
		try {
			String content = new String(file.getBytes());

			  List<ServiceLog> result = new ArrayList<>();
		        BufferedReader reader = new BufferedReader(new StringReader(content));
		            String line = reader.readLine();
		       	 
		   		 DateTimeFormatter formatter = new DateTimeFormatterBuilder()
		   				  .appendPattern("yyyy-MM-dd HH:mm:ss")
		   				  .appendFraction(ChronoField.MILLI_OF_SECOND, 2, 3, true) // min 2 max 3
		   				  .toFormatter();
		   		  
		            while (line != null) {
		            String splittedContent[] = line.split("\\|");
		            System.out.println(splittedContent[0]);
		            ServiceLog log = new ServiceLog(
		            	                            	LocalDateTime.parse(splittedContent[0],formatter),
									            		splittedContent[1],
									            		splittedContent[2],
									            		Integer.parseInt(splittedContent[3]),
									            		splittedContent[4]);

		                result.add(log);
		                line = reader.readLine();
		            }
		            System.out.println(result.size());
		          this.serviceLogService.saveServiceLogs(result);
		     //       this.serviceLogService.
			return ResponseEntity.ok("File uploaded successfully");
			
			
		} 
		catch(SizeLimitExceededException e) {
			 System.out.println(e);
			return  ResponseEntity.status( HttpStatus.BAD_REQUEST).build();
		}
		catch (IllegalStateException|IOException e) {
			// TODO Auto-generated catch block
		System.out.println(e);
		  return 	ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
        
	}
	
	
      

}

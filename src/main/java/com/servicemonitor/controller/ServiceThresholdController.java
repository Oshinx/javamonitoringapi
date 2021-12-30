package com.servicemonitor.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.List;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.servicemonitor.model.ServiceLog;
import com.servicemonitor.model.ServiceThreshold;
import com.servicemonitor.service.ServiceLogService;
import com.servicemonitor.service.ServiceThresholdService;

@RestController
@RequestMapping(path = "api/v1/servicethreshold")
public class ServiceThresholdController {
	
    final ServiceThresholdService serviceThresholdService;
	
	@Autowired
	public ServiceThresholdController(ServiceThresholdService serviceThresholdService) {
		this.serviceThresholdService = serviceThresholdService;
	}
	
	 @GetMapping("/")
	 public ResponseEntity<?> getThesholdDetails(@RequestBody ServiceThreshold serviceThreshold ) {
		   
		 return serviceThresholdService.saveServiceLogs(serviceThreshold);
		 
	   
	 }
	
}

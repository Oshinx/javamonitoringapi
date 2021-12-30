package com.servicemonitor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.servicemonitor.model.ServiceLog;
import com.servicemonitor.repository.ServiceLogRepository;


@Service
public class ServiceLogService {

	final ServiceLogRepository serviceLogRepository;
	
	@Autowired
  public ServiceLogService(ServiceLogRepository serviceLogRepository) {
		this.serviceLogRepository = serviceLogRepository;
	}

	public void saveServiceLogs(List<ServiceLog> serviceLogs) {
	this.serviceLogRepository.saveAll(serviceLogs);
   // this.serviceLogRepository.finby
	}
	
	public List<ServiceLog> getServiceLogByIP(String ip, int offset, int pageSize){
		Page<ServiceLog> pages =  serviceLogRepository.findServiceLogByIP(ip, PageRequest.of(offset, pageSize));
		List<ServiceLog> serviceLogs = pages.getContent();
		return serviceLogs;
	}
	
	
	
}

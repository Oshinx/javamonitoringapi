package com.servicemonitor.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.servicemonitor.model.ServiceLog;

public interface ServiceThresholdRepository  extends JpaRepository<ServiceLog, Long> {
         
	   @Query(value="SELECT service_requestip, COUNT(service_requestip) AS total_request FROM service_log"
	   		+ " WHERE service_request_date >= STR_TO_DATE(:startDate, '%Y-%m-%d %H:%i:%s.%f')"
	   		+ " AND service_request_date <= STR_TO_DATE(:endDate, '%Y-%m-%d %H:%i:%s.%f')"
	   		+ " GROUP BY service_requestip"
	   		+ " Having (total_request) >= 100;", nativeQuery = true)
		   List<Object[]> getThreshold(@Param("startDate") String startDate, @Param("endDate")String endDate);
	
}

package com.servicemonitor.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.servicemonitor.model.ServiceLog;

@Repository
public interface ServiceLogRepository  extends JpaRepository<ServiceLog, Long> {

	   @Query("SELECT s FROM ServiceLog s WHERE s.serviceRequestIP =?1")
	   Page<ServiceLog> findServiceLogByIP(String ip, Pageable pageable );
	   
	
}

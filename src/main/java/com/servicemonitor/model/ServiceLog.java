package com.servicemonitor.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;



@Entity
public class ServiceLog {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen")
    @SequenceGenerator(name = "seqGen", sequenceName = "seq", initialValue = 1)
   private Long id;
	//@Column(name = "A_")
   private LocalDateTime serviceRequestDate;
	//@Column(name = "B_")
   private String serviceRequestIP;
	//@Column(name = "C_")
   private String serviceRequestType;
//	@Column(name = "D_")
   private int serviceRequestResponseCode;
	//@Column(name = "E_")
   private String serviceRequestSource;
   
   
   
   

   
public ServiceLog() {

	}



public ServiceLog(LocalDateTime serviceRequestDate, String serviceRequestIP, String serviceRequestType,
		int serviceRequestResponseCode, String serviceRequestAgent) {

	this.serviceRequestDate = serviceRequestDate;
	this.serviceRequestIP = serviceRequestIP;
	this.serviceRequestType = serviceRequestType;
	this.serviceRequestResponseCode = serviceRequestResponseCode;
	this.serviceRequestSource = serviceRequestAgent;
}



public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public LocalDateTime getServiceRequestDate() {
	return serviceRequestDate;
}
public void setServiceRequestDate(LocalDateTime serviceRequestDate) {
	this.serviceRequestDate = serviceRequestDate;
}
public String getServiceRequestIP() {
	return serviceRequestIP;
}
public void setServiceRequestIP(String serviceRequestIP) {
	this.serviceRequestIP = serviceRequestIP;
}
public String getServiceRequestType() {
	return serviceRequestType;
}
public void setServiceRequestType(String serviceRequestType) {
	this.serviceRequestType = serviceRequestType;
}
public int getServiceRequestResponseCode() {
	return serviceRequestResponseCode;
}
public void setServiceRequestResponseCode(int serviceRequestResponseCode) {
	this.serviceRequestResponseCode = serviceRequestResponseCode;
}
public String getServiceRequestAgent() {
	return serviceRequestSource;
}
public void setServiceRequestAgent(String serviceRequestAgent) {
	this.serviceRequestSource = serviceRequestAgent;
}



@Override
public String toString() {
	return "ServiceLog [serviceRequestDate=" + serviceRequestDate + ", serviceRequestIP=" + serviceRequestIP
			+ ", serviceRequestType=" + serviceRequestType + ", serviceRequestResponseCode="
			+ serviceRequestResponseCode + ", serviceRequestAgent=" + serviceRequestSource + ", getId()=" + getId()
			+ ", getServiceRequestDate()=" + getServiceRequestDate() + ", getServiceRequestIP()="
			+ getServiceRequestIP() + ", getServiceRequestType()=" + getServiceRequestType()
			+ ", getServiceRequestResponseCode()=" + getServiceRequestResponseCode() + ", getServiceRequestAgent()="
			+ getServiceRequestAgent() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
			+ super.toString() + "]";
}
   
   
   
   
   
}

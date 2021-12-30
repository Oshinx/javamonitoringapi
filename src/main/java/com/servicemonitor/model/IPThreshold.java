package com.servicemonitor.model;

import java.math.BigInteger;

import org.springframework.stereotype.Component;

@Component
public class IPThreshold {
	
	
   private String serviceRequestIP;
   private BigInteger totalRequest;
   
   
	public IPThreshold() {
	
      }


	public IPThreshold(String serviceRequestIP, BigInteger totalRequest) {
    this.serviceRequestIP = serviceRequestIP;
	this.totalRequest = totalRequest;
     }
	
	
	public String getServiceRequestIP() {
		return serviceRequestIP;
	}
	public void setServiceRequestIP(String serviceRequestIP) {
		this.serviceRequestIP = serviceRequestIP;
	}
	public BigInteger getTotalRequest() {
		return totalRequest;
	}
	public void setTotalRequest(BigInteger totalRequest) {
		this.totalRequest = totalRequest;
	}
	   
   
}

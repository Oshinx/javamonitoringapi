package com.servicemonitor.model;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class ServiceThreshold {

	private String startDate;
	private String duration;
	private  int threshold;
	
	
	
	
	
	
	public ServiceThreshold(String startDate, String duration, int threshold) {
		super();
		this.startDate = startDate;
		this.duration = duration;
		this.threshold = threshold;
	}






	public ServiceThreshold() {
	
	}







	
	
	








	public String getStartDate() {
		return startDate;
	}






	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}






	public String getDuration() {
		return duration;
	}






	public void setDuration(String duration) {
		this.duration = duration;
	}






	public int getThreshold() {
		return threshold;
	}






	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}






	@Override
	public String toString() {
		return "ServiceThreshold [startDate=" + startDate + ", duration=" + duration + ", threshold=" + threshold + "]";
	}
	
	
}

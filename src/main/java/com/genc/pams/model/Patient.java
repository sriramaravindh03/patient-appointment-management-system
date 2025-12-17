package com.genc.pams.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Patient {
	@Id
	private int patientID;
	private String patientName;
	private long phno;
	private String email;
	private String address;
	public Patient() {}
	public Patient(String patientName, long phno, String email, String address) {
		this.patientName = patientName;
		this.phno = phno;
		this.email = email;
		this.address = address;
	}
	public int getPatientID() {
		return patientID;
	}
	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public long getPhno() {
		return phno;
	}
	public void setPhno(long phno) {
		this.phno = phno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}

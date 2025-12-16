package com.genc.pams.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Patient {
	@Id
	private int patientID;
	private String patientName;
	private long phno;
	public Patient() {}
	public Patient(String patientName, long phno) {
		super();
		this.patientName = patientName;
		this.phno = phno;
	}
}

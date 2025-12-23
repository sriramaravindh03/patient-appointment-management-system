package com.genc.pams.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "patient")
@Data
public class Patient {
	@Id
	private int patientID;
	private String patientName;
	private String phno;
	private String email;
	private String address;
	private String dob;
	private String password;
}
package com.genc.pams.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genc.pams.model.Patient;
import com.genc.pams.repository.PatientRepository;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public void savePatient(Patient patient) {
        patientRepository.save(patient);
    }
}

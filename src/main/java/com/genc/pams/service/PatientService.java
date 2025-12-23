package com.genc.pams.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genc.pams.model.Patient;
import com.genc.pams.repository.PatientRepository;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient getPatientById(Integer id) {
        return patientRepository.findById(id).orElse(null);
    }

    public Patient getPatientByPhno(Long phno) {
        return patientRepository.findByPhno(phno);
    }
}

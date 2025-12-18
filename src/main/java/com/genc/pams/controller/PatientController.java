package com.genc.pams.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.genc.pams.model.Patient;
import com.genc.pams.service.PatientService;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping("/register")
    public String show(Model model) {
        model.addAttribute("patient",new Patient());
        return "registerPatient";
    }
    
    @PostMapping("/home")
    public String registerPatient(@ModelAttribute Patient patient, Model model) {
        patientService.savePatient(patient);
        String name = patient.getPatientName();
        String email = patient.getEmail();
        String out = "Patient Name: "+name+"\n"+"Email: "+email;
        model.addAttribute("out", out);
        return "patientHome";
    }
}

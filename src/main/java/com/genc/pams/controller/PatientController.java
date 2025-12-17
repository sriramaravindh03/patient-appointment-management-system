package com.genc.pams.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.genc.pams.model.Patient;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PatientController {
    @GetMapping("/register")
    public String show(Model model) {
        // String name = "Patient 1";
        // Patient p = new Patient();
        model.addAttribute("patient",new Patient());
        return "registerPatient";
    }
    
    @PostMapping("/home")
    public String registerPatient(@ModelAttribute Patient patient, Model model) {
        String name = patient.getPatientName();
        String email = patient.getEmail();
        String out = "Patient Name: "+name+"\n"+"Email: "+email;
        model.addAttribute("out", out);
        return "patientHome";
    }
}

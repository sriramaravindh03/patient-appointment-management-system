package com.genc.pams.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.genc.pams.model.Patient;
import com.genc.pams.service.PatientService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping("/")
    public String start() {
        return "loginPatient";
    }   

    @GetMapping("/register")
    public String showReg(Model model) {
        model.addAttribute("patient",new Patient());
        return "registerPatient";
    }

    @GetMapping("/log")
    public String showLog() {
        return "loginPatient";
    }
    
    
    @PostMapping("/log")
    public String registerPatient(@ModelAttribute Patient patient, Model model) {
        patientService.savePatient(patient);
        return "loginPatient";
    }

    @PostMapping("/home")
    public String loginPatient(@ModelAttribute Patient patient, Model model) {
        Patient byPhone = patientService.getPatientByPhno(patient.getPhno());
        if (byPhone == null) {
            patient.setPassword(null);
            model.addAttribute("patient", patient);
            model.addAttribute("error", "Wrong phone number");
            return "loginPatient";
        }

        String provided = patient.getPassword() == null ? "" : patient.getPassword();
        String actual = byPhone.getPassword() == null ? "" : byPhone.getPassword();

        if (!actual.equals(provided)) {
            // wrong password (case-sensitive check)
            patient.setPassword(null);
            model.addAttribute("patient", patient);
            model.addAttribute("error", "Wrong password");
            return "loginPatient";
        }

        // success
        String name = byPhone.getPatientName();
        String out = "Welcome to your dashboard, "+name+"\n";
        model.addAttribute("out", out);
        model.addAttribute("patient", byPhone);
        return "patientHome";
    }
    
    @GetMapping("/profile")
    public String profileForm(@RequestParam Integer id, Model model) {
        Patient patient = patientService.getPatientById(id);
        model.addAttribute("patient", patient);
        return "patientProfile";
    }

    @PostMapping("/profile")
    public String updateProfile(@ModelAttribute Patient patient, Model model) {
        Patient existing = patientService.getPatientById(patient.getPatientID());
        if (existing == null) {
            existing = patient;
        } else {
            existing.setPatientName(patient.getPatientName());
            existing.setEmail(patient.getEmail());
            existing.setPhno(patient.getPhno());
            existing.setAddress(patient.getAddress());
            existing.setDob(patient.getDob());
            if(patient.getPassword() != null && !patient.getPassword().isEmpty())
                existing.setPassword(patient.getPassword());
        }
        Patient updated = patientService.savePatient(existing);
        model.addAttribute("patient", updated);
        String out = "Welcome to your dashboard, "+updated.getPatientName();
        model.addAttribute("out", out);
        return "patientHome";
    }
}
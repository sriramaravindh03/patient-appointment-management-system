package com.genc.pams.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PatientController {
    @GetMapping("/test")
    public String show(Model model) {
        String name = "Patient 1";
        model.addAttribute("patientName",name);
        return "abc";
    }
    // Hello world
    //hello tamizh selvan

}

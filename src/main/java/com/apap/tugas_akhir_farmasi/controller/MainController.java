package com.apap.tugas_akhir_farmasi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    /*
    Write your mapping here
     */
    @RequestMapping(value = "/")
    private String home(){
        return "home";
    }
}

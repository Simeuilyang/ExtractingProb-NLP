package com.project.extractingNLP.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String Index() {
        return "index";
    }

    @GetMapping("/addFile")
    public String addFile() {
    	return "addFile";
    }
  
    @GetMapping("/signUp")
    public String signUp() {
       return "signUp";
    }
    
    @GetMapping("/editProb")
    public String editProb() {
       return "editProb";
    }
    
    @GetMapping("/solveProb")
    public String solveProb() {
       return "solveProb";
    }
    
    @GetMapping("/pdfviewtest")
    public String pdfviewtest() {
        return "pdfViewtest";
    }
    
    @GetMapping("/pdftest")
    public String pdftest() {
        return "pdfview";
    }
       
}
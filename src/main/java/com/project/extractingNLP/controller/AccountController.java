package com.project.extractingNLP.controller;

import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.extractingNLP.model.dao.AccountDao;
import com.project.extractingNLP.model.dao.FileDao;

@Controller
public class AccountController {
   AccountDao aDao = AccountDao.getInstance();
   FileDao fDao = FileDao.getInstance();
   
   /*GET ACCOUNTS*/
   @ResponseBody
   @GetMapping("/account/get/{id}")
    public JSONObject getUsers(@PathVariable("id") String id) throws SQLException {
      System.out.println("id: " + id);
      JSONArray tmp = aDao.getAccountInfoById(id);
      
      
      return (JSONObject) tmp.get(0);
    }
   
    @ResponseBody
    @GetMapping("/account/login/{id}/{pwd}")
    public boolean validLogin(@PathVariable("id") String id, @PathVariable("pwd") String pwd) throws SQLException {
        System.out.println("@ REQUEST : [id]" + id + " try to login by [pw] " + pwd);
       
        JSONArray result = aDao.validLogin(id, pwd);
        if(result.size() == 1)
        {
           return true;
        }
        return false;        
    }

    @ResponseBody
    @GetMapping("/account/checkid/{id}")
    public boolean checkDup(@PathVariable("id") String id) throws SQLException {
        JSONArray result = aDao.getAccountInfoById(id);
        
        if(result.size() == 0) {
           return true;
        }
        return false;
        
    }
    
    @ResponseBody
    @PostMapping("/account/add/{id}/{pwd}/{name}")
    public boolean addAccount(@PathVariable("id") String id, @PathVariable("pwd") String pwd, @PathVariable("name") String name) throws SQLException {
        int result = aDao.addAccount(id, pwd, name);
        
        
        if(result != 0) {
           return true;
        }
        return false;
        
    }
}
package com.project.extractingNLP.Controller;

import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.extractingNLP.Model.Dao.AccountDao;
import com.project.extractingNLP.Model.Dao.FileDao;

@Controller
public class AccountController {
	AccountDao aDao = AccountDao.getInstance();
	FileDao fDao = FileDao.getInstance();
	
	/*GET ACCOUNTS*/
	@ResponseBody
	@GetMapping("/account/{id}")
    public void getUsers(@PathVariable("id") String id) throws SQLException {
		System.out.println("id: " + id);
		JSONArray tmp = aDao.getAccountInfoById(id);
		for(int i = 0; i<tmp.size();i++)
		{

		   System.out.println(tmp.get(i));
		}
		
    }
	
}

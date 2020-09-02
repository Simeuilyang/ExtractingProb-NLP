package com.project.extractingNLP.controller;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.extractingNLP.model.dao.*;


@CrossOrigin
@Controller
public class DirController {
//private static final Logger logger = LoggerFactory.getLogger(DirController.class);
	DirDao dDao = DirDao.getInstance();
	
	@ResponseBody
	@RequestMapping(value = "/dir/add/{pdid}/{dname}/{aid}", method = {RequestMethod.GET})
	public boolean addDir(@PathVariable("pdid") int pdid,@PathVariable("dname") String dname, @PathVariable("aid") String aid) throws SQLException {
		System.out.println("@POST: ADD DIR");
		return dDao.addDir(pdid, dname, aid);
	}
	
	@ResponseBody
	@PutMapping("/dir/move/{did}/{pdid}")
	public boolean movedir(@PathVariable("did") int did, @PathVariable("pdid") int pdid) throws SQLException {
		System.out.println("did:" + did + " pdid: " + pdid);
		return dDao.moveDir(did, pdid);
	}
	
	
	
	@ResponseBody
	@GetMapping("/dir/delete/{aid}/{did}")
	public boolean deletedir(@PathVariable("aid") String aid, @PathVariable("did") int did) throws SQLException {
		return dDao.deleteDir(aid,did);
	}
	
	@ResponseBody
	@GetMapping("/dir/get/{aid}/{did}")
	public JSONArray[] findFiles(@PathVariable("aid") String aid, @PathVariable("did") int did) throws SQLException {
	    JSONArray[] tmp = new JSONArray[3]; 
	    tmp[0] = dDao.NowFolder(aid, did);
	    tmp[1] = dDao.findChildFolder(aid, did);
	    tmp[2] = dDao.findSibilingFolder(aid, did);
	    System.out.println(tmp[0]);
	    System.out.println(tmp[1]);
	    System.out.println(tmp[2]);
	    return tmp;
	}
	
	@ResponseBody
	@GetMapping("/dir/find/{aid}/{did}")
	public JSONArray childdir(@PathVariable("aid") String aid, @PathVariable("did") int did) throws SQLException {
	    JSONArray tmp = dDao.findChildFolder(aid, did);
	    return tmp;
	}
	@ResponseBody
	@GetMapping("/dir/now/{aid}/{did}")
	public JSONArray nowdir(@PathVariable("aid") String aid, @PathVariable("did") int did) throws SQLException {
	    JSONArray tmp = dDao.NowFolder(aid, did);
	    return tmp;
	}

}
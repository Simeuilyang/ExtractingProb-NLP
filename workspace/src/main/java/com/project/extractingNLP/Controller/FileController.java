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
public class FileController {
	AccountDao aDao = AccountDao.getInstance();
	FileDao fDao = FileDao.getInstance();
	
	/*GET ACCOUNTS*/
	@ResponseBody
	@GetMapping("/getfiles/{id}/{did}")
    public void findFiles(@PathVariable("id") String id, @PathVariable("did") int did) throws SQLException {
		System.out.println("id: " + id  + " did: " + did);
		JSONArray tmp = fDao.findFiles(id, did);
		for(int i = 0; i<tmp.size();i++)
		{
			System.out.println(tmp.get(i));
		}
		
    }
	
	@ResponseBody
	@GetMapping("/file/add/{fid}/{pdid}/{fname}/{aid}")
	public boolean addFile(@PathVariable("fid") int fid, @PathVariable("pdid") int pdid, @PathVariable("fname") String fname, @PathVariable("aid") String aid) throws SQLException {
	  System.out.println("fid, pdid, fname, aid: " + fid + " " + pdid + " " + "fname" + " " + aid);
	      
	  return fDao.addFile(fid, pdid, fname, aid);
	}
	
	@ResponseBody
	@GetMapping("/file/delete/{fid}")
	public boolean deleteFile(@PathVariable("fid") int fid) throws SQLException {
		System.out.println("DELETE FILE!   fid: " + fid);
		return fDao.deleteFile(fid);
	}
	
	@ResponseBody
	@GetMapping("/file/move/{fid}/{movdid}")
	public boolean moveFile(@PathVariable("fid") int fid, @PathVariable("movdid") int movdid) throws SQLException {
		System.out.println("MOVE FILE!! fid:" + fid + " did:" + movdid);
		   
		return fDao.moveFile(fid, movdid);
	}
	
	@ResponseBody
	@GetMapping("/file/rename/{fid}/{newName}")
	public boolean renameFile(@PathVariable("fid") int fid, @PathVariable("newName") String newName) throws SQLException{
		System.out.println("RENAME FILE!!");
		   
		return fDao.renameFile(fid, newName);
	}
	
}

package com.project.extractingNLP.controller;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFImageWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.project.extractingNLP.model.dao.FileDao;

@Controller
public class FileController {
   FileDao fDao = FileDao.getInstance();
   
   @ResponseBody
   @GetMapping("/file/get/{aid}/{did}") 
   public JSONArray findFiles(@PathVariable("aid") String aid, @PathVariable("did") int did) throws SQLException {
      System.out.println("id: " + aid  + " did: " + did);
      JSONArray tmp = fDao.findFiles(aid, did);
      for(int i = 0; i<tmp.size();i++)
      {
         System.out.println(tmp.get(i));
      }
      return tmp;
    }
   

	 

   
   @ResponseBody
   @GetMapping("/file/add/{pdid}/{fname}/{aid}")
    public String addFile(@PathVariable("pdid") int pdid, @PathVariable("fname") String fname, @PathVariable("aid") String aid) throws SQLException {
      
	  System.out.println("pdid: " + pdid + " " + "fname:" + fname + " aid:" + aid);
      String fid = Integer.toString(fDao.addFile(pdid, fname, aid));
      
      return fid;
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

   private final String PATH = "C:\\Users\\dmlfi\\Desktop\\pdfFile\\";
   
   @Autowired
   MappingJackson2JsonView jsonView;
   
   @RequestMapping(value = "/uploadForm.do")
   public String uploadForm() throws Exception {
	   return "uploadForm";
   }
   
   @RequestMapping(value="/file/upload/{fid}", method= {RequestMethod.POST, RequestMethod.GET}, produces="text/plain")
   public ModelAndView upload(MultipartHttpServletRequest request, @PathVariable("fid") String fid) throws Exception{
	   System.out.println("upload Controller!!");
	   System.out.println("Controller내 fid: " + fid);
	   ModelAndView model = new ModelAndView();
	   model.setView(jsonView);
	   
	   Iterator itr = request.getFileNames();
	   
	   if(itr.hasNext()) {
		   List mpf =request.getFiles((String) itr.next());
		   
		   
		   for(int i=0;i<mpf.size();i++) {
			   String origName = new String(((MultipartFile) mpf.get(i)).getOriginalFilename().getBytes("8859_1"), "UTF-8");
			   System.out.println("origName: " + origName);
			   
			   File file = new File(PATH + fid + "_" + origName);
			   
			   ((MultipartFile) mpf.get(i)).transferTo(file);
		   }
		   
		   System.out.println("for문 다음!");
		   JSONObject json = new JSONObject();
		   json.put("code", "ture");
		   model.addObject("result", "json");
		   return model;
		 
	   }else {
		   JSONObject json = new JSONObject();
		   json.put("code", "false");
		   model.addObject("result", json);
		   return model;
		  
	   }
	
   }

   @RequestMapping(value="/file/image/{fid}", method= {RequestMethod.POST, RequestMethod.GET}, produces="text/plain")
   public ModelAndView makeImage(MultipartHttpServletRequest request, @PathVariable("fid") String fid) throws Exception{
	   
	   ModelAndView model = new ModelAndView();
	   model.setView(jsonView);
	      
	   Iterator itr = request.getFileNames();
	      
	   if(itr.hasNext()) 
	   {
	      List mpf =request.getFiles((String) itr.next());
	      boolean result = false;   
	      for(int i=0;i<mpf.size();i++) {
	    	  String origName = new String(((MultipartFile) mpf.get(i)).getOriginalFilename().getBytes("8859_1"), "UTF-8");
	    	  System.out.println("origName: " + origName);
	    	  String saveName = fid + "_" + origName;
	    	  
	    	  String imageFormat = "gif";
	    	  int pdfPageCn = 0;
	    	  PDDocument pdfDoc = null;
	    	  try {
	    		  pdfDoc = PDDocument.load(PATH+saveName);
	    		  pdfPageCn = pdfDoc.getNumberOfPages();
	    		  System.out.println("PDF파일 총페이지 수 : " + pdfPageCn);
	    	  }catch (IOException ioe) {
	    		  System.out.println("PDF 정보취득 실패 : " + ioe.getMessage());
	          }
	    	  PDFImageWriter imageWriter = new PDFImageWriter();
	  		  try {
	  			 saveName = saveName.replace(".pdf", "");
	         	 String destPath = "E:\\심의량\\공개 소프트웨어 개발자 대회\\ExtractingProb-NLP\\extractingNLP\\src\\main\\webapp\\resources\\fileImage\\"+saveName;
	  			  result = imageWriter.writeImage(pdfDoc, imageFormat, "",1, 1,destPath, BufferedImage.TYPE_INT_RGB, 300);
	  	      } catch (IOException ioe) {
	  	    	  System.out.println("PDF 이미지저장 실패 : " + ioe.getMessage());
	  	      }
	  	  }
	      System.out.println("for문 다음!");
	      JSONObject json = new JSONObject();
	      json.put("code", result);
	      model.addObject("result", "json");
	      return model;
	  }else 
	  {
		  JSONObject json = new JSONObject();
	      json.put("code", "false");
	      model.addObject("result", json);
	      return model;
	   }
       
       
        
   }

}
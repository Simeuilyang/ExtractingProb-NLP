package com.project.extractingNLP.controller;
import java.io.*;
import java.io.InputStreamReader;
import java.util.List;
import java.io.BufferedReader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.python.util.PythonInterpreter;

@Controller
public class textrankController  {
	
	
	//@GetMapping("/textRank/{file}")
	//public boolean execPython(@PathVariable("file") String file) throws Exception {
	@RequestMapping("/textRank")
	@ResponseBody
	public boolean execPython(@RequestParam(value="list") List<String> list) throws Exception {
		
		
		//String file="5_test1.pdf";
		boolean isExists = true;
		System.out.println("running python file..");
		
		
		for(int i=0;i<list.size();i++)
		{
			String pdffile = list.get(i);
			System.out.println("pdffile: " + pdffile);
			
			String file = pdffile.replace(".pdf","");
			String keywordFile="E:\\심의량\\공개 소프트웨어 개발자 대회\\ExtractingProb-NLP\\extractingNLP\\src\\main\\webapp\\resources\\file\\questions\\"+ file +"_keword.txt";
			System.out.println("keword file: " + keywordFile);
			File kfile= new File(keywordFile);
			
			if(kfile.exists())
			{
				
				System.out.println("이미 있음");
				continue;
			}
			
			
			//Stringfile = pdffile.replace(".pdf","");
			ProcessBuilder pb = new ProcessBuilder("C:\\Users\\dmlfi\\AppData\\Local\\Programs\\Python\\Python37\\python",
					"E:\\심의량\\공개 소프트웨어 개발자 대회\\ExtractingProb-NLP\\extractingNLP\\src\\main\\webapp\\resources\\file\\textRank.py",
					pdffile);
			
			
	
			Process p = pb.start();
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(),"utf-8"));
			try {
				String line="";
				while((line=br.readLine()) != null) {
					System.out.println(line);
				}
			}
			finally {
				try {
					if(br!=null)
						br.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}	
			}
			keywordFile="E:\\심의량\\공개 소프트웨어 개발자 대회\\ExtractingProb-NLP\\extractingNLP\\src\\main\\webapp\\resources\\file\\questions\\"+file+"_keword.txt";
			System.out.println(keywordFile);
			kfile= new File(keywordFile);
			isExists=kfile.exists();
			
		}
		
		
		if(isExists)
		{
			System.out.println("file exist");
			return true;
		}
		else 
		{
			System.out.println("file not exist");
			return false;	
		}
		
	}

}
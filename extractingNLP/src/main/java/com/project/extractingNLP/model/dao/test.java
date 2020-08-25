package com.project.extractingNLP.model.dao;

import java.sql.SQLException;

import com.project.extractingNLP.model.DBconfig;

public class test {

	public static void main(String[] args)
	{
		DBconfig dbconnect = new DBconfig();
		
		FileDao a = new FileDao();
		AccountDao b = new AccountDao();
		DirDao c = new DirDao();
		
		try {
			System.out.println("[findFiles]test: " + a.findFiles("dayoon98", 8));
			
			//System.out.println("[addFiles]test: " + a.addFile(17, 3, "f17", "dmlfid1348"));
			//addFiles 해당 디렉토리id 찾고 실행하기			
			
			//System.out.println("[moveFiles]test: " + a.moveFile(17, 2));
			//moveFiles 옮기려는 디렉토리의 id찾고 실행
			
			//System.out.println("[deleteFiles]test: " + a.deleteFile(17));
			//deleteFiles 삭제하려는 파일 id찾고 실행
			
			////////////////////////////////////////////////////////////////////
			//System.out.println("[addAccount]test: " + b.addAccount("gongjju", "123456", "hihi"));
			
			//System.out.println("[updateAccount]test: " + b.updateAccount("id = 'dmlfid1348'", "pwd", "9811"));
			//condition ?!
			
			//System.out.println("[deleteAccount]test: " + b.deleteAccount("id='gongjju'"));
			
			////////////////////////////////////////////////////////////////////
			//System.out.println("[addDir]test: " + c.addDir(12, -1, "eunjuDIR", "eun2u"));
			//addDir did 순차적으로 값 넣어주기
			
			//System.out.println("[moveDir]test:" + c.moveDir(10, -1));
			
			//System.out.println("[deleteDir]test: " + c.deleteDir(1));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

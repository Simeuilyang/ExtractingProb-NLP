package com.project.extractingNLP.Model.Dao;

import java.sql.SQLException;

import com.project.extractingNLP.Model.DBconfig;

public class test {

	public static void main(String[] args)
	{
		DBconfig dbconnect = new DBconfig();
		
		FileDao a = new FileDao();
		AccountDao b = new AccountDao();
		DirDao c = new DirDao();
		
		try {
			//System.out.println("[findFiles]test: " + a.findFiles("dayoon98", 8));
			
			//System.out.println("[addFiles]test: " + a.addFile(17, 3, "f17", "dmlfid1348"));
			//addFiles �ش� ���丮id ã�� �����ϱ�			
			
			//System.out.println("[moveFiles]test: " + a.moveFile(17, 2));
			//moveFiles �ű���� ���丮�� idã�� ����
			
			//System.out.println("[deleteFiles]test: " + a.deleteFile(17));
			//deleteFiles �����Ϸ��� ���� idã�� ����
			
			////////////////////////////////////////////////////////////////////
			//System.out.println("[addAccount]test: " + b.addAccount("gongjju", "123456", "hihi"));
			
			//System.out.println("[updateAccount]test: " + b.updateAccount("id = 'dmlfid1348'", "pwd", "9811"));
			//condition ?!
			
			//System.out.println("[deleteAccount]test: " + b.deleteAccount("id='gongjju'"));
			
			////////////////////////////////////////////////////////////////////
			//System.out.println("[addDir]test: " + c.addDir(12, -1, "eunjuDIR", "eun2u"));
			//addDir did ���������� �� �־��ֱ�
			
			//System.out.println("[moveDir]test:" + c.moveDir(10, -1));
			
			System.out.println("[deleteDir]test: " + c.deleteDir(1));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

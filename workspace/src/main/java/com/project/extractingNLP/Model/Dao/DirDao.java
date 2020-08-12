package com.project.extractingNLP.Model.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;

import com.project.extractingNLP.Model.Dto.FileDto;

public class DirDao extends Dao {
    private static DirDao instance = null;
    
    public DirDao(){
        super();
        System.out.println("DirDao's JDBC driver is found");
    }

    public static DirDao getInstance() {
        if (instance == null) {
            instance = new DirDao();
        }
        return instance;
    }

    private JSONArray executeQuery(String query) throws SQLException {
        JSONArray jsonResults = new JSONArray();
        try {
            holdConnection();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                FileDto Category = new FileDto(rs.getInt("fid"), rs.getInt("pdid"), rs.getString("fname"), rs.getString("aid"));
                jsonResults.add(Category.toJSONObject());
            }
        } catch (SQLException e) {
            System.out.println("! SQL ERROR (" + query + ") : " + e.getMessage());
        } finally {
            releaseConnection();
        }
        return jsonResults;
    }

    //�깉 �뵒�젆�넗由� 異붽�
    public boolean addDir(int did, int pdid, String dname, String aid) throws SQLException {
        try {
            holdConnection();
            String sql = "INSERT INTO DIR(DID, PDID, DNAME, AID)"
            		+ "VALUES(" 
            		+ did + ","
            		+ pdid + ",'"
            		+ dname + "','"
            		+ aid + "')";

            executeSQL(sql);
            return true;
        } catch (SQLException e) {
            System.err.println("! SQL ERROR (return item) : " + e.getMessage());
            return false;
        }
    } 
   
    //moveDir �뵒�젆�넗由� �씠�룞
    //did: �씠�룞�떆�궎�젮�뒗 �뵒�젆�넗由촫d, pdid: �씠�룞�븯�젮�뒗 �뵒�젆�넗由촫d 
    public boolean moveDir(int did, int pdid) throws SQLException {
        try { 
            holdConnection();
            
           String sql = "SELECT AID FROM DIR WHERE DID = '"+ did +"'";
           pstmt = conn.prepareStatement(sql);
           rs = pstmt.executeQuery(); 
           String accountid = null;
           
           while(rs.next()) {
        	   accountid = rs.getString(1); 
           }
           
           sql = "UPDATE DIR"
            		+ " SET PDID = " + pdid
            		+ " WHERE DID = " + did
            		+ " AND AID = '" + accountid + "'";

            executeSQL(sql);
            return true;
        } catch (SQLException e) {
            System.err.println("! SQL ERROR (return item) : " + e.getMessage());
            return false;
        }
    }
    
    public JSONArray findDirs(String aid, int pdid) throws SQLException{
    	String query = "select d.did, d.pdid, d.dname, d.aid "
    			+ "from dir d, account a "
    			+ "where d.aid = a.id "
    			+ "and d.pdid = " + pdid
    			+ " and a.id = '" + aid
    			+ "' and d.did != -1";
    	System.out.println(query);
    	return executeQuery(query);
    }
    
    public boolean renameFile(int did, String newName) throws SQLException{
		try { 
            holdConnection();
            String sql = " UPDATE DIR SET"
					+ " DNAME = '" + newName
					+ "' WHERE DID = " + did;
			
			executeSQL(sql);
			
			return true;
        } catch (SQLException e) {
            System.err.println("! SQL ERROR (return item) : " + e.getMessage());
            return false;
        }
	}
    
    //deleteDir �뵒�젆�넗由� �궘�젣 - �냽�빐�엳�뒗 �뙆�씪, �뵒�젆�넗由� 紐⑤몢 �궘�젣
    public boolean deleteDir(int did) throws SQLException {
        try { 
            holdConnection();
            
//            String sql = "SELECT AID FROM DIR WHERE DID = '"+ did +"'";
//            pstmt = conn.prepareStatement(sql);
//            rs = pstmt.executeQuery(); 
//            String accountid = null;
//            
//            while(rs.next()) {
//         	   accountid = rs.getString(1); 
//            }
            
//            String sql = "DELETE FROM DIR"
//            		+ " WHERE DID = " + did; //�빐�떦 did �뵒�젆�넗由� �궘�젣 -> on delete cascade �뻹臾몄뿉 �냽�븳 �뙆�씪 媛숈씠 �궘�젣�맖
//            	
//            executeSQL(sql);
            
            
            String sql = "SELECT D2.DID"
            		+ " FROM DIR D1, DIR D2"
            		+ " WHERE D1. DID = D2.PDID AND D1.DID = " + did;
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery(); 
            ArrayList<Integer> childDir = new ArrayList<Integer>();
            int deletedid;
            
            while(rs.next()) {
            	deletedid = rs.getInt(1);
            	System.out.println("deletedid: " + deletedid);
            	childDir.add(deletedid); 
            }
            
            System.out.println("childDir 由ъ뒪�듃�뿉 諛붾줈諛� �븯�쐞 �뵒�젆�넗由촫d ���옣");
            
            // �걧 �뜥�꽌 �빐蹂닿린 !!!!!!//
            for(int id : childDir) { 
            	sql = "SELECT D2.DID"
                		+ " FROM DIR D1 INNER JOIN DIR D2 ON D1.DID = D2.PDID"
                		+ " WHERE D1.DID = " + id;
            	 pstmt = conn.prepareStatement(sql);
                 rs = pstmt.executeQuery();
                 while(rs.next()) {
                 	deletedid = rs.getInt(1);
                 	childDir.add(deletedid);
                 }
            }
            System.out.println("洹� �븯�쐞 �뵒�젆�넗由щ룄 由ъ뒪�듃�뿉 ���옣");
            
            for(int id : childDir) { 
            	sql = "DELETE FROM DIR"
                		+ " WHERE DID = " + id;
            	executeSQL(sql);
            }
            System.out.println("�냽�븳 �뵒�젆�넗由щ뱾�룄 紐⑤몢 �궘�젣");
            
            sql = "DELETE FROM DIR"
            		+ " WHERE DID = " + did; //�빐�떦 did �뵒�젆�넗由� �궘�젣 -> on delete cascade �뻹臾몄뿉 �냽�븳 �뙆�씪 媛숈씠 �궘�젣�맖
            System.out.println("�썝�옒 �궘�젣�븯�젮�뜕 �뵒�젆�넗由� �궘�젣 => �냽�븳 �뙆�씪�뱾�룄 �궘�젣");
            
            executeSQL(sql);
            return true;
            
        } catch (SQLException e) {
            System.err.println("! SQL ERROR (return item) : " + e.getMessage());
            return false;
        }
    }
    
   
    
    public JSONArray findAll() throws SQLException {
        String query = "SELECT * FROM category";
        return executeQuery(query);
    }
    public JSONArray findByCondition(String condition) throws SQLException {
        String query = "SELECT * FROM category WHERE " + condition;
        return executeQuery(query);
    }
}
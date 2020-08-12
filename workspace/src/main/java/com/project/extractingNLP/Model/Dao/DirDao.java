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

    //새 디렉토리 추가
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
   
    //moveDir 디렉토리 이동
    //did: 이동시키려는 디렉토리id, pdid: 이동하려는 디렉토리id 
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
    
    //deleteDir 디렉토리 삭제 - 속해있는 파일, 디렉토리 모두 삭제
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
//            		+ " WHERE DID = " + did; //해당 did 디렉토리 삭제 -> on delete cascade 떄문에 속한 파일 같이 삭제됨
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
            
            System.out.println("childDir 리스트에 바로밑 하위 디렉토리id 저장");
            
            // 큐 써서 해보기 !!!!!!//
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
            System.out.println("그 하위 디렉토리도 리스트에 저장");
            
            for(int id : childDir) { 
            	sql = "DELETE FROM DIR"
                		+ " WHERE DID = " + id;
            	executeSQL(sql);
            }
            System.out.println("속한 디렉토리들도 모두 삭제");
            
            sql = "DELETE FROM DIR"
            		+ " WHERE DID = " + did; //해당 did 디렉토리 삭제 -> on delete cascade 떄문에 속한 파일 같이 삭제됨
            System.out.println("원래 삭제하려던 디렉토리 삭제 => 속한 파일들도 삭제");
            
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
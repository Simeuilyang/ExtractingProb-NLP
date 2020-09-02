package com.project.extractingNLP.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import org.json.simple.JSONArray;

import com.project.extractingNLP.model.dto.DirDto;
import com.project.extractingNLP.model.dto.FileDto;

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
        //System.out.println("dirdao getInstance()호출");
        return instance;
    }

    @SuppressWarnings("unchecked")
	private JSONArray executeQuery(String query) throws SQLException {
        JSONArray jsonResults = new JSONArray();
        try {
            holdConnection();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                DirDto dir = new DirDto(rs.getInt("did"), rs.getInt("pdid"), rs.getString("dname"), rs.getString("aid"));
                System.out.println("ab: "+dir.toJSONObject());
                jsonResults.add(dir.toJSONObject());
            }
        } catch (SQLException e) {
            System.out.println("! SQL ERROR (" + query + ") : " + e.getMessage());
        } finally {
            releaseConnection();
        }
        System.out.println("aaa:"+jsonResults);
        return jsonResults;
    }

  //자식 디렉토리 
    public JSONArray findChildFolder(String aid, int did) throws SQLException{
    	String query = "SELECT * from dir WHERE aid = '" +aid+ 
    			"' AND pdid='"+did+"' "+
    			"AND not did = -1 ORDER BY DNAME DESC";
    	return executeQuery(query);			
    }

    //형제 디렉토리 
    public JSONArray findSibilingFolder(String aid, int did) throws SQLException{
    	String query = "SELECT * from DIR WHERE PDID = (SELECT PDID FROM DIR WHERE AID = '"+aid+ 
    			"' AND did='"+did+"') "+
    			"AND AID = '"+aid+"' "+
    			"AND not did = -1 "+
    			"AND NOT DID = "+did+
    			" ORDER BY DNAME DESC";
    	return executeQuery(query);			
    }

    //현재 디렉토리 정보 
    public JSONArray NowFolder(String aid, int did) throws SQLException{
    	//SELECT *
    	//FROM DIR
    	//WHERE DID = 11;
    	String query = "SELECT * from DIR WHERE DID = "+did+" AND AID = '"+aid+"'"; 
    	System.out.println("nowquery : "+query);
    	return executeQuery(query);			
    }

    

    public boolean addDir(int pdid, String dname, String aid) throws SQLException {
        try {
            holdConnection();
            
            String sql = "SELECT MAX(DID) FROM DIR WHERE AID = '" + aid + "'";
            
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            int did = 0;
            while(rs.next()) {
            	did = rs.getInt(1);
            }
            did++;
            System.out.println("maxdid : " + did);
            
            sql = "INSERT INTO DIR"
            		+ " VALUES(" 
            		+ did + ", "
            		+ pdid + ", '"
            		+ dname + "', '"
            		+ aid + "') ";

            executeSQL(sql);
            //releaseConnection();
            return true;
        } catch (SQLException e) {
            System.err.println("! SQL ERROR (return item) : " + e.getMessage());
            return false;
        }
    } 
   
    //moveDir 디렉토리이동
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
    
    //deleteDir 디렉토리 삭제 - 속해있는 파일, 디렉토리 모두 삭제
    public boolean deleteDir(String aid, int did) throws SQLException {
        try { 
            holdConnection();
            String sql = "SELECT * from dir WHERE aid = '" +aid+ 
        			"' AND pdid='"+did+"' "+
        			"AND not did = -1 ";

            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery(); 
            ArrayList<Integer> childDir = new ArrayList<Integer>();
            int deletedid;
            
            while(rs.next()) {
            	deletedid = rs.getInt(1);
            	childDir.add(deletedid); 
            }

            // 큐 써서 해보기 !!!!!!//
            int size = childDir.size();
            for(int i=0 ;i<size;i++) { 
            	int id = childDir.get(i);
            	sql = "SELECT D2.DID"
                		+ " FROM DIR D1 INNER JOIN DIR D2 ON D1.DID = D2.PDID"
                		+ " WHERE D1.DID = " + id+" AND D1.AID = '"+aid+"'";

            	pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                while(rs.next()) {
                	deletedid = rs.getInt(1);
                 	childDir.add(deletedid);
                 	size++;
                }

            }
            for(int id : childDir) { 
            	sql = "DELETE FROM DIR"
                		+ " WHERE DID = " + id+" AND AID = '"+aid+"'";
            	executeSQL(sql);
            }
            
            sql = "DELETE FROM DIR"
            		+ " WHERE DID = " + did+" AND AID = '"+aid+"'"; //해당 did 디렉토리 삭제 -> on delete cascade 떄문에 속한 파일 같이 삭제
            executeSQL(sql);
            return true;
            
        } catch (SQLException e) {
            System.err.println("! SQL ERROR (return item) : " + e.getMessage());
            return false;
        }
    }

    
   
    
    public JSONArray findAll() throws SQLException {
        String query = "SELECT * FROM DIR";
        return executeQuery(query);
    }
    public JSONArray findByCondition(String condition) throws SQLException {
        String query = "SELECT * FROM DIR WHERE " + condition;
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
}
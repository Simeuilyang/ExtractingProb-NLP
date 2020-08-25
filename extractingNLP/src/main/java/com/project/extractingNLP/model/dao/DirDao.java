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
        System.out.println("dirdao getInstance()»£√‚");
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
                jsonResults.add(dir.toJSONObject());
            }
        } catch (SQLException e) {
            System.out.println("! SQL ERROR (" + query + ") : " + e.getMessage());
        } finally {
            releaseConnection();
        }
        return jsonResults;
    }

    

    public boolean addDir(int pdid, String dname, String aid) throws SQLException {
        try {
            holdConnection();
            
            String sql = "SELECT MAX(DID) FROM DIR";
            
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
   
    //moveDir ?îî?†â?Ü†Î¶? ?ù¥?èô
    //did: ?ù¥?èô?ãú?Ç§?†§?äî ?îî?†â?Ü†Î¶¨id, pdid: ?ù¥?èô?ïò?†§?äî ?îî?†â?Ü†Î¶¨id 
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
    
    //deleteDir ?îî?†â?Ü†Î¶? ?Ç≠?†ú - ?Üç?ï¥?ûà?äî ?åå?ùº, ?îî?†â?Ü†Î¶? Î™®Îëê ?Ç≠?†ú
    public boolean deleteDir(int did) throws SQLException {
        try { 
            holdConnection();
            

            
            LinkedList<Integer> qu = new LinkedList<Integer>(); //make queue 
            
            
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
            	qu.add(deletedid);
            	//childDir.add(deletedid); 
            }
            
           //System.out.println(qu); //check Queue data
            
            while(qu.peek() != null) { //Qø° ≥ªøÎ ¿÷¿ª µøæ»
            	int thisdir = 0;
            	thisdir = qu.poll(); //≈•ø°º≠ ≤®≥ªº≠ 
            	
            	childDir.add(thisdir); //'thisdir'∏¶ childDirø° ≥÷∞Ì
            	
            	sql = "SELECT D2.DID"
                		+ " FROM DIR D1 INNER JOIN DIR D2 ON D1.DID = D2.PDID"
                		+ " WHERE D1.DID = " + thisdir;
            	pstmt = conn.prepareStatement(sql);
            	rs = pstmt.executeQuery();
            	
            	while(rs.next()) {
                 	deletedid = rs.getInt(1);
                 	qu.add(deletedid);
                 }
            }
            
            //System.out.println(childDir);
            
            
//            for(int id : childDir) { 
//            	sql = "SELECT D2.DID"
//                		+ " FROM DIR D1 INNER JOIN DIR D2 ON D1.DID = D2.PDID"
//                		+ " WHERE D1.DID = " + id;
//            	 pstmt = conn.prepareStatement(sql);
//                 rs = pstmt.executeQuery();
//                 while(rs.next()) {
//                 	deletedid = rs.getInt(1);
//                 	childDir.add(deletedid);
//                 }
//            }
//            System.out.println("Í∑? ?ïò?úÑ ?îî?†â?Ü†Î¶¨ÎèÑ Î¶¨Ïä§?ä∏?óê ???û•");
//            
            for(int id : childDir) { 
            	sql = "DELETE FROM DIR"
                		+ " WHERE DID = " + id;
            	executeSQL(sql);
            }

            
            sql = "DELETE FROM DIR"
            		+ " WHERE DID = " + did; //?ï¥?ãπ did ?îî?†â?Ü†Î¶? ?Ç≠?†ú -> on delete cascade ?ñÑÎ¨∏Ïóê ?Üç?ïú ?åå?ùº Í∞ôÏù¥ ?Ç≠?†ú?ê®
            System.out.println("ø¯∑° ªË¡¶«œ∑¡¥¯ dir ªË¡¶");
            
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
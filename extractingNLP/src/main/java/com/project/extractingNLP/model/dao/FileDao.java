package com.project.extractingNLP.model.dao;

import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.project.extractingNLP.model.dto.FileDto;

public class FileDao extends Dao {
    private static FileDao instance = null;

    public FileDao() {
        super();
        System.out.println("FileDao's JDBC driver is found");
    }

    public static FileDao getInstance() {
        if (instance == null) {
            instance = new FileDao();
        }
        return instance;
    }

    private JSONArray executeQuery(String query) throws SQLException {
        JSONArray jsonResults = new JSONArray();
        try {
            holdConnection();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                FileDto file = new FileDto(rs.getInt("fid"), rs.getInt("pdid"), rs.getString("fname"), rs.getString("aid"));
                System.out.println(file);
                jsonResults.add(file.toJSONObject());
            }
        } catch (SQLException e) {
            System.out.println("! SQL ERROR (" + query + ") : " + e.getMessage());
        } finally {
            releaseConnection();
        }
        return jsonResults;
    }
    
    //findFiles: ?Ç¨?ö©?ûê id?? Í∑? ?Ç¨?ö©?ûê?ùò ?îî?†â?Ü†Î¶? id?óê ?Üç?ïò?äî ?åå?ùº Ï∞æÍ∏∞
    public JSONArray findFiles(String id, int pdid) throws SQLException {
    	String query = "SELECT * from Filelist " + 
    			"where pdid='"+pdid+"' "+
    			"AND aid ='"+id+"'";
        System.out.println(query);
        return executeQuery(query);
    }
    
	//?Éàfile Ï∂îÍ?
	public int addFile(int pdid, String fname, String aid) throws SQLException {
	        try {
	            holdConnection();
	            String sql = "SELECT MAX(FID) AS MAXFID FROM FILELIST";
	            
	            pstmt = conn.prepareStatement(sql);
	            rs = pstmt.executeQuery();
	            int fid = 0;
	            while(rs.next()) {
	            	fid = rs.getInt(1);
	            }
	            fid++;
	            System.out.println("maxfid : " + fid);
	            
	            sql = "INSERT INTO filelist(fid, pdid, fname, aid) " 
	                    + "VALUES("
	                    + fid + ", "
	                    + pdid + ", '"
	                    + fname + "','"
	                    + aid + "')";
	
	            executeSQL(sql);
	            return fid;
	        } catch (SQLException e) {
	            System.err.println("! SQL ERROR (return item) : " + e.getMessage());
	            return 0;
	        }
	    }


	//moveFile ?åå?ùº ?ù¥?èô 
	//fid: ?ù¥?èô?ãú?Ç§?†§?äî ?åå?ùºid, movdid:?ù¥?èô?ïò?†§?äî ?îî?†â?Ü†Î¶¨id 
	public boolean moveFile(int fid, int movdid) throws SQLException {
	    try { 
	        holdConnection();
	        String sql = "UPDATE FILELIST SET "
	        		+ "PDID = " + movdid
	        		+ " WHERE FID = " + fid;
	        
	        executeSQL(sql);
	        return true;
	    } catch (SQLException e) {
	        System.err.println("! SQL ERROR (return item) : " + e.getMessage());
	        return false;
	    }
	}

	//deleteFile ?åå?ùº ?Ç≠?†ú
	//fid: ?Ç≠?†ú?ïò?†§?äî ?åå?ùº id
	public boolean deleteFile(int fid) throws SQLException {
	    try { 
	        holdConnection();
	        String sql = "DELETE FROM FILELIST "
	        		+ "WHERE FID = " + fid;
	
	        executeSQL(sql);
	        return true;
	    } catch (SQLException e) {
	        System.err.println("! SQL ERROR (return item) : " + e.getMessage());
	        return false;
	    }
	}
	
	public boolean renameFile(int fid, String newName) throws SQLException{
		try {
			holdConnection();
			String sql = " UPDATE FILELIST SET"
					+ " FNAME = '" + newName
					+ "' WHERE FID = " + fid;
			
			executeSQL(sql);
			return true;
		}catch(SQLException e) {
			System.err.println("! SQL ERROR (return item) : " + e.getMessage());
			return false;
		}
	}

   
    
    

}


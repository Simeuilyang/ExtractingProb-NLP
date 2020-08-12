package com.project.extractingNLP.Model.Dao;

import com.project.extractingNLP.Model.Dto.FileDto;

import java.sql.CallableStatement;
import java.sql.SQLException;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

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

    @SuppressWarnings("unchecked")
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
    
    //�닔�젙�뻽�쓬
    public JSONArray getFiles(String id) throws SQLException {
        JSONArray files = new JSONArray();
        String query = 
        		"SELECT a.id, a.name, d.did, d.dname, f.fid, f.fname " +
        		"FROM account a, dir d, filelist f "+ 
        		"WHERE a.id = d.aid and f.pdid = d.did and a.id= '"+ id +"'";
        try {
            holdConnection();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                JSONObject eachFile = new JSONObject();
                eachFile.put("id", rs.getInt("id"));
                eachFile.put("name", rs.getString("name"));
                eachFile.put("did", rs.getString("did"));
                eachFile.put("dname", rs.getInt("dname"));
                eachFile.put("fid", rs.getString("fid"));
                eachFile.put("fname", rs.getString("fname"));
                files.add(eachFile);
            }
        } catch (SQLException e) {
            System.err.println("! SQL ERROR (getFiles fail) :" + e.getMessage());
        } finally {
            releaseConnection();
        }

        return files;
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
    
    
    //findFiles: �궗�슜�옄 id�� 洹� �궗�슜�옄�쓽 �뵒�젆�넗由� id�뿉 �냽�븯�뒗 �뙆�씪 李얘린
    public JSONArray findFiles(String id, int did) throws SQLException {
        String query = "select f.fid, f.pdid, f.fname, f.aid " + 
        		"from account a, dir d, filelist f " + 
        		"where f.pdid=d.did " + 
        		"and f.aid=d.aid " + 
        		"and d.aid = a.id " + 
        		"and a.id = '"+ id + 
        		"' and d.did = " + did;
        System.out.println(query);
        return executeQuery(query);
    }
    
	//�깉file 異붽�
	public boolean addFile(int fid, int pdid, String fname, String aid) throws SQLException {
	        try {
	            holdConnection();
	            String sql = "INSERT INTO filelist(fid, pdid, fname, aid) " 
	                    + "VALUES("
	                    + fid + ","
	                    + pdid + ", '"
	                    + fname + "','"
	                    + aid + "')";
	
	            executeSQL(sql);
	            return true;
	        } catch (SQLException e) {
	            System.err.println("! SQL ERROR (return item) : " + e.getMessage());
	            return false;
	        }
	    }


	//moveFile �뙆�씪 �씠�룞 
	//fid: �씠�룞�떆�궎�젮�뒗 �뙆�씪id, movdid:�씠�룞�븯�젮�뒗 �뵒�젆�넗由촫d 
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

	//deleteFile �뙆�씪 �궘�젣
	//fid: �궘�젣�븯�젮�뒗 �뙆�씪 id
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


    public JSONArray findAll() {
        String query = "SELECT * FROM item";
        try {
            return executeQuery(query);
        } catch (SQLException e) {
            System.err.println("! SQL ERROR (executeQuery): " + e.getMessage());
            return null;
        }
    }

    public JSONArray findByCondition(String condition) {
        String query = "SELECT * FROM item WHERE " + condition;
        try {
            return executeQuery(query);
        } catch (SQLException e) {
            System.err.println("! SQL ERROR (executeQuery): " + e.getMessage());
            return null;
        }
    }

   
    public boolean borrowItem(int uuid, int iid, int count) throws SQLException {
        try {
            holdConnection();
            cstmt = conn.prepareCall("{call add_borrow(?,?,?)}");
            cstmt.setInt(1, uuid);
            cstmt.setInt(2, iid);
            cstmt.setInt(3, count);
            Boolean flag = cstmt.execute();
            cstmt.close();
            releaseConnection();
        } catch (SQLException e) {
            System.out.println("! SQL ERROR (Borrow item procedure) :" + e.getMessage());
            cstmt.close();
            releaseConnection();
            return false;
        }
        return true;
    }

    public JSONArray getItemsOfCategory(String cname) throws SQLException {
        JSONArray items = new JSONArray();
        String query = "SELECT i.iid, d.dname, c.cname, i.iname, i.remain_count " + "FROM DEPARTMENT d, CATEGORY c, ITEM i "
                + "WHERE c.cid = i.category_cid and i.did = d.did and c.cname='" + cname + "'";
        try {
            holdConnection();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                JSONObject eachItem = new JSONObject();
                eachItem.put("iid", rs.getInt("iid"));
                eachItem.put("cname", rs.getString("cname"));
                eachItem.put("dname", rs.getString("dname"));
                eachItem.put("iname", rs.getString("iname"));
                eachItem.put("count", rs.getInt("remain_count"));
                items.add(eachItem);
            }

        } catch (SQLException e) {
            System.err.println("! SQL ERROR (getItemsOfCategory) : " + e.getMessage());
        } finally {
            releaseConnection();
        }
        return items;
    }

    public JSONArray getAllItems() throws SQLException {
        JSONArray items = new JSONArray();
        String query = "SELECT d.dname, i.iid, i.iname, i.remain_count, c.cname " + "FROM DEPARTMENT d, ITEM i, CATEGORY c "
                + "WHERE i.did = d.did and i.category_cid = c.cid";
        try {
            holdConnection();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                JSONObject eachItem = new JSONObject();
                eachItem.put("iid", rs.getString("iid"));
                eachItem.put("dname", rs.getString("dname"));
                eachItem.put("iname", rs.getString("iname"));
                eachItem.put("cname", rs.getString("cname"));
                eachItem.put("count", rs.getInt("remain_count"));
                items.add(eachItem);
            }
        } catch (SQLException e) {
            System.err.println("! SQL ERROR (getItemsOfCategory) : " + e.getMessage());
        } finally {
            releaseConnection();
        }
        return items;
    }

    public JSONArray getItemsOfDept(String dname) throws SQLException {
        JSONArray items = new JSONArray();
        String query = "SELECT i.iid, c.cname, d.dname, i.iname, i.remain_count " + "FROM DEPARTMENT d, CATEGORY c, ITEM i "
                + "where c.cid = i.category_cid and i.did = d.did and d.dname = '" + dname + "' ";
        try {
            holdConnection();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                JSONObject eachItem = new JSONObject();
                eachItem.put("iid", rs.getInt("iid"));
                eachItem.put("cname", rs.getString("cname"));
                eachItem.put("dname", rs.getString("dname"));
                eachItem.put("iname", rs.getString("iname"));
                eachItem.put("count", rs.getInt("remain_count"));
                items.add(eachItem);
            }
        } catch (SQLException e) {
            System.err.println("! SQL ERROR (getItemsOfCategory) : " + e.getMessage());
        } finally {
            releaseConnection();
        }
        return items;
    }

    public JSONArray getOnesItemById(int uuid, int iid) throws SQLException {
        JSONArray items = new JSONArray();
        String query = "SELECT i.iid, i.iname, d.dname ,b.count, b.start_date, b.end_date "
                + "FROM borrow b, item i, department d " + "WHERE b.borrow_uuid = " + uuid
                + " and b.borrow_iid = " + iid + " and d.did = i.did";
        try {
            holdConnection();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                JSONObject eachItem = new JSONObject();
                eachItem.put("iid", rs.getInt("iid"));
                eachItem.put("iname", rs.getString("iname"));
                eachItem.put("dname", rs.getString("dname"));
                eachItem.put("count", rs.getInt("count"));
                eachItem.put("start_date", rs.getString("start_date"));
                eachItem.put("end_date", rs.getString("end_date"));
                items.add(eachItem);
            }
        } catch (SQLException e) {
            System.err.println("! SQL ERROR (getOnesItem fail) :" + e.getMessage());
        } finally {
            releaseConnection();
        }
        return items;
    }

    public boolean returnItem(int uuid, int iid) throws SQLException {
        try {
            holdConnection();
            String sql = "DELETE FROM borrow " +
                    "WHERE borrow_iid=" + iid +" " +
                    "and borrow_uuid=" + uuid;
            executeSQL(sql);
            return true;
        } catch (SQLException e) {
            System.err.println("! SQL ERROR (return item) : " + e.getMessage());
            return false;
        }
    }

    public boolean updateItem(int iid, int newCount) throws SQLException {
        try {
            holdConnection();
            cstmt = conn.prepareCall("{call update_item(?,?)}");
            cstmt.setInt(1, iid);
            cstmt.setInt(2, newCount);
            Boolean flag = cstmt.execute();
            cstmt.close();
            releaseConnection();
            return true;
        } catch (SQLException e) {
            System.out.println("! SQL ERROR (Add item procedure) :" + e.getMessage());
            cstmt.close();
            releaseConnection();
            return false;
        }
    }

}


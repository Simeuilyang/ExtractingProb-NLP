package com.project.extractingNLP.Model.Dao;

import com.project.extractingNLP.Model.Dto.AccountDto;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.sql.*;

public class AccountDao extends Dao {
    private static AccountDao instance = null;

    public AccountDao() {
        super();
        System.out.println("AccountDao's JDBC driver is found");
    }

    public static AccountDao getInstance() {
        if (instance == null) {
            instance = new AccountDao();
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
	private JSONArray executeQuery(String query) throws SQLException {
        JSONArray jsonResults = new JSONArray();
        try {
        	System.out.println("call executeQuery");
            holdConnection();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                AccountDto account = new AccountDto(rs.getString("id"), rs.getString("name"), rs.getString("pwd"));
                System.out.println(account.toJSONObject());
                jsonResults.add(account.toJSONObject());
            }
        } catch (SQLException e) {
            System.out.println("! SQL ERROR (" + query + ") : " + e.getMessage());
        } finally {
            releaseConnection();
        }
        return jsonResults;
    }


    public JSONArray getAccountInfoById(String id) throws SQLException {
        String query = "SELECT a.id, a.name, a.pwd " +
                "FROM account a " +
                "WHERE a.id= '"+ id + "'";
        
        System.out.println(query);
        return executeQuery(query);
    }
    
    public int addAccount(String id, String pwd, String name) throws SQLException {
        String sql = "INSERT INTO account(id, pwd, name) "
                + "VALUES('"
                + id + "','"
                + pwd + "','"
                + name + "')";
                
        return executeSQL(sql);
    }

    public int updateAccount(String condition, String field, String value) throws SQLException {
        String sql = "UPDATE account SET " + field + "=" + value + " WHERE " + condition;
        return executeSQL(sql);
    }


    public int deleteAccount(String condition) throws SQLException {
        String sql = "DELETE FROM account WHERE " + condition;
        return executeSQL(sql);
    }
}

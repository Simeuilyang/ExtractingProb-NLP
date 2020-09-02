package com.project.extractingNLP.model.dao;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.project.extractingNLP.model.DBconfig;

public class Dao {
    protected Connection conn;
    protected Statement stmt;
    protected CallableStatement cstmt;
    protected ResultSet rs;
    protected PreparedStatement pstmt;
    
    public Dao() {
        /* DB configuration */
        if (conn == null) {
            try {
                /* Search JDBC driver*/
                Class.forName("oracle.jdbc.driver.OracleDriver");
                /* Connection to DB server */
            } catch (ClassNotFoundException e) {
                System.err.println("Driver Search error!!! : " + e.getMessage());
                System.exit(1);
            }
        }
    }

    protected void holdConnection() throws SQLException {
        this.conn = DriverManager.getConnection(DBconfig.getHostIp(), DBconfig.getUser(), DBconfig.getPw());
        this.stmt = conn.createStatement();
        this.conn.setAutoCommit(false);
    }

    protected void releaseConnection() throws SQLException {
        if (this.rs != null) {
            this.rs.close();
        }
        if (this.stmt != null) {
            this.stmt.close();
        }
        if (this.conn != null) {
        	this.conn.commit();
        	this.conn.setAutoCommit(true);
            this.conn.close();
        }
    }

    protected int executeSQL(String sql) throws SQLException {
        int result = 0;
        try {
            holdConnection();
            System.out.println(sql);//test
            result = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println("! SQL ERROR (" + sql + ") : " + e.getMessage());
        } finally {
            releaseConnection();
        }
        return result;
    }
//
//    protected void executeProcedure(String proc_name)  {
//        try {
//            holdConnection();
//            CallableStatement csmt = conn.prepareCall("{call functioname}")
//            releaseConnection();
//        } catch (SQLException e) {
//            System.err.println("! SQL ERROR (" + proc_name + "): " + e.getMessage());
//        }
//    }
}

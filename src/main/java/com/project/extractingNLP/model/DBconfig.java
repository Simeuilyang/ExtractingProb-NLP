package com.project.extractingNLP.model;

public class DBconfig {
    private final static String hostIp = "jdbc:oracle:thin:@localhost:1600:xe";
    private final static String user = "nlp";
    private final static String pw = "nlp";

    public static String getHostIp() {
        return hostIp;
    }

    public static String getUser() {
        return user;
    }

    public static String getPw() {
        return pw;
    }
}

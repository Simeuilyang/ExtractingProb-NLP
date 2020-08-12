package com.project.extractingNLP.Model;

public class DBconfig {
    private final static String hostIp = "jdbc:oracle:thin:@127.0.0.1:1521:oraknu";
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

package com.heretoo.util;

import java.sql.*;

public class DBManager {

    private static DBManager instance = null;
    private String url;
    private String user;
    private String pw;
    private Connection conn = null;

    private DBManager(String url, String user, String pw) {
        this.url = url;
        this.user = user;
        this.pw = pw;
    }

    public static synchronized DBManager getInstance(String url, String user, String pw) {
        if (instance == null) {
            instance = new DBManager(url, user, pw);
        }
        return instance;
    }

    public void makeConnection() {
        conn = null;

        try {
            // JDBC 드라이버를 로드
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 데이터베이스에 연결
            conn = DriverManager.getConnection(url, user, pw);

            if (conn != null) {
                System.out.println("데이터베이스에 성공적으로 연결되었습니다!");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery(String query) {
        try {
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            return statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("데이터베이스 연결이 닫혔습니다.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

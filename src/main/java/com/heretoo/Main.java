package com.heretoo;

import com.heretoo.util.DBManager;
import com.heretoo.view.MainFrame;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {

    public static void main(String[] args){
        String database;
        String userName;
        String password;

        String apiUrl;
        String apiKeyEnc;
        String apiKeyDec;

        Properties prop = new Properties();
        try (InputStream resource = Main.class.getClassLoader().getResourceAsStream("database.properties")) {
            prop.load(resource);
            database = prop.getProperty("db");
            userName = prop.getProperty("user");
            password = prop.getProperty("password");

            apiUrl = prop.getProperty("openApiUrl");
            apiKeyEnc = prop.getProperty("openApiKeyEnc");
            apiKeyDec = prop.getProperty("openApiKeyDec");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        DBManager.getInstance(database, userName, password).makeConnection();

        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
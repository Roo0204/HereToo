package com.heretoo.view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private int width;
    private int height;

    public MainFrame() throws HeadlessException {
        width = 1000;
        height = 800;
        setTitle("여기에도");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 닫기 버튼 동작 설정
        setLocationRelativeTo(null); // 화면 가운데에 프레임 표시
        setLayout(new BorderLayout());
        add(new MapPanel());
        initMenu();
    }

    private void initMenu() {
        // 메뉴바
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(new JMenuItem("Open"));
        fileMenu.add(new JMenuItem("Save"));
        fileMenu.add(new JMenuItem("Exit"));

        JMenu editMenu = new JMenu("Edit");
        editMenu.add(new JMenuItem("Cut"));
        editMenu.add(new JMenuItem("Copy"));
        editMenu.add(new JMenuItem("Paste"));

        // 메뉴바에 메뉴 추가
        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        // 메뉴바를 Frame의 NORTH 위치에 추가
        add(menuBar, BorderLayout.NORTH);
    }
}

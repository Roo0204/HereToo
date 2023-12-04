package com.heretoo.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Stack;

public class MainFrame extends JFrame {

    private int width;
    private int height;
    private Stack<JPanel> panelStack;

    public MainFrame() throws HeadlessException {
        width = 1000;
        height = 800;
        setTitle("여기에도");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 닫기 버튼 동작 설정
        setLocationRelativeTo(null); // 화면 가운데에 프레임 표시
        setLayout(new BorderLayout());

        panelStack = new Stack<>();

        MapPanel mapPanel = new MapPanel();
        panelStack.add(mapPanel);
        add(new SearchPanel(this), BorderLayout.WEST);
        add(mapPanel, BorderLayout.EAST);
        initMenu();

        // focus 설정
        this.setFocusable(true);
        this.requestFocus();
    }

    private void initMenu() {
        // 메뉴바
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem mp = new JMenuItem("MyPage");
        mp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyPagePanel myPagePanel = new MyPagePanel();
                changePanel(myPagePanel);
            }
        });
        fileMenu.add(mp);

        JMenuItem rc = new JMenuItem("Recommendation");
        rc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RecommendationPanel recommendationPanel = new RecommendationPanel();
                changePanel(recommendationPanel);
            }
        });
        fileMenu.add(rc);

        JMenuItem rv = new JMenuItem("Review");

        rv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReviewPanel reviewPanel = new ReviewPanel();
                changePanel(reviewPanel);
            }
        });
        fileMenu.add(rv);

        JMenuItem wt = new JMenuItem("Weather");
        wt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WeatherPanel weatherPanel = new WeatherPanel();
                changePanel(weatherPanel);
            }
        });
        fileMenu.add(wt);

        JMenuItem lc = new JMenuItem("Location");
        lc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocationPanel2 locationPanel2 = new LocationPanel2();
                changePanel(locationPanel2);
            }
        });
        fileMenu.add(lc);
        

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

    public void updateMap(List<List<String>> pointList) {
        JPanel jp;
        while ((jp = panelStack.pop()).getClass() != MapPanel.class) {
            jp.setVisible(false);
        }
        jp.setVisible(true);
        panelStack.add(jp);
        ((MapPanel)jp).updateMap(pointList);
    }

    /*public void changePanel(JPanel newPanel) {
        panelStack.peek().setVisible(false);
        newPanel.setVisible(true);
        panelStack.add(newPanel);
        add(newPanel, BorderLayout.EAST);
    }*/

    public void changePanel(JPanel newPanel) {
        JPanel currentPanel = panelStack.isEmpty() ? null : panelStack.peek();
        if (currentPanel != null) {
            getContentPane().remove(currentPanel); // 현재 패널을 제거
            panelStack.pop();
        }
        newPanel.setVisible(true);
        getContentPane().add(newPanel, BorderLayout.CENTER); // CENTER에 추가
        panelStack.push(newPanel);
        validate(); // 레이아웃을 다시 계산합니다.
        repaint(); // UI를 다시 그립니다.
    }



}

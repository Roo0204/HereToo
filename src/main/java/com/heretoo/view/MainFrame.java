package com.heretoo.view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() throws HeadlessException {
        setTitle("여기에도");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 닫기 버튼 동작 설정
        setLocationRelativeTo(null); // 화면 가운데에 프레임 표시
        add(new MapPanel());
    }
}

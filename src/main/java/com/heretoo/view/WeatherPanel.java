package com.heretoo.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class WeatherPanel extends JPanel {
    public final String PANEL_TITLE = "지역 날씨";

    public WeatherPanel() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(680, 700));
        JPanel panel = new JPanel();
        panel.add(this.titlePanel("지역 날씨"));
        panel.add(this.whitespacePanel(630, 10));
        panel.add(this.panelsToday());
        panel.add(this.whitespacePanel(630, 10));
        panel.add(this.panelsTime());
        panel.add(this.whitespacePanel(630, 10));
        panel.add(this.panelsDay());
        this.add(panel);
    }

    private JPanel whitespacePanel(int width, int height) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(width, height));
        return panel;
    }

    private JPanel titlePanel(String title) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(630, 50));
        panel.setLayout(new BoxLayout(panel, 1));
        this.addASizedLabel(title, 36.0F, panel);
        return panel;
    }

    private JPanel panelsToday() {
        JPanel panely = new JPanel();
        panely.setBorder(new LineBorder(Color.lightGray, 1));
        panely.setBackground(Color.lightGray);
        panely.setPreferredSize(new Dimension(630, 220));
        panely.setLayout(new BoxLayout(panely, 1));
        panely.add(this.currentHeaderPanel("경상남도 통영시 정량동", "placeholder.png"));
        panely.add(this.panelsTodayX());
        panely.add(this.currentFooterPanel("현재 남해안에 폭우주의보로 인해 비가 많이 옵니다, 각별히 주의하세요"));
        return panely;
    }

    private JPanel panelsTodayX() {
        JPanel panelx = new JPanel();
        panelx.setOpaque(false);
        panelx.setPreferredSize(new Dimension(630, 140));
        panelx.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        panelx.setLayout(new BoxLayout(panelx, 0));
        panelx.add(this.currentPanel("sun.png", "20℃", "맑음"));
        panelx.add(this.whitePanel("미세먼지", "fine_dust.png", "낮음"));
        panelx.add(Box.createHorizontalStrut(10));
        panelx.add(this.whitePanel("자외선", "uv-index.png", "낮음"));
        panelx.add(Box.createHorizontalStrut(10));
        panelx.add(this.whitePanel("바람", "wind.png", "5m/s"));
        panelx.add(Box.createHorizontalStrut(10));
        panelx.add(this.whitePanel("습도", "humidity.png", "50%"));
        panelx.add(Box.createHorizontalStrut(10));
        panelx.add(this.whitePanel("일출", "sunrise_arrow.png", "06:00"));
        panelx.add(Box.createHorizontalStrut(10));
        panelx.add(this.whitePanel("일몰", "sunset_arrow.png", "18:00"));
        return panelx;
    }

    private JPanel currentPanel(String img, String temp, String status) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(90, 140));
        panel.setLayout(new BoxLayout(panel, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        this.addAnImage(img, panel);
        this.addASizedLabel(temp, 24.0F, panel);
        this.addALabel(status, panel);
        return panel;
    }

    private JPanel whitePanel(String type, String img, String status) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        panel.setBorder(new EmptyBorder(0, 7, 0, 7));
        panel.setLayout(new BoxLayout(panel, 1));
        this.addALabel(type, panel);
        this.addAnImage(img, panel);
        this.addALabel(status, panel);
        return panel;
    }

    private JPanel currentHeaderPanel(String text, String img) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(630, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        panel.setLayout(new FlowLayout(0));
        this.addASizedImage(img, 20, 20, panel);
        this.addASizedLabel(text, 16.0F, panel);
        return panel;
    }

    private JPanel currentFooterPanel(String text) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(630, 40));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        panel.setLayout(new BoxLayout(panel, 1));
        this.addALabel(text, panel);
        return panel;
    }

    private JPanel panelsTime() {
        JPanel panels = new JPanel();
        panels.setBorder(new LineBorder(Color.lightGray, 1));
        panels.setBackground(Color.lightGray);
        panels.setPreferredSize(new Dimension(630, 160));
        panels.setLayout(new BoxLayout(panels, 0));
        panels.add(this.onePanel("시간", "온도", "슾도", ""));
        panels.add(this.onePanel("10am", "10℃", "50%", "sun.png"));
        panels.add(this.onePanel("11am", "20℃", "60%", "cloud.png"));
        panels.add(this.onePanel("12pm", "30℃", "70%", "rain.png"));
        panels.add(this.onePanel("1pm", "40℃", "80%", "cloudy.png"));
        panels.add(this.onePanel("2pm", "50℃", "90%", "storm.png"));
        panels.add(this.onePanel("3pm", "60℃", "100%", "wind.png"));
        panels.add(this.onePanel("4pm", "70℃", "110%", "sunset.png"));
        return panels;
    }

    private JPanel panelsDay() {
        JPanel panels = new JPanel();
        panels.setBorder(new LineBorder(Color.lightGray, 1));
        panels.setBackground(Color.lightGray);
        panels.setPreferredSize(new Dimension(630, 160));
        panels.setLayout(new BoxLayout(panels, 0));
        panels.add(this.onePanel("요일", "온도", "슾도", ""));
        panels.add(this.onePanel("월요일", "10/16℃", "50%", "sun.png"));
        panels.add(this.onePanel("화요일", "20/26℃", "60%", "cloud.png"));
        panels.add(this.onePanel("수요일", "30/36℃", "70%", "rain.png"));
        panels.add(this.onePanel("목요일", "40/46℃", "80%", "cloudy.png"));
        panels.add(this.onePanel("금요일", "50/56℃", "90%", "storm.png"));
        panels.add(this.onePanel("토요일", "60/66℃", "100%", "wind.png"));
        panels.add(this.onePanel("일요일", "70/76℃", "110%", "sunset.png"));
        return panels;
    }

    private JPanel onePanel(String time, String temp, String humid, String img) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(80, 160));
        panel.setLayout(new BoxLayout(panel, 1));
        this.addALabel(time, panel);
        this.addAnImage(img, panel);
        this.addALabel(temp, panel);
        this.addALabel(humid, panel);
        return panel;
    }

    private void addASizedLabel(String text, float size, JPanel panel) {
        JLabel label = new JLabel(text);
        label.setFont(label.getFont().deriveFont(size));
        label.setAlignmentX(0.5F);
        panel.add(label);
    }

    private void addALabel(String text, JPanel panel) {
        JLabel label = new JLabel(text);
        label.setFont(label.getFont().deriveFont(16.0F));
        label.setAlignmentX(0.5F);
        label.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        panel.add(label);
    }

    private void addASizedImage(String imgName, int w, int h, JPanel panel) {
        JLabel image = new JLabel();
        Image mapImage = null;

        try {
            URL imgURL = this.getClass().getResource("/icon/" + imgName);
            if (imgURL != null) {
                BufferedImage img = ImageIO.read(imgURL);
                mapImage = img.getScaledInstance(w, h, 4);
            } else {
                System.err.println("Couldn't find file.");
            }
        } catch (IOException var9) {
            var9.printStackTrace();
        }

        assert mapImage != null;

        image.setIcon(new ImageIcon(mapImage));
        image.setAlignmentX(0.5F);
        panel.add(image);
    }

    private void addAnImage(String imgName, JPanel panel) {
        if (imgName.isEmpty()) {
            JPanel whitespace = new JPanel();
            whitespace.setOpaque(false);
            whitespace.setSize(new Dimension(60, 60));
            panel.add(whitespace);
        } else {
            JLabel image = new JLabel();
            Image mapImage = null;

            try {
                URL imgURL = this.getClass().getResource("/icon/" + imgName);
                if (imgURL != null) {
                    BufferedImage img = ImageIO.read(imgURL);
                    mapImage = img.getScaledInstance(60, 60, 4);
                } else {
                    System.err.println("Couldn't find file.");
                }
            } catch (IOException var7) {
                var7.printStackTrace();
            }

            assert mapImage != null;

            image.setIcon(new ImageIcon(mapImage));
            image.setAlignmentX(0.5F);
            panel.add(image);
        }

    }
}

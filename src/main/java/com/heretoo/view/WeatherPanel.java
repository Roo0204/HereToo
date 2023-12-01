package com.heretoo.view;

import com.heretoo.util.IconManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

public class WeatherPanel extends JPanel {
    public String PANEL_TITLE = "지역 날씨";

    // image list for weather
    private final HashMap<String, String> weatherImageList = new HashMap<>() {{
        put("SUN", "sun");
        put("CLOUD", "cloud");
        put("RAIN", "rain");
        put("CLOUDY", "cloudy");
        put("STORM", "storm");
//        put("SNOW", "snow");
        put("WIND", "wind");
        put("SUNSET", "sunset");
        put("SUNRISE", "sunrise");
        put("SUNSET_ARROW", "sunset_arrow");
        put("SUNRISE_ARROW", "sunrise_arrow");
        put("FINE_DUST", "fine_dust");
        put("HUMIDITY", "humidity");
        put("UV", "uv-index");
    }};

    public WeatherPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(680, 700));

        JPanel panel = new JPanel();
        // create margin bottom for each panel
        panel.add(titlePanel(PANEL_TITLE));
        panel.add(whitespacePanel(90*7, 10));
        panel.add(panelsToday());
        panel.add(whitespacePanel(90*7, 10));
        panel.add(panelsTime());
        panel.add(whitespacePanel(90*7, 10));
        panel.add(panelsDay());
        add(panel);
    }

    private JPanel whitespacePanel(int width, int height) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(width, height));
        return panel;
    }

    private JPanel titlePanel(String title) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(90*7, 50));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        addASizedLabel(title, 36.0f, panel);
        return panel;
    }

    private JPanel panelsToday() {
        JPanel panely = new JPanel();
        panely.setBorder(new LineBorder(Color.lightGray, 1));
        panely.setBackground(Color.lightGray);
        panely.setPreferredSize(new Dimension(90*7, 220));
        panely.setLayout(new BoxLayout(panely, BoxLayout.Y_AXIS));
        panely.add(currentHeaderPanel("경상남도 통영시 정량동", IconManager.PLACEHOLDER));
        panely.add(panelsTodayX());
        panely.add(currentFooterPanel("현재 남해안에 폭우주의보로 인해 비가 많이 옵니다, 각별히 주의하세요"));
        return panely;
    }

    private JPanel panelsTodayX() {
        JPanel panelx = new JPanel();
        panelx.setOpaque(false);
        panelx.setPreferredSize(new Dimension(90*7, 140));
        panelx.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        panelx.setLayout(new BoxLayout(panelx, BoxLayout.X_AXIS));
        panelx.add(currentPanel(IconManager.SUN, "20℃", "맑음"));
        panelx.add(whitePanel("미세먼지", IconManager.FINE_DUST, "낮음"));
        panelx.add(Box.createHorizontalStrut(10));
        panelx.add(whitePanel("자외선", IconManager.UV, "낮음"));
        panelx.add(Box.createHorizontalStrut(10));
        panelx.add(whitePanel("바람", IconManager.WIND, "5m/s"));
        panelx.add(Box.createHorizontalStrut(10));
        panelx.add(whitePanel("습도", IconManager.HUMIDITY, "50%"));
        panelx.add(Box.createHorizontalStrut(10));
        panelx.add(whitePanel("일출", IconManager.SUNRISE_ARROW, "06:00"));
        panelx.add(Box.createHorizontalStrut(10));
        panelx.add(whitePanel("일몰", IconManager.SUNSET_ARROW, "18:00"));
        return panelx;
    }

    private JPanel currentPanel(String img, String temp, String status) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(90, 140));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10,15,10,15));
        addAnImage(img, panel);
        addASizedLabel(temp, 24.0f, panel);
        addALabel(status, panel);
        return panel;
    }

    private JPanel whitePanel(String type, String img, String status) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        panel.setBorder(new EmptyBorder(0,7,0,7));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        addALabel(type, panel);
        addAnImage(img, panel);
        addALabel(status, panel);
        return panel;
    }

    private JPanel currentHeaderPanel(String text, String img) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(90*7, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        addASizedImage(img, 20,20, panel);
        addASizedLabel(text, 16.0f, panel);
        return panel;
    }

    private JPanel currentFooterPanel(String text) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(90*7, 40));
        panel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        addALabel(text, panel);
        return panel;
    }

    private JPanel panelsTime() {
        JPanel panels = new JPanel();
        panels.setBorder(new LineBorder(Color.lightGray, 1));
        panels.setBackground(Color.lightGray);
        panels.setPreferredSize(new Dimension(90*7, 160));
        panels.setLayout(new BoxLayout(panels, BoxLayout.X_AXIS));
        panels.add(onePanel("시간", "온도", "슾도", ""));
        panels.add(onePanel("10am", "10℃", "50%", IconManager.SUN));
        panels.add(onePanel("11am", "20℃", "60%", IconManager.CLOUD));
        panels.add(onePanel("12pm", "30℃", "70%", IconManager.RAIN));
        panels.add(onePanel("1pm", "40℃", "80%", IconManager.CLOUDY));
        panels.add(onePanel("2pm", "50℃", "90%", IconManager.STORM));
        panels.add(onePanel("3pm", "60℃", "100%", IconManager.WIND));
        panels.add(onePanel("4pm", "70℃", "110%", IconManager.SUNSET));
        return panels;
    }

    private JPanel panelsDay() {
        JPanel panels = new JPanel();
        panels.setBorder(new LineBorder(Color.lightGray, 1));
        panels.setBackground(Color.lightGray);
        panels.setPreferredSize(new Dimension(90*7, 160));
        panels.setLayout(new BoxLayout(panels, BoxLayout.X_AXIS));
        panels.add(onePanel("요일", "온도", "슾도", ""));
        panels.add(onePanel("월요일", "10/16℃", "50%", IconManager.SUN));
        panels.add(onePanel("화요일", "20/26℃", "60%", IconManager.CLOUD));
        panels.add(onePanel("수요일", "30/36℃", "70%", IconManager.RAIN));
        panels.add(onePanel("목요일", "40/46℃", "80%", IconManager.CLOUDY));
        panels.add(onePanel("금요일", "50/56℃", "90%", IconManager.STORM));
        panels.add(onePanel("토요일", "60/66℃", "100%", IconManager.WIND));
        panels.add(onePanel("일요일", "70/76℃", "110%", IconManager.SUNSET));
        return panels;
    }

    private JPanel onePanel(String time, String temp, String humid, String img) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(80, 160));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        addALabel(time, panel);
        addAnImage(img, panel);
        addALabel(temp, panel);
        addALabel(humid, panel);
        return panel;
    }

    private void addASizedLabel(String text, float size, JPanel panel) {
        JLabel label = new JLabel(text);
        label.setFont(label.getFont().deriveFont(size));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);
    }

    private void addALabel(String text, JPanel panel) {
        JLabel label = new JLabel(text);
        label.setFont(label.getFont().deriveFont(16.0f));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(5,0,5,0));
        panel.add(label);
    }

    // add an image into panel
    private void addASizedImage(String imgName, int w, int h, JPanel panel) {
        JLabel image = new JLabel();
        Image mapImage = null;
        try {
            URL imgURL = getClass().getResource(IconManager.ICON_PATH + imgName);
            if (imgURL != null) {
                BufferedImage img = ImageIO.read(imgURL);
                mapImage = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
            } else {
                System.err.println("Couldn't find file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert mapImage != null;
        image.setIcon(new ImageIcon(mapImage));
        image.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(image);
    }

    // add an image into panel
    private void addAnImage(String imgName, JPanel panel) {
        if (imgName.isEmpty()){
            // add a whitespace panel to keep the layout
            JPanel whitespace = new JPanel();
            whitespace.setOpaque(false);
            whitespace.setSize(new Dimension(60, 60));
            panel.add(whitespace);
        } else {
            JLabel image = new JLabel();
            Image mapImage = null;
            try {
                URL imgURL = getClass().getResource(IconManager.ICON_PATH + imgName);
                if (imgURL != null) {
                    BufferedImage img = ImageIO.read(imgURL);
                    mapImage = img.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                } else {
                    System.err.println("Couldn't find file.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            assert mapImage != null;
            image.setIcon(new ImageIcon(mapImage));
            image.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(image);
        }
    }
}

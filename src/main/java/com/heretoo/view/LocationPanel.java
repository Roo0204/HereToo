package com.heretoo.view;

import com.heretoo.util.IconManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class LocationPanel extends JPanel {
    public final String IMG_PATH = "/img/";
    public final String PANEL_TITLE = "지역 정보";

    public LocationPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(680, 700));

        JPanel panel = new JPanel();
        panel.add(titlePanel(PANEL_TITLE));
        panel.add(whitespacePanel(90*7, 10));
        panel.add(titleOnPanel("지역 뉴스 / 축제", newsPanel()));
        panel.add(whitespacePanel(90*7, 10));
        panel.add(titleOnPanel("지역 추천 코스", recommendationPanel()));
        panel.add(whitespacePanel(90*7, 10));
        panel.add(titleOnPanel("지역 추천 코스", panelsTime()));
        add(panel);
    }

    private JPanel titlePanel(String title) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(90*7, 50));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        addASizedLabel(title, 36.0f, panel);
        addASizedImage("slogan.png", 600, 60, panel);
        return panel;
    }

    private JPanel titleOnPanel(String title, JPanel targetPanel) {
        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(Color.lightGray, 1));
        panel.setBackground(Color.lightGray);
        panel.setPreferredSize(new Dimension(90*7, 200));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        addASizedLabel(title, 16.0f, panel);
        panel.add(targetPanel);
        return panel;
    }

    private JPanel newsPanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(90*7, 160));
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        addASizedIcon(IconManager.LEFT_ARROW, 20, 20, panel);
        addASizedImage("festival_1.png", 100, 160, panel);
        addASizedImage("festival_2.png", 200, 160, panel);
        addASizedImage("festival_3.png", 200, 160, panel);
        addASizedIcon(IconManager.RIGHT_ARROW, 20, 20, panel);
        return panel;
    }

    private JPanel recommendationPanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(90*7, 160));
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        addASizedIcon(IconManager.LEFT_ARROW, 20, 20, panel);
        addASizedImage("course_1.png", 140, 160, panel);
        addASizedImage("course_2.png", 400, 160, panel);
        addASizedIcon(IconManager.RIGHT_ARROW, 20, 20, panel);
        return panel;
    }

    private JPanel panelsTime() {
        JPanel panels = new JPanel();
        panels.setOpaque(false);
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

    private JPanel onePanel(String time, String temp, String humid, String img) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(80, 160));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        addALabel(time, panel);
        addAnIcon(img, panel);
        addALabel(temp, panel);
        addALabel(humid, panel);
        return panel;
    }

    private JPanel whitespacePanel(int width, int height) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(width, height));
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

    private void addASizedImage(String imgName, int w, int h, JPanel panel) {
        JLabel image = new JLabel();
        Image mapImage = null;
        try {
            URL imgURL = getClass().getResource(IMG_PATH + imgName);
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

    private void addASizedIcon(String imgName, int w, int h, JPanel panel) {
        if (imgName.isEmpty()){
            JPanel whitespace = new JPanel();
            whitespace.setOpaque(false);
            whitespace.setSize(new Dimension(w, h));
            panel.add(whitespace);
        } else {
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
    }

    private void addAnIcon(String imgName, JPanel panel) {
        if (imgName.isEmpty()){
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

    private void addAnImage(String imgName, JPanel panel) {
        if (imgName.isEmpty()){
            JPanel whitespace = new JPanel();
            whitespace.setOpaque(false);
            whitespace.setSize(new Dimension(60, 60));
            panel.add(whitespace);
        } else {
            JLabel image = new JLabel();
            Image mapImage = null;
            try {
                URL imgURL = getClass().getResource(IMG_PATH + imgName);
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

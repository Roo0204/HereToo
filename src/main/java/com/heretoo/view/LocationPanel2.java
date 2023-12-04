package com.heretoo.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

public class LocationPanel2 extends JPanel {
    public final String IMG_PATH = "/img/";
    public final String ICON_PATH = "/icon/";
    public final String PANEL_TITLE = "지역 정보";

    private int randomNum() {
        Random random = new Random();
        return random.nextInt(439);
    }

    public LocationPanel2() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(680, 700));
        JPanel panel = new JPanel();
        panel.add(this.titlePanel(PANEL_TITLE));
        panel.add(this.whitespacePanel(630, 10));
        panel.add(this.titleOnPanel("지역 뉴스 / 축제", this.newsPanel()));
        panel.add(this.whitespacePanel(630, 10));
        panel.add(this.titleOnPanel("지역 추천 코스", this.recommendationPanel()));
        panel.add(this.whitespacePanel(630, 10));
        panel.add(this.titleOnPanel("지역 날씨", this.panelsTime()));
        this.add(panel);
    }

    private JPanel titlePanel(String title) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(630, 50));
        panel.setLayout(new BoxLayout(panel, 1));
        this.addASizedLabel(title, 36.0F, panel);
        this.addASizedImage("slogan.png", 600, 60, panel);
        return panel;
    }

    private JPanel titleOnPanel(String title, JPanel targetPanel) {
        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(Color.lightGray, 1));
        panel.setBackground(Color.lightGray);
        panel.setPreferredSize(new Dimension(630, 200));
        panel.setLayout(new BoxLayout(panel, 1));
        this.addASizedLabel(title, 16.0F, panel);
        panel.add(targetPanel);
        return panel;
    }

    private JPanel newsPanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(630, 160));
        panel.setLayout(new BoxLayout(panel, 0));
        this.addASizedIcon("left_arrow.png", 20, 20, panel);
        this.addASizedImage("festival_1.png", 100, 160, panel);
        this.addASizedImage("festival_2.png", 200, 160, panel);
        this.addASizedImage("festival_3.png", 200, 160, panel);
        this.addASizedIcon("right_arrow.png", 20, 20, panel);
        return panel;
    }

    private JPanel recommendationPanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(630, 160));
        panel.setLayout(new BoxLayout(panel, 0));
        this.addASizedIcon("left_arrow.png", 20, 20, panel);
        this.addASizedImage("course_1.png", 140, 160, panel);
        this.addASizedImage("course_2.png", 400, 160, panel);
        this.addASizedIcon("right_arrow.png", 20, 20, panel);
        return panel;
    }

    private JPanel panelsTime() {
        JPanel panels = new JPanel();
        panels.setOpaque(false);
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

    private JPanel onePanel(String time, String temp, String humid, String img) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(80, 160));
        panel.setLayout(new BoxLayout(panel, 1));
        this.addALabel(time, panel);
        this.addAnIcon(img, panel);
        this.addALabel(temp, panel);
        this.addALabel(humid, panel);
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
            URL imgURL = this.getClass().getResource(IMG_PATH + imgName);
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

    private void addASizedIcon(String imgName, int w, int h, JPanel panel) {
        if (imgName.isEmpty()) {
            JPanel whitespace = new JPanel();
            whitespace.setOpaque(false);
            whitespace.setSize(new Dimension(w, h));
            panel.add(whitespace);
        } else {
            JLabel image = new JLabel();
            Image mapImage = null;

            try {
                URL imgURL = this.getClass().getResource(ICON_PATH + imgName);
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

    }

    private void addAnIcon(String imgName, JPanel panel) {
        if (imgName.isEmpty()) {
            JPanel whitespace = new JPanel();
            whitespace.setOpaque(false);
            whitespace.setSize(new Dimension(60, 60));
            panel.add(whitespace);
        } else {
            JLabel image = new JLabel();
            Image mapImage = null;

            try {
                URL imgURL = this.getClass().getResource(ICON_PATH + imgName);
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
                URL imgURL = this.getClass().getResource(IMG_PATH + imgName);
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
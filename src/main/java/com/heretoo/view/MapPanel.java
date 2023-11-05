package com.heretoo.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.Properties;

public class MapPanel extends JPanel {

    private final String APIKey;
    private final JLabel mapLabel;

    public MapPanel() {
        Properties prop = new Properties();
        try (InputStream resource = getClass().getClassLoader().getResourceAsStream("database.properties")) {
            prop.load(resource);
            APIKey = prop.getProperty("apiKey");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        setLayout(new BorderLayout());

        JPanel mapPanel = new JPanel();
        mapPanel.setPreferredSize(new Dimension(700, 700)); // 지도가 표시될 오른쪽 패널 크기

        mapLabel = new JLabel(new ImageIcon());
        mapPanel.add(mapLabel);

        SearchPanel searchPanel = new SearchPanel(this);

        // Set up layout
        add(mapPanel, BorderLayout.EAST);
        add(searchPanel, BorderLayout.WEST);

        // focus 설정
        this.setFocusable(true);
        this.requestFocus();

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

    public void updateMap(List<List<String>> pointList) {

        if (pointList == null) {
            // 이미지 아이콘 추가
            Image mapImage = null;
            try {
                URL imgURL = getClass().getResource("/img/korea.png");
                if (imgURL != null) {
                    BufferedImage img = ImageIO.read(imgURL);
                    mapImage = img.getScaledInstance(700, 700, Image.SCALE_SMOOTH);
                } else {
                    System.err.println("Couldn't find file.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            assert mapImage != null;
            mapLabel.setIcon(new ImageIcon(mapImage));
            return;
        }

        StringBuilder points = new StringBuilder();
        for (List<String> p : pointList) {
            points.append("&markers=label:").append(p.get(0)).append("%7C")
                    .append(p.get(2)).append(",").append(p.get(1));
        }

        String path = "./src/main/resources/img/tmp";

        try {
            String imgURL = "https://maps.googleapis.com/maps/api/staticmap?" +
                    points +
                    "&size=640x640&scale=2" +
                    "&key=" + APIKey;
            System.out.println(imgURL);
            URL url = new URL(imgURL);
            InputStream is = url.openStream();
            OutputStream os = new FileOutputStream(path);
            byte[] b = new byte[2048];
            int length;
            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
            }
            is.close();
            os.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BufferedImage img;
        try {
            img = ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image mapImg = img.getScaledInstance(700, 700, Image.SCALE_SMOOTH);

        mapLabel.setIcon(new ImageIcon(mapImg));

        File f = new File(path);
        if (!f.delete()) {
            System.err.println("Couldn't delete file.");
        }
    }
}

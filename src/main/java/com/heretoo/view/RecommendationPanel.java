package com.heretoo.view;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.heretoo.Main;
import com.heretoo.util.XMLModel;
import com.heretoo.util.RecommendCourseModel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

class Destination2 {
    String name;
    String description;
    Image[] images;

    public Destination2(String name, String description, Image[] images) {
        this.name = name;
        this.description = description;
        this.images = images;
    }
}

class DestinationPanel extends JPanel {
    public DestinationPanel(Destination2 destination) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // 세로로 정렬
        setBorder(BorderFactory.createLineBorder(Color.BLACK)); // 테두리 추가

        JLabel nameLabel = new JLabel(destination.name);
        nameLabel.setFont(new Font("Serif", Font.BOLD, 20)); // 폰트 설정
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // 가운데 정렬

        JTextArea descriptionArea = new JTextArea(destination.description);
        descriptionArea.setEditable(false);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);

        // 이미지를 표시하기 위한 패널 또는 레이블 추가 (아직 이미지가 없으니 임시로 처리)
        JLabel imageLabel = new JLabel("이미지 자리");
        imageLabel.setPreferredSize(new Dimension(100, 100)); // 적당한 크기 설정
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(new JButton("숙박"));
        buttonPanel.add(new JButton("맛집"));
        buttonPanel.add(new JButton("투어 상품"));

        add(nameLabel);
        add(imageLabel);
        add(descriptionArea);
        add(buttonPanel);
    }
}
public class RecommendationPanel extends JPanel {
    private List<RecommendCourseModel> recommendItems = new ArrayList<>();

    private void FetchRecommend() {
        String apiUrl;
        String apiKeyDec;

        Properties prop = new Properties();
        try (InputStream resource = Main.class.getClassLoader().getResourceAsStream("database.properties")) {
            prop.load(resource);
            apiUrl = prop.getProperty("openApiUrl");
            apiKeyDec = prop.getProperty("openApiKeyDec");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int pageNo = 1;
        int numOfRows = 10;
        String CURRENT_DATE = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String HOUR = new SimpleDateFormat("HH").format(new Date());
        String COURSE_ID = "1";
        String fetchUrl = apiUrl + "?ServiceKey=" + apiKeyDec + "&pageNo=" + pageNo + "&numOfRows=" + numOfRows + "&CURRENT_DATE=" + CURRENT_DATE + "&HOUR=" + HOUR + "&COURSE_ID=" + COURSE_ID;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(fetchUrl))
                .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(this::mapXml)
                .join();

    }

    void mapXml(String xml) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            XMLModel recommendCourse = xmlMapper.readValue(xml, XMLModel.class);
            List<RecommendCourseModel> recommendCourseModel = recommendCourse.getRecommendCourseModelList();
            System.out.println(recommendCourseModel.toString());
            recommendItems = recommendCourseModel;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RecommendationPanel() {
        FetchRecommend();

        setLayout(new BorderLayout());

        String[] seasons = {"봄", "여름", "가을", "겨울"};
        String[] locations = {"서울", "부산", "제주", "경기", "강원", "전북", "전남", "경북", "경남", "충북", "충남"};
        String[] themes = {"자연", "문화", "역사", "레저", "음식"};

        JComboBox<String> seasonCombo = new JComboBox<>(seasons);
        JComboBox<String> locationCombo = new JComboBox<>(locations);
        JComboBox<String> themeCombo = new JComboBox<>(themes);

        JPanel comboPanel = new JPanel(new FlowLayout());
        comboPanel.add(new JLabel("시즌:"));
        comboPanel.add(seasonCombo);
        comboPanel.add(new JLabel("지역:"));
        comboPanel.add(locationCombo);
        comboPanel.add(new JLabel("테마:"));
        comboPanel.add(themeCombo);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2)); // 4행 2열의 GridLayout 설정

        for (RecommendCourseModel item : recommendItems) {
            Destination2 dest = new Destination2(item.courseName, item.spotName + " (" + item.tm + ")", new Image[]{});
            panel.add(new DestinationPanel(dest));
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(scrollPane.getWidth(), 300)); // 400은 4개의 DestinationPanel 높이와 일치해야 합니다

        JLabel titleLabel = new JLabel("여행지 추천");
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), titleLabel.getFont().getStyle(), 24)); // 폰트 크기를 24로 설정
        titleLabel.setHorizontalAlignment(JLabel.CENTER); // 중앙에 배치

        JButton backButton = new JButton("뒤로 가기");
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(backButton, BorderLayout.WEST);
        topPanel.add(titleLabel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
}

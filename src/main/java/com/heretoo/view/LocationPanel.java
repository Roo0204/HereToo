package com.heretoo.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class Destination {
    private String name;
    private String history;
    private String review;
    private String location;
    private String contact;
    private String hours;
    private String introduction;
    private ImageIcon image; // 이미지를 위한 필드

    public Destination(String name, String history, String review, String location, String contact, String hours, String introduction, ImageIcon image) {
        this.name = name;
        this.history = history;
        this.review = review;
        this.location = location;
        this.contact = contact;
        this.hours = hours;
        this.introduction = introduction;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getHistory() {
        return history;
    }

    public String getReview() {
        return review;
    }

    public String getLocation() {
        return location;
    }

    public String getContact() {
        return contact;
    }

    public String getHours() {
        return hours;
    }

    public String getIntroduction() {
        return introduction;
    }

    public ImageIcon getImage() {
        return image;
    }

    @Override
    public String toString() {
        return name;
    }
}

public class LocationPanel extends JPanel {
    private DefaultListModel<Destination> destinationListModel;
    private JList<Destination> destinationList;
    private JTextArea descriptionTextArea;
    private JLabel imageLabel; // 이미지를 표시하기 위한 JLabel

    public LocationPanel(List<Destination> destinations) {
        destinationListModel = new DefaultListModel<>();
        for (Destination destination : destinations) {
            destinationListModel.addElement(destination);
        }

        setLayout(new BorderLayout(10, 10));

        // 지역 목록을 보여주는 JList
        destinationList = new JList<>(destinationListModel);
        destinationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        destinationList.addListSelectionListener(e -> {
            Destination selectedDestination = destinationList.getSelectedValue();
            if (selectedDestination != null) {
                updateDescription(selectedDestination); // 선택된 목적지의 설명 업데이트
                imageLabel.setIcon(selectedDestination.getImage()); // 선택된 목적지의 이미지를 표시
            }
        });
        JScrollPane scrollPane = new JScrollPane(destinationList);
        scrollPane.setPreferredSize(new Dimension(200, 0));
        add(scrollPane, BorderLayout.WEST);

        // 지역 설명과 이미지를 보여주는 JTextArea와 JLabel
        descriptionTextArea = new JTextArea();
        descriptionTextArea.setEditable(false);
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionTextArea);
        add(descriptionScrollPane, BorderLayout.CENTER);

        imageLabel = new JLabel();
        add(imageLabel, BorderLayout.EAST);
    }

    private void updateDescription(Destination destination) {
        StringBuilder description = new StringBuilder();
        description.append("이름: ").append(destination.getName()).append("\n");
                description.append("역사: ").append(destination.getHistory()).append("\n");
                        description.append("리뷰: ").append(destination.getReview()).append("\n");
                                description.append("위치: ").append(destination.getLocation()).append("\n");
                                        description.append("연락처: ").append(destination.getContact()).append("\n");
                                                description.append("영업시간: ").append(destination.getHours()).append("\n");
                                                        description.append("소개: ").append(destination.getIntroduction());
        descriptionTextArea.setText(description.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            List<Destination> destinations = createDestinations();
            LocationPanel locationPanel = new LocationPanel(destinations);

            JFrame frame = new JFrame("지역 정보");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(locationPanel);
            frame.pack();
            frame.setVisible(true);
        });
    }

    private static List<Destination> createDestinations() {
        List<Destination> destinations = new ArrayList<>();

        // Destination 1
        String history1 = "이 지역은 오랜 역사와 전통을 가지고 있습니다...";
        String review1 = "여기를 방문해서 정말 좋은 경험을 했습니다...";
        String location1 = "지도상의 위치 정보";
        String contact1 = "연락처 정보";
        String hours1 = "영업시간 정보";
        String introduction1 = "이 지역은 자연의 아름다움과 풍부한 문화를 갖고 있습니다...";
        ImageIcon image1 = new ImageIcon("지역1.jpg"); // 이미지 파일의 경로
        Destination dest1 = new Destination("지역 1", history1, review1, location1, contact1, hours1, introduction1, image1);

        // Destination 2
        String history2 = "이 지역은 고대부터 인류의 역사와 깊은 연관이 있습니다...";
        String review2 = "이곳은 정말로 매력적인 장소입니다. 꼭 방문해보세요...";
        String location2 = "지도상의 위치 정보";
        String contact2 = "연락처 정보";
        String hours2 = "영업시간 정보";
        String introduction2 = "이 지역은 독특한 문화와 아름다운 자연 경관을 자랑합니다...";
        ImageIcon image2 = new ImageIcon("지역2.jpg"); // 이미지 파일의 경로
        Destination dest2 = new Destination("지역 2", history2, review2, location2, contact2, hours2, introduction2, image2);

        // Destination 3
        String history3 = "이 지역은 오래된 도시로, 역사적인 유적과 건축물이 많이 보존되어 있습니다...";
        String review3 = "여기에서는 훌륭한 음식과 아름다운 풍경을 즐길 수 있습니다...";
        String location3 = "지도상의 위치 정보";
        String contact3 = "연락처 정보";
        String hours3 = "영업시간 정보";
        String introduction3 = "이 지역은 전통과 현대의 조화로운 모습을 보여줍니다...";
        ImageIcon image3 = new ImageIcon("지역3.jpg"); // 이미지 파일의 경로
        Destination dest3 = new Destination("지역 3", history3, review3, location3, contact3, hours3, introduction3, image3);

        // Destination 4
        String history4 = "이 지역은 아름다운 자연 경관과 풍부한 자원을 가지고 있습니다...";
        String review4 = "숨막히는 풍경과 신선한 공기를 마시며 여행을 즐겼습니다...";
        String location4 = "지도상의 위치 정보";
        String contact4 = "연락처 정보";
        String hours4 = "영업시간 정보";
        String introduction4 = "이 지역은 자연 속에서 휴식과 여유를 즐길 수 있는 곳입니다...";
        ImageIcon image4 = new ImageIcon("지역4.jpg"); // 이미지 파일의 경로
        Destination dest4 = new Destination("지역 4", history4, review4, location4, contact4, hours4, introduction4, image4);

        // Destination 5
        String history5 = "이 지역은 독특한 문화와 전통을 가지고 있으며, 많은 관광객들이 찾는 명소입니다...";
        String review5 = "여기에서는 다양한 활동과 이벤트를 즐길 수 있습니다...";
        String location5 = "지도상의 위치 정보";
        String contact5 = "연락처 정보";
        String hours5 = "영업시간 정보";
        String introduction5 = "이 지역은 다채로운 문화와 예술을 경험할 수 있는 곳입니다...";
        ImageIcon image5 = new ImageIcon("지역5.jpg"); // 이미지 파일의 경로
        Destination dest5 = new Destination("지역 5", history5, review5, location5, contact5, hours5, introduction5, image5);

        destinations.add(dest1);
        destinations.add(dest2);
        destinations.add(dest3);
        destinations.add(dest4);
        destinations.add(dest5);

        return destinations;
    }

    private static void createAndShowGUI() {
        List<Destination> destinations = createDestinations();
        LocationPanel locationPanel = new LocationPanel(destinations);

        JFrame frame = new JFrame("지역 정보");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(locationPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
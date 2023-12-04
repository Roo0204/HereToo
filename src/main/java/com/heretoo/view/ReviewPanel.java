package com.heretoo.view;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ReviewPanel extends JPanel {

    private JPanel contentPanel;
    private JTextField nameField;
    private JTextField inputField;
    private List<Review> reviews;
    private ArrayList<JButton> starButtons;
    private JLabel averageRatingLabel;
    private int currentRating;
    private JLabel locationNameLabel;
    private JLabel locationImageLabel;

    // Review 객체를 저장할 클래스
    private class Review {
        String name;
        String text;
        int rating;
        JPanel panel;
        LocalDateTime time; // 리뷰 등록 시간
        public Review(String name, String text, int rating, LocalDateTime time, JPanel panel) {
            this.name = name;
            this.text = text;
            this.rating = rating;
            this.time = time;
            this.panel = panel;
        }
    }

    public ReviewPanel() {
       /* setTitle("Review Application");*/
        setSize(1200, 800);
        /*setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
        setLayout(new BorderLayout(10, 10));

        reviews = new ArrayList<>();
        starButtons = new ArrayList<>(5);
        currentRating = 0;

        // 상단 오른쪽 패널에 관광지 이름과 사진을 표시합니다.
        JPanel topRightPanel = new JPanel();
        topRightPanel.setLayout(new BorderLayout());
        locationNameLabel = new JLabel("관광지 이름", SwingConstants.CENTER);
        locationNameLabel.setFont(new Font("Serif", Font.BOLD, 24));
        topRightPanel.add(locationNameLabel, BorderLayout.NORTH);

        // 평균 평점 라벨을 추가합니다.
        averageRatingLabel = new JLabel("평균 평점: N/A", SwingConstants.CENTER);
        topRightPanel.add(averageRatingLabel, BorderLayout.CENTER);

        // 관광지 사진 라벨을 추가합니다.
        locationImageLabel = new JLabel(new ImageIcon("path/to/image.jpg")); // 이미지 경로를 설정하세요.
        topRightPanel.add(locationImageLabel, BorderLayout.SOUTH);

        // 하단 오른쪽에 리뷰 입력과 별점을 위한 패널을 생성합니다.
        JPanel bottomRightPanel = new JPanel();
        bottomRightPanel.setLayout(new BorderLayout());

        // 별점 버튼을 추가합니다.
        JPanel starPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        for (int i = 1; i <= 5; i++) {
            JButton starButton = new JButton("★");
            starButton.setForeground(Color.GRAY);
            int finalI = i;
            starButton.addActionListener(e -> setRating(finalI));
            starButtons.add(starButton);
            starPanel.add(starButton);
        }
        bottomRightPanel.add(starPanel, BorderLayout.NORTH);

        // 이름과 리뷰 입력 필드를 추가합니다.
        nameField = new JTextField(10);
        inputField = new JTextField(20);
        JButton addButton = new JButton("리뷰 추가");
        addButton.addActionListener(e -> submitReview());

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputPanel.add(new JLabel("이름:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("리뷰:"));
        inputPanel.add(inputField);
        inputPanel.add(addButton);
        bottomRightPanel.add(inputPanel, BorderLayout.SOUTH);

        // 리뷰 목록 패널을 생성합니다.
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setPreferredSize(new Dimension(300, 300));

        // 왼쪽에 리뷰 스크롤 패널을 추가합니다.
        add(scrollPane, BorderLayout.WEST);

        // 오른쪽 패널에 상단과 하단 패널을 추가합니다.
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(topRightPanel, BorderLayout.NORTH);
        rightPanel.add(bottomRightPanel, BorderLayout.SOUTH);
        add(rightPanel, BorderLayout.EAST);

        /*pack();
        setLocationRelativeTo(null);*/
        setVisible(true);
    }

    private void setRating(int rating) {
        currentRating = rating;
        for (int i = 0; i < starButtons.size(); i++) {
            starButtons.get(i).setForeground(i < currentRating ? Color.ORANGE : Color.GRAY);
        }
    }

    private void submitReview() {
        String name = nameField.getText();
        String reviewText = inputField.getText();
        LocalDateTime now = LocalDateTime.now();


        if (!name.isEmpty() && currentRating > 0 && !reviewText.isEmpty()) {
            addReview(name, currentRating,reviewText,now);
            nameField.setText("");
            inputField.setText("");
            setRating(0);
        } else {
            JOptionPane.showMessageDialog(this, "모든 필드를 채워주세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addReview(String name, int rating,  String text,LocalDateTime time) {
        JPanel reviewPanel = new JPanel();
        reviewPanel.setLayout(new BoxLayout(reviewPanel, BoxLayout.Y_AXIS));
        reviewPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel nameLabel = new JLabel("이름: " + name);
        JLabel textLabel = new JLabel("리뷰: " + text);
        JLabel ratingLabel = new JLabel("별점: " + "★".repeat(rating) + "☆".repeat(5 - rating));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        JLabel timeLabel = new JLabel("시간: " + time.format(formatter));
        reviewPanel.add(timeLabel); // 시간 라벨을 패널에 추가
        // 편집 및 삭제 버튼
        JButton editButton = new JButton("편집");
        JButton deleteButton = new JButton("삭제");

        editButton.addActionListener(e -> {
            inputField.setText(text);
            nameField.setText(name);
            setRating(rating);
            contentPanel.remove(reviewPanel);
            reviews.removeIf(r -> r.panel == reviewPanel);
            updateAverageRating();
            contentPanel.revalidate();
            contentPanel.repaint();
        });

        deleteButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(
                    this,
                    "이 리뷰를 삭제하시겠습니까?",
                    "리뷰 삭제 확인",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );
            if (response == JOptionPane.YES_OPTION) {
                contentPanel.remove(reviewPanel);
                reviews.removeIf(r -> r.panel == reviewPanel);
                updateAverageRating();
                contentPanel.revalidate();
                contentPanel.repaint();
            }
        });

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(editButton);
        buttonsPanel.add(deleteButton);

        reviewPanel.add(nameLabel);
        reviewPanel.add(textLabel);
        reviewPanel.add(ratingLabel);
        reviewPanel.add(buttonsPanel);

        reviews.add(new Review(name, text, rating,time, reviewPanel));
        contentPanel.add(reviewPanel);
        updateAverageRating();
        sortReviewsByLatest();

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void updateAverageRating() {
        double average = reviews.stream().mapToInt(r -> r.rating).average().orElse(0);
        averageRatingLabel.setText("평균 평점: " + String.format("%.2f", average));
    }

    private void sortReviewsByLatest() {
        contentPanel.removeAll();
        reviews.sort(Comparator.comparingInt(reviews::indexOf).reversed());
        reviews.forEach(r -> contentPanel.add(r.panel));
        contentPanel.revalidate();
        contentPanel.repaint();
    }


    protected ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("File not found: " + path);
            return null;
        }
    }

}
package com.heretoo.view;// 필요한 추가 import 문

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MyPagePanel extends JPanel {

    private JLabel nameLabel;
    private JLabel emailLabel;
    private JLabel phoneLabel;
    private JButton loginLogoutButton;
    private JButton getOutButton;

    private JTextArea reviewTextArea;

    private int width;
    private int height;

    public MyPagePanel() {
        width = 500;
        height = 800;
        setLayout(new GridLayout(1, 3, 10, 10)); // 1행 3열의 그리드 레이아웃 설정
        setBackground(Color.WHITE);

        // 프로필 블록
        JPanel profileBlock = createProfileBlock();
        add(profileBlock);

        // 활동 블록
        JPanel activityBlock = createActivityBlock();
        add(activityBlock);

        // 코스 추천 기본 설정 블록
        JPanel courseBlock = createCourseBlock();
        add(courseBlock);
    }

    private JPanel createProfileBlock() {
        JPanel profileBlock = new JPanel();
        profileBlock.setLayout(new BorderLayout(10, 10));
        profileBlock.setBackground(Color.LIGHT_GRAY);

        // 프로필 이미지 추가
        ImageIcon profileIcon = new ImageIcon("path/to/profile/image.png");
        JLabel profileImageLabel = new JLabel(profileIcon);
        profileBlock.add(profileImageLabel, BorderLayout.NORTH);

        // 이름, 이메일, 전화번호, 닉네임 라벨 추가
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));

        nameLabel = new JLabel("내 프로필");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setBorder(new EmptyBorder(10, 10, 10, 10)); // 패딩 추가
        labelPanel.add(nameLabel);

        JPanel nicknamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel nicknameLabel = new JLabel("닉네임");
        JButton nicknameEditButton = new JButton("닉네임 수정");
        nicknamePanel.add(nicknameLabel);
        nicknamePanel.add(nicknameEditButton);
        labelPanel.add(nicknamePanel);

        JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        emailLabel = new JLabel("livingwood94@gmail.com");
        JButton emailEditButton = new JButton("이메일 수정");
        emailPanel.add(emailLabel);
        emailPanel.add(emailEditButton);
        labelPanel.add(emailPanel);

        JPanel phonePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        phoneLabel = new JLabel("010-4598-****");
        JButton phoneEditButton = new JButton("전화번호 수정");
        phonePanel.add(phoneLabel);
        phonePanel.add(phoneEditButton);
        labelPanel.add(phonePanel);

        // 버튼 추가
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton getOutButton = new JButton("회원탈퇴");
        buttonPanel.add(getOutButton);
        profileBlock.add(buttonPanel, BorderLayout.SOUTH);

        profileBlock.add(labelPanel, BorderLayout.CENTER);

        return profileBlock;
    }






    private JPanel createActivityBlock() {
        JPanel activityBlock = new JPanel();
        activityBlock.setLayout(new BorderLayout(10, 10));
        activityBlock.setBackground(Color.LIGHT_GRAY);

// 활동 관련 컴포넌트 추가
        JLabel levelLabel = new JLabel("나의 활동률");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        levelLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // 여백 추가
        activityBlock.add(levelLabel, BorderLayout.NORTH);

        JProgressBar progressBar = new JProgressBar();
        progressBar.setValue(50);
        progressBar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 여백 추가
        activityBlock.add(progressBar, BorderLayout.CENTER);

        return activityBlock;
    }

    private JPanel createCourseBlock() {
        JPanel courseBlock = new JPanel();
        courseBlock.setLayout(new BorderLayout(10, 10));
        courseBlock.setBackground(Color.LIGHT_GRAY);

        // 코스 추천 관련 컴포넌트 추가
        JPanel coursePanel = new JPanel();
        coursePanel.setLayout(new BorderLayout(10, 10));
        coursePanel.setBackground(Color.LIGHT_GRAY);

        JLabel courseLabel = new JLabel("코스 추천 기본 설정");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        coursePanel.add(courseLabel, BorderLayout.NORTH);

        // 설정 옵션 버튼 추가
        JButton activeButton = new JButton("활동적인");
        JButton quietButton = new JButton("조용한");
        JButton insideButton = new JButton("실내");
        JButton outsideButton = new JButton("실외");
        JButton backpackButton = new JButton("holiday");
        JButton otherButton = new JButton("기타");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 3, 10, 10)); // 그리드 레이아웃으로 변경
        buttonPanel.add(activeButton);
        buttonPanel.add(quietButton);
        buttonPanel.add(insideButton);
        buttonPanel.add(outsideButton);
        buttonPanel.add(backpackButton);
        buttonPanel.add(otherButton);

        coursePanel.add(buttonPanel, BorderLayout.CENTER);
        courseBlock.add(coursePanel, BorderLayout.NORTH);

        // 내가 쓴 리뷰 부분 추가
        JPanel reviewPanel = new JPanel();
        reviewPanel.setLayout(new BorderLayout(10, 10));
        reviewPanel.setBackground(Color.LIGHT_GRAY);

        JLabel reviewLabel = new JLabel("내가 쓴 리뷰 보기");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        reviewPanel.add(reviewLabel, BorderLayout.NORTH);

        String[] reviews = {
                "이순신 공원은 시원하고 아기자기한 경지의 바다조망과 걷기 좋은 산책로가 있어 잠시 둘러보고 휴식을 취하기 좋은 무료 공원이다.",
                "이순신 공원은 자연환경과 조성된 정원이 조화를 이루어 아름다운 풍경을 자랑한다.",
                "이순신 공원은 식물이 풍부하게 조성되어 있어 자연을 느끼기에 좋은 장소이다.",
                "이순신 공원은 가족과 함께 산책하기에 안성맞춤이다.",
                "이순신 공원은 공기가 맑아서 여유롭게 산책하기에 좋은 장소이다."
        };

        JTextArea reviewTextArea = new JTextArea();
        reviewTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reviewTextArea);
        reviewPanel.add(scrollPane, BorderLayout.CENTER);

        for (String review : reviews) {
            reviewTextArea.append(review + "\n");
        }

        courseBlock.add(reviewPanel, BorderLayout.CENTER);

        return courseBlock;
    }

}

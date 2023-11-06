package com.heretoo.view;

import com.heretoo.dto.SearchResultDTO;
import com.heretoo.util.DBManager;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SearchPanel extends JPanel {

    private List<SearchResultDTO> resultList;
    private Integer page;
    private JPanel[] res;
    private JLabel pageNum;
    private Integer total;
    private JLabel resultNum;
    private final MapPanel mapPanel;
    private static final String indexAlphabet = "ABCDE";

    public SearchPanel(MapPanel mp) {
        mapPanel = mp;

        resultList = new ArrayList<>();
        page = 1;

        setPreferredSize(new Dimension(300, 100)); // 검색창이 들어갈 왼쪽 패널 크기
        setLayout(new BorderLayout());

        JTextField searchField = new JTextField(20); // 검색창 생성
        searchField.setText("여기에 검색어를 입력하세요");

        searchField.setMaximumSize(new Dimension(10, 20)); // 최대 사이즈 설정

        // 포커스 이벤트를 통해 힌트 텍스트 관리
        searchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("여기에 검색어를 입력하세요")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setForeground(Color.GRAY);
                    searchField.setText("여기에 검색어를 입력하세요");
                }
            }
        });

        // 검색 버튼
        JButton searchButton = new JButton("검색");

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    search(searchField.getText());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // 검색창 부분 추가
        JPanel searchButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // 검색 버튼을 오른쪽에 배치
        searchButtonPanel.add(searchButton);
        add(searchField, BorderLayout.CENTER);
        add(searchButtonPanel, BorderLayout.EAST);

        // 검색 결과 부분
        JPanel resultPanel = new JPanel();
        resultNum = new JLabel();
        resultNum.setForeground(Color.GRAY);
        total = 0;
        resultPanel.add(resultNum);
        resultPanel.setPreferredSize(new Dimension(300, 700));
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        res = new JPanel[5];
        for (int i = 0; i < 5; i++) {
            res[i] = new JPanel();
            res[i].setPreferredSize(new Dimension(300, 100));
            res[i].setBorder(new LineBorder(Color.black, 1));
            res[i].setLayout(new BorderLayout());
            resultPanel.add(res[i]);
        }

        // 페이지 이동 부분
        JPanel pagePanel = new JPanel();
        pagePanel.setPreferredSize(new Dimension(300, 200));
        pagePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton prev = new JButton("이전");
        JButton next = new JButton("다음");

        prev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (page > 1) {
                    page--;
                    displayPage(page);
                }
            }
        });

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (page * 5 < total) {
                    page++;
                    displayPage(page);
                }
            }
        });

        pageNum = new JLabel();
        pagePanel.add(prev);
        pagePanel.add(pageNum);
        pagePanel.add(next);

        updateResult();

        resultPanel.add(pagePanel);

        add(resultPanel, BorderLayout.SOUTH);
    }

    private void search(String keyWord) throws SQLException {

        String database;
        String userName;
        String password;

        Properties prop = new Properties();
        try (InputStream resource = getClass().getClassLoader().getResourceAsStream("database.properties")) {
            prop.load(resource);
            database = prop.getProperty("db");
            userName = prop.getProperty("user");
            password = prop.getProperty("password");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        DBManager db = DBManager.getInstance(database, userName, password);
        db.makeConnection();
        String query = "select * from spot where name like '%" + keyWord + "%'";
        ResultSet resultSet = db.executeQuery(query);


        resultList = new ArrayList<>();

        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            Double lat = resultSet.getDouble("latitude");
            Double lng = resultSet.getDouble("longitude");
            String theme = resultSet.getString("theme_name");
            SearchResultDTO dto = new SearchResultDTO(id, name, lat, lng, theme);
            resultList.add(dto);
        }

        resultSet.last();
        total = resultSet.getRow();
        db.closeConnection();
        updateResult();
    }

    private void updateResult() {
        page = 1;
        pageNum.setText(page.toString());
        resultNum.setText("total result: " + total.toString());
        displayPage(page);
    }

    private void displayPage(int page) {

        pageNum.setText(String.valueOf(page));

        for (int i = 0; i < 5; i++) {
            res[i].removeAll();
        }

        if (total <= 0) {
            mapPanel.updateMap(null);
            return;
        }

        int idx = (page - 1) * 5;
        List<List<String>> pointList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {

            if (idx + i >= total) {
                break;
            }

            SearchResultDTO data = resultList.get(idx + i);

            List<String> point = new ArrayList<>();

            point.add(((Character)indexAlphabet.charAt(i)).toString());
            point.add(data.getLat().toString());
            point.add(data.getLng().toString());
            pointList.add(point);

            JLabel indexLabel = new JLabel();
            indexLabel.setText(String.valueOf(indexAlphabet.charAt(i)));
            JLabel spotLabel = new JLabel();
            spotLabel.setText(data.getName());
            JLabel themeLabel = new JLabel();
            themeLabel.setText("(" + data.getTheme() + ")");
            res[i].add(indexLabel, BorderLayout.NORTH);
            res[i].add(spotLabel, BorderLayout.CENTER);
            res[i].add(themeLabel, BorderLayout.SOUTH);
            res[i].repaint();
        }
        mapPanel.updateMap(pointList);
    }


}

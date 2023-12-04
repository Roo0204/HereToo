package com.heretoo.view;

import javax.swing.*;
import java.awt.*;

public class SpotPanel extends JPanel {

    public SpotPanel(String name) {

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(700, 700));

        JLabel spotName = new JLabel(name);
        spotName.setPreferredSize(new Dimension(700, 700));
        add(spotName, BorderLayout.NORTH);
        spotName.setHorizontalAlignment(JLabel.CENTER);
    }
}

package scormac;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class App extends JFrame {
    public App() {
        setTitle("Weather App");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(211, 201, 179));

        JLabel title = new JLabel("Weather");
        title.setFont(new Font(Font.SERIF, Font.ITALIC, 28));
        title.setForeground(new Color(40,30,15));
        titlePanel.add(title);

        add(titlePanel, BorderLayout.NORTH);

        JPanel viewPanel = new JPanel();
        viewPanel.setLayout(new BorderLayout());

        JPanel searchPanel = new JPanel();
        searchPanel.setBorder(new EmptyBorder(20, 0, 20, 0));
        JTextField searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(200, 25));
        searchPanel.add(searchField);
        viewPanel.add(searchPanel, BorderLayout.NORTH);

        JPanel results = new JPanel();
        results.setLayout(new BoxLayout(results, BoxLayout.PAGE_AXIS));

        JLabel cityLabel = new JLabel();
        cityLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        cityLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cityLabel.setBorder(new EmptyBorder(0, 0, 10, 0));
        results.add(cityLabel);

        JLabel tempLabel = new JLabel();
        tempLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        tempLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 48));
        tempLabel.setBorder(new EmptyBorder(30, 0, 40, 0));
        results.add(tempLabel);

        JPanel descPanel = new JPanel();
        JLabel descLabel = new JLabel();
        descLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        descPanel.add(descLabel);

        results.add(descPanel);

        viewPanel.add(results, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(0, 0, 25, 0));
        JButton btn = new JButton("Check the Weather");
        btn.setPreferredSize(new Dimension(150, 30));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        buttonPanel.add(btn);

        viewPanel.add(buttonPanel, BorderLayout.SOUTH);

        btn.addActionListener(e -> {
            if (!searchField.getText().isEmpty()) {
                try {
                    String city = searchField.getText().toLowerCase().trim();
                    WeatherData wd = new WeatherData(city);
                    Weather w = wd.getData();
                    cityLabel.setText("Weather in " + w.getCity() + ", " + w.getCountry());
                    tempLabel.setText((int)Math.round(w.getTemp()) + "°C");
                    descLabel.setText("<html>" +
                            "<b><i>" + w.getDescription() + "</i></b><br/>" +
                            "Feels like: " + w.getFeelsLike() + "°C<br/>" +
                            "Min temperature: " + w.getTempMin() + "°C<br/>" +
                            "Max temperature: " + w.getTempMax() + "°C<br/>" +
                            "Visibility: " + w.getVisibility() + " m<br/>" +
                            "Sunrise: " + w.getSunrise() + "<br/>" +
                            "Sunset: " + w.getSunset() +
                            "</html>");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this.getContentPane(), "Check the city name");
                }

            } else {
                JOptionPane.showMessageDialog(this.getContentPane(), "Enter the name of the city");
            }
            searchField.setText("");
        });

        add(viewPanel, BorderLayout.CENTER);

        JLabel devBy = new JLabel("Developed By Oleksandr Lavruk");
        devBy.setHorizontalAlignment(SwingConstants.CENTER);
        add(devBy, BorderLayout.SOUTH);

        setVisible(true);
    }
}
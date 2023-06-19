package scormac;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class App extends JFrame {
    public App() {
        setTitle("Weather App");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(211, 201, 179));

        JLabel title = new JLabel("Weather");
        title.setFont(new Font(Font.SERIF, Font.ITALIC, 28));
        title.setForeground(new Color(40,30,15));
        titlePanel.add(title);

        add(titlePanel, BorderLayout.NORTH);

        JPanel viewPanel = new JPanel();
//        viewPanel.setBackground(new Color(239, 237, 232));
        viewPanel.setLayout(new BorderLayout());

        JPanel searchPanel = new JPanel();
        searchPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        JTextField searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(200, 25));
        searchPanel.add(searchField);
        viewPanel.add(searchPanel, BorderLayout.NORTH);

        JPanel results = new JPanel();
        results.setLayout(new BoxLayout(results, BoxLayout.Y_AXIS));

        JLabel cityLabel = new JLabel();
        cityLabel.setHorizontalAlignment(SwingConstants.CENTER);
        results.add(cityLabel);

        JLabel tempLabel = new JLabel();
        tempLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tempLabel.setFont(new Font(Font.SERIF, 0, 48));
        results.add(tempLabel);

        JLabel descLabel = new JLabel();
        descLabel.setHorizontalAlignment(SwingConstants.CENTER);
        results.add(descLabel);

        viewPanel.add(results, BorderLayout.CENTER);

        JButton btn = new JButton("Check the Weather");
        viewPanel.add(btn, BorderLayout.SOUTH);

        btn.addActionListener(e -> {
            String city = searchField.getText().toLowerCase().trim();
            WeatherData wd = new WeatherData(city);
            cityLabel.setText("Weather in " + wd.getData().getCity() + ", " + wd.getData().getCountry());
            tempLabel.setText(wd.getData().getTemp() + "°C");
            descLabel.setText("<html>" +
                    wd.getData().getDescription() + "<br/>" +
                    "Feels like: " + wd.getData().getFeelsLike() + "°C<br/>" +
                    "Min temperature: " + wd.getData().getTempMin() + "°C<br/>" +
                    "Max temperature: " + wd.getData().getTempMax() + "°C<br/>" +
                    "Visibility: " + wd.getData().getVisibility() + " m<br/>" +
                    "Sunrise: " + wd.getData().getSunrise() + " UTC<br/>" +
                    "Sunset: " + wd.getData().getSunset() + " UTC" +
                    "</html>");
        });

        add(viewPanel, BorderLayout.CENTER);

        JLabel devBy = new JLabel("Developed By Oleksandr Lavruk");
        devBy.setHorizontalAlignment(SwingConstants.CENTER);
        add(devBy, BorderLayout.SOUTH);

        setVisible(true);
    }
}
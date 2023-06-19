package scormac;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        WeatherData wd = new WeatherData("kalush");
        System.out.println(wd.getResponseContent());

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new App();
            }
        });
    }
}

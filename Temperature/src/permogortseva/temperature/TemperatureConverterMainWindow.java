package permogortseva.temperature;

import javax.swing.*;
import java.awt.*;

public class TemperatureConverterMainWindow {
    public void start() {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            JFrame frame = new JFrame("Temperature Converter");

            frame.setTitle("Конвертер температур");

            frame.setResizable(false);
            frame.setSize(200, 160);
            frame.setLocationRelativeTo(null);

            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            Object[] options = {"Цельсий", "Кельвин", "Фаренгейт"};

            String inputScale = (String) JOptionPane.showInputDialog(frame,
                    "Выберите начальную шкалу:",
                    "Конвертер температур",
                    JOptionPane.PLAIN_MESSAGE, null,
                    options,
                    "Цельсий");

            String outputScale = (String) JOptionPane.showInputDialog(frame,
                    "Выберите конечную шкалу:",
                    "Конвертер температур",
                    JOptionPane.PLAIN_MESSAGE, null,
                    options,
                    "Цельсий");

            JPanel panel = new JPanel(new GridBagLayout());
            frame.add(panel);

            GridBagConstraints c = new GridBagConstraints();

            JLabel defaultValueLabel = new JLabel("Начальная величина в " + inputScale + ":");
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 0;
            panel.add(defaultValueLabel, c);

            JTextField inputTextField = new JTextField("0", 10);
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 1;
            panel.add(inputTextField, c);

            JButton button = new JButton("Конвертировать");
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 2;
            panel.add(button, c);

            JLabel newValueLabel = new JLabel("Конечная величина в " + outputScale + ":");
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 3;
            panel.add(newValueLabel, c);

            JTextField outputTextField = new JTextField();
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 4;

            outputTextField.setEditable(false);

            panel.add(outputTextField, c);

            button.addActionListener(e -> {
                try {
                    double inputTemperature = Double.parseDouble(inputTextField.getText());

                    TemperatureConverter converter = new TemperatureConverter(inputTemperature, inputScale, outputScale);
                    double outputTemperature = converter.convert();

                    outputTextField.setText(String.valueOf(outputTemperature));
                } catch (NumberFormatException e2) {
                    JOptionPane.showMessageDialog(frame, "Необходимо ввести число!", "Ошибка", JOptionPane.WARNING_MESSAGE);
                }

            });

            frame.setVisible(true);
        });
    }
}

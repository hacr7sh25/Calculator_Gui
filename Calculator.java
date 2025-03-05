import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    private JFrame frame;
    private JTextField textField;
    private String operator;
    private double num1, num2, result;

    public Calculator() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setLayout(new BorderLayout());

        textField = new JTextField();
        textField.setEditable(false);
        textField.setHorizontalAlignment(JTextField.RIGHT);
        frame.add(textField, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 5, 5));

        String[] buttons = {"7", "8", "9", "/",
                            "4", "5", "6", "*",
                            "1", "2", "3", "-",
                            "0", "C", "=", "+"};

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.addActionListener(new ButtonClickListener());
            panel.add(button);
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
                textField.setText(textField.getText() + command);
            } else if (command.equals("C")) {
                textField.setText("");
                num1 = num2 = result = 0;
                operator = "";
            } else if (command.equals("=")) {
                try {
                    num2 = Double.parseDouble(textField.getText());
                    switch (operator) {
                        case "+": result = num1 + num2; break;
                        case "-": result = num1 - num2; break;
                        case "*": result = num1 * num2; break;
                        case "/": result = num1 / num2; break;
                    }
                    textField.setText(String.valueOf(result));
                } catch (NumberFormatException ex) {
                    textField.setText("Error");
                }
            } else {
                try {
                    num1 = Double.parseDouble(textField.getText());
                    operator = command;
                    textField.setText("");
                } catch (NumberFormatException ex) {
                    textField.setText("Error");
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Calculator::new);
    }
}

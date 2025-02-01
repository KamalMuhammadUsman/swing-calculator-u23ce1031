package calculatore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame {
    private JTextField display;
    private double result = 0;
    private String lastOperation = "=";
    private boolean startNewNumber = true;

    public Calculator() {
        setTitle("Kamal Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

       
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                int w = getWidth();
                int h = getHeight();
                GradientPaint gp = new GradientPaint(0, 0, new Color(255, 255, 255),
                                                    w, h, new Color(255, 255, 255));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        
        display = new JTextField("0");
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("CENTER GOTHIC", Font.BOLD, 24));
        display.setBackground(new Color(240, 240, 240));
        display.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 2),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        mainPanel.add(display, BorderLayout.NORTH);

        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 8, 8));
        buttonPanel.setOpaque(false);

      
        JPanel topButtonsPanel = new JPanel();
        topButtonsPanel.setLayout(new GridLayout(2, 2, 8, 8));
        topButtonsPanel.setOpaque(false);

        
        JButton backspaceButton = createStyledButton("←");
        backspaceButton.setBackground(new Color(255, 165, 0)); 
        backspaceButton.addActionListener(e -> {
            String currentText = display.getText();
            if (currentText.length() > 0 && !startNewNumber) {
                display.setText(currentText.substring(0, currentText.length() - 1));
                if (display.getText().isEmpty()) {
                    display.setText("0");
                    startNewNumber = true;
                }
            }
        });
        topButtonsPanel.add(backspaceButton);

        
        JButton clearButton = createStyledButton("C");
        clearButton.setBackground(new Color(255, 100, 100));
        clearButton.addActionListener(e -> {
            result = 0;
            lastOperation = "=";
            startNewNumber = true;
            display.setText("0");
        });
        topButtonsPanel.add(clearButton);

        
        buttonPanel.add(topButtonsPanel);

       
        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = createStyledButton(label);
            buttonPanel.add(button);
            button.addActionListener(new ButtonClickListener());
        }

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        add(mainPanel);
        setSize(350, 500); 
        setLocationRelativeTo(null);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("WEBBINGS", Font.BOLD, 18));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(80, 80, 80));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        
      
        
        return button;
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String command = event.getActionCommand();
            
            if (command.charAt(0) >= '0' && command.charAt(0) <= '9' || command.equals(".")) {
                if (startNewNumber) {
                    display.setText(command);
                    startNewNumber = false;
                } else {
                    display.setText(display.getText() + command);
                }
            } else {
                if (!startNewNumber) {
                    calculate();
                    startNewNumber = true;
                }
                lastOperation = command;
            }
        }
    }

    private void calculate() {
        double displayNumber = Double.parseDouble(display.getText());
        
        switch (lastOperation) {
            case "+":
                result += displayNumber;
                break;
            case "-":
                result -= displayNumber;
                break;
            case "*":
                result *= displayNumber;
                break;
            case "/":
                if (displayNumber != 0) {
                    result /= displayNumber;
                } else {
                    JOptionPane.showMessageDialog(this, "Cannot divide by zero!");
                    return;
                }
                break;
            case "=":
                result = displayNumber;
                break;
        }
        display.setText(String.valueOf(result));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Calculator().setVisible(true);
        });
    }
}
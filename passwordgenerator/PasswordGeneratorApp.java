import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class PasswordGeneratorApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PasswordGeneratorApp().createUI());
    }

    private void createUI() {
        JFrame frame = new JFrame("🔐 Modern Password Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(30, 30, 30));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        frame.add(panel);

        JLabel title = new JLabel("🔐 Password Generator", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);

        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel lengthLabel = new JLabel("Select Password Length:");
        lengthLabel.setForeground(Color.LIGHT_GRAY);
        panel.add(lengthLabel);

        JSlider lengthSlider = new JSlider(4, 30, 12);
        lengthSlider.setPaintTicks(true);
        lengthSlider.setPaintLabels(true);
        lengthSlider.setMajorTickSpacing(5);
        lengthSlider.setMinorTickSpacing(1);
        lengthSlider.setForeground(Color.WHITE);
        lengthSlider.setBackground(new Color(30, 30, 30));
        panel.add(lengthSlider);

        JCheckBox includeNumbers = new JCheckBox("Include Numbers", true);
        JCheckBox includeSpecials = new JCheckBox("Include Special Characters", true);
        styleCheckbox(includeNumbers);
        styleCheckbox(includeSpecials);
        panel.add(includeNumbers);
        panel.add(includeSpecials);

        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        JButton generateBtn = new JButton("Generate Password");
        styleButton(generateBtn);
        panel.add(generateBtn);

        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        JTextField passwordField = new JTextField();
        passwordField.setEditable(false);
        passwordField.setFont(new Font("Consolas", Font.BOLD, 16));
        passwordField.setBackground(new Color(50, 50, 50));
        passwordField.setForeground(Color.GREEN);
        passwordField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(passwordField);

        generateBtn.addActionListener(e -> {
            int length = lengthSlider.getValue();
            boolean numbers = includeNumbers.isSelected();
            boolean specials = includeSpecials.isSelected();
            String password = generatePassword(length, numbers, specials);
            passwordField.setText(password);
        });

        frame.setVisible(true);
    }

    private void styleCheckbox(JCheckBox cb) {
        cb.setForeground(Color.WHITE);
        cb.setBackground(new Color(30, 30, 30));
        cb.setFocusPainted(false);
    }

    private void styleButton(JButton btn) {
        btn.setBackground(new Color(76, 175, 80));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Add hover effect
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(56, 142, 60));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(76, 175, 80));
            }
        });
    }

    private String generatePassword(int minLength, boolean includeNumbers, boolean includeSpecials) {
        String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String digits = "0123456789";
        String special = "!@#$%^&*()_+-=[]{}|;:',.<>?/";
        StringBuilder characterPool = new StringBuilder(letters);

        if (includeNumbers) characterPool.append(digits);
        if (includeSpecials) characterPool.append(special);

        Random rand = new Random();
        StringBuilder password = new StringBuilder();
        boolean hasNumber = false;
        boolean hasSpecial = false;

        while (password.length() < minLength || 
              (includeNumbers && !hasNumber) || 
              (includeSpecials && !hasSpecial)) {

            char c = characterPool.charAt(rand.nextInt(characterPool.length()));
            password.append(c);
            if (digits.indexOf(c) >= 0) hasNumber = true;
            if (special.indexOf(c) >= 0) hasSpecial = true;
        }

        return password.toString();
    }
}

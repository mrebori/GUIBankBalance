import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIBankBalance extends JFrame implements ActionListener {
    private double balance;
    private JTextField balanceField, amountField;
    private JButton depositButton, withdrawButton, checkBalanceButton;

    public GUIBankBalance() {
        // Initialize balance
        balance = 0.00;

        // Create the main frame
        setTitle("Bank Balance Application");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create and add a panel for the components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        // Add components to the panel
        panel.add(new JLabel(" Current Account Balance:"));
        balanceField = new JTextField("0.00");
        balanceField.setEditable(false);
        panel.add(balanceField);

        panel.add(new JLabel(" Amount:"));
        amountField = new JTextField();
        panel.add(amountField);

        depositButton = new JButton("DEPOSIT");
        withdrawButton = new JButton("WITHDRAW");
        checkBalanceButton = new JButton("CHECK BALANCE");

        Font font = new Font("Arial", Font.BOLD, 12);

        depositButton.addActionListener(this);
        depositButton.setFont(font);
        withdrawButton.addActionListener(this);
        withdrawButton.setFont(font);
        checkBalanceButton.addActionListener(this);
        checkBalanceButton.setFont(font);

        panel.add(depositButton);
        panel.add(withdrawButton);
        panel.add(checkBalanceButton);

        add(panel, BorderLayout.CENTER);

        // Display the window
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evnt) {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (evnt.getSource() == depositButton) {
                balance += amount;
                JOptionPane.showMessageDialog(this, "You Deposited: $" + amount);
            } else if (evnt.getSource() == withdrawButton) {
                if (balance >= amount) {
                    balance -= amount;
                    JOptionPane.showMessageDialog(this, "You Withdrew: $" + amount);
                } else {
                    JOptionPane.showMessageDialog(this, "Insufficient funds! Your balance is: $" + balance);
                }
            } else if (evnt.getSource() == checkBalanceButton) {
                JOptionPane.showMessageDialog(this, "Current Balance: $" + balance);
            }
            // Update balance field
            balanceField.setText(String.format("%.2f", balance));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid amount.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUIBankBalance());
    }
}

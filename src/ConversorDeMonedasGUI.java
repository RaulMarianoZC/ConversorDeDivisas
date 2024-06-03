import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CurrencyConverterGUI {

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Currency Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.getContentPane().setBackground(Color.decode("#00C29F"));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10)); // Añade espacios entre elementos
        panel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Añade márgenes al panel

        Font font = new Font("Monaco", Font.PLAIN, 14);

        JLabel fromLabel = new JLabel("Moneda Origen:");
        fromLabel.setFont(font);
        JComboBox<String> fromCurrency = new JComboBox<>(new String[]{
                "MXN", "COP", "PEN", "ARS", "BRL", "USD", "SEK", "EUR", "JPY", "GBP"
        });
        fromCurrency.setFont(font);

        JLabel toLabel = new JLabel("A:");
        toLabel.setFont(font);
        JComboBox<String> toCurrency = new JComboBox<>(new String[]{
                "MXN", "COP", "PEN", "ARS", "BRL", "USD", "SEK", "EUR", "JPY", "GBP"
        });
        toCurrency.setFont(font);

        JLabel amountLabel = new JLabel("Cantidad:");
        amountLabel.setFont(font);
        JTextField amountField = new JTextField();
        amountField.setFont(font);

        JButton convertButton = new JButton("Convertir") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, getBackground().brighter(), 0, getHeight(), getBackground().darker(), true);
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(g);
            }
        };
        convertButton.setFont(font);
        convertButton.setContentAreaFilled(false);
        convertButton.setFocusPainted(false);
        convertButton.setBorderPainted(false);
        convertButton.setOpaque(false);

        JLabel resultLabel = new JLabel("Resultado:");
        resultLabel.setFont(font);

        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String from = (String) fromCurrency.getSelectedItem();
                String to = (String) toCurrency.getSelectedItem();
                double amount = Double.parseDouble(amountField.getText());
                double rate = CurrencyConverter.getExchangeRate(from, to);
                if (rate != -1) {
                    double result = CurrencyConverter.convertCurrency(amount, rate);
                    resultLabel.setText("Resultado: " + result);
                } else {
                    resultLabel.setText("Error en obtener tasa de cambio.");
                }
            }
        });

        panel.add(fromLabel);
        panel.add(fromCurrency);
        panel.add(toLabel);
        panel.add(toCurrency);
        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(new JLabel());
        panel.add(convertButton);
        panel.add(new JLabel());
        panel.add(resultLabel);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
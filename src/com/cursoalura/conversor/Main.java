package com.cursoalura.conversor;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.cursoalura.conversor.Modulos.*;

public class Main {
    private static final String LOG_FILE = "conversion_log.txt";

    public static void main(String[] args) {
        Map<Integer, String> conversionMap = new HashMap<>();
        setupConversionMap(conversionMap);

        String apiKey = JOptionPane.showInputDialog("Por favor, ingrese su API key:");
        if (apiKey == null || apiKey.isEmpty()) {
            showErrorDialog("Debe ingresar una API key para continuar.");
            return;
        }

        SwingUtilities.invokeLater(() -> createAndShowGUI(conversionMap, apiKey));
    }

    private static void createAndShowGUI(Map<Integer, String> conversionMap, String apiKey) {
        JFrame frame = new JFrame("Conversor de Moneda");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 830);
        frame.setLayout(new BorderLayout());

        Color darkColor = new Color(30, 30, 30);
        frame.getContentPane().setBackground(darkColor);

        JTextArea textArea = createTextArea();
        JPanel panel = createButtonPanel(conversionMap, textArea, apiKey);

        panel.setBackground(darkColor);

        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);
        frame.add(panel, BorderLayout.WEST);

        // Centra la ventana en la pantalla
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    private static JTextArea createTextArea() {
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBackground(new Color(30, 30, 30));
        textArea.setForeground(Color.WHITE);
        return textArea;
    }

    private static JPanel createButtonPanel(Map<Integer, String> conversionMap, JTextArea textArea, String apiKey) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        Color darkColor = new Color(30, 30, 30);
        panel.setBackground(darkColor);

        for (int i = 1; i <= 16; i++) {
            String conversion = conversionMap.get(i);
            JButton button = createConversionButton(conversion, textArea, apiKey);
            button.setBackground(darkColor);
            panel.add(button);
        }

        return panel;
    }

    private static JButton createConversionButton(String conversion, JTextArea textArea, String apiKey) {
        JButton button = new JButton(conversion);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setMargin(new Insets(10, 10, 10, 10));
        button.setBorderPainted(false);
        button.setFocusPainted(false);

        // Agregar un MouseListener para detectar cuando el cursor entra y sale del botón
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Cuando el cursor entra en el botón, aumenta su tamaño
                button.setFont(button.getFont().deriveFont(Font.BOLD, 15)); // Cambiar el tamaño de la fuente
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Cuando el cursor sale del botón, restaura su tamaño original
                button.setFont(button.getFont().deriveFont(Font.PLAIN, 14)); // Restaurar el tamaño de la fuente
            }
        });

        button.addActionListener(e -> {
            try {
                double amount = getConversionAmount();
                textArea.append(String.format("Conversion: %s - Monto: %.2f%n", conversion, amount));
                convertAndDisplay(conversion, amount, textArea, apiKey);
            } catch (NumberFormatException ex) {
                showErrorDialog("Ingrese un valor numérico válido.");
            }
        });
        return button;
    }

    private static double getConversionAmount() {
        String input = JOptionPane.showInputDialog("Ingrese el valor que desea convertir:");
        if (input == null) {
            throw new NumberFormatException();
        }
        return Double.parseDouble(input);
    }

    private static void convertAndDisplay(String conversion, double amount, JTextArea textArea, String apiKey) {
        try {
            String apiUrl = "https://v6.exchangerate-api.com/v6/*/latest/*";
            String apiRequestUrl = createRequest(apiUrl, apiKey, "USD");
            String exampleJson = new Api().getResponse(apiRequestUrl);
            Monedas currencies = new JsonParsing().getMonedas(exampleJson);
            Divisa currencyConverter = new Divisa(currencies);
            currencyConverter.setBase_code(conversion, amount);
            double result = currencyConverter.getResultado();

            textArea.append(String.format("Resultado de la conversión: %.2f%n%n", result));
            writeConversionLog(conversion, amount, result);
        } catch (Exception e) {
            textArea.append("Error: " + e.getMessage() + "\n\n");
        }
    }

    private static void writeConversionLog(String conversion, double amount, double result) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            String logEntry = String.format("Conversión: %s - Monto: %.2f - Resultado: %.2f%n", conversion, amount, result);
            writer.write(logEntry);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo de registro: " + e.getMessage());
        }
    }

    private static void setupConversionMap(Map<Integer, String> map) {
        map.put(1, "USDARS");
        map.put(2, "ARSUSD");
        map.put(3, "USDBRL");
        map.put(4, "BRLUSD");
        map.put(5, "USDCOP");
        map.put(6, "COPUSD");
        map.put(7, "USDMXN");
        map.put(8, "MXNUSD");
        map.put(9, "JPYMXN");
        map.put(10, "MXNJPY");
        map.put(11, "EURUSD");
        map.put(12, "USDEUR");
        map.put(13, "USDCAD");
        map.put(14, "CADUSD");
        map.put(15, "USDAUD");
        map.put(16, "AUDUSD");
    }

    private static String createRequest(String url, String... args) {
        for (String temp : args) {
            url = url.replaceFirst("\\*", temp);
        }
        return url;
    }

    private static void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}



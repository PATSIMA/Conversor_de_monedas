package com.cursoalura.conversor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.net.ConnectException;
import java.util.*;

import com.cursoalura.conversor.Modulos.*;

public class Main {
    private static final String LOG_FILE = "conversion_log.txt";

    public static void main(String[] args) {
        String apiKey;
        String apiUrl = "https://v6.exchangerate-api.com/v6/*/latest/*";
        String apiKeyPath = "apiKey.txt";
        Map<Integer, String> conversionMap = new HashMap<>();
        setupConversionMap(conversionMap);

        String menu = """
                --------------------------------------
                Bienvenido(a) al Conversor de Moneda
                
                1) Dólar -----------> Peso Argentino
                2) Peso Argentino --> Dólar
                3) Dólar -----------> Real Brasileño
                4) Real Brasileño --> Dólar
                5) Dólar -----------> Peso Colombiano
                6) Peso Colombiano -> Dólar
                7) Dólar -----------> Peso Mexicano
                8) Peso Mexicano ---> Dólar
                9) Yen Japonés------> Peso Mexicano
                10) Peso Mexicano --> Yen Japonés
                11) Euro ------------> Dólar
                12) Dólar ----------> Euro
                13) Dólar ----------> Dólar Canadiense
                14) Dólar Canadiense -> Dólar
                15) Dólar ----------> Dólar Australiano
                16) Dólar Australiano -> Dólar
                17) ----- Salir ----------------------
                
                Elija una opción del menú:
                ---------------------------------------""";

        try (Scanner scanner = new Scanner(System.in);
             BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {

            Key apiKeyFile = new Key(apiKeyPath);
            apiKey = apiKeyFile.getApiKey();
            String apiRequestUrl = createRequest(apiUrl, apiKey, "USD");
            Api apiConnector = new Api();
            String exampleJson = apiConnector.getResponse(apiRequestUrl);

            JsonParsing jsonParser = new JsonParsing();
            Monedas currencies = jsonParser.getMonedas(exampleJson);
            Divisa currencyConverter = new Divisa(currencies);

            double amount;
            int option;

            do {
                System.out.println(menu);

                try {
                    option = scanner.nextInt();

                    if (option < 1 || option > 17) {
                        if (option == 17) {
                            break;
                        }
                        System.out.println("Opción no válida. Intente de nuevo.");
                        continue;
                    }

                    System.out.println("Ingrese el valor que desea convertir");
                    amount = scanner.nextDouble();
                    currencyConverter.setBase_code(conversionMap.get(option), amount);
                    double result = currencyConverter.getResultado();
                    System.out.println("Resultado de la conversión: " + result);

                    writeConversionLog(writer, conversionMap.get(option), amount, result);

                } catch (InputMismatchException e) {
                    System.out.println("Error con el valor ingresado. Intente de nuevo.");
                    scanner.nextLine(); // Limpiar el buffer de entrada después de un error de entrada
                }

            } while (true);

        } catch (IllegalArgumentException e) {
            System.out.println("Error: Verifique la dirección de la API." + e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("Error: Verifique el archivo apiKey");
        } catch (ConnectException e) {
            System.out.println("Error de conexion.");
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Error de interrupción: " + e.getMessage());
        }

        System.out.println("Hasta pronto! Programa finalizado.");
    }

    private static void writeConversionLog(BufferedWriter writer, String conversion, double amount, double result) {
        try {
            String logEntry = String.format("Conversión: %s - Monto: %.2f - Resultado: %.2f%n", conversion, amount, result);
            writer.write(logEntry);
            writer.flush();
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
}

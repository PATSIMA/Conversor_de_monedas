package com.cursoalura.conversor.Modulos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Key {
    private String apiKey;

    public Key(String filePath) throws IOException {
        readApiKeyFromFile(filePath);
    }

    private void readApiKeyFromFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            apiKey = reader.readLine();
        }
    }

    public String getApiKey() {
        return apiKey;
    }
}

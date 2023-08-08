package ex00;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SignatureMap {
    private final Map<String, byte[]> signatureMap;

    private String searchResult;

    public SignatureMap() {
        this.signatureMap = new HashMap<>();;
    }

    public void fillSignatureMap(String filePath) {
        try (FileInputStream inputStream = new FileInputStream(filePath);
             InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {

            StringBuilder stringBuilder = new StringBuilder();
            int data;

            while ((data = reader.read()) != -1) {
                if (data == '\n') {
                    processLine(stringBuilder.toString(), signatureMap);
                    stringBuilder.setLength(0); // Clear the StringBuilder
                } else {
                    stringBuilder.append((char) data);
                }
            }

            // Process the last line (if any)
            if (stringBuilder.length() > 0) {
                processLine(stringBuilder.toString(), signatureMap);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processLine(String line, Map<String, byte[]> signatureMap) {
        String[] parts = line.split(", ");
        if (parts.length == 2) {
            String formatName = parts[0];
            String hexSignature = parts[1];
            byte[] signatureBytes = hexStringToByteArray(hexSignature);

            signatureMap.put(formatName, signatureBytes);
        }
    }

    // Helper function to convert a hex string to a byte array
    private static byte[] hexStringToByteArray(String hexString) {
        hexString = hexString.replaceAll(" ", "");
        int len = hexString.length();
        byte[] data = new byte[len / 2];

        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4) +
                    Character.digit(hexString.charAt(i + 1), 16));
        }

        return data;
    }

    public String getSearchResult() {
        return searchResult;
    }

    public boolean contains(byte[] fileByteArray) {
        boolean foundInMap = false;
        for (Map.Entry<String, byte[]> pair : signatureMap.entrySet()) {
            byte[] signatureBytes = pair.getValue();
            if (Arrays.equals(signatureBytes, fileByteArray)) {
                foundInMap = true;
                searchResult = pair.getKey();
            }
        }
        return foundInMap;
    }
}

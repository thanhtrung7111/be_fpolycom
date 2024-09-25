package service;

import java.util.Base64;

public class Test {
    public static void main(String[] args) {
        String originalInput = "Hello, World!";

        // Mã hóa chuỗi
        String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());

        System.out.println("Encoded String: " + encodedString);

        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        String decodedString = new String(decodedBytes);
        System.out.println("Decoded String: " + decodedString);
    }
}

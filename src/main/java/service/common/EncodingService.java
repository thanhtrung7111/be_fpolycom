package service.common;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class EncodingService {

    public String encode(String input) {
        return Base64.getEncoder().encodeToString(input.getBytes());
    }

    public String decode(String encodedInput) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedInput);
        return new String(decodedBytes);
    }

}

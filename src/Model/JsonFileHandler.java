package Model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.FileTime;

public class JsonFileHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void writeRequest(Account account) throws IOException {
        objectMapper.writeValue(Variables.request, account);
    }

    public boolean waitForResponse() throws IOException, InterruptedException {
        while (true) {
            Thread.sleep(100);
            String content = new String(Files.readAllBytes(Variables.response.toPath()));
            if (!content.isEmpty()) {
                return objectMapper.readValue(content, boolean.class);
            }
        }
    }
}
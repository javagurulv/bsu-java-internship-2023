package lv.javaguru.travel.insurance.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Component
public class JsonFileReader {
    private  ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

    public String readJsonFile(String filePath) {
        try {
            File file = ResourceUtils.getFile("classpath:" + filePath);
            byte[] jsonData = Files.readAllBytes(file.toPath());
            return objectMapper.readTree(jsonData).toString();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}

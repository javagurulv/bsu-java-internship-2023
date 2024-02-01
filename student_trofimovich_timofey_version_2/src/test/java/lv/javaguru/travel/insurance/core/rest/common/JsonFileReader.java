package lv.javaguru.travel.insurance.core.rest.common;


import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


@Component
public class JsonFileReader {
    public String readJsonFromFile(String path) {
        try {
            File file = new ClassPathResource(path).getFile();
            return Files.readString(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}

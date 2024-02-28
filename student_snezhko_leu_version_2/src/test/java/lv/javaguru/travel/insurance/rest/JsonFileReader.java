package lv.javaguru.travel.insurance.rest;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

@Component
public class JsonFileReader {
    public String readJsonFromFile(String path) throws FileNotFoundException, IOException {
        File file = ResourceUtils.getFile("classpath:" + path);
        String result = new String(Files.readAllBytes(file.toPath()));
        return result;
    }
}

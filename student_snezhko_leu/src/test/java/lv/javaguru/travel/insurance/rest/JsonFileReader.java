package lv.javaguru.travel.insurance.rest;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
@Component
public class JsonFileReader {
    public String readJsonFile(String path) throws FileNotFoundException, IOException {
        File file = ResourceUtils.getFile("classpath:" + path);
        String result = new String(Files.readAllBytes(file.toPath()));
        return result;
    }
}

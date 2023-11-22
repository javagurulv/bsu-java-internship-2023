package lv.javaguru.travel.insurance.rest;

import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonFileReader {
    public static String readJsonFile(String path) throws FileNotFoundException, IOException {
        File file = ResourceUtils.getFile("classpath:" + path);
        String result = new String(Files.readAllBytes(file.toPath()));
        return result;
    }
}

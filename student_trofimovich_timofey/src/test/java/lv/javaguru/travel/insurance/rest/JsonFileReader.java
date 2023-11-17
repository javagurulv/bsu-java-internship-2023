package lv.javaguru.travel.insurance.rest;

import com.fasterxml.jackson.core.JsonParser;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class JsonFileReader {
    public String readJsonFromFile(String path) {
            try {
                File file = new ClassPathResource(path).getFile();
                return Files.readString(file.toPath());
            }
          catch (IOException e) {
            throw new RuntimeException();
        }
    }
}

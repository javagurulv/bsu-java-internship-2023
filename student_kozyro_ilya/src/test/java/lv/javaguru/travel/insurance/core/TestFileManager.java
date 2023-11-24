package lv.javaguru.travel.insurance.core;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Component
public class TestFileManager {

    public String openFile(String filePath) throws IOException {
        File file = ResourceUtils.getFile("classpath:" + filePath);
        return new String(Files.readAllBytes(file.toPath()));

    }
}

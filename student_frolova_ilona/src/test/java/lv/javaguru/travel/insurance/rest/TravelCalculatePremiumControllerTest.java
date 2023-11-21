package lv.javaguru.travel.insurance.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void simpleRestControllerTestExample() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content("{" +
                                "\"personFirstName\" : \"Vasja\",\n" +
                                "\"personLastName\" : \"Pupkin\",\n" +
                                "\"agreementDateFrom\" : \"2021-05-25\",\n" +
                                "\"agreementDateTo\" : \"2021-05-29\"\n" +
                                "}")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is("Vasja")))
                .andExpect(jsonPath("personLastName", is("Pupkin")))
                .andExpect(jsonPath("agreementDateFrom", is("2021-05-25")))
                .andExpect(jsonPath("agreementDateTo", is("2021-05-29")))
                .andExpect(jsonPath("agreementPrice", is(4)))
                .andReturn();
    }

    @Test
    public void simpleRestControllerTest() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content("{" +
                                "\"personFirstName\" : \"Name\",\n" +
                                "\"personLastName\" : \"Surname\",\n" +
                                "\"agreementDateFrom\" : \"2021-05-20\",\n" +
                                "\"agreementDateTo\" : \"2021-05-29\"\n" +
                                "}")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is("Name")))
                .andExpect(jsonPath("personLastName", is("Surname")))
                .andExpect(jsonPath("agreementDateFrom", is("2021-05-20")))
                .andExpect(jsonPath("agreementDateTo", is("2021-05-29")))
                .andExpect(jsonPath("agreementPrice", is(9)))
                .andReturn();
    }

    @Test
    public void JsonReaderTest() throws IOException {

        String fileName = "temp.json";
        File file = new File(fileName);

        if (!file.exists()) {
            file.createNewFile();
        }

        String initial = "smth\nsmth2";

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(initial);
        writer.close();

        String result = JsonReader.read("temp.json");

        file.delete();

        assertEquals(initial, result);
    }

    @Test
    public void correctResponseToRequestInJsonFiles() throws IOException {

        File requestFile = new File("request.json");
        File responseFile = new File("res.json");

        /*if (!file.exists()) {
            file.createNewFile();
        }

        String initial = "smth\nsmth2";

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(initial);
        writer.close();

        String result = JsonReader.read("temp.json");

        file.delete();

        assertEquals(initial, result);*/
    }
}

package lv.javaguru.travel.insurance.load;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.javaguru.travel.insurance.load.Calling;
import org.hibernate.dialect.Sybase11Dialect;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Call extends Calling implements Runnable{
    private SynchroLoad load;

    @Override
    public void run() {
        try {
            Long executionTime = sendRequest("rest/v2/test_case_1",
                    "http://localHost:8080/insurance/travel/api/v2/");
            load.add(executionTime);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

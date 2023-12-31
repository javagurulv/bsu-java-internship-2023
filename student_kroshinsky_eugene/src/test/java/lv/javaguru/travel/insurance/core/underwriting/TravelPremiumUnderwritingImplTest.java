package lv.javaguru.travel.insurance.core.underwriting;
import lv.javaguru.travel.insurance.core.utils.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class TravelPremiumUnderwritingImplTest {
    @Mock TravelCalculatePremiumRequest request;
    @Mock private DateTimeUtil dateDifferenceService;
    @InjectMocks private TravelPremiumUnderwritingImpl underwriting;
    @Test
    public void injectedRepositoryAreNotNull() {
        assertNotNull(dateDifferenceService);
        assertNotNull(request);
        assertNotNull(underwriting);
    }
    @Test
    void calculatePremiumTest(){
        when(request.getAgreementDateFrom()).thenReturn(createDate("18.11.2023"));
        when(request.getAgreementDateTo()).thenReturn(createDate("24.11.2023"));
        when(dateDifferenceService.calculateDateDifference(
                request.getAgreementDateFrom(), request.getAgreementDateTo())).thenReturn(new BigDecimal(6));
        assertEquals(new BigDecimal(6), underwriting.calculatePremium(request));
    }
    private Date createDate(String s) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(s);
        } catch (ParseException e) {
            throw new RuntimeException();
        }
    }
}

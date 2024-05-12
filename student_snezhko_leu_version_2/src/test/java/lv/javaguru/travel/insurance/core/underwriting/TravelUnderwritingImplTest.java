package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static lv.javaguru.travel.insurance.core.api.dto.AgreementDTOBuilder.createAgreementDTO;
import static lv.javaguru.travel.insurance.core.validations.calculate.integration.CreateDateUtil.createDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TravelUnderwritingImplTest {
        @Mock
        SelectedRisksPremiumCalculator calculator;

        @Mock
        private AgreementDTO agreement;

        @Mock
        private PersonDTO person;

    @InjectMocks
    TravelUnderwriting underwriting = new TravelUnderwritingImpl();

        @Test
        public void calculatePremiumTest() {

            agreement = createAgreementDTO().withDateFrom(createDate("2022-09-11"))
                    .withDateTo(createDate("2022-09-12"))
                    .withSelectedRisks("TRAVEL_MEDICAL")
                    .build();
/*
            List<String> risks = new ArrayList<>();
            risks.add("TRAVEL_MEDICAL");


            when(agreement.getAgreementDateTo()).thenReturn(Date.valueOf("2022-09-12"));
            when(agreement.getAgreementDateFrom()).thenReturn(Date.valueOf("2022-09-11"));
            when(agreement.getSelectedRisks()).thenReturn(risks);
*/
            List<RiskDTO> risksPremium = new ArrayList<>();
            risksPremium.add(new RiskDTO("TRAVEL_MEDICAL", BigDecimal.valueOf(1)));
            when(calculator.calculatePremiumForAllRisks(agreement, person)).thenReturn(risksPremium);

            assertEquals(BigDecimal.valueOf(1),underwriting.calculatePremium(agreement, person).getTotalPremium());
            assertEquals(risksPremium.get(0).getRiskIc(),underwriting.calculatePremium(agreement, person).getRisks().get(0).getRiskIc());
            assertEquals(risksPremium.get(0).getPremium(),underwriting.calculatePremium(agreement, person).getRisks().get(0).getPremium());
        }
    }

//package lv.javaguru.travel.insurance.core;
//
//import org.junit.jupiter.api.Test;
//
//import java.util.Date;
//import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
//import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//class TravelCalculatePremiumServiceImplTest {
//
//    @Test
//    public void TravelCalculatePremiumServiceImplTest() {
//        Date dateTo = new Date(2005, 2, 15);
//        Date dateFrom = new Date(2001, 1, 1);
//        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Anton",
//                "Koktysh", dateFrom, dateTo);
//
//        TravelCalculatePremiumServiceImpl travelCalculatePremiumService = new TravelCalculatePremiumServiceImpl();
//        travelCalculatePremiumService.calculatePremium(request);
//
//        assertThat(request.getPersonLastName()).isEqualTo("Koktysh");
//        assertThat(request.getPersonFirstName()).isEqualTo("Anton");
//        assertThat(request.getAgreementDateTo()).isEqualTo(dateTo);
//        assertThat(request.getAgreementDateFrom()).isEqualTo(dateFrom);
//    }
//
//}
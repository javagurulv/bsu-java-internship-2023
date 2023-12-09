package lv.javaguru.travel.insurance.core.api.dto;

public class AgreementBuildDto {

    AgreementDto agreementDto = new AgreementDto();
    public AgreementDto build() {
        return AgreementDto.builder()
                .agreementDateFrom(agreementDto.getAgreementDateFrom())
                .agreementDateTo(agreementDto.getAgreementDateTo())
                .country(agreementDto.getCountry())
                .selectedRisks(agreementDto.getSelectedRisks())
                .persons(agreementDto.getPersons())
                .agreementPremium(agreementDto.getAgreementPremium())
                .build();
    }
}

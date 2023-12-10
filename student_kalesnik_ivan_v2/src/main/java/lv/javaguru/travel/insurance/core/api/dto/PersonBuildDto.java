package lv.javaguru.travel.insurance.core.api.dto;

public class PersonBuildDto {
    PersonDto personDto = new PersonDto();

    public PersonDto build() {
        return PersonDto.builder()
                .personFirstName(personDto.getPersonFirstName())
                .personLastName(personDto.getPersonLastName())
                .personCode(personDto.getPersonCode())
                .personBirthDate(personDto.getPersonBirthDate())
                .medicalRiskLimitLevel(personDto.getMedicalRiskLimitLevel())
                .risks(personDto.getRisks())
                .build();
    }
}

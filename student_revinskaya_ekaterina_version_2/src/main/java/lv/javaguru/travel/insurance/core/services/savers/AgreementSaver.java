package lv.javaguru.travel.insurance.core.services.savers;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.Agreement;
import lv.javaguru.travel.insurance.core.domain.Person;
import lv.javaguru.travel.insurance.core.domain.PersonAgreement;
import lv.javaguru.travel.insurance.core.domain.SelectedRisk;
import lv.javaguru.travel.insurance.core.repositories.AgreementRepository;
import lv.javaguru.travel.insurance.core.repositories.PersonAgreementRepository;
import lv.javaguru.travel.insurance.core.repositories.SelectedRiskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AgreementSaver {
    @Autowired
    PersonSaver personSaver;
    @Autowired
    AgreementRepository agreementRepository;
    @Autowired
    SelectedRiskRepository selectedRiskRepository;
    @Autowired
    PersonAgreementRepository personAgreementRepository;
    public void saveAgreements(AgreementDTO agreementDTO) {
        Agreement agr = convertToAgreement(agreementDTO);
        Agreement agreement = saveAgreement(agr);
        agreementDTO.getPersons()
                .forEach(personDTO -> savePersonWithAgreement(personDTO, agreement));
        saveRisks(agreementDTO, agreement);
    }
    private void savePersonWithAgreement(PersonDTO personDTO, Agreement agreement){
        Person person = personSaver.saveNotAlreadyExistPerson(personDTO);
        saveNotExistPersonAgreement(convertToPersonAgreement(person, agreement, personDTO));
    }
    private PersonAgreement convertToPersonAgreement(Person person, Agreement agreement, PersonDTO personDTO){
        PersonAgreement personAgreement = new PersonAgreement();
        personAgreement.setPersonId(person);
        personAgreement.setAgreementId(agreement);
        personAgreement.setMedicalRiskLimitLevel(personDTO.getMedicalRiskLimitLevel());
        return personAgreement;
    }
    private Agreement convertToAgreement(AgreementDTO agreementDTO) {
        Agreement agreement = new Agreement();
        agreement.setDateFrom(agreementDTO.getAgreementDateFrom());
        agreement.setDateTo(agreementDTO.getAgreementDateTo());
        agreement.setCountry(agreementDTO.getCountry());
        agreement.setPremium(agreementDTO.getAgreementPremium());
        return agreement;
    }
private void saveNotExistPersonAgreement(PersonAgreement personAgreement){
        if(personAgreementRepository.findByPersonIdAndAgreementId(personAgreement.getPersonId(), personAgreement.getAgreementId()).isEmpty()){
            personAgreementRepository.save(personAgreement);
        }
}
    private Agreement saveAgreement(Agreement agreement) {
            return agreementRepository.save(agreement);
    }
    private SelectedRisk convertToSelectedRisk(String riskIc, Agreement agreement) {
        SelectedRisk selectedRisk = new SelectedRisk();
        selectedRisk.setAgreementId(agreement);
        selectedRisk.setRiskIc(riskIc);
        return selectedRisk;
    }

    private void saveNotExistRisk(String riskIc, Agreement agreement) {
        SelectedRisk selectedRisk = convertToSelectedRisk(riskIc, agreement);
        if(notExist(selectedRisk)) {
            selectedRiskRepository.save(selectedRisk);
        }
    }
    private void saveRisks(AgreementDTO agreementDTO, Agreement agreement) {
        agreementDTO.getSelectedRisks().stream()
                .forEach(risk -> saveNotExistRisk(risk, agreement));

    }
    private boolean notExist(SelectedRisk selectedRisk) {
        return selectedRiskRepository.findByAgreementIdAndRiskIc(selectedRisk.getAgreementId(), selectedRisk.getRiskIc())
                .isEmpty();
    }
}

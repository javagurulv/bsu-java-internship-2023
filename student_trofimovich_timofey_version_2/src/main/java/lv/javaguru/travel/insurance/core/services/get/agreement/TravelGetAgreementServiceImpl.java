package lv.javaguru.travel.insurance.core.services.get.agreement;

import lv.javaguru.travel.insurance.core.api.command.get.agreement.TravelGetAgreementCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.get.agreement.TravelGetAgreementCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.person.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.risk.RiskDTO;
import lv.javaguru.travel.insurance.core.domain.entities.AgreementEntity;
import lv.javaguru.travel.insurance.core.domain.entities.AgreementPersonEntity;
import lv.javaguru.travel.insurance.core.domain.entities.AgreementPersonRiskEntity;
import lv.javaguru.travel.insurance.core.domain.entities.PersonEntity;
import lv.javaguru.travel.insurance.core.repositories.AgreementEntityRepository;
import lv.javaguru.travel.insurance.core.repositories.AgreementPersonEntityRepository;
import lv.javaguru.travel.insurance.core.repositories.AgreementPersonRiskEntityRepository;
import lv.javaguru.travel.insurance.core.validations.get.agreement.TravelGetAgreementUuidValidator;
import lv.javaguru.travel.insurance.dto.common.EntitiesToDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TravelGetAgreementServiceImpl implements TravelGetAgreementService {

    @Autowired
    private AgreementEntityRepository agreementEntityRepository;
    @Autowired
    private AgreementPersonEntityRepository agreementPersonEntityRepository;
    @Autowired
    private AgreementPersonRiskEntityRepository agreementPersonRiskEntityRepository;
    @Autowired
    private TravelGetAgreementUuidValidator validator;
    @Autowired
    private EntitiesToDtoConverter converter;

    @Override
    public TravelGetAgreementCoreResult getAgreement(TravelGetAgreementCoreCommand command) {
        Optional<ValidationErrorDTO> error = validator.validate(command.getUuid());
        return error.map(this::buildEmptyCoreResult)
                .orElseGet(() -> buildCoreResult(findAgreementEntityInRepository(command)));
    }

    private AgreementEntity findAgreementEntityInRepository(TravelGetAgreementCoreCommand command) {
        return agreementEntityRepository.findByUuid(command.getUuid()).get();
    }

    private TravelGetAgreementCoreResult buildEmptyCoreResult(ValidationErrorDTO error) {
        return new TravelGetAgreementCoreResult(Optional.of(error));
    }

    private TravelGetAgreementCoreResult buildCoreResult(AgreementEntity agreementEntity) {
        TravelGetAgreementCoreResult result = new TravelGetAgreementCoreResult();
        AgreementDTO agreementDTO = converter.transformAgreementEntity(agreementEntity);
        findPersonsForAgreement(agreementDTO);
        result.setAgreement(agreementDTO);
        result.setError(Optional.empty());
        return result;
    }

    private void findPersonsForAgreement(AgreementDTO agreementDTO) {

        List<AgreementPersonEntity> agreementPersonEntities = agreementPersonEntityRepository.findByAgreement_Uuid(agreementDTO.getUuid());
        List<PersonDTO> persons = new ArrayList<>();
        agreementPersonEntities.forEach(
                agreementPersonEntity -> {
                    PersonEntity personEntity = agreementPersonEntity.getPerson();
                    PersonDTO personDTO = converter.transformPersonEntity(personEntity, agreementPersonEntity);
                    addMedicalRiskLimitLevel(personDTO, agreementPersonEntity);
                    addPersonRisks(agreementPersonEntity, personDTO);
                    persons.add(personDTO);
                }
        );
        /*List<PersonEntity> personEntities = agreementPersonEntities.stream().map(AgreementPersonEntity::getPerson).toList();
        List<PersonDTO> personDTOS = personEntities.stream().map(converter::transformPersonEntity).toList();
        addMedicalRiskLimitLevel(personDTOS, agreementPersonEntities);
        addPersonRisks(agreementPersonEntities, personDTOS);*/
        agreementDTO.setPersons(persons);
    }

    private void addPersonRisks(AgreementPersonEntity agreementPersonEntity, PersonDTO personDTO) {

            List<AgreementPersonRiskEntity> agreementPersonRiskEntities = agreementPersonRiskEntityRepository
                    .findByAgreementAndPersonUniqueInfo(personDTO.getPersonUUID(), personDTO.getPersonFirstName(),
                            personDTO.getPersonLastName(), agreementPersonEntity.getId());
            List<RiskDTO> riskDTOS = agreementPersonRiskEntities.stream().map(converter::transformRiskEntity).toList();
            personDTO.setSelectedRisks(riskDTOS);

    }



    private void addMedicalRiskLimitLevel(PersonDTO personDTO, AgreementPersonEntity agreementPersonEntity) {
            personDTO.setMedicalRiskLimitLevel(agreementPersonEntity.getMedicalRiskLimitLevel());
    }


}

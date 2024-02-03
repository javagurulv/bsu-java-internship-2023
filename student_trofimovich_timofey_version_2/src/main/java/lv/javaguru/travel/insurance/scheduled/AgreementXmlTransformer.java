package lv.javaguru.travel.insurance.scheduled;


import lv.javaguru.travel.insurance.core.api.command.get.agreement.TravelGetAgreementCoreCommand;
import lv.javaguru.travel.insurance.core.api.command.get.agreement.TravelGetAgreementCoreResult;
import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.core.domain.entities.AgreementEntity;
import lv.javaguru.travel.insurance.core.repositories.AgreementEntityRepository;
import lv.javaguru.travel.insurance.core.services.get.agreement.TravelGetAgreementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component
public class AgreementXmlTransformer {

    @Autowired
    private AgreementEntityRepository repository;
    @Autowired
    private TravelGetAgreementServiceImpl service;

    @Scheduled(fixedRateString = "P1D")
    public void writeXml() throws JAXBException, IOException {

        List<AgreementEntity> agreementEntities = repository.findAll();
        List<AgreementDTO> agreementsList = agreementEntities.stream()
                .limit(10)
                .map(agr -> {
                    TravelGetAgreementCoreResult coreResult = service.getAgreement(
                            new TravelGetAgreementCoreCommand(String.valueOf(agr.getUuid())));
                    return coreResult.getAgreement();

                })
                .toList();
        JAXBContext jaxbContext = JAXBContext.newInstance( Agreements.class );
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
        Agreements agreements = new Agreements();
        agreements.setAgreements(agreementsList);
        jaxbMarshaller.marshal( agreements, new FileWriter("src/main/resources/agreements.xml"));
    }

}

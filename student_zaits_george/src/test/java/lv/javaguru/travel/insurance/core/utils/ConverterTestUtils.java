package lv.javaguru.travel.insurance.core.utils;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.api.RandomDataProviderStrategyImpl;

public class ConverterTestUtils {

    private static final int MAX_DEPTH = 1;
    private static final int NUMBER_COLLECTION_ELEMENT = 1;

    private final static PodamFactory podamFactory;

    private ConverterTestUtils() {
    }

    static {
        var randomDataProviderStrategy = new RandomDataProviderStrategyImpl();
        randomDataProviderStrategy.setMaxDepth(MAX_DEPTH);
        randomDataProviderStrategy.setDefaultNumberOfCollectionElements(NUMBER_COLLECTION_ELEMENT);
        podamFactory = new PodamFactoryImpl(randomDataProviderStrategy);
    }

    public static <T> T buildPojo(Class<T> pojoClass) {
        return podamFactory.manufacturePojo(pojoClass);
    }

}
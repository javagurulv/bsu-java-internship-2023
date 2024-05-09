package lv.javaguru.travel.insurance.rest;

public class RemoveRandomValues {
    public static String removeRandomValues(String jsonContent) {
        return removeAgreementUUID(removePersonIc(jsonContent));
    }
    public static String removeAgreementUUID(String jsonContent) {
        return jsonContent.replaceAll("\"uuid\":\".{36}\"", "\"uuid\" : null");
    }

    public static String removePersonIc(String jsonContent) {
        return jsonContent.replaceAll("\"personIc\":\".{36}\",", "\"personIc\" : null,");
    }
}

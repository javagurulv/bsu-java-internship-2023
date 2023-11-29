package lv.javaguru.travel.insurance.loadtesting;

class RestCallExample {

    public static void main(String[] args) {
        for (int i = 1; i <= 50; i++) {
            Thread v1Call = new Thread(new V1Call());
            Thread v2Call = new Thread(new V2Call());
            v1Call.start();
            v2Call.start();
        }
    }

}

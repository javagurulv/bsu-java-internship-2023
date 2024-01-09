package lv.javaguru.travel.insurance.loadtesting;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class RestCallExample {
    public static void main(String[] args) {
        ArrayList<Thread> threads = new ArrayList<>(100);
        for (int i = 0; i < 50; i++) {
            threads.add(new Thread(new V1Call()));
            threads.add(new Thread(new V2Call()));
        }

        for (var thread : threads) {
            thread.start();
        }
    }
}

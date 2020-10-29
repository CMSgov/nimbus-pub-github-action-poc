package com.sample;

import java.sql.SQLException;
import java.util.List;

public class App {
    private boolean isAutoSyncScheduled = true;

    public static void main(String[] args) throws SQLException {
        SQLInjectionSample c = new SQLInjectionSample();
        List<AccountDTO> accountDTOS = c.unsafeFind("1");
        System.out.println(accountDTOS);


        ComplexityCode cc = new ComplexityCode();
        cc.hello();


        LetsDivideByZero zero = new LetsDivideByZero();
        zero.getAmount();

        App p = new App();
        p.waitIfAutoSyncScheduled();
    }

    synchronized void waitIfAutoSyncScheduled() {
        try {
            while (isAutoSyncScheduled) {
                this.wait(1000);
            }
        } catch (InterruptedException e) {
            // Expected exception. The file cannot be synchronized yet.
        }
    }

}

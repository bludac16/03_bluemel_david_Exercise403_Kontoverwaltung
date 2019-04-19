/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Benutzer;

import BL.Konto;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class KontoBenutzer extends Thread {

    private Konto konto;
    Random rand = new Random();

    public KontoBenutzer(Konto konto) {
        this.konto = konto;
    }

    @Override
    public void run() {
        while (true) {
            int amount = 10 + rand.nextInt(40);
            if (1 + rand.nextInt(1) == 1) {
                synchronized (konto) {
                    if ((konto.getBalance() - amount) >= 0) {
                        konto.withdraw(amount);
                        konto.notifyAll();
                        System.out.format("%s makes withdraw: %d\n", Thread.currentThread().getName(), amount);
                    } else {
                        try {
                            konto.wait();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(KontoBenutzer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

            } else {
                synchronized (konto) {
                    konto.deposit(amount);
                    konto.notifyAll();
                    System.out.format("%s makes deposit: %d\n", Thread.currentThread().getName(), amount);
                }
            }
            try {
                Thread.sleep(1 + rand.nextInt(999));
            } catch (InterruptedException ex) {
                Logger.getLogger(KontoBenutzer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Benutzer;

import BL.Konto;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author David
 */
public class KontoBenutzer extends Thread {

    private Konto konto;
    Random rand = new Random();
    Lock transferLock = new ReentrantLock();

    public KontoBenutzer(Konto konto) {
        this.konto = konto;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {

            int amount = 10 + rand.nextInt(40);
            if (1 + rand.nextInt(1) == 1) {
                transferLock.lock();
                try {
                    if ((konto.getBalance() - amount) >= 0) {
                        konto.withdraw(amount);
                        
                        System.out.format("%s makes withdraw: %d\n", Thread.currentThread().getName(), amount);
                    } 
                } finally {
                    transferLock.unlock();
                }

            } else {
                transferLock.lock();
                try {
                    konto.deposit(amount);
                    
                    System.out.format("%s makes deposit: %d\n", Thread.currentThread().getName(), amount);

                    try {
                        Thread.sleep(1 + rand.nextInt(999));
                    } catch (InterruptedException ex) {
                        
                    }
                } finally {
                    transferLock.unlock();
                }
            }
        }
    }

    public Konto getKonto() {
        return konto;
    }

}

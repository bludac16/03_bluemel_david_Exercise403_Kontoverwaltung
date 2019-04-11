/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Benutzer;

import BL.Konto;

/**
 *
 * @author David
 */
public class KontoBenutzer extends Thread{
    private Konto konto;

    public KontoBenutzer(Konto konto) {
        this.konto = konto;
    }

    @Override
    public void run() {
        
    }
    
    
}

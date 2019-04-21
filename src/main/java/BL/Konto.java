/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

/**
 *
 * @author David
 */
public class Konto {
    private int balance = 50;
    

    public int getBalance() {
        return balance;
    }
    
    public void withdraw(int amount){
        balance -= amount;
    }
    
    public void deposit(int amount){
        balance += amount;
    }
}

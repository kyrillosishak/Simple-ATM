/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.atm;

import GUI.Deposite;
import GUI.Withdraw;

/**
 *
 * @author kerog
 */
public interface ATM {    
        //Returnsthe current balance in string format 
    public String getCurrentBalance();  
        //withdraws from current balance and updates it 
    public boolean withdraw(String amount , Withdraw u ); 
        //adds to the current balance and updates it 
    public void deposit(String amount , Deposite u); 
        //returns the prevtransaction in String format, or Null if no more history 
    public Object prev(); 
        //returns the next transaction in String format, or Null if no morehistory 
    public Object next(); 

}

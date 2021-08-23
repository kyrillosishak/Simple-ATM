/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.atm;

import GUI.Deposite;
import GUI.Validation;
import GUI.Withdraw;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kerog
 */
public class MyATM implements ATM{
    //Singelton implementation
    private static MyATM s = null;
    DataBase n = DataBase.getInstance();
    public static ArrayList<Object> window = new ArrayList<Object>(); 
    public static int index = window.size() -1;
    
    
    public static MyATM getInstance(){
        if (s == null)
            s = new MyATM();
        return s;
    }
    @Override
    public String getCurrentBalance() {
        return Long.toString(n.getBalance());
    }

    @Override
    public boolean withdraw(String amount, Withdraw u) {
        if (Long.parseLong(amount) <= n.getBalance()){
            long s =  n.getBalance() - Long.parseLong(amount) ;
            try {
                 n.Setter(Long.toString(n.getCardNumber()), Long.toString(n.getBalance()), Long.toString(s));
            } catch (IOException ex) {
                 Logger.getLogger(MyATM.class.getName()).log(Level.SEVERE, null, ex);
            }
            window.add(u);
            index = window.size()-1;
            return true;
        }else
            return false;
    }

    @Override
    public void deposit(String amount, Deposite u) {
        long s =  n.getBalance() + Long.parseLong(amount) ;
        try {
            n.Setter(Long.toString(n.getCardNumber()), Long.toString(n.getBalance()), Long.toString(s));
        } catch (IOException ex) {
            Logger.getLogger(MyATM.class.getName()).log(Level.SEVERE, null, ex);
        }
        window.add(u);
        index = window.size()-1;
    }

    @Override
    public Object prev() {
        Object s = window.get(index);
        index--;
        return s;
    }

    @Override
    public Object next() {
        if (index+1 != window.size()){
            index++;
            Object s = window.get(index);
            return s;
        }else
            return null;
    }
    public static void main(String[] args) {
        Validation v = new Validation();
        v.setVisible(true);
    }
    
}

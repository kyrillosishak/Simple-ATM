/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.atm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author kerog
 */
public class DataBase {
      private long cardNumber ;
      private long password ;
      private long balance;
      private static DataBase ds = null;
    
    public static DataBase getInstance(){
        if (ds == null)
            ds = new DataBase();
        return ds;
    }
    public long getCardNumber() {
        return cardNumber;
    }
    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }
    public long getPassword() {
        return password;
    }
    public void setPassword(int password) {
        this.password = password;
    } 
    public long getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }
    
    
    public void Getter(long cardNumber) throws FileNotFoundException{
        File file = new File("DATA.txt");
        Scanner myReader = new Scanner(file);
        while (myReader.hasNextLine()){
            String data = myReader.nextLine();
            StringTokenizer st = new StringTokenizer(data,",");  
             for (int i = 0 ;st.hasMoreTokens() ; i++) {
                switch (i) {
                    case 0:
                        this.setCardNumber(Integer.parseInt(st.nextToken()));
                        if (this.getCardNumber() != cardNumber)
                            continue;
                        break;
                    case 1:
                        setPassword(Integer.parseInt(st.nextToken()));
                        break;
                    case 2:
                        setBalance(Integer.parseInt(st.nextToken()));
                        break;
                    default:
                        break;
                }
            }   
        }
        System.out.println(balance);
    }
    
    public void Setter (String cardNumber, String oldString, String newString) throws FileNotFoundException, IOException{
        File fileToBeModified = new File("DATA.txt");
        String oldContent = "";
        BufferedReader reader = null;
        FileWriter writer = null;
        try
        {
            reader = new BufferedReader(new FileReader(fileToBeModified));
            String line = reader.readLine();
            StringTokenizer st = new StringTokenizer(line,",");
            if (st.nextToken().equals(cardNumber)){
                line = line.replaceFirst(oldString, newString);
            }
            while (line != null ) 
            {
                oldContent = oldContent + line + System.lineSeparator();
                line = reader.readLine();
                if (st.nextToken().equals(cardNumber)){
                    line = line.replaceFirst(oldString, newString);
                }
            }
            
            writer = new FileWriter(fileToBeModified);
            writer.write(oldContent);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                reader.close();
                writer.close();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
        Getter(this.cardNumber);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fetin_oldhouse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import javafx.collections.ObservableList;

/**
 *
 * @author igorg
 */
public class Fetin_OldHouse {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) 
   {
       
       CommunicationManager communication;
       communication = CommunicationManager.getInstance();
       CheckManager check = CheckManager.getInstance();
       Controller C = new Controller();
       C.criarInicio();
       
       communication.start();
       System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss")));
       
    }
}

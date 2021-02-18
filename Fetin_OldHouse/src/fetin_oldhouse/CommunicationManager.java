/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fetin_oldhouse;

import java.net.*;
import javafx.application.Platform;

public class CommunicationManager {

    public String lastTag;
    DAOCheck daocheck = new DAOCheck();
    public String getLastTag() {
        return lastTag;
    }

    public void setLastTag(String lastTag) {
        this.lastTag = lastTag;
    }
    
    private static CommunicationManager instance;
    public static CommunicationManager getInstance() {
        if(instance == null) {
            instance = new CommunicationManager();
        }
        return instance;
    }
    private CommunicationManager() {
        this.checkManager = CheckManager.getInstance();
        this.lastTag = "Passe o cartão";
        running = false;
    }

    private CheckManager checkManager;
    private boolean running;
    private Thread communication;

    public boolean isRunning() {
        return running;
    }

    public void start() {
        if(!running) {
            communication = new Thread(() -> {
                try {
                    
                    running = true;
                    int port = 6000;
                    byte[] receiveData = new byte[12];
                    byte[] sendData;
                    DatagramSocket serverSocket = new DatagramSocket(port);
                    serverSocket.setSoTimeout(1000);
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    while (running) {
                        System.out.println("Waiting for some packet...");
                        try {
                            serverSocket.receive(receivePacket);
                        } catch (SocketTimeoutException e) {
                            //lastTag = "123456789101";
                            continue;
                        }
                        String receivedString = new String(receivePacket.getData(), 0, receivePacket.getLength());
                        System.out.println("Received: [" + receivedString + "] from: " + receivePacket.getAddress() + ":" + receivePacket.getPort());
                        if(CheckManager.validarTag(receivedString)){
                            
                            lastTag = receivedString;
                            checkManager.saveCheck(lastTag);
                            String sendString;
                            
                            if(checkManager.verificaAutorizacao(receivedString)){
                                sendString = "true";
                            }
                            else {
                                sendString = "false";
                            }
                            sendData = sendString.getBytes();
                            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
                            serverSocket.send(sendPacket);
                            System.out.println("Sent: [" + sendString + "] to: " + receivePacket.getAddress() + ":" + receivePacket.getPort());
                        }
                    }
                    serverSocket.close();
                    //running = false;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            communication.start();
        }
    }

    public void stop() {
        if(running) {
            try {
                running = false;
                communication.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void restart() {
        stop();
        start();
    }

}
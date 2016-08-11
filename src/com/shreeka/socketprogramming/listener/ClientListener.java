/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shreeka.socketprogramming.listener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Decode
 */
public class ClientListener extends Thread {
 private Socket socket;
 private ClientHandler handler;

    public ClientListener(Socket socket, ClientHandler handler) {
        this.socket = socket;
        this.handler = handler;
    }

    

    @Override
    public void run() {
         PrintStream ps;
     try {
         ps = new PrintStream(socket.getOutputStream());
     
            ps.println("Welcome to KECircle chatroom ");
            ps.println("Enter your name: ");
            BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String name=reader.readLine();
            
           ps.println("Welcome "+name+ " to the chatroom.");
            Client client= new Client(socket, name);
            client.setSocket(socket);
            client.setUserName(name);
            handler.addClient(client);
            handler.broadcastMessage(client.getUserName(),client.getUserName()+" has joined the chatroom." );
            while(!isInterrupted()){
                String msg=reader.readLine();
                String[] tokens=msg.split(";;");
                if(tokens[0].equalsIgnoreCase("pm"))
                {
                if(tokens.length>2){
                 handler.privateMessage(tokens[1],"PM from "+client.getUserName()+"> "+tokens[2]);
                }
                }else{
                handler.broadcastMessage(client.getUserName(), client.getUserName()+" says> "+msg);
                }
            }
            } catch (IOException ex) {
         System.out.println(ex.getMessage());
         
     }
    }
 
 
    
}

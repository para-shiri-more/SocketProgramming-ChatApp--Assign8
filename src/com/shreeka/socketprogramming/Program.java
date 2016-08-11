/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shreeka.socketprogramming;

import com.shreeka.socketprogramming.listener.ClientHandler;
import com.shreeka.socketprogramming.listener.ClientListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Decode
 */
public class Program {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
       int port=9000;
        ServerSocket server= new ServerSocket(port);
        System.out.println("Server is running...");
        ClientHandler handler=new ClientHandler();
       
        while(true){
            Socket client=server.accept();
            System.out.println("Connection request from "+ client.getInetAddress().getHostAddress());
           ClientListener listener= new ClientListener(client, handler);
           listener.start();
            
           
           
               
           
        }
    }

   
}

package com.example;

import java.net.ServerSocket;
import java.net.Socket;

public class App 
{
    public static void main( String[] args) throws Exception
    {
        ServerSocket ss = new ServerSocket(6789);
        while(true)
        {
            Socket s = ss.accept();
            Server thread = new Server(s);
            thread.start();
        }
    }
}

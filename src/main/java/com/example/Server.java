package com.example;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server extends Thread{
    Socket client;

    BufferedReader inDalClient;
    DataOutputStream outVersoClient;

    String scelta;
    int qualcosa = 0;

    ArrayList messaggio = new ArrayList<String>();

    public Server (Socket client){
        this.client = client;
    }

    public void run (){
        try{
            inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outVersoClient = new DataOutputStream(client.getOutputStream());

            do{
                scelta = inDalClient.readLine();
                if(scelta.equals("Aggiungi nota")){
                    String nota = inDalClient.readLine();
                    messaggio.add(nota);

                    //Controllo; ricordati di cancellarlo
                    for (int i = 0; i < messaggio.size(); i++){
                        System.out.println(messaggio.get(i));
                    }
                //----------------------------------------------
                    outVersoClient.writeBytes("> Nota Salvata");
                    qualcosa = 1;
                    System.out.println("1");
                }

                else if(scelta.equals("@")){

                    if(messaggio.isEmpty()){
                        outVersoClient.writeBytes("> Devi ancora inserire una nota \n \n \n");
                        System.out.println("2");
                    }
                    else{
                        for (int i = 0; i < messaggio.size(); i++){
                            outVersoClient.writeBytes("> " + messaggio.get(i) + "\n");
                        }
                        System.out.println("3");
                    }
                    
                    qualcosa = 2;
                }

                else if (scelta.equals("ESCI")){
                    qualcosa = 4;
                    System.out.println("app chiusa");
                }

            } while (qualcosa != 4);

            System.out.println("4");
        }

        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server !");
            System.exit(1);
        }
    }
}
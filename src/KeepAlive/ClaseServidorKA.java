package KeepAlive;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


import javax.swing.JTextArea;



public class ClaseServidorKA extends Thread{
	
ServerSocket sk = null;
    
    JTextArea jTextAreaVent;
   
	String NombreCliente;
	boolean BoolCliente=true;
	int Puerto;
   ServidorHiloKA cliente;
    boolean continuar = true;
   // hiloCliente hilclient;
    // constructor y parametro
    public ClaseServidorKA(int Puerto,JTextArea jTextAreaVent) {
      
    	this.Puerto=Puerto;
        this.jTextAreaVent=jTextAreaVent;
       
    }
    
    public void StopServer(){
    	  continuar = false;
    	  try {
			sk.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void run() {
    	
		   try {
		         sk = new ServerSocket(Puerto);
		         System.out.println();  
	        	 System.out.println("**************************************************");
			     System.out.println("*********    Servidor Keeps Alive     ************");
	             System.out.println("*********    IP: "+InetAddress.getLocalHost().getHostAddress()+":"+Puerto+"     **********");
	             System.out.println("**************************************************");
	             System.out.println();
		         
	             jTextAreaVent.setText("************    ServidorKeeps Alive      ***********\n");
	              jTextAreaVent.append("************    IP: "+InetAddress.getLocalHost().getHostAddress()+":"+Puerto+"     **********\n");
	              jTextAreaVent.append("****************************************************\n");
	           
	             while (continuar) { 
		        	//  System.out.println("Esperando cliente: "+continuar);
		        	  Socket socketclient;
		        	  socketclient = sk.accept();// se queda a la espera de un cliente
		           //   System.out.println("Ingreso Cliente");
		             //   ((ServidorHiloKA) new ServidorHiloKA(socketclient,jTextAreaVent)).start();
		                cliente=new ServidorHiloKA(socketclient, jTextAreaVent);
		                cliente.start();
		         }
		   } catch (IOException e) {
		          System.out.println("El Puerto se encuentra ocupado");
		          
		            
		          
		          
		         
		   }
    	
    
    }

}

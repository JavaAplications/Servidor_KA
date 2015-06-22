package KeepAlive;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import javax.swing.JTextArea;

import BBDD.Conexion;
import BufferRx.BufferDeDatos;

public class ClaseReadBufferKA {
	
	 private static final Pattern SPACE = Pattern.compile(" ");
	 int Radiobase,Alarma;
	 String StringAlarma;
	 Conexion con = null;
	
	 String mensaje=null;
	 JTextArea TextAreaVent;
	
	public ClaseReadBufferKA(String mensaje,JTextArea TextAreaVent){
		
		
		this.mensaje=mensaje;
		this.TextAreaVent=TextAreaVent;
	
		
	}
	
	
	public void run(){
		 String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
	      
		 
		String[] arr = SPACE.split(mensaje); // str is the string to be split
		Radiobase=Integer.parseInt(arr[1]);
		
		Alarma=Integer.parseInt(arr[2]);
		// cuando la alarma es '1' es una keep alive.
		// caso contrario es una alarma de algun tipo.
		 con=new Conexion();
		String nombre=con.ConsultarNombre(Radiobase);
		 TextAreaVent.append(timeStamp+"   |    "+nombre+" \n");
			
		
		
int Alarma2=1;
//		System.out.println("Radiobase: "+nombre);
		
		
	if (Alarma==Alarma2){	
		BufferDeDatos.bufferDatosIndividual=arr[1];
		
	}   else{System.out.println("Keep Alive NO escrito");}
	
	 con.Desconectar();
	timeStamp=null;
	nombre=null;
}

}

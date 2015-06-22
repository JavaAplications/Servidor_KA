import BufferRx.BufferDeDatos;
import LimpiarRam.Garbage;
import Ventanas.ventana_Principal;


public class ServidorKA {

	
	static ventana_Principal mainFrame;
	//static int Puerto;
	
	
	static BufferDeDatos HiloBuffer;
	 public static void main(String args[]) {
		
		 mainFrame=new ventana_Principal();
		 mainFrame.setVisible(true);
	//	 Puerto=Integer.parseInt(mainFrame.editPuertoKA.getText().toString());
Garbage g=new Garbage();
	 
	 }
}

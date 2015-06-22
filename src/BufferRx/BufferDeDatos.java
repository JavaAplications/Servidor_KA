package BufferRx;

import Ventanas.ventana_Principal;
import BBDD.Conexion;


public class BufferDeDatos extends Thread{
	int LongitudBuffer=0;
	int PosicionBuffer=0;
	int i=0;
	boolean detener=true;
	static public String bufferDatosIndividual="-";
	static public String[] bufferDatosGrupal;
	//static public String[] bufferDatosGrupal2=new String[5000];
	static public boolean finBufferBool=true;
	
	Conexion con;
	
	public BufferDeDatos(){
	}

	public void parar(){
		detener=false;
		
	}
	
	public void run(){
	//	int LongitudBuffer=0;
		System.out.println("Comenzo  Carga de  de Buffer");
		
	while(detener)	{
		
		LongitudBuffer=0;
		PosicionBuffer=0;
		bufferDatosIndividual="-";
		bufferDatosGrupal=new String[5000];
	
		do{
			if(PosicionBuffer==4999){PosicionBuffer=0;	System.out.println("fin buffer");}
	
			do{if (!detener)break;
			}while(bufferDatosIndividual.equals("-"));
		
			bufferDatosGrupal[PosicionBuffer]=bufferDatosIndividual;
			System.out.print(".");
			LongitudBuffer=PosicionBuffer;
			bufferDatosIndividual="-";
			if (!detener)break;
			PosicionBuffer++;
		}while(finBufferBool);
		
		System.out.println("");
		System.out.println(PosicionBuffer);
		ventana_Principal.lbl_numKeeps.setText(String.valueOf(PosicionBuffer));
			
		finBufferBool=true;
		
		//// BBDD///////////////////////////	
		if (LongitudBuffer!=0){
		    HiloWriteBufferKA GrabarKAyONline=new HiloWriteBufferKA(bufferDatosGrupal,LongitudBuffer);
			GrabarKAyONline.start();
			GrabarKAyONline=null;
			}
		bufferDatosGrupal=null;
		
	}
	
	System.out.println("Finalizo Carga de buffer");
	}
	
	
}

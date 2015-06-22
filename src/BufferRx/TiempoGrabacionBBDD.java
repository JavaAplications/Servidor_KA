package BufferRx;

import LimpiarRam.Garbage;


public class TiempoGrabacionBBDD extends Thread {
	
int Tiempo;
boolean stop=true;

   public TiempoGrabacionBBDD(int Tiempo){
	
	this.Tiempo=Tiempo;
	
}
	
public void detener(){
	stop=false;
	
	
}

public void run(){
	// System.out.println("tiempo Run");
	while(stop){
		Garbage g=new Garbage();
		
	try {
		Thread.sleep(Tiempo*1000);
		g=null;
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
//	 System.out.println("Tiempo");	
	 
	 BufferDeDatos.finBufferBool=false;
	
	
	}
	
//	 System.out.println("Tiempo detenido");
}
}

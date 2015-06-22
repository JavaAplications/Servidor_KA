package BufferRx;

import BBDD.Conexion;

public class HiloWriteBufferKA extends Thread{
	Conexion con;
	String[] buffer;
	int LongitudBuffer;
	public HiloWriteBufferKA(String[] buffer,int LongitudBuffer){
		this.buffer=buffer;
		this.LongitudBuffer=LongitudBuffer;
		
	} 
	
	public void run(){
		con=new Conexion();
		//con.Conectar("HiloWriteBufferKA");
		con.InsertarBufferKA(buffer,LongitudBuffer);
		//con.Desconectar();
	
	}

}

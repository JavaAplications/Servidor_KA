package BBDD;

import java.sql.*;

public class Conexion {
	Connection con=null;
public Conexion() {
		
	}

public  Connection Conectar(String nombre){
	
	
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
		con= DriverManager.getConnection("jdbc:mysql://localhost/bdradiobases","root","");	
		
	} catch (Exception e) {
		System.out.println("No se pudo conectar:' "+nombre+" ' a la BBDD");
	}
	return con;
	
}

public void InsertarBufferKA(String[] buffer,int LongitudBuffer){

	int IdRadio = 0;
	int i=0;
	int radiobases=0;
	con=Conectar("InsertarBufferKA");
	String ComandoSQL="(?)";
	PreparedStatement pst;
	
	for(i=0;i<LongitudBuffer;i++){
		
		
		IdRadio=Integer.parseInt(buffer[i]);
		
	//	System.out.print("IdRadio:"+IdRadio);
		radiobases++;
		
	}
	
//	System.out.println();
	
	try {
		for(i=1;i<LongitudBuffer;i++){
			
			
			ComandoSQL=ComandoSQL+",(?)";
		}
		pst = con.prepareStatement("INSERT INTO keepalive (IdRadios) VALUES "+ComandoSQL);
		for(i=1;i<LongitudBuffer+1;i++){
		pst.setInt(i,Integer.parseInt(buffer[i-1]));
	
		}
		pst.execute();
		con.close();
		
	//	InsertarRadiosOnline();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

public void InsertarEventos(int IdRadio,int IdAlarma){
	
	con=Conectar("InsertarEventos");
	
		PreparedStatement pst;
		try {
			pst = con.prepareStatement("INSERT INTO eventos (IdRadios,IdAlarmas) VALUES (?,?)");
		
			pst.setInt(1,IdRadio);
			pst.setInt(2,IdAlarma);
			
			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	

}

public void InsertarKeepAlive(int IdRadio){
	
	con=Conectar("InsertarKeepAlive");
	
		PreparedStatement pst;
		try {
			pst = con.prepareStatement("INSERT INTO keepalive (IdRadios) VALUES (?)");
		
			pst.setInt(1,IdRadio);
		
			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}



public void InsertarStatus(int IdRadio,String NivelBat,String VoltBat, String StatusBat,String TempBat,String SaludBat,String Gps){
	
	con=Conectar("InsertarStatus");
	
		PreparedStatement pst;
		try {
			pst = con.prepareStatement("INSERT INTO status (IdRadios,NivelBat,VoltBat,StatusBat,TempBat,SaludBat, Gps) VALUES (?,?,?,?,?,?,?)");
		
			pst.setInt(1,IdRadio);
			pst.setString(2, NivelBat);
			pst.setString(3, VoltBat);
			pst.setString(4, StatusBat);
			pst.setString(5, TempBat);
			pst.setString(6, SaludBat);
			pst.setString(7, Gps);
		
			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}


public void InsertarRadiobases(String NomRadio,String TelRadio,String LatRadio,String LongRadio,
		String LocRadio,String ProvRadio,String ContacRadio){
	
	con=Conectar("InsertarRadiobases");
	
		PreparedStatement pst;
		try {
			pst = con.prepareStatement("INSERT INTO Radiobases (NomRadio,TelRadio,LatRadio,LongRadio,"
					+ "LocRadio, ProvRadio, ContacRadio) "
					+ "VALUES (?,?,?,?,?,?,?)");
		
			pst.setString(1,NomRadio);
			pst.setString(2,TelRadio);
			pst.setString(3,LatRadio);
			pst.setString(4,LongRadio);
			pst.setString(5,LocRadio);
			pst.setString(6,ProvRadio);
			pst.setString(7,ContacRadio);
			
			
			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	

}

public String ConsultarNombre(int IdRadiobase)
{
	con=Conectar("ConsultarNombre: id: "+IdRadiobase);
	Statement st;
	ResultSet rs=null;
	String NombreRadio = null;
	try {
		st=con.createStatement();
		rs=st.executeQuery("SELECT `NomRadio` FROM `radiobases` WHERE `IdRadios`='"+IdRadiobase+"'");
		while(rs.next()){
			NombreRadio=  rs.getString("NomRadio");
				
					
		}
			
	
	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 NombreRadio="Desconocido";
			 System.out.println("error ConsultarNombre: id"+IdRadiobase);
	}
	
	return NombreRadio;
}
	

public void  InsertarRadiosOnline(){
	
   con=Conectar("InsertarRadiosOnline");

   String ComandoSQL="(?,?)";
   String SQLFINAL=null;
    Statement st;
	ResultSet rs=null;
	PreparedStatement pst;
	try {
		st=con.createStatement();
		rs=st.executeQuery("SELECT `IdRadios`,COUNT(*) as 'Cantidad' FROM keepalive WHERE `TimeKA` > DATE_ADD(now(),INTERVAL -60 SECOND) GROUP BY `IdRadios`");
		rs.last();
		
		int longitudRS=rs.getRow();
		
		if(longitudRS!=0){
		
		rs.beforeFirst();
		//System.out.println("longitudRS:"+longitudRS);
		int c=0;
		
		
			 while(rs.next()){
					SQLFINAL=ComandoSQL;
						ComandoSQL=ComandoSQL+",(?,?)";
					}
			    
			 //   System.out.println("INSERT INTO `onlineradiobases` (`IdRadios`, `Cantidad`) VALUES "+SQLFINAL);
			    
				pst = con.prepareStatement("INSERT INTO `onlineradiobases` (`IdRadios`, `Cantidad`) VALUES "+SQLFINAL);
				rs.beforeFirst();
				c=1;
			 
				while(rs.next()){

				   int Radio=rs.getInt("IdRadios");
					int cantidad=rs.getInt("Cantidad");
				//	System.out.println(c+" "+Radio+" "+cantidad);
					pst.setInt(c,Radio);
					pst.setInt(c+1,cantidad);
					c=c+2;// se suma dos porque es de a dos datos que se  cargan
				}
				
				pst.execute();
			
	}
		
	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
	
	
}


public void Desconectar(){
	
	try {
		con.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

}

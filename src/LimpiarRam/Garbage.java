package LimpiarRam;

public class Garbage {

	
	public Garbage(){
		
		try{
			System.out.println( "********** INICIO: 'LIMPIEZA GARBAGE COLECTOR' **********" );
			Runtime basurero = Runtime.getRuntime();
			System.out.println( "MEMORIA TOTAL 'JVM': " + basurero.totalMemory() );
			System.out.println( "MEMORIA [FREE] 'JVM' [ANTES]: " + basurero.freeMemory() );
			basurero.gc(); //Solicitando ...
			System.out.println( "MEMORIA [FREE] 'JVM' [DESPUES]: " + basurero.freeMemory() );
			System.out.println( "********** FIN: 'LIMPIEZA GARBAGE COLECTOR' **********" );
			}
			catch( Exception e ){
			e.printStackTrace();
			} 

		
		
	}
	


}

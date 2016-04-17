package alumno3Notas;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.TreeMap;

import daw.com.Teclado;

public class AplicacionAlumno {

	private TreeMap<String,Alumno3>alumnos;
	
	public AplicacionAlumno(){
		
		alumnos = new TreeMap<String,Alumno3>();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AplicacionAlumno aplicacion = new AplicacionAlumno();
		aplicacion.run();
	}
	public void run(){
		guardarFichero();
		leerFichero();
	}
	public void guardarFichero(){
		String seguir;
		Alumno3 alumno;
		File f;
		FileOutputStream out;
		DataOutputStream dataOut;
		f = new File("datos.dat");
		if(f.exists()){
			System.err.println("El archivo ya existe");
		}else{

			do{
				alumno = new Alumno3();
				alumno.leerDatos();
				alumnos.put(alumno.getNombre(), alumno);
				seguir = Teclado.leerString("Desea seguir?(s/n): ");
			}while(seguir.equals("s"));
			
			Iterator <Alumno3> it = alumnos.values().iterator();

			try {
				out = new FileOutputStream(f);
				dataOut = new DataOutputStream(out);
				while(it.hasNext()){
					it.next().escribirFichero(dataOut);
					System.out.println();
				}
				dataOut.close();
				out.close();
				
			}catch(IOException e){
				System.out.println("Error de escritura");
			}
		}
	}
	public void leerFichero(){
		FileInputStream in;
		DataInputStream dataIn;
		
		Alumno3 alumno = new Alumno3();
		try {
			in = new FileInputStream("datos.dat");
			dataIn = new DataInputStream(in);
			while(dataIn.available()>0){
				alumno.leerFichero(dataIn);
				alumnos.put(alumno.getNombre(), alumno);
				
			}

			dataIn.close();
			in.close();
			
		}catch(EOFException e){
			System.out.println("Fin del fichero");
		}
		catch(IOException e){
			System.out.println("Error de lectura");
		}
	}
}

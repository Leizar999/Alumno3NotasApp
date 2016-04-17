package alumno3Notas;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;

import daw.com.Teclado;

public class Alumno3 {
	private String nombre;
	private float nota;
	private float notas[];
	
	public Alumno3(){
		nombre = "";
		nota = 0;
		notas = new float [3];
	}
	public Alumno3(String nombre, float nota, float notas[]){
		this.nombre = nombre;
		setNota(nota);
		for(int i = 0; i < notas.length;i++){
			this.notas[i] = notas[i];
		}
	}
	public Alumno3(Alumno3 copia){
		this.nombre = copia.nombre;
		this.nota = copia.nota;
		for(int i = 0; i < notas.length; i++){
			this.notas[i] = copia.notas[i];
		}
	}
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	public String getNombre(){
		return nombre;
	}
	public void setNota(float nota){
		if(nota < 0){
			nota = 0;
		}else if(nota > 10){
			nota = 10;
		}
		this.nota = nota;
	}
	public float getNota(){
		return nota;
	}
	public void leerDatos(){
		this.nombre = Teclado.leerString("Nombre del alumno: ");
		for(int i = 0; i < notas.length;i++){
			setNota(Teclado.leerFloat("Nota del alumno " + (i+1) + ":"));
			notas[i] = getNota();
		}
	}
	public void mostrarDatos(){
		System.out.println("El nombre del alumno es: " + nombre);
		for(int i = 0; i < notas.length;i++){
			System.out.println("La nota "+(i+1) + " Del alumno es: " + notas[i]);
		}
	}
	public void escribirFichero(DataOutputStream out) throws IOException{
		out.writeBytes(nombre+"\n");
		for(int i = 0; i < notas.length;i++){
			out.writeFloat(notas[i]);
		}
		System.out.println();
	}
	public void leerFichero(DataInputStream in) throws IOException{
		this.nombre = in.readLine();
		for(int i = 0; i < notas.length;i++){
			notas[i] = in.readFloat();
		}
	}
}

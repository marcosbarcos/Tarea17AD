package Tarea17.Entidades;

import java.util.ArrayList;

public class Grupo {
	private int codigo;
	private String nombre;
	private ArrayList<Alumno> alumnos;

	public Grupo(int codigo, String nombre) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
	}
	
	public Grupo(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	public Grupo() {
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public ArrayList<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(ArrayList<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	@Override
	public String toString() {
		return "Grupo [codigo=" + codigo + ", nombre=" + nombre + "]";
	}
	 
	
}

package Tarea16.Entidades;

import java.io.Serializable;
import java.util.Date;

public class Alumno implements Serializable{
	private int nia;
	private String nombre;
	private String apellidos;
	private String genero;
	private Date fecha_nacimiento;
	private String ciclo;
	private String curso;
	private Grupo grupo;
	
public Alumno() {
}

public Alumno(int nia, String nombre, String apellidos, String genero, Date fecha_nacimiento, String ciclo, String curso, Grupo grupo) {
	this.nia = nia;
	this.nombre = nombre;
	this.apellidos = apellidos;
	this.genero = genero;
	this.fecha_nacimiento = fecha_nacimiento;
	this.ciclo = ciclo;
	this.curso = curso;
	this.grupo = grupo;
}

public int getNia() {
	return nia;
}


public void setNia(int nia) {
	this.nia = nia;
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

public String getApellidos() {
	return apellidos;
}

public void setApellidos(String apellidos) {
	this.apellidos = apellidos;
}

public String getGenero() {
	return genero;
}

public void setGenero(String genero) {
	this.genero = genero;
}

public Date getFecha_nacimiento() {
	return fecha_nacimiento;
}

public void setFecha_nacimiento(Date fecha_nacimiento) {
	this.fecha_nacimiento = fecha_nacimiento;
}

public String getCiclo() {
	return ciclo;
}

public void setCiclo(String ciclo) {
	this.ciclo = ciclo;
}

public String getCurso() {
	return curso;
}

public void setCurso(String curso) {
	this.curso = curso;
}

public Grupo getGrupo() {
	return grupo;
}

public void setGrupo(Grupo grupo) {
	this.grupo = grupo;
}

@Override
public String toString() {
	return "Alumno [nia=" + nia + ", nombre=" + nombre + ", apellidos=" + apellidos + ", genero=" + genero
			+ ", fecha_nacimiento=" + fecha_nacimiento + ", ciclo=" + ciclo + ", curso=" + curso + ", grupo=" + grupo
			+ "]";
}



}

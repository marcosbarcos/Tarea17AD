package Tarea16.Entidades;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name ="alumnos")
public class AlumnoHibernate implements Serializable{
	
	@Id
	private int nia;
	@Column(name = "nombre", nullable = false, length = 30)
	private String nombre;
	@Column(name = "apellidos", nullable = false, length = 50)
	private String apellidos;
	@Column(name = "genero", nullable = false, length = 1)
	private String genero;
	@Column(name = "fecha_nacimiento", nullable = false)
	private Date fecha_nacimiento;
	@Column(name = "ciclo", nullable = true, length = 30)
	private String ciclo;
	@Column(name = "curso", nullable = true, length = 30)
	private String curso;
	@ManyToOne
	@JoinColumn(name = "idgrupo", nullable = true)
	private Grupo grupo;
	
public AlumnoHibernate() {
}

public AlumnoHibernate(int nia, String nombre, String apellidos, String genero, Date fecha_nacimiento, String ciclo, String curso, Grupo grupo) {
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


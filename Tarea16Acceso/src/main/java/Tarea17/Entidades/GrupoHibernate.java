package Tarea17.Entidades;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "grupos")
public class GrupoHibernate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Auto-Increment
	private int codigo;
	@Column(name = "nombre", nullable = false, length = 30)
	private String nombre;
	@OneToMany(mappedBy = "grupo") //Indica que alumno es el dueño de la relacion
	private List<AlumnoHibernate> alumnos;

	public GrupoHibernate(int codigo, String nombre) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
	}
	
	public GrupoHibernate() {
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
	
	public List<AlumnoHibernate> getAlumnos() {
		return alumnos;
	}
	
	public void setAlumnos(ArrayList<AlumnoHibernate> alumnos) {
		this.alumnos = alumnos;
	}
	
	@Override
	public String toString() {
		return "Grupo [codigo=" + codigo + ", nombre=" + nombre + "]";
	}
	 
	
}

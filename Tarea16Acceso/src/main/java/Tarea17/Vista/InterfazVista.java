package Tarea17.Vista;

import java.util.List;
import java.util.Scanner;

import Tarea17.Entidades.Alumno;
import Tarea17.Entidades.Grupo;

public interface InterfazVista {

	void mostrarMenu();
	void mostrarAlumnos(List<Alumno> alumnos);
	void mostrarGrupos(List<Grupo> grupos);
	void mostrarAlumnosmasCodigo(List<Alumno> alumnos);
	void mostrarAlumno(Alumno alumnos);
	void mostrarGrupo(Grupo grupo);
	int pedirRespuestaInt();
	String pedirRespuestaString();
	void mostrarMensaje(String string);
}

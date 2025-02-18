package Tarea16.Vista;

import java.util.List;
import java.util.Scanner;

import Tarea16.Entidades.Alumno;
import Tarea16.Entidades.Grupo;

public interface InterfazVista {

	void mostrarMenu();
	void mostrarAlumnos(List<Alumno> alumnos);
	void mostrarAlumnosmasCodigo(List<Alumno> alumnos);
	void mostrarAlumno(Alumno alumnos);
	int pedirRespuestaInt();
	String pedirRespuestaString();
	void mostrarMensaje(String string);
}

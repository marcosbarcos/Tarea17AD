package Tarea16.Vista;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import Tarea16.Entidades.Alumno;
import Tarea16.Entidades.Grupo;

public class ImplementacionVistaConsola implements InterfazVista{

	private Scanner entrada = new Scanner(System.in);
	
	@Override
	public void mostrarMenu() {
		// TODO Auto-generated method stub
		System.out.println("1: Insertar Alumno");
		System.out.println("2: Insertar Grupo");
		System.out.println("3: Mostrar todos los Alumnos");
		System.out.println("4: Mostrar todos los Grupos");
		System.out.println("5: Modificar Alumno");
		System.out.println("6: Borrar Alumno");
		System.out.println("7: Borrar Alumno por Curso");
		System.out.println("8: Buscar Grupo por Codigo");
		System.out.println("9: Mostrar Alumnos por Grupo");
		System.out.println("10: Mostrar Alumno por Codigo");
		System.out.println("11: Salir");
	}

	@Override
	public int pedirRespuestaInt() {
		int num = entrada.nextInt();
		entrada.nextLine();
		return num;
	}
	
	@Override
	public String pedirRespuestaString() {
		String respuesta = entrada.nextLine();
		return respuesta;
	}

	@Override
	public void mostrarAlumnos(List<Alumno> alumnos) {
		for (Alumno alumno : alumnos) {
			System.out.println(alumno.toString());
		}
	}

	@Override
	public void mostrarAlumno(Alumno alumno) {
		System.out.println(alumno.toString());
	}

	@Override
	public void mostrarAlumnosmasCodigo(List<Alumno> alumnos) {
		for (Alumno alumno : alumnos) {
			System.out.println(alumno.getNia() + ", " + alumno.getNombre());
		}
	}

	@Override
	public void mostrarMensaje(String string) {
		System.out.println(string);
	}

	@Override
	public void mostrarGrupos(List<Grupo> grupos) {
		for (Grupo grupo : grupos) {
			System.out.println(grupo.toString());
		}
	}

	@Override
	public void mostrarGrupo(Grupo grupo) {
		System.out.println(grupo.toString());
	}



}

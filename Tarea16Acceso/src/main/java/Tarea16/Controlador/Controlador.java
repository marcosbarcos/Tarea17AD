package Tarea16.Controlador;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import Tarea16.Entidades.Alumno;
import Tarea16.Entidades.Grupo;
import Tarea16.Modelo.AlumnoDAO;

import Tarea16.Vista.InterfazVista;

public class Controlador {

	public void ejecutar(AlumnoDAO modelo, InterfazVista vista) {
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
		List<Alumno> alumnos;
		List<Grupo> grupos;
		int res;
		do {
			vista.mostrarMenu();
			res = vista.pedirRespuestaInt();
			switch(res) {
				case 1: 
					try {
						Alumno a = new Alumno();
						vista.mostrarMensaje("Dame el nombre del alumno");
						a.setNombre(vista.pedirRespuestaString());
						vista.mostrarMensaje("Dame el NIA del alumno");
						a.setNia(vista.pedirRespuestaInt());
						vista.mostrarMensaje("Dame el apellido del alumno");
						a.setApellidos(vista.pedirRespuestaString());
						vista.mostrarMensaje("Dame el genero del alumno");
						a.setGenero(vista.pedirRespuestaString());
						vista.mostrarMensaje("Dame la fecha de nacimiento del alumno");
						a.setFecha_nacimiento(formato.parse(vista.pedirRespuestaString()));
						vista.mostrarMensaje("Dame el cilo del alumno");
						a.setCiclo(vista.pedirRespuestaString());
						vista.mostrarMensaje("Dame el curso del alumno");
						a.setCurso(vista.pedirRespuestaString());
						vista.mostrarMensaje("Dame el codigo del grupo del alumno");
						a.setGrupo(vista.pedirRespuestaInt());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//					Alumno a = new Alumno();
//					a.setNombre(vista.pedirDatosAlumno());
//					
//					alumnos = null;
//					grupos = null;
//					Grupo g = modelo.buscarGrupoPorCodigo(vista.pedirRespuestaNumero());
//					alumnos = modelo.mostrarAlumnosporGrupo(g);
//					vista.mostrarAlumnos(alumnos);
					break;
				case 2:
					alumnos = null;
					grupos = null;
					alumnos = modelo.mostrarTodosAlumnos();
					vista.mostrarAlumnosmasCodigo(alumnos);
					Alumno a1 = modelo.mostrarAlumnoPorCodigo(vista.pedirRespuestaInt());
					vista.mostrarAlumno(a1);
					break;
				case 3:
					alumnos = modelo.mostrarTodosAlumnos();
					vista.mostrarAlumnosmasCodigo(alumnos);
					break;
				default:
					break;
			}
		}while(res != 12);
			
		
		
		
//		System.out.println("Que alumno quieres modificar? Dame su codigo");
//		Alumno a2 = modelo.mostrarAlumnoPorCodigo(entrada.nextInt());
//		entrada.nextLine();
//		System.out.println("A que grupo quieres cambiarle? Dame su codigo");
//		grupos = modelo.mostrarTodosGrupos();
//		for (Grupo grupo : grupos) {
//			System.out.println(grupo.toString());
//		}
//		g = modelo.buscarGrupoPorCodigo(entrada.nextInt());
//		entrada.nextLine();
//		a2.setGrupo(g);
//		modelo.modificarAlumno(a2);
//		
//		//4
//		System.out.println("EJERCICIO 4:");
//		System.out.println("Que grupo quieres guardar en JSON? Dame su codigo");
//		modelo.guardarenJSON(entrada.nextInt());
//		entrada.nextLine();
//		entrada.close();
	}
	

}

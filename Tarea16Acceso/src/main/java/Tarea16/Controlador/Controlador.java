
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
			switch (res) {
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
					vista.mostrarMensaje("Dame el codigo del Grupo");
					int codGrupo = vista.pedirRespuestaInt();
					if (modelo.buscarGrupoPorCodigo(codGrupo) == null) {
						vista.mostrarMensaje("El Grupo insertado no existe, ¿deseas crearlo?");
						if (vista.pedirRespuestaString().equalsIgnoreCase("si")) {
							vista.mostrarMensaje("Dame el nombre del Grupo");
							Grupo g = new Grupo(0, vista.pedirRespuestaString());
							modelo.insertarGrupo(g);
							a.setGrupo(g);
							modelo.insertarAlumno(a);
						} else {
							vista.mostrarMensaje("Alumno no creado");
						}
					} else {
						a.setGrupo(modelo.buscarGrupoPorCodigo(codGrupo));
						modelo.insertarAlumno(a);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
				break;
			case 2:
				vista.mostrarMensaje("Dame el nombre del Grupo");
				Grupo g = new Grupo(0, vista.pedirRespuestaString());
				modelo.insertarGrupo(g);
				break;
			case 3:
				alumnos = modelo.mostrarTodosAlumnos();
				if(alumnos == null) {
					vista.mostrarMensaje("No hay alumnos");
				}else {
					vista.mostrarAlumnos(alumnos);
				}
				
				break;
			case 4:
				grupos = modelo.mostrarTodosGrupos();
				vista.mostrarGrupos(grupos);
				break;
			case 5:
				Alumno a = new Alumno();
				vista.mostrarMensaje("Que alumno quieres modificar, inserte su codigo");
				a = modelo.mostrarAlumnoPorCodigo(vista.pedirRespuestaInt());
				if(a != null) {
					vista.mostrarMensaje("Dame el nuevo nombre del alumno");
					a.setNombre(vista.pedirRespuestaString());
					vista.mostrarMensaje("Dame el nuevo apellido del alumno");
					a.setApellidos(vista.pedirRespuestaString());
					vista.mostrarMensaje("Dame el nuevo genero del alumno");
					a.setGenero(vista.pedirRespuestaString());
					vista.mostrarMensaje("Dame la nueva fecha de nacimiento del alumno");
					try {
						a.setFecha_nacimiento(formato.parse(vista.pedirRespuestaString()));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					vista.mostrarMensaje("Dame el nuevo ciclo del alumno");
					a.setCiclo(vista.pedirRespuestaString());
					vista.mostrarMensaje("Dame el nuevo curso del alumno");
					a.setCurso(vista.pedirRespuestaString());
					vista.mostrarMensaje("Dame el nuevo codigo del Grupo");
					int codGrupo = vista.pedirRespuestaInt();
					if (modelo.buscarGrupoPorCodigo(codGrupo) == null) {
						vista.mostrarMensaje("El Grupo insertado no existe, ¿deseas crearlo?");
						if (vista.pedirRespuestaString().equalsIgnoreCase("si")) {
							vista.mostrarMensaje("Dame el nombre del Grupo");
							Grupo grup = new Grupo(0, vista.pedirRespuestaString());
							modelo.insertarGrupo(grup);
							a.setGrupo(grup);
						} else {
							vista.mostrarMensaje("Alumno no modificado");
						}
					} else {
						a.setGrupo(modelo.buscarGrupoPorCodigo(codGrupo));
					}
					modelo.modificarAlumno(a);
					vista.mostrarMensaje("Alumno Modificado");
				} 
				else {
					vista.mostrarMensaje("No se ha encontrado un alumno con este codigo");
				}
				
				break;
			case 6:
				vista.mostrarMensaje("Dame el codigo del alumno a eliminar");
				modelo.borrarAlumno(vista.pedirRespuestaInt());
				break;
			case 7:
				vista.mostrarMensaje("Dame el curso de los alumnos a eliminar");
				modelo.borrarAlumnoporCurso(vista.pedirRespuestaString());
				break;
			case 8:
				vista.mostrarMensaje("Dame el codigo del grupo a buscar");
				Grupo grup = modelo.buscarGrupoPorCodigo(vista.pedirRespuestaInt());
				if(grup == null) {
					vista.mostrarMensaje("Grupo no encontrado");
				}
				else {
					vista.mostrarGrupo(grup);
				}
				break;
			case 9:
				vista.mostrarMensaje("De cual grupo quieres mostrar todos los alumnos? Dame el codigo");
				grupos = modelo.mostrarTodosGrupos();
				vista.mostrarGrupos(grupos);
				modelo.mostrarAlumnosporGrupo(modelo.buscarGrupoPorCodigo(vista.pedirRespuestaInt()));
				break;
			case 10:
				vista.mostrarMensaje("Dame el codigo del alumno que quieras visualziar");
				vista.mostrarAlumno(modelo.mostrarAlumnoPorCodigo(vista.pedirRespuestaInt()));
				break;
			default:
				break;
			}
		} while (res != 11);

	}

}

	

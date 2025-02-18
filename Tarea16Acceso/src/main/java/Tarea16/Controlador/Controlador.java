
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
					if (modelo.buscarGrupoPorCodigo(vista.pedirRespuestaInt()).equals(null)) {
						vista.mostrarMensaje("El Grupo insertado no existe, ¿deseas crearlo?");
						if (vista.pedirRespuestaString().equalsIgnoreCase("si")) {
							vista.mostrarMensaje("Dame el nombre del Grupo");
							Grupo g = new Grupo(0, vista.pedirRespuestaString());
							modelo.insertarGrupo(g);
						} else {
							vista.mostrarMensaje("Alumno no creado");
						}
					} else {
						a.setGrupo(modelo.buscarGrupoPorCodigo(vista.pedirRespuestaInt()));
						modelo.insertarAlumno(a);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
				break;
			case 2:
				vista.mostrarMensaje("Dame el nombre del Grupo");
				Grupo g = new Grupo(0, vista.pedirRespuestaString());
				break;
			case 3:
				alumnos = modelo.mostrarTodosAlumnos();
				vista.mostrarAlumnos(alumnos);
				break;
			case 4:

				break;
			case 5:

				break;
			case 6:

				break;
			case 7:

				break;
			case 8:

				break;
			case 9:

				break;
			default:
				break;
			}
		} while (res != 12);

	}

}

package Tarea16.Modelo;

import java.util.List;

import Tarea16.Entidades.Alumno;
import Tarea16.Entidades.Grupo;

public interface AlumnoDAO {

	int insertarAlumno(Alumno alumno);
	int insertarGrupo(Grupo grupo);
	List<Alumno> mostrarTodosAlumnos();
	List<Grupo> mostrarTodosGrupos();
	int modificarAlumno(Alumno alumno);
	void borrarAlumno(int id);
	void borrarAlumnoporCurso(String curso);
	Grupo buscarGrupoPorCodigo(int codigo);
	List<Alumno> mostrarAlumnosporGrupo(Grupo grupo);
	Alumno mostrarAlumnoPorCodigo(int codigo);
	//	void guardarenJSON(int codigo); esto va en el controlador
}

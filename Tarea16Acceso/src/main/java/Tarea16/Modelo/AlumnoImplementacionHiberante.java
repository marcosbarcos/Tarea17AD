package Tarea16.Modelo;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Tarea16.Entidades.Alumno;
import Tarea16.Entidades.AlumnoHibernate;
import Tarea16.Entidades.Grupo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class AlumnoImplementacionHiberante implements AlumnoDAO {
	private static final Logger logger = LogManager.getLogger(AlumnoImplementacionBBDD.class);
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	@Override
	public int insertarAlumno(Alumno alumno) {
		AlumnoHibernate alumnoHB = convertirAlumnoHibernate(alumno);
		emf = Persistence.createEntityManagerFactory("Persistencia");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(alumnoHB);
		em.getTransaction().commit();
		return 0;
	}

	@Override
	public int insertarGrupo(Grupo grupo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Alumno> mostrarTodosAlumnos() {
		emf = Persistence.createEntityManagerFactory("Persistencia");
		em = emf.createEntityManager();
		
		List<AlumnoHibernate> alumnosHiber = em.createQuery("From alumnos").getResultList();
		List<Alumno> alumnos = new ArrayList<Alumno>();
		for (AlumnoHibernate alumnoHibernate : alumnosHiber) {
			alumnos.add(convertirAlumno(alumnoHibernate));
		}
		return alumnos;
	}

	@Override
	public List<Grupo> mostrarTodosGrupos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int modificarAlumno(Alumno alumno) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void borrarAlumno(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrarAlumnoporCurso(String curso) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Grupo buscarGrupoPorCodigo(int codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Alumno> mostrarAlumnosporGrupo(Grupo grupo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Alumno mostrarAlumnoPorCodigo(int codigo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Alumno convertirAlumno(AlumnoHibernate a) {
		Alumno alumno = new Alumno();
		alumno.setNia(a.getNia());
		alumno.setNombre(a.getNombre());
		alumno.setApellidos(a.getApellidos());
		alumno.setGenero(a.getGenero());
		alumno.setFecha_nacimiento(a.getFecha_nacimiento());
		alumno.setCiclo(a.getCiclo());
		alumno.setCurso(a.getCurso());
		alumno.setGrupo(a.getGrupo());
		return alumno;
	}
	
	public AlumnoHibernate convertirAlumnoHibernate(Alumno a) {
		AlumnoHibernate alumno = new AlumnoHibernate();
		alumno.setNia(a.getNia());
		alumno.setNombre(a.getNombre());
		alumno.setApellidos(a.getApellidos());
		alumno.setGenero(a.getGenero());
		alumno.setFecha_nacimiento(a.getFecha_nacimiento());
		alumno.setCiclo(a.getCiclo());
		alumno.setCurso(a.getCurso());
		alumno.setGrupo(a.getGrupo());
		return alumno;
	}

}

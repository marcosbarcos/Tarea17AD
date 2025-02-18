package Tarea16.Modelo;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Tarea16.Entidades.Alumno;
import Tarea16.Entidades.AlumnoHibernate;
import Tarea16.Entidades.Grupo;
import Tarea16.Entidades.GrupoHibernate;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class AlumnoImplementacionHiberante implements AlumnoDAO {
	private static final Logger logger = LogManager.getLogger(AlumnoImplementacionHiberante.class);
	
	private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");;
	private EntityManager em;
	
	@Override
	public int insertarAlumno(Alumno alumno) {
		AlumnoHibernate alumnoHB = convertirAlumnoHibernate(alumno);
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			for (int i = 0; i < 50; i++) {
				em.persist(alumnoHB);
			}
			em.getTransaction().commit();
			em.close();
			return 1;
		}catch(Exception e) {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			logger.error("Trasaccion Fallida");
			em.close();
			return 0;
		}
	}

	
	@Override
	public int insertarGrupo(Grupo grupo) {
		GrupoHibernate grupoo = convertirGrupoHibernate(grupo);
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(grupoo);
			em.getTransaction().commit();
			em.close();
			return 1;
		}catch(Exception e) {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			logger.error("Transaccion Fallida");
			em.close();
			return 0;
		}
	}

	@Override
	public List<Alumno> mostrarTodosAlumnos() {
		List<AlumnoHibernate> alumnosHibernate = new ArrayList<AlumnoHibernate>();
		try {
			em = emf.createEntityManager();
			alumnosHibernate = em.createQuery("FROM grupos").getResultList();
			List<Alumno> alumnos = new ArrayList<Alumno>();
			for (AlumnoHibernate alumnoHibernate : alumnosHibernate) {
				alumnos.add(convertirAlumno(alumnoHibernate));
			}
			return alumnos;
		} catch (Exception e) {
			logger.error("Transaccion Fallida");
			
		} finally {
	        if (em != null) {
	            em.close();
	        }
	    }
		return null;
	}

	@Override
	public List<Grupo> mostrarTodosGrupos() {
		List<GrupoHibernate> gruposHibernate = new ArrayList<GrupoHibernate>();
		try {
			em = emf.createEntityManager();
			gruposHibernate = em.createQuery("FROM grupos").getResultList();
			List<Grupo> grupos = new ArrayList<Grupo>();
			for (GrupoHibernate grupoH : gruposHibernate) {
				grupos.add(convertirGrupo(grupoH));
			}
			return grupos;
		}catch(Exception e) {
			if(em != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			logger.error("Transaccion Fallida");
		}finally {
			if(em != null) {
				em.close();
			}
		}
		return null;
	}

	@Override
	public int modificarAlumno(Alumno alumno) {
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			AlumnoHibernate alumnoHibernate = convertirAlumnoHibernate(alumno);
			AlumnoHibernate buscarAlumno = em.find(AlumnoHibernate.class, alumnoHibernate.getNia());
			if(buscarAlumno != null) {
				buscarAlumno.setNombre(alumnoHibernate.getNombre());
				buscarAlumno.setApellidos(alumnoHibernate.getApellidos());
				buscarAlumno.setGenero(alumnoHibernate.getGenero());
				buscarAlumno.setFecha_nacimiento(alumnoHibernate.getFecha_nacimiento());
				buscarAlumno.setCiclo(alumnoHibernate.getCiclo());
				buscarAlumno.setCurso(alumnoHibernate.getCurso());
				buscarAlumno.setGrupo(alumnoHibernate.getGrupo());
				em.merge(buscarAlumno);
				em.getTransaction().commit();
				return 1;
			}
		}catch(Exception e) {
			if(em != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			logger.error("Transaccion Fallida");
		}finally {
			if(em != null) {
				em.close();
			}
 		}
		return 0;
	}

	@Override
	public void borrarAlumno(int id) {
		
		
	}

	@Override
	public void borrarAlumnoporCurso(String curso) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Grupo buscarGrupoPorCodigo(int codigo) {
		try {
			em = emf.createEntityManager();
			GrupoHibernate gh = (GrupoHibernate) em.createQuery("FROM grupo WHERE codigo = :codigoGrupo")
					.setParameter("codigoGrupo", codigo)
					.getSingleResult();
			return convertirGrupo(gh);
		}catch(Exception e) {
			if(em != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			logger.error("Transaccion Fallida");
		}finally {
			if(em != null) {
				em.close();
			}
		}
		return null;
	}

	@Override
	public List<Alumno> mostrarAlumnosporGrupo(Grupo grupo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Alumno mostrarAlumnoPorCodigo(int codigo) {
		try {
			em = emf.createEntityManager();
			em.createQuery("FROM alumnos WHERE codigo");
		}catch(Exception e) {
			
		}
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
	
	public GrupoHibernate convertirGrupoHibernate(Grupo g) {
		GrupoHibernate grupo = new GrupoHibernate();
		grupo.setCodigo(g.getCodigo());
		grupo.setNombre(g.getNombre());
		return grupo;
	}
	
	public Grupo convertirGrupo(GrupoHibernate g) {
		Grupo grupo = new Grupo();
		grupo.setCodigo(g.getCodigo());
		grupo.setNombre(g.getNombre());
		return grupo;
	}

}

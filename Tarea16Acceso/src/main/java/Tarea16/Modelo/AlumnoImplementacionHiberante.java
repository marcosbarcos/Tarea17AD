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
	private static final Logger logger = LogManager.getLogger(AlumnoImplementacionBBDD.class);
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	@Override
	public int insertarAlumno(Alumno alumno) {
		AlumnoHibernate alumnoHB = convertirAlumnoHibernate(alumno);
		try {
			emf = Persistence.createEntityManagerFactory("Persistencia");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(alumnoHB);
			em.getTransaction().commit();
			emf.close();
			em.close();
			return 1;
		}catch(Exception e) {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			logger.error("Trasaccion Fallida");
			emf.close();
			em.close();
			return 0;
		}
	}

	
	@Override
	public int insertarGrupo(Grupo grupo) {
		GrupoHibernate grupoo = convertirGrupoHibernate(grupo);
		try {
			emf = Persistence.createEntityManagerFactory("Persistencia");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(grupoo);
			em.getTransaction().commit();
			emf.close();
			em.close();
			return 1;
		}catch(Exception e) {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			logger.error("Transaccion Fallida");
			emf.close();
			em.close();
			return 0;
		}
	}

	@Override
	public List<Alumno> mostrarTodosAlumnos() {
		List<AlumnoHibernate> alumnosHibernate = new ArrayList<AlumnoHibernate>();
		try {
			emf = Persistence.createEntityManagerFactory("Persistencia");
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
	        if (emf != null) {
	            emf.close();
	        }
	    }
		return null;
	}

	@Override
	public List<Grupo> mostrarTodosGrupos() {
		List<GrupoHibernate> gruposHibernate = new ArrayList<GrupoHibernate>();
		try {
			emf = Persistence.createEntityManagerFactory("Persistencia");
			em = emf.createEntityManager();
			gruposHibernate = em.createQuery("FROM grupos").getResultList();
			List<Grupo> grupos = new ArrayList<Grupo>();
			for (GrupoHibernate grupoH : gruposHibernate) {
				grupos.add(convertirGrupo(grupoH));
			}
			return grupos;
		}catch(Exception e) {
			logger.error("Transaccion Fallida");
		}finally {
			if(em != null) {
				em.close();
			}
			if (emf != null) {
				emf.close();
			}
		}
		return null;
	}

	@Override
	public int modificarAlumno(Alumno alumno) {
		try {
			emf = Persistence.createEntityManagerFactory("Persistencia");
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
			if(emf != null) {
				emf.close();
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
		try {
			emf = Persistence.createEntityManagerFactory("Persistencia");
			em = emf.createEntityManager();
			em.createQuery("FROM alumnos");
		}catch(Exception e
				
				
				) {
			
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

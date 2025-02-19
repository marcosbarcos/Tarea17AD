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
		em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(alumnoHB);
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
		em = emf.createEntityManager();
		try {
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Alumno> mostrarTodosAlumnos() {
		List<AlumnoHibernate> alumnosHibernate = new ArrayList<AlumnoHibernate>();
		em = emf.createEntityManager();
		try {
			alumnosHibernate = em.createQuery("FROM AlumnoHibernate").getResultList();
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Grupo> mostrarTodosGrupos() {
		List<GrupoHibernate> gruposHibernate = new ArrayList<GrupoHibernate>();
		em = emf.createEntityManager();
		try {
			gruposHibernate = em.createQuery("FROM GrupoHibernate").getResultList();
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
		em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			AlumnoHibernate buscarAlumno = em.find(AlumnoHibernate.class, alumno.getNia());
			if(buscarAlumno != null) {
				buscarAlumno.setNombre(alumno.getNombre());
				buscarAlumno.setApellidos(alumno.getApellidos());
				buscarAlumno.setGenero(alumno.getGenero());
				buscarAlumno.setFecha_nacimiento(alumno.getFecha_nacimiento());
				buscarAlumno.setCiclo(alumno.getCiclo());
				buscarAlumno.setCurso(alumno.getCurso());
				buscarAlumno.setGrupo(convertirGrupoHibernate(alumno.getGrupo()));
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
		em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			AlumnoHibernate ah = em.find(AlumnoHibernate.class, id);
			em.remove(ah);
			em.getTransaction().commit();
		}catch(Exception e) {
			if (em != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			logger.error("Transaccion Fallida");
		} finally {
			em.close();
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void borrarAlumnoporCurso(String curso) {
		List<AlumnoHibernate> alumnosCurso = new ArrayList<AlumnoHibernate>();
		em = emf.createEntityManager();
		try {
			alumnosCurso = em.createQuery("FROM AlumnoHibernate WHERE curso = :cursoBuscar")
			.setParameter("cursoBuscar", curso)
			.getResultList();
			em.getTransaction().begin();
			for (AlumnoHibernate alumnoHibernate : alumnosCurso) {
				em.remove(alumnosCurso);
			}
			em.getTransaction().commit();
		}catch(Exception e) {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			logger.error("Transaccion Fallida");
		}finally {
			if(em != null) {
				em.close();
			}
		}
		
	}

	@Override
	public Grupo buscarGrupoPorCodigo(int codigo) {
		em = emf.createEntityManager();
		try {
			GrupoHibernate gh = (GrupoHibernate) em.createQuery("FROM GrupoHibernate WHERE codigo = :codigoGrupo")
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
		List<AlumnoHibernate> alumnosHB = new ArrayList<AlumnoHibernate>();
		em = emf.createEntityManager();
		try {
			em.createQuery("FROM AlumnoHibernate WHERE idgrupo = :codigoGrupo")
			.setParameter("codigoGrupo", grupo)
			.getResultList();
		}catch(Exception e) {
			logger.error("Transaccion Fallida");
		}finally {
			if(em != null) {
				em.close();
			}
		}
		return null;
	}

	@Override
	public Alumno mostrarAlumnoPorCodigo(int codigo) {
		em = emf.createEntityManager();
		try {
			AlumnoHibernate ah = (AlumnoHibernate) em.createQuery("FROM AlumnoHibernate WHERE nia = :codigo")
			.setParameter("codigo", codigo)
			.getSingleResult();
			return convertirAlumno(ah);
		}catch(Exception e) {
			logger.error("Trsansaccion Fallida");
		}finally {
			if(em != null) {
				em.close();
			}
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
		alumno.setGrupo(convertirGrupo(a.getGrupo()));
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
		alumno.setGrupo(convertirGrupoHibernate(a.getGrupo()));
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

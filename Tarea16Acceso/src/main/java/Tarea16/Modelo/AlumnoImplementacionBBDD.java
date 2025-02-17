package Tarea16.Modelo;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import Tarea16.Conexion.MyDataSource;
import Tarea16.Entidades.Alumno;
import Tarea16.Entidades.Grupo;


public class AlumnoImplementacionBBDD implements AlumnoDAO{
	private static final Logger logger = LogManager.getLogger(AlumnoImplementacionBBDD.class);
	
	@Override
	public int insertarAlumno(Alumno alumno) {
		String sql = "INSERT INTO alumno (NIA,Nombre,Apellidos,Genero,FechaDeNacimiento,Ciclo,Curso,Grupo)"
				+ "VALUES (?,?,?,?,?,?,?,?)";
		int result = 0;
		try(Connection conexion = MyDataSource.getConnection();
			PreparedStatement consulta = conexion.prepareStatement(sql)) {
			if(alumno.getGrupo() == null) {
				logger.error("El grupo introducido no existe");
			}
			else {
				consulta.setInt(1, alumno.getNia());
				consulta.setString(2, alumno.getNombre());
				consulta.setString(3, alumno.getApellidos());
				consulta.setString(4, alumno.getGenero());
				consulta.setDate(5, new java.sql.Date(alumno.getFecha_nacimiento().getTime()));
				consulta.setString(6, alumno.getCiclo());
				consulta.setString(7, alumno.getCurso());
				consulta.setInt(8, alumno.getGrupo().getCodigo());
			}
			result = consulta.executeUpdate();
			logger.info("Alumno creado con exito");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Algo ha fallado");
		}
		return result;
	}

	@Override
	public int insertarGrupo(Grupo grupo) {
		String sql = "INSERT INTO grupos (Codigo,Nombre)"
				+ "VALUES (?,?)";
		int result = 0;
		try(Connection conexion = MyDataSource.getConnection();
			PreparedStatement consulta = conexion.prepareStatement(sql)) {
			consulta.setInt(1, grupo.getCodigo());
			consulta.setString(2, grupo.getNombre());
			result = consulta.executeUpdate();
			logger.info("Grupo creado con exito");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Algo ha fallado");
		}
		return result;
	}

	@Override
	public List<Alumno> mostrarTodosAlumnos() {
		String sql = "SELECT NIA,Nombre,Apellidos,Genero,FechaDeNacimiento,Ciclo,Curso,Grupo FROM alumno";
		List<Alumno> result = new ArrayList<>();
		try(Connection conexion = MyDataSource.getConnection();
			PreparedStatement ps = conexion.prepareStatement(sql);
			ResultSet rs = ps.executeQuery()) {
			Alumno a;
			while(rs.next()) {
				a = new Alumno();
				a.setNia(rs.getInt("NIA"));
				a.setNombre(rs.getString("Nombre"));
				a.setApellidos(rs.getString("Apellidos"));
				a.setGenero(rs.getString("Genero"));
				a.setFecha_nacimiento(rs.getDate("FechaDeNacimiento"));
				a.setCiclo(rs.getString("Ciclo"));
				a.setCurso(rs.getString("Curso"));
				a.setGrupo(buscarGrupoPorCodigo(rs.getInt("Grupo")));
				result.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Algo ha fallado");
		}
		return result;
	}
	
	@Override
	public List<Grupo> mostrarTodosGrupos() {
		String sql = "SELECT codigo,nombre FROM grupo";
		List<Grupo> result = new ArrayList<>();
		try(Connection conexion = MyDataSource.getConnection();
			PreparedStatement ps = conexion.prepareStatement(sql);
			ResultSet rs = ps.executeQuery()) {
			Grupo g;
			while(rs.next()) {
				g = new Grupo();
				g.setCodigo(rs.getInt("codigo"));
				g.setNombre(rs.getString("nombre"));
				result.add(g);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Algo ha fallado");
		}
		return result;
	}

	@Override
	public int modificarAlumno(Alumno alumno) {
		String sql = "UPDATE alumno SET Nombre = ?,Apellidos = ?,Genero = ?,"
				+ "FechaDeNacimiento = ?,Ciclo = ?,Curso = ?,Grupo = ? WHERE NIA = ?";
		int result = 0;
		try(Connection conexion = MyDataSource.getConnection();
			PreparedStatement ps = conexion.prepareStatement(sql)) {
			ps.setString(1, alumno.getNombre());
			ps.setString(2, alumno.getApellidos());
			ps.setString(3, alumno.getGenero());
			ps.setDate(4, new java.sql.Date(alumno.getFecha_nacimiento().getTime()));
			ps.setString(5, alumno.getCiclo());
			ps.setString(6, alumno.getCurso());
			ps.setInt(7, alumno.getGrupo());
			ps.setInt(8, alumno.getNia());
			result = ps.executeUpdate();
			logger.info("Alumno modificado con exito");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Algo ha fallado");
		}
		return result;
	}

	@Override
	public void borrarAlumno(int id) {
		String sql = "DELETE FROM alumno WHERE NIA = ?";
		try(Connection conexion = MyDataSource.getConnection();
			PreparedStatement ps = conexion.prepareStatement(sql)) {
			ps.setInt(1, id);
			ps.executeUpdate();
			logger.info("Alumno borrado con exito");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Algo ha fallado");
		}
		
	}

	@Override
	public void borrarAlumnoporCurso(String curso) {
		String borrar = "DELETE FROM alumno WHERE Curso = ?";
		try(Connection conexion = MyDataSource.getConnection();
			PreparedStatement ps =  conexion.prepareStatement(borrar)) {
			ps.setString(1, curso);
			ps.executeUpdate();
			logger.info("Alumnos borrados con exito");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Algo ha fallado");
		}
	}

	@Override
	public Grupo buscarGrupoPorCodigo(int codigo) {
		// TODO Auto-generated method stub
		Grupo g = null;
		String sql = "SELECT codigo, nombre FROM grupo WHERE codigo = ?";
		try(Connection conexion = MyDataSource.getConnection();
			PreparedStatement ps = conexion.prepareStatement(sql)) {
			ps.setInt(1, codigo);
			try(ResultSet rs = ps.executeQuery()) {
				while(rs.next()) {
					g = new Grupo();
					g.setCodigo(rs.getInt("codigo"));
					g.setNombre(rs.getString("nombre"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return g;
	}

	@Override
	public List<Alumno> mostrarAlumnosporGrupo(Grupo grupo) {
		// TODO Auto-generated method stub
		String sql = "SELECT NIA,Nombre,Apellidos,Genero,FechaDeNacimiento,Ciclo,Curso,Grupo FROM alumno WHERE Grupo = ?";
		List<Alumno> result = new ArrayList<>();
		try(Connection conexion = MyDataSource.getConnection();
			PreparedStatement ps = conexion.prepareStatement(sql)) {
			ps.setInt(1, grupo.getCodigo());
			ResultSet rs = ps.executeQuery();
			Alumno a;
			while(rs.next()) {
				a = new Alumno();
				a.setNia(rs.getInt("NIA"));
				a.setNombre(rs.getString("Nombre"));
				a.setApellidos(rs.getString("Apellidos"));
				a.setGenero(rs.getString("Genero"));
				a.setFecha_nacimiento(rs.getDate("FechaDeNacimiento"));
				a.setCiclo(rs.getString("Ciclo"));
				a.setCurso(rs.getString("Curso"));
				a.setGrupo(rs.getInt("Grupo"));
				result.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Algo ha fallado");
		}
		return result;
	}

	@Override
	public Alumno mostrarAlumnoPorCodigo(int codigo) {
		// TODO Auto-generated method stub
		Alumno a = null;
		String sql = "SELECT NIA,Nombre,Apellidos,Genero,FechaDeNacimiento,Ciclo,Curso,Grupo FROM alumno WHERE NIA = ?";
		try(Connection conexion = MyDataSource.getConnection();
			PreparedStatement ps = conexion.prepareStatement(sql)) {
			ps.setInt(1, codigo);
			try(ResultSet rs = ps.executeQuery()) {
				while(rs.next()) {
					a = new Alumno();
					a.setNia(rs.getInt("NIA"));
					a.setNombre(rs.getString("Nombre"));
					a.setApellidos(rs.getString("Apellidos"));
					a.setGenero(rs.getString("Genero"));
					a.setFecha_nacimiento(rs.getDate("FechaDeNacimiento"));
					a.setCiclo(rs.getString("Ciclo"));
					a.setCurso(rs.getString("Curso"));
					a.setGrupo(rs.getInt("Grupo"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}


	

}

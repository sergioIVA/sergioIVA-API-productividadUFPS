package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import conexion.Conexion;
import model.Persona;
import model.Estudiante;
import util.ExcepcionProductividad;

public class EstudianteDao {

	final Conexion con = new Conexion();
	
	public EstudianteDao() {
		// TODO Auto-generated constructor stub
	}
	
	public Estudiante createEstudiante(int tipo_identificacion, String nombre, String fecha_nacimiento, String direccion,
			String telefono, String celular, String sexo, String correo_electronico, String foto, String nacionalidad,
			String numero_identificacion, String codigo, int semestre, int id_programa) throws Exception {
		
		Estudiante estudiante = null;
		Connection reg = null;
		PreparedStatement stmt = null;
		int id = -1;
		try {
			reg = con.conectar("");
			String sql = "insert into persona(id, nombre, fecha_nacimiento, direccion, telefono, celular, "
					+ "sexo, correo_electronico, foto, nacionalidad, numero_identificacion, tipo_identificacion) "
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?)";
			String gc[] = {"id"};
			stmt = reg.prepareStatement(sql, gc);
			stmt.setInt(1, 0);
			stmt.setString(2, nombre);
			stmt.setString(3, fecha_nacimiento);
			stmt.setString(4, direccion);
			stmt.setString(5, telefono);
			stmt.setString(6, celular);
			stmt.setString(7, sexo);
			stmt.setString(8, correo_electronico);
			stmt.setString(9, foto);
			stmt.setString(10, nacionalidad);
			stmt.setString(11, numero_identificacion);
			stmt.setInt(12, tipo_identificacion);
			
			if(stmt.executeUpdate() > 0) {
				ResultSet gk = stmt.getGeneratedKeys();
				if(gk.next())
					id = gk.getInt(1);
				
				sql = "insert into participante (id_participante, tipo_participante) values (?, ?)";
				stmt = reg.prepareStatement(sql);
				stmt.setInt(1, id);
				stmt.setInt(2, 2); //2 = estudiante
				stmt.executeUpdate();	
				
				Persona me = new Persona(id, tipo_identificacion, nombre, fecha_nacimiento, direccion, telefono, celular, sexo, correo_electronico, foto, nacionalidad, numero_identificacion);
				
				sql = "insert into estudiante (id_estudiante, codigo, semestre, id_programa) values (?,?,?,?)";
				stmt = reg.prepareStatement(sql);
				stmt.setInt(1, id);
				stmt.setString(2, codigo);
				stmt.setInt(3, semestre);
				stmt.setInt(4, id_programa);
				
				if(stmt.executeUpdate() > 0) {
					estudiante = new Estudiante(id, codigo, semestre, id_programa, me);
				}
			}
		} catch(Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e);
		}
		
		finally {
			con.cerrarConexion();
		}
		
		return estudiante;
	}
	
	public List<Estudiante> getEstudiantes() throws Exception {
		
		List<Estudiante> estudiantes = new LinkedList<Estudiante>();
		Connection reg = null;
		PreparedStatement stmt = null;
		
		try {
			reg = con.conectar("");
			String sql = "select * from estudiante e inner join persona p on p.id = e.id_estudiante";
			stmt = reg.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				estudiantes.add(new Estudiante(rs.getInt("id"), rs.getString("codigo"), rs.getInt("semestre"), rs.getInt("id_programa"), 
						new Persona(rs.getInt("id"), rs.getInt("tipo_identificacion"), rs.getString("nombre"), 
						rs.getString("fecha_nacimiento"), rs.getString("direccion"), rs.getString("telefono"), rs.getString("celular"), 
						rs.getString("sexo"), rs.getString("correo_electronico"), rs.getString("foto"), rs.getString("nacionalidad"), rs.getString("numero_identificacion"))));
			}
		} catch(Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e);
		}
		
		finally {
			con.cerrarConexion();
		}
		
		return estudiantes;
	}
	
	public Estudiante getEstudiante(int id_estudiante) throws Exception {
		
		Estudiante estudiante = null;
		Connection reg = null;
		PreparedStatement stmt = null;
		
		try {
			reg = con.conectar("");
			String sql = "select * from estudiante e inner join persona p on p.id = e.id_estudiante where e.id_estudiante = ?";
			stmt = reg.prepareStatement(sql);
			stmt.setInt(1, id_estudiante);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				estudiante = new Estudiante(rs.getInt("id"), rs.getString("codigo"), rs.getInt("semestre"), rs.getInt("id_programa"), 
						new Persona(rs.getInt("id"), rs.getInt("tipo_identificacion"), rs.getString("nombre"), 
						rs.getString("fecha_nacimiento"), rs.getString("direccion"), rs.getString("telefono"), rs.getString("celular"), 
						rs.getString("sexo"), rs.getString("correo_electronico"), rs.getString("foto"), rs.getString("nacionalidad"), rs.getString("numero_identificacion")));
			}
		} catch(Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e);
		}
		finally {
			con.cerrarConexion();
		}
		
		return estudiante;
	}
	
	public boolean deleteEstudiante(int id_estudiante) throws Exception {
		
		Connection reg = null;
		PreparedStatement stmt = null;
		int value = -1;
		
		try {
			reg = con.conectar("");
			String sql = "delete from estudiante where id_estudiante = ?";
			stmt = reg.prepareStatement(sql);
			stmt.setInt(1, id_estudiante);
			
			if(stmt.executeUpdate() > 0) {
				
				sql = "delete from participante where id_participante = ?";
				stmt = reg.prepareStatement(sql);
				stmt.setInt(1, id_estudiante);
				stmt.executeUpdate();
				
				sql = "delete from persona where id = ?";
				stmt = reg.prepareStatement(sql);
				stmt.setInt(1, id_estudiante);
				
				value = stmt.executeUpdate();
			}
		} catch(Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e);
		}
		
		finally {
			con.cerrarConexion();
		}
		
		return value > 0;
	}
}

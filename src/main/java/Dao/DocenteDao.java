package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import conexion.Conexion;
import model.Persona;
import model.Docente;
import util.ExcepcionProductividad;

public class DocenteDao {

	final Conexion con = new Conexion();
	
	public DocenteDao() {
		// TODO Auto-generated constructor stub
	}
	
	public Docente createDocente(int tipo_identificacion, String nombre, String fecha_nacimiento, String direccion,
			String telefono, String celular, String sexo, String correo_electronico, String foto, String nacionalidad,
			String numero_identificacion, String codigo, int id_departamento, int id_modalidad, int id_semillero_director, 
			int tipo_participante, int tipo_investigador, int estudio) throws Exception {
		
		Docente docente = null;
		Connection reg = null;
		PreparedStatement stmt = null;
		int id = -1;
		try {
			reg  = con.conectar("");
			String sql = "insert into persona(id, nombre, fecha_nacimiento, direccion, telefono, celular, "
					+ "sexo, correo_electronico, foto, nacionalidad, numero_identificacion, tipo_identificacion) "
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?)";
			String generatedColumns[] = { "id" };
			stmt = reg.prepareStatement(sql, generatedColumns);
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
				ResultSet generatedKeys = stmt.getGeneratedKeys();
				if(generatedKeys.next()) 
					id = generatedKeys.getInt(1);
				
				sql = "insert into participante (id_participante, tipo_participante) values (?, ?)";
				stmt = reg.prepareStatement(sql);
				stmt.setInt(1, id);
				stmt.setInt(2, tipo_participante);
				stmt.executeUpdate();
				
				sql = "insert into investigador (id_investigador, id_tipo, id_estudio) values (?, ? ,?)";
				stmt = reg.prepareStatement(sql);
				stmt.setInt(1, id);
				stmt.setInt(2, tipo_investigador);
				stmt.setInt(3, estudio);
				
				Persona me = new Persona(id, tipo_identificacion, nombre, fecha_nacimiento, direccion, telefono, celular, sexo, correo_electronico, foto, nacionalidad, numero_identificacion);
						
				sql = "insert into docente_ufps(id_investigador, codigo, id_departamento, id_modalidad, id_semillero_director) values (?,?,?,?,?)";
				stmt = reg.prepareStatement(sql);
				stmt.setInt(1, id);
				stmt.setString(2, codigo);
				stmt.setInt(3, id_departamento);
				stmt.setInt(4, id_modalidad);
				stmt.setInt(5, id_semillero_director);
				
				if(stmt.executeUpdate() > 0) {
					docente = new Docente(codigo, id, id_departamento, id_modalidad, id_semillero_director, me);
				}
			}
			
		} catch(Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e);
		}
		finally {
			con.cerrarConexion();
		}
		
		return docente;
	}
	
	public List<Docente> getDocentes() throws Exception{
		
		List<Docente> docentes = new LinkedList<Docente>();
		Connection reg = null;
		PreparedStatement stmt = null;
		
		try {
			reg = con.conectar("");
			String sql = "select * from docente_ufps d inner join persona p on p.id = d.id_investigador";
			stmt = reg.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				docentes.add(new Docente(rs.getString("codigo"), rs.getInt("id"), rs.getInt("id_departamento"), rs.getInt("id_modalidad"), 
						rs.getInt("id_semillero_director"), new Persona(rs.getInt("id"), rs.getInt("tipo_identificacion"), rs.getString("nombre"), 
						rs.getString("fecha_nacimiento"), rs.getString("direccion"), rs.getString("telefono"), rs.getString("celular"), 
						rs.getString("sexo"), rs.getString("correo_electronico"), rs.getString("foto"), rs.getString("nacionalidad"), rs.getString("numero_identificacion"))));
			}
		} catch(Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e);
		}
		finally {
			con.cerrarConexion();
		}
		
		return docentes;
	}
	
	public Docente getDocente(int id) throws Exception{
		
		Docente docente = null;
		Connection reg = null;
		PreparedStatement stmt = null;
		
		try {
			reg = con.conectar("");
			String sql = "select * from docente_ufps d inner join persona p on p.id = d.id_investigador where d.id_investigador = ?";
			stmt = reg.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				docente = new Docente(rs.getString("codigo"), rs.getInt("id"), rs.getInt("id_departamento"), rs.getInt("id_modalidad"), 
						rs.getInt("id_semillero_director"), new Persona(rs.getInt("id"), rs.getInt("tipo_identificacion"), rs.getString("nombre"), 
						rs.getString("fecha_nacimiento"), rs.getString("direccion"), rs.getString("telefono"), rs.getString("celular"), 
						rs.getString("sexo"), rs.getString("correo_electronico"), rs.getString("foto"), rs.getString("nacionalidad"), rs.getString("numero_identificacion")));
			}
		} catch(Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e);
		}
		finally {
			con.cerrarConexion();
		}
		
		return docente;
	}
	
	public boolean deleteDocente(int id) throws Exception{
		
		Connection reg = null;
		PreparedStatement stmt = null;
		int value = -1;
		
		try {
			reg = con.conectar("");
			String sql = "delete from docente_ufps where id_investigador = ?";
			stmt = reg.prepareStatement(sql);
			stmt.setInt(1, id);
			
			if(stmt.executeUpdate() > 0) {
				sql = "delete from investigador where id_investigador = ?";
				stmt = reg.prepareStatement(sql);
				stmt.setInt(1, id);
				stmt.executeUpdate();
				
				sql = "delete from participante where id_participante = ?";
				stmt = reg.prepareStatement(sql);
				stmt.setInt(1, id);
				stmt.executeUpdate();
				
				sql = "delete from persona where id = ?";
				stmt = reg.prepareStatement(sql);
				stmt.setInt(1, id);
				
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

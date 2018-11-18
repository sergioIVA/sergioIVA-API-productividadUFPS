package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import conexion.Conexion;
import model.Persona;
import model.Externo;
import util.ExcepcionProductividad;

public class ExternoDao {

	final Conexion con = new Conexion();
	
	public ExternoDao() {
		
	}
	
	public Externo createExterno(int tipo_identificacion, String nombre, String fecha_nacimiento, String direccion,
			String telefono, String celular, String sexo, String correo_electronico, String foto, String nacionalidad,
			String numero_identificacion, String institucion_empresa, String cargo, String profesion) throws Exception {
		
		Externo externo = null;
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
				
				sql = "insert into investigador_externo(id_investigador, institucion_empresa, cargo, profesion) values (?, ?, ?, ?)";
				stmt = reg.prepareStatement(sql);
				stmt.setInt(1, id);
				stmt.setString(2, institucion_empresa);
				stmt.setString(3, cargo);
				stmt.setString(4, profesion);
				
				Persona me = new Persona(id, tipo_identificacion, nombre, fecha_nacimiento, direccion, telefono, celular, sexo, correo_electronico, foto, nacionalidad, numero_identificacion);
				
				if(stmt.executeUpdate() > 0)
					externo = new Externo(id, institucion_empresa, cargo, profesion, me);
			}
		} catch (Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e);
		}
		finally {
			con.cerrarConexion();
		}
		
		return externo;
	}
	
	public List<Externo> getExternos() throws Exception {
		
		List<Externo> externos = new LinkedList<Externo>();
		Connection reg = null;
		PreparedStatement stmt = null;
		
		try {
			reg = con.conectar("");
			String sql = "select * from investigador_externo e inner join persona p on p.id = e.id_investigador";
			stmt = reg.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				externos.add(new Externo(rs.getInt("id"), rs.getString("institucion_empresa"), rs.getString("cargo"), rs.getString("profesion"), 
						new Persona(rs.getInt("id"), rs.getInt("tipo_identificacion"), rs.getString("nombre"), 
						rs.getString("fecha_nacimiento"), rs.getString("direccion"), rs.getString("telefono"), rs.getString("celular"), 
						rs.getString("sexo"), rs.getString("correo_electronico"), rs.getString("foto"), rs.getString("nacionalidad"), rs.getString("numero_identificacion"))));
			}
		} catch(Exception e) {
			throw new ExcepcionProductividad("Error dels ervidor: " + e);
		}
		finally {
			con.cerrarConexion();
		}
		return externos;
	}
	
	public Externo getExterno(int id_investigador) throws Exception {
		
		Externo externo = null;
		Connection reg = null;
		PreparedStatement stmt = null;
		
		try {
			reg = con.conectar("");
			String sql = "select * from investigador_externo e inner join persona p on p.id = e.id_investigador where e.id_investigador = ?";
			stmt = reg.prepareStatement(sql);
			stmt.setInt(1, id_investigador);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
				externo = new Externo(rs.getInt("id"), rs.getString("institucion_empresa"), rs.getString("cargo"), rs.getString("profesion"), 
						new Persona(rs.getInt("id"), rs.getInt("tipo_identificacion"), rs.getString("nombre"), 
						rs.getString("fecha_nacimiento"), rs.getString("direccion"), rs.getString("telefono"), rs.getString("celular"), 
						rs.getString("sexo"), rs.getString("correo_electronico"), rs.getString("foto"), rs.getString("nacionalidad"), rs.getString("numero_identificacion")));
		} catch (Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e);
		}
		finally {
			con.cerrarConexion();
		}
		return externo;
	}
	
	public boolean deleteExterno(int id_investigador) throws Exception {
		
		Connection reg = null;
		PreparedStatement stmt = null;
		int value = -1;
		
		try {
			reg = con.conectar("");
			String sql = "delete from investigador_externo where id_investigador = ?";
			stmt = reg.prepareStatement(sql);
			stmt.setInt(1, id_investigador);
			
			if(stmt.executeUpdate() > 0) {
				sql = "delete from persona where id = ?";
				stmt = reg.prepareStatement(sql);
				stmt.setInt(1, id_investigador);
				
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

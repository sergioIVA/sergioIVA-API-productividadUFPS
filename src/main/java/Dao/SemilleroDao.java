package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;


import conexion.Conexion;
import model.Semillero;
import util.ExcepcionProductividad;

public class SemilleroDao {

	final Conexion con = new Conexion();
	
	public SemilleroDao() {
		
	}
	
	public Semillero createSemillero(String codigo, String nombre, String sigla, String ubicacion, 
			String fecha_creacion, int id_grupo, int id_linea_grupo, int id_director, String correo) throws Exception {
		
		Semillero semillero = null;
		int id = -1;
		try {
			Connection reg = con.conectar("");
			String sql = "insert into semillero(id,codigo,nombre,sigla,ubicacion,fecha_creacion,"
					+ "id_grupo,id_linea_grupo,id_director,correo) values (?,?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement pst;
			String generatedColumns[] = { "id" };
			pst = reg.prepareStatement(sql, generatedColumns);
			
			pst.setInt(1, 0);
			pst.setString(2, codigo);
			pst.setString(3, nombre);
			pst.setString(4, sigla);
			pst.setString(5, ubicacion);
			pst.setString(6, fecha_creacion);
			pst.setInt(7, id_grupo);
			pst.setInt(8, id_linea_grupo);
			pst.setInt(9, id_director);
			pst.setString(10, correo);
			pst.executeUpdate();
			
			ResultSet generatedKeys = pst.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getInt(1);
			}
				
				
			
		} catch(Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e);
		}
		finally {
			con.cerrarConexion();
		}
		semillero = new Semillero(id, codigo, nombre, sigla, ubicacion, fecha_creacion, id_grupo, correo, id_linea_grupo, id_director);
		return semillero;
	}
	
	public Object getSemilleros() throws Exception {
		
		LinkedHashMap<String, Object> semilleros = new LinkedHashMap<String, Object>();
		LinkedHashMap<String, Object> director = new LinkedHashMap<String, Object>();
		LinkedHashMap<String, Object> linea_investigacion = new LinkedHashMap<String, Object>();
		LinkedHashMap<String, Object> grupo = new LinkedHashMap<String, Object>();
		
		try {
			Connection reg = con.conectar("");
			String sql = "select s.id, s.codigo, s.nombre, s.sigla, s.ubicacion, s.fecha_creacion, s.id_grupo, s.id_linea_grupo, s.id_director, "
					+ "s.correo,  g.nombre grupo, li.nombre linea_investigacion, p.nombre director, p.correo_electronico correo_director, p.celular contacto "
					+ "from semillero s inner join grupo_investigacion g on s.id_grupo = g.id "
					+ "inner join linea_grupo lg on s.id_linea_grupo = lg.id_linea "
					+ "inner join linea_investigacion li on li.id = lg.id_linea "
					+ "inner join persona p on p.id = s.id_director";
			
			PreparedStatement stmt = reg.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				semilleros.put("id", rs.getInt("id"));
				semilleros.put("codigo", rs.getString("codigo"));
				semilleros.put("nombre", rs.getString("nombre"));
				semilleros.put("sigla", rs.getString("sigla"));
				semilleros.put("ubicacion", rs.getString("ubicacion"));
				semilleros.put("fecha-creacion", rs.getString("fecha_creacion"));
				semilleros.put("correo", rs.getString("correo"));
				
				director.put("id", rs.getInt("id_director"));
				director.put("nombre", rs.getString("director"));
				
				linea_investigacion.put("id", rs.getInt("id_linea_grupo"));
				linea_investigacion.put("nombre", rs.getString("linea_investigacion"));
				
				grupo.put("id", rs.getInt("id_grupo"));
				grupo.put("nombre", rs.getString("grupo"));
				
				semilleros.put("director", director);
				semilleros.put("linea-investigacion", linea_investigacion);
				semilleros.put("grupo-investigacion", grupo);
			}
		} catch(Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e); 
		}
		finally {
			con.cerrarConexion();
		}
		
		return semilleros;
	}
	
	public Object getSemillero(int id) throws Exception {
		
		LinkedHashMap<String, Object> semillero = new LinkedHashMap<String, Object>();
		LinkedHashMap<String, Object> director = new LinkedHashMap<String, Object>();
		LinkedHashMap<String, Object> linea_investigacion = new LinkedHashMap<String, Object>();
		LinkedHashMap<String, Object> grupo = new LinkedHashMap<String, Object>();
		
		try {
			Connection reg = con.conectar("");
			String sql = "select s.id, s.codigo, s.nombre, s.sigla, s.ubicacion, s.fecha_creacion, s.id_grupo, s.id_linea_grupo, s.id_director, "
					+ "s.correo,  g.nombre grupo, li.nombre linea_investigacion, p.nombre director, p.correo_electronico correo_director, p.celular contacto "
					+ "from semillero s inner join grupo_investigacion g on s.id_grupo = g.id "
					+ "inner join linea_grupo lg on s.id_linea_grupo = lg.id_linea "
					+ "inner join linea_investigacion li on li.id = lg.id_linea "
					+ "inner join persona p on p.id = s.id_director where s.id = ?";
			
			PreparedStatement stmt = reg.prepareStatement(sql);
			stmt.setInt(1,id);
			
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				semillero.put("id", rs.getInt("id"));
				semillero.put("codigo", rs.getString("codigo"));
				semillero.put("nombre", rs.getString("nombre"));
				semillero.put("sigla", rs.getString("sigla"));
				semillero.put("ubicacion", rs.getString("ubicacion"));
				semillero.put("fecha-creacion", rs.getString("fecha_creacion"));
				semillero.put("correo", rs.getString("correo"));
				
				director.put("id", rs.getInt("id_director"));
				director.put("nombre", rs.getString("director"));
				
				linea_investigacion.put("id", rs.getInt("id_linea_grupo"));
				linea_investigacion.put("nombre", rs.getString("linea_investigacion"));
				
				grupo.put("id", rs.getInt("id_grupo"));
				grupo.put("nombre", rs.getString("grupo"));
				
				semillero.put("director", director);
				semillero.put("linea-investigacion", linea_investigacion);
				semillero.put("grupo-investigacion", grupo);
			}
		} catch(Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e); 
		}
		finally {
			con.cerrarConexion();
		}
		
		return semillero;
	}
	
	public boolean deleteSemillero(int id) throws Exception {
		
		int value = -1;
		try {
			Connection reg = con.conectar("");
			String sql = "delete from semillero where id = ?";
			PreparedStatement stmt = reg.prepareStatement(sql);
			stmt.setInt(1, id);
			value = stmt.executeUpdate();
		} catch(Exception e) {
			throw new ExcepcionProductividad("Error del Servidor: " + e);
		}
		finally {
			con.cerrarConexion();
		}
		
		return value > 0;
	}
}

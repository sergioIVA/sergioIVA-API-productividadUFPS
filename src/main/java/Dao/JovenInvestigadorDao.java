package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import conexion.Conexion;
import model.Persona;
import model.JovenInvestigador;
import util.ExcepcionProductividad;

public class JovenInvestigadorDao {

	final Conexion con = new Conexion();
	
	public JovenInvestigadorDao() {
		
	}
	
	public JovenInvestigador createJoven(int tipo_identificacion, String nombre, String fecha_nacimiento, String direccion,
			String telefono, String celular, String sexo, String correo_electronico, String foto, String nacionalidad,
			String numero_identificacion, String propuesta_desarrollada, String periodo_beca, String modalidad, int tutor) throws Exception {
		
		JovenInvestigador joven = null;
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
				
				Persona me = new Persona(id, tipo_identificacion, nombre, fecha_nacimiento, direccion, telefono, celular, sexo, correo_electronico, foto, nacionalidad, numero_identificacion);

				sql = "insert into joven_investigador (id_investigador, propuesta_desarrollada, periodo_beca, modalidad, tutor) values (?,?,?,?,?)";
				stmt = reg.prepareStatement(sql);
				stmt.setInt(1, id);
				stmt.setString(2, propuesta_desarrollada);
				stmt.setString(3, periodo_beca);
				stmt.setString(4, modalidad);
				stmt.setInt(5, tutor);
				
				if(stmt.executeUpdate() > 0)
					joven = new JovenInvestigador(id, propuesta_desarrollada, periodo_beca, modalidad, tutor, me);
			}
		} catch(Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e);
		}
		finally {
			con.cerrarConexion();
		}
		return joven;
	}
	
	public List<JovenInvestigador> getJovenes() throws Exception {
		
		List<JovenInvestigador> jovenes = new LinkedList<JovenInvestigador>();
		Connection reg = null;
		PreparedStatement stmt = null;
		
		try {
			reg = con.conectar("");
			String sql = "select * from joven_investigador j inner join persona p on p.id = j.id_investigador";
			stmt = reg.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				jovenes.add(new JovenInvestigador(rs.getInt("id"), rs.getString("propuesta_desarrollada"), rs.getString("periodo_beca"), rs.getString("modalidad"), rs.getInt("tutor"), 
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
		return jovenes;
	}
	
	public JovenInvestigador getJoven(int id_investigador) throws Exception {
		
		JovenInvestigador joven = null;
		Connection reg = null;
		PreparedStatement stmt = null;
		
		try {
			reg = con.conectar("");
			String sql = "select * from joven_investigador inner join persona p on p.id = j.id_investigador where j.id_investigador = ?";
			stmt = reg.prepareStatement(sql);
			stmt.setInt(1, id_investigador);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
				joven = new JovenInvestigador(rs.getInt("id"), rs.getString("propuesta_desarrollada"), rs.getString("periodo_beca"), rs.getString("modalidad"), rs.getInt("tutor"), 
						new Persona(rs.getInt("id"), rs.getInt("tipo_identificacion"), rs.getString("nombre"), 
						rs.getString("fecha_nacimiento"), rs.getString("direccion"), rs.getString("telefono"), rs.getString("celular"), 
						rs.getString("sexo"), rs.getString("correo_electronico"), rs.getString("foto"), rs.getString("nacionalidad"), rs.getString("numero_identificacion")));
		} catch(Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e);
		}
		finally {
			con.cerrarConexion();
		}
		return joven;
	}
	
	public boolean deleteJoven(int id_investigador) throws Exception {
		
		Connection reg = null;
		PreparedStatement stmt = null;
		int value = -1;
		
		try {
			reg = con.conectar("");
			String sql = "delete from joven_investigador where id_investigador = ?";
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

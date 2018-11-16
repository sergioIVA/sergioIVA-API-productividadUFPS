package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import conexion.Conexion;
import model.SubTipologia;

public class SubTipologiaDao {
	
	static final Conexion con = new Conexion();
	
	public SubTipologiaDao() {
		// TODO Auto-generated constructor stub
	}
	
	public SubTipologia crearSubTipologia(int id_tipologia, String nombre) throws SQLException {
		
		Connection reg = null;
		String sql = "insert into subtipo_tipologia(id,nombre,tipologia_producto_id) values(?,?,?)";
		String generatedColumns[] = {"id"};
		PreparedStatement stmt = reg.prepareStatement(sql, generatedColumns);
		stmt.setInt(1, 0);
		stmt.setString(2, nombre);
		stmt.setInt(3, id_tipologia);
		
		int id = -1;
		
		if(stmt.executeUpdate() > 0) {
			ResultSet generatedKeys = stmt.getGeneratedKeys();
			if(generatedKeys.next())
				id = generatedKeys.getInt(1);
		}
		
		con.cerrarConexion();
		return new SubTipologia(id, id_tipologia, nombre);
	}
	
	public List<SubTipologia> getSubTipologias() throws SQLException {
		
		List<SubTipologia> subtipologias = new LinkedList<SubTipologia>();
		Connection reg = con.conectar("");
		Statement stmt = reg.createStatement();
		String sql = "select * from subtipo_tipologia order by id";
		
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			subtipologias.add(new SubTipologia(rs.getInt("id"),rs.getInt("tipologia_producto_id"), rs.getString("nombre")));
		}
		
		con.cerrarConexion();
		return subtipologias;
	}
	
	public List<SubTipologia> getSubTipologiaDeTipo(int id_tipologia) throws SQLException {
		
		List<SubTipologia> subtipologias = new LinkedList<SubTipologia>();
		Connection reg = con.conectar("");
		String sql = "select * from subtipo_tipologia where tipologia_producto_id = ?";
		PreparedStatement stmt = reg.prepareStatement(sql);
		stmt.setInt(1, id_tipologia);
		
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			subtipologias.add(new SubTipologia(rs.getInt("id"),rs.getInt("tipologia_producto_id"), rs.getString("nombre")));
		}
		
		con.cerrarConexion();
		return subtipologias;
	}
	
	public SubTipologia getSpecificSubTipologia(int id) throws SQLException {
		
		Connection reg = con.conectar("");
		String sql = "select * from subtipo_tipologia where id = ?";
		PreparedStatement stmt = reg.prepareStatement(sql);
		stmt.setInt(1, id);
		
		ResultSet rs = stmt.executeQuery();
		if(rs.next())
			return new SubTipologia(rs.getInt("id"), rs.getInt("tipologia_producto_id"), rs.getString("nombre"));
		
		con.cerrarConexion();
		return null;
	}
	
	public boolean deleteSubTipologia(int id) throws SQLException {
		
		Connection reg = con.conectar("");
		int value = -1;
		
		String sql = "delete from subtipo_tipologia where id = ?";
		PreparedStatement stmt = reg.prepareStatement(sql);
		stmt.setInt(1, id);
		
		value = stmt.executeUpdate();
		
		con.cerrarConexion();
		return value > 0;
	}

}

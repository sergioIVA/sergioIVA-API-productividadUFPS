package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import conexion.Conexion;
import model.Tipologia;

public class TipologiaDao {

	static final Conexion con = new Conexion();
	
	public TipologiaDao() {
		// TODO
	}
	
	public Tipologia createTipologia(String nombre) throws SQLException {
		
		Connection reg = con.conectar("");
		String sql = "insert into tipologia_producto(id, nombre) values (?, ?)";
		String generatedColumns[] = {"id"};
		PreparedStatement stmt = reg.prepareStatement(sql, generatedColumns);
		stmt.setInt(1, 0);
		stmt.setString(2, nombre);
		
		int id = -1;
		
		if(stmt.executeUpdate() > 0) {
			ResultSet generatedKeys = stmt.getGeneratedKeys();
			if(generatedKeys.next())
				id = generatedKeys.getInt(1);
		}
			
		con.cerrarConexion();
		
		return new Tipologia(id,  nombre);
	}
	
	public List<Tipologia> getTipologias() throws SQLException {
		
		List<Tipologia> resp = new LinkedList<Tipologia>();
		Connection reg = con.conectar("");
		Statement stmt = reg.createStatement();
		String sql = "select * from tipologia_producto order by id";
		
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			resp.add(new Tipologia(rs.getInt("id"), rs.getString("nombre")));
		}
		
		con.cerrarConexion();
		
		return resp;
	}
	
	public Tipologia getTipologia(int id) throws SQLException {
		
		Connection reg = con.conectar("");
		Tipologia tipologia = null;
		
		String sql = "select * from tipologia_producto where id = ?";
		PreparedStatement stmt = reg.prepareStatement(sql);
		stmt.setInt(1, id);
		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			tipologia = new Tipologia(rs.getInt("id"), rs.getString("nombre"));
		}
		
		con.cerrarConexion();
		return tipologia;
	}
	
	public Tipologia updateTipologia(Tipologia tipologia, String nombre) throws SQLException {
		
		Connection reg = con.conectar("");
		String sql = "update tipologia_producto set nombre = ? where id = ?";
		PreparedStatement stmt = reg.prepareStatement(sql);
		stmt.setString(1, nombre);
		stmt.setInt(2, tipologia.getId());
		
		if(stmt.executeUpdate() > 0)
			tipologia.setNombre(nombre);
		
		con.cerrarConexion();
		return tipologia;
	}
	
	public boolean deleteTipologia(int id) throws SQLException {
		
		Connection reg = con.conectar("");
		int value = -1;
		
		String sql = "delete from tipologia_producto where id = ?";
		PreparedStatement stmt = reg.prepareStatement(sql);
		stmt.setInt(1, id);
		
		value = stmt.executeUpdate();
		
		return value > 0;
	}
}

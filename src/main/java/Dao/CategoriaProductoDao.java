package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import conexion.Conexion;
import model.CategoriaProducto;

public class CategoriaProductoDao {
	
	static final Conexion con = new Conexion();
	
	public CategoriaProductoDao() {
		// TODO Auto-generated constructor stub
	}
	
	public CategoriaProducto crearCategoriaProducto(int id_tipo_producto, int id_subtipo_producto, String nombre) throws SQLException {
		
		Connection reg = con.conectar("");
		String sql = "insert into categoria(id, nombre, id_tipo_producto, id_subtipo_producto) values(?,?,?,?)";
		String generatedColumns[] = {"id"};
		PreparedStatement stmt = reg.prepareStatement(sql, generatedColumns);
		stmt.setInt(1, 0);
		stmt.setInt(2, id_tipo_producto);
		stmt.setInt(3, id_subtipo_producto);
		stmt.setString(4, nombre);
		
		int id = -1;
		
		if(stmt.executeUpdate() > 0) {
			ResultSet generatedKeys = stmt.getGeneratedKeys();
			if(generatedKeys.next())
				id = generatedKeys.getInt(1);
		}
		
		con.cerrarConexion();
		return new CategoriaProducto(id, id_tipo_producto, id_subtipo_producto, nombre);
	}
	
	public List<CategoriaProducto> getCategoriasXTipologia(int id_tipo_producto) throws SQLException {
		
		List<CategoriaProducto> categorias = new LinkedList<CategoriaProducto>();
		Connection reg = con.conectar("");
		String sql = "select * from categoria where id_tipo_producto = ?";
		PreparedStatement stmt = reg.prepareStatement(sql);
		stmt.setInt(1, id_tipo_producto);
		
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			categorias.add(new CategoriaProducto(rs.getInt("id"), rs.getInt("id_tipo_producto"), rs.getInt("id_subtipo_producto"), rs.getString("nombre")));
		}
		
		con.cerrarConexion();
		return categorias;
	}
	
	public List<CategoriaProducto> getCategoriasXSubTipo(int id_subtipo_producto) throws SQLException {
		
		List<CategoriaProducto> categorias = new LinkedList<CategoriaProducto>();
		Connection reg = con.conectar("");
		String sql = "select * from categoria where id_subtipo_producto = ?";
		PreparedStatement stmt = reg.prepareStatement(sql);
		stmt.setInt(1, id_subtipo_producto);
		
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			categorias.add(new CategoriaProducto(rs.getInt("id"), rs.getInt("id_tipo_producto"), rs.getInt("id_subtipo_producto"), rs.getString("nombre")));
		}
		
		con.cerrarConexion();
		return categorias;
	}
	
	public CategoriaProducto getSpecificCategoria(int id) throws SQLException {
		
		Connection reg = con.conectar("");
		String sql =  "select * from categoria where id = ?";
		PreparedStatement stmt = reg.prepareStatement(sql);
		stmt.setInt(1, id);
		
		ResultSet rs = stmt.executeQuery();
		if(rs.next())
			return new CategoriaProducto(rs.getInt("id"), rs.getInt("id_tipo_producto"), rs.getInt("id_subtipo_producto"), rs.getString("nombre"));
		
		con.cerrarConexion();
		return null;
	}
	
	public boolean deleteCategoria(int id) throws SQLException {
		
		Connection reg = con.conectar("");
		int value = -1;
		
		String sql = "delete from categoria where id = ?";
		PreparedStatement stmt = reg.prepareStatement(sql);
		stmt.setInt(1, id);
		
		value = stmt.executeUpdate();
		
		con.cerrarConexion();
		return value > 0;
	}
}

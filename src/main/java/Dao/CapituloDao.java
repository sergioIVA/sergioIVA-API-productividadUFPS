package Dao;

import model.Capitulo;
import util.ExcepcionProductividad;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import conexion.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CapituloDao {

	final Conexion con = new Conexion();
	
	public CapituloDao() {}
	
	public Capitulo createCapitulo(int tipo_desarrollo_id, String titulo_libro, String titulo_capitulo,
			String iSBN_librol, String fecha_publica, String autores, String editorial, String lugar_publica,
			String certificacion_entidad, String curriculo_capitulo, String nombre, String descripcion, int id_proyecto) throws Exception {
		
		Capitulo cap = null;
		int id = -1;
		try {
			Connection reg = con.conectar("");
			String sql = "insert into producto(id, nombre, id_proyecto, descripcion, id_tipo_producto) values (?,?,?,?,?)";
			String[] generatedCols = { "id" };
			PreparedStatement stmt = reg.prepareStatement(sql, generatedCols);
			stmt.setInt(1, 0);
			stmt.setString(2, nombre);
			stmt.setInt(3, id_proyecto);
			stmt.setString(4, descripcion);
			stmt.setInt(5, 3);
			
			if(stmt.executeUpdate() > 0) {
				ResultSet keys = stmt.getGeneratedKeys();
				if(keys.next())
					id = keys.getInt(1);
				
				sql = "insert into capitulo(producto_id, titulo_libro, titulo_capitulo, ISBN_librol, fecha_publica, autores, editorial, lugar_publica, "
						+ "tipo_desarrollo_id, certificacion_entidad, curriculo_capitulo) values (?,?,?,?,?,?,?,?,?,?,?)";
				stmt = reg.prepareStatement(sql);
				stmt.setInt(1, id);
				stmt.setString(2, titulo_libro);
				stmt.setString(3, titulo_capitulo);
				stmt.setString(4, iSBN_librol);
				stmt.setString(5, fecha_publica);
				stmt.setString(6, autores);
				stmt.setString(7, editorial);
				stmt.setString(8, lugar_publica);
				stmt.setInt(9, tipo_desarrollo_id);
				stmt.setString(10, certificacion_entidad);
				stmt.setString(11, curriculo_capitulo);
				
				if(stmt.executeUpdate() > 0)
					cap = new Capitulo(id, tipo_desarrollo_id, titulo_libro, titulo_capitulo, iSBN_librol, fecha_publica, autores, editorial, lugar_publica, certificacion_entidad, curriculo_capitulo);
			}
		} catch(Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e);
		} finally {
			con.cerrarConexion();
		}
		return cap;
	}
	
	public Object getCapitulos() throws Exception {
		
		LinkedHashMap<String, Object> capitulo = null;
		LinkedHashMap<String, Object> producto = null;
		LinkedList<Object> capitulos = new LinkedList<Object>();
		
		try {
			Connection reg = con.conectar("");
			String sql = "select p.nombre, tp.nombre tipo, c.* from capitulo c "
					+ "inner join producto p on c.producto_id = p.id inner join tipo_producto tp on tp.id = p.id_tipo_producto";
			PreparedStatement stmt = reg.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				producto = new LinkedHashMap<String, Object>();
				producto.put("id", rs.getInt("producto_id"));
				producto.put("nombre", rs.getString("nombre"));
				producto.put("tipo-producto", rs.getString("tipo"));
				
				capitulo = new LinkedHashMap<String, Object>();
				capitulo.put("producto", producto);
				capitulo.put("libro", rs.getString("titulo_libro"));
				capitulo.put("titulo", rs.getString("titulo_capitulo"));
				capitulo.put("isbn-libro", rs.getString("ISBN_librol"));
				capitulo.put("fecha-publicacion", rs.getString("fecha_publica"));
				capitulo.put("autores", rs.getString("autores"));
				capitulo.put("editorial", rs.getString("editorial"));
				capitulo.put("lugar-publicacion", rs.getString("lugar_publica"));
				capitulo.put("tipo-desarrollo", rs.getInt("tipo_desarrollo_id"));
				capitulo.put("certificacion-entidad", rs.getString("certificacion_entidad"));
				capitulo.put("curriculo", rs.getString("curriculo_capitulo"));
				
				capitulos.add(capitulo);
			}
		} catch(Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e);
		} finally {
			con.cerrarConexion();
		}
		return capitulos;
	}
	
	public Object getCapitulo(int id_producto) throws Exception {

		LinkedHashMap<String, Object> capitulo = null;
		LinkedHashMap<String, Object> producto = null;
		LinkedList<Object> capitulos = new LinkedList<Object>();
		
		try {
			Connection reg = con.conectar("");
			String sql = "select p.nombre, tp.nombre tipo, c.* from capitulo c "
					+ "inner join producto p on c.producto_id = p.id inner join tipo_producto tp on tp.id = p.id_tipo_producto "
					+ "where c.producto_id = ?";
			PreparedStatement stmt = reg.prepareStatement(sql);
			stmt.setInt(1, id_producto);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				producto = new LinkedHashMap<String, Object>();
				producto.put("id", rs.getInt("producto_id"));
				producto.put("nombre", rs.getString("nombre"));
				producto.put("tipo-producto", rs.getString("tipo"));
				
				capitulo = new LinkedHashMap<String, Object>();
				capitulo.put("producto", producto);
				capitulo.put("libro", rs.getString("titulo_libro"));
				capitulo.put("titulo", rs.getString("titulo_capitulo"));
				capitulo.put("isbn-libro", rs.getString("ISBN_librol"));
				capitulo.put("fecha-publicacion", rs.getString("fecha_publica"));
				capitulo.put("autores", rs.getString("autores"));
				capitulo.put("editorial", rs.getString("editorial"));
				capitulo.put("lugar-publicacion", rs.getString("lugar_publica"));
				capitulo.put("tipo-desarrollo", rs.getInt("tipo_desarrollo_id"));
				capitulo.put("certificacion-entidad", rs.getString("certificacion_entidad"));
				capitulo.put("curriculo", rs.getString("curriculo_capitulo"));
				
				capitulos.add(capitulo);
			}
		} catch(Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e);
		} finally {
			con.cerrarConexion();
		}
		return capitulos;
	}
}

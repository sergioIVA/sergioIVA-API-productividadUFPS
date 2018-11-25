package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.LinkedHashMap;

import conexion.Conexion;
import model.Articulo;
import util.ExcepcionProductividad;

public class ArticuloDao {

	final Conexion con = new Conexion();
	
	public Articulo createArticulo(int id_producto, int tipo_referencia, String nombre_revista, String titulo_articulo, String autores,
			String anio, String mes, String volumen, String numero, String paginas_ini, String paginas_final,
			String iSSN, String paginaWeb, String dOI) throws Exception {
		
		try {
			Connection reg = con.conectar("");
			String SQL = "insert into articulo(id_producto, tipo_referencia, nombre_revista, titulo_articulo, autores, "
					+ "anio, mes, volumen, numero, paginas_ini, paginas_final, ISSN, paginaWeb, DOI) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = reg.prepareStatement(SQL);
			stmt.setInt(1, id_producto);
			stmt.setInt(2, tipo_referencia);
			stmt.setString(3, nombre_revista);
			stmt.setString(4, titulo_articulo);
			stmt.setString(5, autores);
			stmt.setString(6, anio);
			stmt.setString(7, mes);
			stmt.setString(8, volumen);
			stmt.setString(9, numero);
			stmt.setString(10, paginas_ini);
			stmt.setString(11, paginas_final);
			stmt.setString(12, iSSN);
			stmt.setString(13, paginaWeb);
			stmt.setString(14, dOI);
			
			if(stmt.executeUpdate() > 0)
				return new Articulo(id_producto, tipo_referencia, nombre_revista, titulo_articulo, autores, anio, mes, volumen, numero, paginas_ini, paginas_final, iSSN, paginaWeb, dOI);
		} catch(Exception e) {
			throw new ExcepcionProductividad("error del servidor: " + e);
		} finally {
			con.cerrarConexion();
		}
		return null;
	}
	
	public boolean deleteArticulo(int id_producto) throws Exception {
		
		int value = -1;
		try {
			Connection reg = con.conectar("");
			String SQL = "delete * from articulo where id_producto = ?";
			PreparedStatement stmt = reg.prepareStatement(SQL);
			stmt.setInt(1, id_producto);
			
			value = stmt.executeUpdate();
		} catch(Exception e) {
			throw new ExcepcionProductividad("error del servidor: " + e);
		} finally {
			con.cerrarConexion();
		}
		return value > 0;
	}
	
	public LinkedList<Object> getArticulos() throws Exception {
		
		LinkedHashMap<String, Object> articulo = new LinkedHashMap<String, Object>();
		LinkedHashMap<String, Object> producto = new LinkedHashMap<String, Object>();
		
		LinkedList<Object> articulos = new LinkedList<Object>();
		try {
			Connection reg = con.conectar("");
			String SQL = "select a.*, p.nombre from articulo a inner join producto p on p.id = a.id_producto";
			PreparedStatement stmt = reg.prepareStatement(SQL);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				producto.put("id-producto", rs.getInt("id_producto"));
				producto.put("nombre", rs.getString("nombre"));
				
				articulo.put("producto", producto);
				articulo.put("tipo-referencia", rs.getInt("tipo_referencia"));
				articulo.put("nombre-revista", rs.getString("nombre_revista"));
				articulo.put("titulo-articulo", rs.getString("titulo_articulo"));
				articulo.put("autores", rs.getString("autores"));
				articulo.put("anio", rs.getString("anio"));
				articulo.put("mes", rs.getString("mes"));
				articulo.put("volumen", rs.getString("volumen"));
				articulo.put("numero", rs.getString("numero"));
				articulo.put("paginas-ini", rs.getString("paginas_ini"));
				articulo.put("paginas-final", rs.getString("paginas_final"));
				articulo.put("ISSN", rs.getString("ISSN"));
				articulo.put("pagina-web", rs.getString("paginaWeb"));
				articulo.put("DOI", rs.getString("DOI"));
				
				articulos.add(articulo);
			}
		} catch(Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e);
		} finally {
			con.cerrarConexion();
		}
		return articulos;
	}
	
	public LinkedList<Object> getArticuloProducto(int id_producto) throws Exception {
		
		LinkedHashMap<String, Object> articulo = new LinkedHashMap<String, Object>();
		LinkedHashMap<String, Object> producto = new LinkedHashMap<String, Object>();
		
		LinkedList<Object> articulos = new LinkedList<Object>();
		try {
			Connection reg = con.conectar("");
			String SQL = "select a.*, p.nombre from articulo a inner join producto p on p.id = a.id_producto where a.id_producto = ?";
			PreparedStatement stmt = reg.prepareStatement(SQL);
			stmt.setInt(1, id_producto);
			
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				producto.put("id-producto", rs.getInt("id_producto"));
				producto.put("nombre", rs.getString("nombre"));
				
				articulo.put("producto", producto);
				articulo.put("tipo-referencia", rs.getInt("tipo_referencia"));
				articulo.put("nombre-revista", rs.getString("nombre_revista"));
				articulo.put("titulo-articulo", rs.getString("titulo_articulo"));
				articulo.put("autores", rs.getString("autores"));
				articulo.put("anio", rs.getString("anio"));
				articulo.put("mes", rs.getString("mes"));
				articulo.put("volumen", rs.getString("volumen"));
				articulo.put("numero", rs.getString("numero"));
				articulo.put("paginas-ini", rs.getString("paginas_ini"));
				articulo.put("paginas-final", rs.getString("paginas_final"));
				articulo.put("ISSN", rs.getString("ISSN"));
				articulo.put("pagina-web", rs.getString("paginaWeb"));
				articulo.put("DOI", rs.getString("DOI"));
				
				articulos.add(articulo);
			}
		} catch(Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e);
		} finally {
			con.cerrarConexion();
		}
		return articulos;
	}
}

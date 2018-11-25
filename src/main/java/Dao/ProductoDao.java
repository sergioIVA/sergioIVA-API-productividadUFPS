package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import conexion.Conexion;
import model.Producto;
import util.ExcepcionProductividad;

public class ProductoDao {

	static final Conexion con = new Conexion();
	
	public ProductoDao() {}
	
	public Producto createProductoEmpresarial(String nombre, String descripcion, int id_proyecto, int id_tipo_producto, String valor_contrato, String numero_contrato, String nit, String certificado_camara, 
			String certificado_institucional, String autores, String certificacion_implementacion, String documento_ley, String certificacion_producto, String nombre_empresa, String fecha, 
			String titulo, String nombre_innovacion) throws Exception {
		
		Producto producto = null;
		
		try {
			int id = -1;
			Connection reg = con.conectar("");
			String sql = "insert into producto (id, nombre, id_proyecto, descripcion, id_tipo_producto values (?,?,?,?,?)";
			String generatedColumns[] = { "id" };
			PreparedStatement stmt = reg.prepareStatement(sql, generatedColumns);
			stmt.setInt(1, 0);
			stmt.setString(2, nombre);
			stmt.setInt(3, id_proyecto);
			stmt.setString(4, descripcion);
			stmt.setInt(5, id_tipo_producto);
			
			if(stmt.executeUpdate() > 0) {
				ResultSet keys = stmt.getGeneratedKeys();
				if(keys.next())
					id = keys.getInt("id");
				
				sql = "insert into producto_empresarial(id_producto, nombre_producto, valor_contrato, numero_contrato, nit, certificado_camara, certificado_institucional, autores, certificacion_implementacion, "
						+ "documento_ley, certificacion_producto, nombre_empresa, fecha, titulo, nombre_innovacion) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				stmt = reg.prepareStatement(sql);
				stmt.setInt(1, id);
				stmt.setString(2, nombre);
				stmt.setString(3, valor_contrato);
				stmt.setString(4, numero_contrato);
				stmt.setString(5, nit);
				stmt.setString(6, certificado_camara);
				stmt.setString(7, certificado_institucional);
				stmt.setString(8, autores);
				stmt.setString(9, certificacion_implementacion);
				stmt.setString(10, documento_ley);
				stmt.setString(11, certificacion_producto);
				stmt.setString(12, nombre_empresa);
				stmt.setString(13, fecha);
				stmt.setString(14, titulo);
				stmt.setString(15, nombre_innovacion);
				
				if(stmt.executeUpdate() > 0) {
					producto = new Producto(id, id_proyecto, id_tipo_producto, nombre, descripcion);
				}
			}
		} catch(Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e);
		}
		finally {
			con.cerrarConexion();
		}
		return producto;
	}
	
	public Object createProductoTecnologicoPatentado(String nombre, String descripcion, int id_proyecto, int id_tipo_producto, String numero, String certificado, String titular, String anio_obtencion, 
			String paises_obtencion, String gaceta_publica, int contrato_explotacion_licen_id, int id_solicitud, int estado_patente) throws Exception{
		
		Producto producto = null;
		
		try {
			int id = -1;
			Connection reg = con.conectar("");
			String sql = "insert into producto (id, nombre, id_proyecto, descripcion, id_tipo_producto values (?,?,?,?,?)";
			String generatedColumns[] = { "id" };
			PreparedStatement stmt = reg.prepareStatement(sql, generatedColumns);
			stmt.setInt(1, 0);
			stmt.setString(2, nombre);
			stmt.setInt(3, id_proyecto);
			stmt.setString(4, descripcion);
			stmt.setInt(5, id_tipo_producto);
			
			if(stmt.executeUpdate() > 0) {
				ResultSet keys = stmt.getGeneratedKeys();
				if(keys.next())
					id = keys.getInt("id");
				
				sql = "insert into producto_tecnologico_patentado(id_producto, numero, titulo, certificado, titular, anio_obtencion, paises_obtencion, gaceta_publica, contrato_explotacion_licen_id, "
						+ "id_solicitud, estado_patente) values(?,?,?,?,?,?,?,?,?,?,?)";
				stmt = reg.prepareStatement(sql);
				stmt.setInt(1, id);
				stmt.setString(2, numero);
				stmt.setString(3, nombre);
				stmt.setString(4, certificado);
				stmt.setString(5, titular);
				stmt.setString(6, anio_obtencion);
				stmt.setString(7, paises_obtencion);
				stmt.setString(8, gaceta_publica);
				stmt.setInt(9, contrato_explotacion_licen_id);
				stmt.setInt(10, id_solicitud);
				stmt.setInt(11, estado_patente);
				
				if(stmt.executeUpdate() > 0) {
					producto = new Producto(id, id_proyecto, id_tipo_producto, nombre, descripcion);
				}
			}
		} catch(Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e);
		}
		finally {
			con.cerrarConexion();
		}
		return producto;
	}
	
	public Object getProductosEmpresariales() throws Exception {
		
		LinkedHashMap<String, Object> producto = new LinkedHashMap<String, Object>();
		LinkedList<Object> productos = new LinkedList<Object>();
		try {
			Connection reg = con.conectar("");
			String sql = "select p.nombre, p.descripcion, pe.* from producto p inner join producto_empresarial pe on p.id = pe.id_producto";
			PreparedStatement stmt = reg.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				producto.put("id", rs.getInt("id_producto"));
				producto.put("nombre-producto", rs.getString("nombre"));
				producto.put("valor-contrato", rs.getString("valor_contrato"));
				producto.put("numero-contrato", rs.getString("numero_contrato"));
				producto.put("nit", rs.getString("nit"));
				producto.put("certificado-camara", rs.getString("certificado_camara"));
				producto.put("certificado-institucional", rs.getString("certificado_institucional"));
				producto.put("autores", rs.getString("autores"));
				producto.put("certificacion-implementacion", rs.getString("certificacion_implementacion"));
				producto.put("documento-ley", rs.getString("documento_ley"));
				producto.put("certificacion-producto", rs.getString("certificacion_producto"));
				producto.put("nombre-empresa", rs.getString("nombre_empresa"));
				producto.put("fecha", rs.getString("fecha"));
				producto.put("titulo", rs.getString("titulo"));
				producto.put("nombre-innovacion", rs.getString("nombre_innovacion"));
				
				productos.add(producto);
			}
		} catch(Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e);
		}
		finally {
			con.cerrarConexion();
		}
		return productos;
	}
	
	public Object getProductoEmpresarial(int id_producto) throws Exception {
		
		LinkedHashMap<String, Object> producto = new LinkedHashMap<String, Object>();
		LinkedList<Object> productos = new LinkedList<Object>();
		try {
			Connection reg = con.conectar("");
			String sql = "select p.nombre, p.descripcion, pe.* from producto p inner join producto_empresarial pe on p.id = pe.id_producto where p.id = ?";
			PreparedStatement stmt = reg.prepareStatement(sql);
			stmt.setInt(1, id_producto);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				producto.put("id", rs.getInt("id_producto"));
				producto.put("nombre-producto", rs.getString("nombre"));
				producto.put("valor-contrato", rs.getString("valor_contrato"));
				producto.put("numero-contrato", rs.getString("numero_contrato"));
				producto.put("nit", rs.getString("nit"));
				producto.put("certificado-camara", rs.getString("certificado_camara"));
				producto.put("certificado-institucional", rs.getString("certificado_institucional"));
				producto.put("autores", rs.getString("autores"));
				producto.put("certificacion-implementacion", rs.getString("certificacion_implementacion"));
				producto.put("documento-ley", rs.getString("documento_ley"));
				producto.put("certificacion-producto", rs.getString("certificacion_producto"));
				producto.put("nombre-empresa", rs.getString("nombre_empresa"));
				producto.put("fecha", rs.getString("fecha"));
				producto.put("titulo", rs.getString("titulo"));
				producto.put("nombre-innovacion", rs.getString("nombre_innovacion"));
				
				productos.add(producto);
			}
		} catch(Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e);
		}
		finally {
			con.cerrarConexion();
		}
		return productos;
	}
	
	public Object getProductosTecnologicos() throws Exception {
		
		LinkedHashMap<String, Object> producto = new LinkedHashMap<String, Object>();
		LinkedList<Object> productos = new LinkedList<Object>();
		try {
			Connection reg = con.conectar("");
			String sql = "select p.nombre, p.descripcion, pt* from producto p inner join producto_tecnologico_patentado pt on p.id = pt.id_producto";
			PreparedStatement stmt = reg.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				producto.put("id", rs.getInt("id_producto"));
				producto.put("nombre-producto", rs.getString("nombre"));
				producto.put("numero", rs.getString("numero"));
				producto.put("certificado", rs.getString("certificado"));
				producto.put("titular", rs.getString("titular"));
				producto.put("anio_obtencion", rs.getString("anio_obtencion"));
				producto.put("paises_obtencion", rs.getString("paises_obtencion"));
				producto.put("gaceta_publica", rs.getString("gaceta_publica"));
				producto.put("contrato", rs.getInt("contrato_explotacion_licen_id"));
				producto.put("id-solicitud", rs.getInt("id_solicitud"));
				producto.put("estado-patente", rs.getInt("estado_patente"));
				
				productos.add(producto);
			}
		} catch(Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e);
		}
		finally {
			con.cerrarConexion();
		}
		return productos;
	}
	
	public Object getProductoTecnologico(int id_producto) throws Exception {
		
		LinkedHashMap<String, Object> producto = new LinkedHashMap<String, Object>();
		LinkedList<Object> productos = new LinkedList<Object>();
		try {
			Connection reg = con.conectar("");
			String sql = "select p.nombre, p.descripcion, pt* from producto p inner join producto_tecnologico_patentado pt on p.id = pt.id_producto where p.id = ?";
			PreparedStatement stmt = reg.prepareStatement(sql);
			stmt.setInt(1, id_producto);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				producto.put("id", rs.getInt("id_producto"));
				producto.put("nombre-producto", rs.getString("nombre"));
				producto.put("numero", rs.getString("numero"));
				producto.put("certificado", rs.getString("certificado"));
				producto.put("titular", rs.getString("titular"));
				producto.put("anio_obtencion", rs.getString("anio_obtencion"));
				producto.put("paises_obtencion", rs.getString("paises_obtencion"));
				producto.put("gaceta_publica", rs.getString("gaceta_publica"));
				producto.put("contrato", rs.getInt("contrato_explotacion_licen_id"));
				producto.put("id-solicitud", rs.getInt("id_solicitud"));
				producto.put("estado-patente", rs.getInt("estado_patente"));
				
				productos.add(producto);
			}
		} catch(Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e);
		}
		finally {
			con.cerrarConexion();
		}
		return productos;
	}
}

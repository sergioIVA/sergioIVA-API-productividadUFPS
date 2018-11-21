package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;

import conexion.Conexion;
import model.Proyecto;
import util.ExcepcionProductividad;

public class ProyectoDao {

	final Conexion con = new Conexion();
	
	public ProyectoDao() {
		
	}
	
	public Proyecto createProyecto(int costoTotal, int porcentaje_cumplimiento, int id_tipo, int id_linea, int duracion,
			int tiempo_total_ejecucion, int id_facultad, int tipo_participacion_id, int estado, String titulo,
			String fecha_inicio, String fecha_final, String valor_financiado, String institucion,
			String resultados_esperados, String representante_facultad, String documento_proyecto, String n_contrato) throws Exception {
		
		Proyecto proyecto = null;
		int id = -1;
		try {
			Connection reg = con.conectar("");
			String sql = "insert into proyecto(id, titulo, fecha_inicio, fecha_final, costoTotal, porcentaje_cumplimiento, "
					+ "valor_financiado, id_tipo, id_linea, duracion, institucion, tiempo_total_ejecucion, resultados_esperados, "
					+ "id_facultad, representante_facultad, documneto_proyecto, tipo_participacion_id, estado, n_contrato "
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = reg.prepareStatement(sql);
			stmt.setInt(1, 0);
			stmt.setString(2, titulo);
			stmt.setString(3, fecha_inicio);
			stmt.setString(4, fecha_final);
			stmt.setInt(5, costoTotal);
			stmt.setInt(6, porcentaje_cumplimiento);
			stmt.setString(7, valor_financiado);
			stmt.setInt(8, id_tipo);
			stmt.setInt(9, id_linea);
			stmt.setInt(10, duracion);
			stmt.setString(11, institucion);
			stmt.setInt(12, tiempo_total_ejecucion);
			stmt.setString(13, resultados_esperados);
			stmt.setInt(14, id_facultad);
			stmt.setString(15, representante_facultad);
			stmt.setString(16, documento_proyecto);
			stmt.setInt(17, tipo_participacion_id);
			stmt.setInt(18, estado);
			stmt.setString(19, n_contrato);
			
			if(stmt.executeUpdate() > 0) {
				ResultSet keys = stmt.getGeneratedKeys();
				if(keys.next())
					id = keys.getInt(1);
				
				proyecto = new Proyecto(id, costoTotal, porcentaje_cumplimiento, id_tipo, id_linea, duracion, tiempo_total_ejecucion, id_facultad, tipo_participacion_id, estado, titulo, fecha_inicio, fecha_final, valor_financiado, institucion, resultados_esperados, representante_facultad, documento_proyecto, n_contrato);
			}
		} catch(Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e);
		}
		finally {
			con.cerrarConexion();
		}
		return proyecto;
	}
	
	public Proyecto updateProyecto(int id, Proyecto p) throws Exception {
		
		try {
			Connection reg = con.conectar("");
			String sql = "update proyecto set "
					+ "titulo = ?, "
					+ "costoTotal = ?, "
					+ "porcentaje_cumplimiento = ?, "
					+ "resultados_esperados = ?, "
					+ "documneto_proyecto = ?, "
					+ "estado = ? "
					+ "where id = ?";
			PreparedStatement stmt = reg.prepareStatement(sql);
			stmt.setString(1, p.getTitulo());
			stmt.setInt(2, p.getCostoTotal());
			stmt.setInt(3, p.getPorcentaje_cumplimiento());
			stmt.setString(4, p.getResultados_esperados());
			stmt.setString(5, p.getDocumento_proyecto());
			stmt.setInt(6, p.getEstado());
			stmt.setInt(7, id);
			
			if(stmt.executeUpdate() > 0)
				return p;
		} catch(Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e);
		}
		finally {
			con.cerrarConexion();
		}
		
		return null;
	}
	
	public boolean deleteProyecto(int id) throws Exception {
		
		int value = -1;
		try {
			Connection reg = con.conectar("");
			String sql = "delete from proyecto where id = ?";
			PreparedStatement stmt = reg.prepareStatement(sql);
			stmt.setInt(1, id);
			
			value = stmt.executeUpdate();
		} catch(Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e);
		}
		finally {
			con.cerrarConexion();
		}
		
		return value > 0;
	}
	
	public Object getProyectos() throws Exception {
		
		LinkedHashMap<String, Object> proyectos = new LinkedHashMap<String, Object>();
		LinkedHashMap<String, Object> tipo = new LinkedHashMap<String, Object>();
		LinkedHashMap<String, Object> linea = new LinkedHashMap<String, Object>();
		
		try {
			Connection reg = con.conectar("");
			String sql = "select p.*, tp.nombre tipoproyecto, li.nombre linea, li.lider_linea liderlinea from proyecto p "
					+ "inner join tipo_proyecto tp on p.id_tipo = tp.id "
					+ "inner join linea_investigacion li on p.id_linea = li.id";
			PreparedStatement stmt = reg.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				proyectos.put("id", rs.getInt("id"));
				proyectos.put("titulo", rs.getString("titulo"));
				proyectos.put("fecha-inicio", rs.getString("fecha_inicio"));
				proyectos.put("fecha-final", rs.getString("fecha_final"));
				proyectos.put("costoTotal", rs.getInt("costoTotal"));
				proyectos.put("porcentaje-cumplimiento", rs.getInt("porcentaje_cumplimiento"));
				proyectos.put("valor-financiado", rs.getString("valor_financiado"));
				
				tipo.put("id", rs.getInt("id_tipo"));
				tipo.put("nombre", rs.getString("tipoproyecto"));
				
				proyectos.put("tipo", tipo);
				
				linea.put("id", rs.getInt("id_linea"));
				linea.put("nombre", rs.getString("linea"));
				linea.put("lider", rs.getString("liderlinea"));
				
				proyectos.put("linea", linea);
				
				proyectos.put("duracion", rs.getInt("duracion"));
				proyectos.put("tiempo-total-ejecucion", rs.getInt("tiempo_total_ejecucion"));
				proyectos.put("resultados-esperados", rs.getString("resultados_esperados"));
				proyectos.put("id-facultad", rs.getInt("id_facultad"));
				proyectos.put("representante-facultad", rs.getString("representante_facultad"));
				proyectos.put("documento-proyecto", rs.getString("documneto_proyecto"));
				proyectos.put("tipo-participacion", rs.getInt("tipo_participacion_id"));
				proyectos.put("estado", rs.getInt("estado"));
				proyectos.put("n_contrato", rs.getString("n_contrato"));
			}
		} catch(Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e);
		}
		finally {
			con.cerrarConexion();
		}
		
		return proyectos;
	}
	
	public Object getProyectosGrupo(int id_grupo) throws Exception {
		
		LinkedHashMap<String, Object> proyectos = new LinkedHashMap<String, Object>();
		LinkedHashMap<String, Object> tipo = new LinkedHashMap<String, Object>();
		LinkedHashMap<String, Object> linea = new LinkedHashMap<String, Object>();
		
		try {
			Connection reg = con.conectar("");
			String sql = "select p.*, tp.nombre tipoproyecto, li.nombre linea, li.lider_linea liderlinea "
					+ "from proyecto_grupo pg inner join proyecto p on pg.id_proyecto = p.id "
					+ "inner join tipo_proyecto tp on p.id_tipo = tp.id "
					+ "inner join linea_investigacion li on p.id_linea = li.id "
					+ "where pg.id_grupo = ?";
			PreparedStatement stmt = reg.prepareStatement(sql);
			stmt.setInt(1, id_grupo);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				proyectos.put("id", rs.getInt("id"));
				proyectos.put("titulo", rs.getString("titulo"));
				proyectos.put("fecha-inicio", rs.getString("fecha_inicio"));
				proyectos.put("fecha-final", rs.getString("fecha_final"));
				proyectos.put("costoTotal", rs.getInt("costoTotal"));
				proyectos.put("porcentaje-cumplimiento", rs.getInt("porcentaje_cumplimiento"));
				proyectos.put("valor-financiado", rs.getString("valor_financiado"));
				
				tipo.put("id", rs.getInt("id_tipo"));
				tipo.put("nombre", rs.getString("tipoproyecto"));
				
				proyectos.put("tipo", tipo);
				
				linea.put("id", rs.getInt("id_linea"));
				linea.put("nombre", rs.getString("linea"));
				linea.put("lider", rs.getString("liderlinea"));
				
				proyectos.put("linea", linea);
				
				proyectos.put("duracion", rs.getInt("duracion"));
				proyectos.put("tiempo-total-ejecucion", rs.getInt("tiempo_total_ejecucion"));
				proyectos.put("resultados-esperados", rs.getString("resultados_esperados"));
				proyectos.put("id-facultad", rs.getInt("id_facultad"));
				proyectos.put("representante-facultad", rs.getString("representante_facultad"));
				proyectos.put("documento-proyecto", rs.getString("documneto_proyecto"));
				proyectos.put("tipo-participacion", rs.getInt("tipo_participacion_id"));
				proyectos.put("estado", rs.getInt("estado"));
				proyectos.put("n_contrato", rs.getString("n_contrato"));
			}
		} catch(Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e);
		}
		finally {
			con.cerrarConexion();
		}
		
		return proyectos;
	}
	
	public Object getSpecificProyecto(int id) throws Exception {
		
		LinkedHashMap<String, Object> proyectos = new LinkedHashMap<String, Object>();
		LinkedHashMap<String, Object> tipo = new LinkedHashMap<String, Object>();
		LinkedHashMap<String, Object> linea = new LinkedHashMap<String, Object>();
		
		try {
			Connection reg = con.conectar("");
			String sql = "select p.*, tp.nombre tipoproyecto, li.nombre linea, li.lider_linea liderlinea from proyecto p "
					+ "inner join tipo_proyecto tp on p.id_tipo = tp.id "
					+ "inner join linea_investigacion li on p.id_linea = li.id "
					+ "where p.id = ?";
			PreparedStatement stmt = reg.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				proyectos.put("id", rs.getInt("id"));
				proyectos.put("titulo", rs.getString("titulo"));
				proyectos.put("fecha-inicio", rs.getString("fecha_inicio"));
				proyectos.put("fecha-final", rs.getString("fecha_final"));
				proyectos.put("costoTotal", rs.getInt("costoTotal"));
				proyectos.put("porcentaje-cumplimiento", rs.getInt("porcentaje_cumplimiento"));
				proyectos.put("valor-financiado", rs.getString("valor_financiado"));
				
				tipo.put("id", rs.getInt("id_tipo"));
				tipo.put("nombre", rs.getString("tipoproyecto"));
				
				proyectos.put("tipo", tipo);
				
				linea.put("id", rs.getInt("id_linea"));
				linea.put("nombre", rs.getString("linea"));
				linea.put("lider", rs.getString("liderlinea"));
				
				proyectos.put("linea", linea);
				
				proyectos.put("duracion", rs.getInt("duracion"));
				proyectos.put("tiempo-total-ejecucion", rs.getInt("tiempo_total_ejecucion"));
				proyectos.put("resultados-esperados", rs.getString("resultados_esperados"));
				proyectos.put("id-facultad", rs.getInt("id_facultad"));
				proyectos.put("representante-facultad", rs.getString("representante_facultad"));
				proyectos.put("documento-proyecto", rs.getString("documneto_proyecto"));
				proyectos.put("tipo-participacion", rs.getInt("tipo_participacion_id"));
				proyectos.put("estado", rs.getInt("estado"));
				proyectos.put("n_contrato", rs.getString("n_contrato"));
			}
		} catch(Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e);
		}
		finally {
			con.cerrarConexion();
		}
		
		return proyectos;
	}
}

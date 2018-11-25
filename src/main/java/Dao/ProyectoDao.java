package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import conexion.Conexion;
import model.Proyecto;
import util.ExcepcionProductividad;

public class ProyectoDao {

	final Conexion con = new Conexion();

	public ProyectoDao() {

	}

	public Proyecto createProyecto(int costoTotal, int id_tipo, int id_linea, String tiempo_ejecucion,String titulo, String fecha_inicio, String fecha_final,
			String resultados_esperados, String n_contrato, String resumen, String objetivoGeneral, int tipoSession,
			int idGrupoSemillero) throws Exception {

		Proyecto proyecto = null;
		int id = -1;
		try {
			Connection reg = con.conectar("");
			String sql = "insert into proyecto(id, titulo, fecha_inicio, fecha_final,costoTotal,"
					+ "id_tipo,id_linea,tiempo_ejecucion,resultados_esperados,n_contrato,resumen,"
					+ "objetivo_general)" + "values(?,?,?,?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement pst;
			String generatedColumns[] = { "id" };
			pst= reg.prepareStatement(sql, generatedColumns);
			pst.setInt(1, 0);
			pst.setString(2, titulo);
			pst.setString(3, fecha_inicio);
			pst.setString(4, fecha_final);
			pst.setInt(5, costoTotal);
			pst.setInt(6, id_tipo);
			pst.setInt(7, id_linea);
			pst.setString(8, tiempo_ejecucion);
			pst.setString(9, resultados_esperados);
			pst.setString(10, n_contrato);
			pst.setString(11, resumen);
			pst.setString(12, objetivoGeneral);

			pst.executeUpdate();


			ResultSet generatedKeys = pst.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getInt(1);
			}

			
			System.out.println(id);

			if (tipoSession == 1) {
				sql = "insert into proyecto_grupo(id_proyecto,id_grupo) values(?,?)";
			} else {
				sql = "insert into proyecto_semillero(id_proyecto,id_semillero) values(?,?)";
			}
			
			System.out.println("valor id"+id);

			pst = reg.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setInt(2, idGrupoSemillero);
			pst.executeUpdate();

			proyecto = new Proyecto(id, costoTotal, id_tipo, id_linea, tiempo_ejecucion, titulo,
					fecha_inicio, fecha_final, resultados_esperados, n_contrato, resumen, objetivoGeneral, 1);

		} catch (Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e);
		} finally {
			con.cerrarConexion();
		}
		return proyecto;

	}

	public Proyecto updateProyecto(int id, Proyecto p) throws Exception {

		try {
			Connection reg = con.conectar("");
			String sql = "update proyecto set " + "titulo = ?, " + "costoTotal = ?, " + "porcentaje_cumplimiento = ?, "
					+ "resultados_esperados = ?, " + "documneto_proyecto = ?, " + "estado = ? " + "where id = ?";
			PreparedStatement stmt = reg.prepareStatement(sql);
			stmt.setString(1, p.getTitulo());
			stmt.setInt(2, p.getCostoTotal());
			stmt.setInt(3, p.getPorcentaje_cumplimiento());
			stmt.setString(4, p.getResultados_esperados());
			stmt.setString(5, p.getDocumento_proyecto());
			stmt.setInt(6, p.getEstado());
			stmt.setInt(7, id);

			if (stmt.executeUpdate() > 0)
				return p;
		} catch (Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e);
		} finally {
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
		} catch (Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e);
		} finally {
			con.cerrarConexion();
		}

		return value > 0;
	}

	public Object getProyectos() throws Exception {

		LinkedHashMap<String, Object> proyectos = new LinkedHashMap<String, Object>();
		LinkedHashMap<String, Object> tipo = new LinkedHashMap<String, Object>();
		LinkedHashMap<String, Object> linea = new LinkedHashMap<String, Object>();
		
		LinkedList<Object> proyectlist = new LinkedList<Object>();

		try {
			Connection reg = con.conectar("");
			String sql = "select p.*, tp.nombre tiponombre, li.nombre linea, li.lider_linea liderlinea from proyecto p "
					+ "inner join tipoproyecto tp on p.id_tipo = tp.id "
					+ "inner join linea_investigacion li on p.id_linea = li.id";
			PreparedStatement stmt = reg.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				proyectos.put("id", rs.getInt("id"));
				proyectos.put("titulo", rs.getString("titulo"));
				proyectos.put("fecha-inicio", rs.getString("fecha_inicio"));
				proyectos.put("fecha-final", rs.getString("fecha_final"));
				proyectos.put("costoTotal", rs.getInt("costoTotal"));
				proyectos.put("porcentaje-cumplimiento", rs.getInt("porcentaje_cumplimiento"));

				tipo.put("id", rs.getInt("id_tipo"));
				tipo.put("nombre", rs.getString("tiponombre"));

				proyectos.put("tipo", tipo);

				linea.put("id", rs.getInt("id_linea"));
				linea.put("nombre", rs.getString("linea"));
				linea.put("lider", rs.getString("liderlinea"));

				proyectos.put("linea", linea);

				proyectos.put("resultados-esperados", rs.getString("resultados_esperados"));
				proyectos.put("documento-proyecto", rs.getString("documento_proyecto"));
				proyectos.put("tipo-participacion", rs.getInt("tipo_participacion_id"));
				proyectos.put("estado", rs.getInt("estado"));
				proyectos.put("n_contrato", rs.getString("n_contrato"));
				
				proyectlist.add(proyectos);
			}
		} catch (Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e);
		} finally {
			con.cerrarConexion();
		}

		return proyectlist;
	}

	public Object getProyectosGrupo(int id_grupo) throws Exception {

		LinkedHashMap<String, Object> proyectos = new LinkedHashMap<String, Object>();
		LinkedHashMap<String, Object> tipo = new LinkedHashMap<String, Object>();
		LinkedHashMap<String, Object> linea = new LinkedHashMap<String, Object>();
		
		LinkedList<Object> proyectlist = new LinkedList<Object>();

		try {
			Connection reg = con.conectar("");
			String sql = "select p.*, tp.nombre tiponombre, li.nombre linea, li.lider_linea liderlinea "
					+ "from proyecto_grupo pg inner join proyecto p on pg.id_proyecto = p.id "
					+ "inner join tipoproyecto tp on p.id_tipo = tp.id "
					+ "inner join linea_investigacion li on p.id_linea = li.id " + "where pg.id_grupo = ?";
			PreparedStatement stmt = reg.prepareStatement(sql);
			stmt.setInt(1, id_grupo);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				proyectos.put("id", rs.getInt("id"));
				proyectos.put("titulo", rs.getString("titulo"));
				proyectos.put("fecha-inicio", rs.getString("fecha_inicio"));
				proyectos.put("fecha-final", rs.getString("fecha_final"));
				proyectos.put("costoTotal", rs.getInt("costoTotal"));
				proyectos.put("porcentaje-cumplimiento", rs.getInt("porcentaje_cumplimiento"));

				tipo.put("id", rs.getInt("id_tipo"));
				tipo.put("nombre", rs.getString("tiponombre"));

				proyectos.put("tipo", tipo);

				linea.put("id", rs.getInt("id_linea"));
				linea.put("nombre", rs.getString("linea"));
				linea.put("lider", rs.getString("liderlinea"));

				proyectos.put("linea", linea);

				proyectos.put("resultados-esperados", rs.getString("resultados_esperados"));
				proyectos.put("documento-proyecto", rs.getString("documento_proyecto"));
				proyectos.put("tipo-participacion", rs.getInt("tipo_participacion_id"));
				proyectos.put("estado", rs.getInt("estado"));
				proyectos.put("n_contrato", rs.getString("n_contrato"));
				
				proyectlist.add(proyectos);
			}
		} catch (Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e);
		} finally {
			con.cerrarConexion();
		}

		return proyectlist;
	}

	public Object getSpecificProyecto(int id) throws Exception {

		LinkedHashMap<String, Object> proyectos = new LinkedHashMap<String, Object>();
		LinkedHashMap<String, Object> tipo = new LinkedHashMap<String, Object>();
		LinkedHashMap<String, Object> linea = new LinkedHashMap<String, Object>();
		
		LinkedList<Object> proyectlist = new LinkedList<Object>();

		try {
			Connection reg = con.conectar("");
			String sql = "select p.*, tp.nombre tiponombre, li.nombre linea, li.lider_linea liderlinea from proyecto p "
					+ "inner join tipoproyecto tp on p.id_tipo = tp.id "
					+ "inner join linea_investigacion li on p.id_linea = li.id " + "where p.id = ?";
			PreparedStatement stmt = reg.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				proyectos.put("id", rs.getInt("id"));
				proyectos.put("titulo", rs.getString("titulo"));
				proyectos.put("fecha-inicio", rs.getString("fecha_inicio"));
				proyectos.put("fecha-final", rs.getString("fecha_final"));
				proyectos.put("costoTotal", rs.getInt("costoTotal"));
				proyectos.put("porcentaje-cumplimiento", rs.getInt("porcentaje_cumplimiento"));

				tipo.put("id", rs.getInt("id_tipo"));
				tipo.put("nombre", rs.getString("tiponombre"));

				proyectos.put("tipo", tipo);

				linea.put("id", rs.getInt("id_linea"));
				linea.put("nombre", rs.getString("linea"));
				linea.put("lider", rs.getString("liderlinea"));

				proyectos.put("linea", linea);

				proyectos.put("resultados-esperados", rs.getString("resultados_esperados"));
				proyectos.put("documento-proyecto", rs.getString("documento_proyecto"));
				proyectos.put("tipo-participacion", rs.getInt("tipo_participacion_id"));
				proyectos.put("estado", rs.getInt("estado"));
				proyectos.put("n_contrato", rs.getString("n_contrato"));
				
				proyectlist.add(proyectos);
			}
		} catch (Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e);
		} finally {
			con.cerrarConexion();
		}

		return proyectlist;
	}
}

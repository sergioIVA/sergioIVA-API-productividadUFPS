package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import conexion.Conexion;
import model.PlanAccionGrupo;
import model.ActividadPlanAccionGrupo;
import model.ProyectoPlanAccionGrupo;
import util.ExcepcionProductividad;

public class PlanAccionGrupoDao {

	final Conexion con = new Conexion();
	
	public PlanAccionGrupoDao() {
		// TODO Auto-generated constructor stub
	}
	
	public PlanAccionGrupo createPlan(int year, String semestre, int id_grupo) throws Exception{
		/**
		PlanAccionGrupo plan = null;
		try {
			Connection reg = con.conectar("");
			String sql = "insert into plan_accion_grupo(year, semestre, id_grupo) values (?,?,?)";
			PreparedStatement stmt = reg.prepareStatement(sql);
			stmt.setInt(1, year);
			stmt.setInt(2, semestre);
			stmt.setInt(3, id_grupo);
			
			if(stmt.executeUpdate() > 0)
				plan = new PlanAccionGrupo(year, semestre, id_grupo);
		} catch(Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e);
		}
		finally {
			con.cerrarConexion();
		}
		return plan;
		**/
		return null;
	}
	
	public ActividadPlanAccionGrupo createActividadPlan(int id_actividad, int year, String semestre, int id_grupo) throws Exception {
		
		ActividadPlanAccionGrupo actividad = null;
		try {
			Connection reg = con.conectar("");
			String sql = "insert into plan_accion_grupo_actividad(id_actividad, year, semestre, id_grupo) values (?,?,?,?)";
			PreparedStatement stmt = reg.prepareStatement(sql);
			stmt.setInt(1, id_actividad);
			stmt.setInt(2, year);
			stmt.setString(3, semestre);
			stmt.setInt(4, id_grupo);
			
			if(stmt.executeUpdate() > 0)
				actividad = new ActividadPlanAccionGrupo(id_actividad, year, semestre, id_grupo);
		} catch(Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e);
		}
		finally {
			con.cerrarConexion();
		}
		return actividad;
	}
	
	public ProyectoPlanAccionGrupo createProyectoPlan(int year, String semestre, int id_grupo, int id_proyecto) throws Exception {
		
		ProyectoPlanAccionGrupo proyecto = null;
		try {
			Connection reg = con.conectar("");
			String sql = "insert into plan_accion_grupo_proyecto(year, semestre, id_grupo, id_proyecto) values (?,?,?,?)";
			PreparedStatement stmt = reg.prepareStatement(sql);
			stmt.setInt(1, year);
			stmt.setString(2, semestre);
			stmt.setInt(3, id_grupo);
			stmt.setInt(4, id_proyecto);
			
			if(stmt.executeUpdate() > 0)
				proyecto = new ProyectoPlanAccionGrupo(year, semestre, id_grupo, id_proyecto);
		} catch(Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e);
		}
		finally {
			con.cerrarConexion();
		}
		return proyecto;
	}
	
	public Object getPlanes() throws Exception {
		
		LinkedHashMap<String, Object> planes = new LinkedHashMap<String, Object>();
		LinkedHashMap<String, Object> grupo = new LinkedHashMap<String, Object>();
		LinkedHashMap<String, Object> actividades = new LinkedHashMap<String, Object>();
		LinkedHashMap<String, Object> proyectos = new LinkedHashMap<String, Object>();
		
		LinkedList<Object> planlist = new LinkedList<Object>();
		
		try {
			Connection reg = con.conectar("");
			String sql = "select pag.year, pag.semestre, pag.id_grupo, g.nombre nombregrupo, g.sigla siglagrupo, paga.id_actividad, paga.year actividadyear, "
					+ "paga.semestre actividadsemestre, act.nombre actividad, act.responsables, act.producto, act.estado, "
					+ "pagp.year proyectoyear, pagp.semestre proyectosemestre, pagp.id_proyecto, p.titulo tituloproyecto, p.fecha_inicio startproyecto, p.fecha_final endproyecto "
					+ "from plan_accion_grupo pag inner join grupo_investigacion g on pag.id_grupo = g.id "
					+ "inner join plan_accion_grupo_actividad paga on paga.id_grupo = pag.id_grupo "
					+ "inner join actividad_investigacion_grupo act on act.id = paga.id_actividad "
					+ "inner join plan_accion_grupo_proyecto pagp on pagp.id_grupo = pag.id_grupo "
					+ "inner join proyecto_grupo pg on pg.id_proyecto = pagp.id_proyecto "
					+ "inner join proyecto p on p.id = pg.id_proyecto";
			PreparedStatement stmt = reg.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				planes.put("year", rs.getInt("year"));
				planes.put("semestre", rs.getString("semestre"));
				planes.put("id-grupo", rs.getInt("id_grupo"));
				
				grupo.put("id", rs.getInt("id_grupo"));
				grupo.put("nombre", rs.getString("nombregrupo"));
				grupo.put("sigla", rs.getString("siglagrupo"));
				
				planes.put("grupo", grupo);
				
				actividades.put("id", rs.getInt("id_actividad"));
				actividades.put("year", rs.getInt("actividadyear"));
				actividades.put("semestre", rs.getString("actividadsemestre"));
				actividades.put("nombre", rs.getString("actividad"));
				actividades.put("responsables", rs.getString("responsables"));
				actividades.put("producto", rs.getString("producto"));
				actividades.put("estado", rs.getString("estado"));
				
				proyectos.put("id", rs.getInt("id_proyecto"));
				proyectos.put("year", rs.getInt("proyectoyear"));
				proyectos.put("semestre", rs.getString("proyectosemestre"));
				proyectos.put("titulo", rs.getString("tituloproyecto"));
				proyectos.put("fecha-inicio", rs.getString("startproyecto"));
				proyectos.put("fecha-fin", rs.getString("endproyecto"));
				
				planes.put("actividades", actividades);
				planes.put("proyectos", proyectos);
				
				planlist.add(planes);
			}
		} catch(Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e);
		}
		finally {
			con.cerrarConexion();
		}
		
		return planes;
	}
	
	public Object getPlanesGrupo(int id_grupo) throws Exception {
		
		LinkedHashMap<String, Object> planes = new LinkedHashMap<String, Object>();
		LinkedHashMap<String, Object> grupo = new LinkedHashMap<String, Object>();
		LinkedHashMap<String, Object> actividades = new LinkedHashMap<String, Object>();
		LinkedHashMap<String, Object> proyectos = new LinkedHashMap<String, Object>();
		
		LinkedList<Object> planlist = new LinkedList<Object>();
		
		try {
			Connection reg = con.conectar("");
			String sql = "select pag.year, pag.semestre, pag.id_grupo, g.nombre nombregrupo, g.sigla siglagrupo, paga.id_actividad, paga.year actividadyear, "
					+ "paga.semestre actividadsemestre, act.nombre actividad, act.responsables, act.producto, act.estado, "
					+ "pagp.year proyectoyear, pagp.semestre proyectosemestre, pagp.id_proyecto, p.titulo tituloproyecto, p.fecha_inicio startproyecto, p.fecha_final endproyecto "
					+ "from plan_accion_grupo pag inner join grupo_investigacion g on pag.id_grupo = g.id "
					+ "inner join plan_accion_grupo_actividad paga on paga.id_grupo = pag.id_grupo "
					+ "inner join actividad_investigacion_grupo act on act.id = paga.id_actividad "
					+ "inner join plan_accion_grupo_proyecto pagp on pagp.id_grupo = pag.id_grupo "
					+ "inner join proyecto_grupo pg on pg.id_proyecto = pagp.id_proyecto "
					+ "inner join proyecto p on p.id = pg.id_proyecto "
					+ "where pag.id_grupo = ?";
			PreparedStatement stmt = reg.prepareStatement(sql);
			stmt.setInt(1, id_grupo);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				planes.put("year", rs.getInt("year"));
				planes.put("semestre", rs.getString("semestre"));
				planes.put("id-grupo", rs.getInt("id_grupo"));
				
				grupo.put("id", rs.getInt("id_grupo"));
				grupo.put("nombre", rs.getString("nombregrupo"));
				grupo.put("sigla", rs.getString("siglagrupo"));
				
				planes.put("grupo", grupo);
				
				actividades.put("id", rs.getInt("id_actividad"));
				actividades.put("year", rs.getInt("actividadyear"));
				actividades.put("semestre", rs.getString("actividadsemestre"));
				actividades.put("nombre", rs.getString("actividad"));
				actividades.put("responsables", rs.getString("responsables"));
				actividades.put("producto", rs.getString("producto"));
				actividades.put("estado", rs.getString("estado"));
				
				proyectos.put("id", rs.getInt("id_proyecto"));
				proyectos.put("year", rs.getInt("proyectoyear"));
				proyectos.put("semestre", rs.getString("proyectosemestre"));
				proyectos.put("titulo", rs.getString("tituloproyecto"));
				proyectos.put("fecha-inicio", rs.getString("startproyecto"));
				proyectos.put("fecha-fin", rs.getString("endproyecto"));
				
				planes.put("actividades", actividades);
				planes.put("proyectos", proyectos);
				
				planlist.add(planes);
			}
			
		} catch(Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e);
		}
		finally {
			con.cerrarConexion();
		}
		
		return planes;
	}
}

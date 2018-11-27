package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import conexion.Conexion;
import model.Actividad_plan_semillero;
import model.Articulo;
import model.Departamento;
import model.Evento;
import model.JovenInvestigador;
import model.Libro;
import model.LineaInvestigacion;
import model.Objetivo;
import model.PlanAccionGrupo;
import model.PlanAccionSemillero;
import model.Plan_accion_capacitacion;
import model.Plan_accion_grupo_actividad;
import model.Plan_accion_grupo_proyecto;
import model.Proyecto_plan_semillero;
import model.Tesis;
import spark.Request;
import spark.Response;
import util.ExcepcionProductividad;

public class ProcesoEspecificoDao {

	final Conexion con = new Conexion();

	public ProcesoEspecificoDao() {
		// TODO Auto-generated constructor stub
	}

	public Object getPanelPrincipal(int idGrupo) throws Exception {

		LinkedHashMap<String, Object> general = new LinkedHashMap<String, Object>();
		LinkedHashMap<String, Object> grupo = new LinkedHashMap<String, Object>();

		try {
			Connection reg = con.conectar("");
			String sql = "select grupo.id,grupo.nombre nombre_grupo,grupo.sigla,tipo.nombre unidad,"
					+ "grupo.ubicacion,grupo.fecha_creacion,grupo.codigo_colciencias,grupo.clasificado,categoria.nombre "
					+ "categoria_grupo,persona.nombre nombre_docente,departamento.nombre nombre_departamento,persona.celular,"
					+ "persona.correo_electronico,persona.telefono,modalida.nombre vinculacion from  grupo_investigacion "
					+ "grupo,categoria_grupo categoria,unidad_academica unidad,tipo_unidad tipo,docente_ufps docente,"
					+ "departamento departamento,persona persona,modalidad modalida  where grupo.id_categoria=categoria.id_categoria "
					+ "and grupo.id_unidad=unidad.id and unidad.id_tipo_unidad=tipo.id and grupo.director_grupo=docente.id_investigador "
					+ "and persona.id=docente.id_investigador and departamento.id_unidad=docente.id_departamento and "
					+ "modalida.id=docente.id_modalidad and grupo.id=?";

			System.out.println(sql);

			PreparedStatement stmt = reg.prepareStatement(sql);
			stmt.setInt(1, idGrupo);

			ResultSet rs = stmt.executeQuery();
			// utilizar un linkedHashMap para preservar el orden de los datos ingresados
			LinkedHashMap<String, Object> director = new LinkedHashMap<String, Object>();
			if (rs.next()) {

				grupo.put("id", rs.getInt("id"));
				grupo.put("nombre", rs.getString("nombre_grupo"));
				grupo.put("sigla", rs.getString("sigla"));
				grupo.put("unidadAcademica", rs.getString("unidad"));
				grupo.put("ubicacion", rs.getString("ubicacion"));
				grupo.put("fechaCreacion", rs.getString("fecha_creacion"));
				grupo.put("codigo", rs.getString("codigo_colciencias"));
				grupo.put("clasificado", rs.getInt("clasificado"));
				grupo.put("categoria", rs.getString("categoria_grupo"));

				director.put("nombre", rs.getString("nombre_docente"));
				director.put("departamento", rs.getString("nombre_departamento"));
				director.put("contacto", rs.getString("celular") + "-" + rs.getString("telefono"));
				director.put("email", rs.getString("correo_electronico"));
				director.put("vinculacion", rs.getString("vinculacion"));

				grupo.put("director", director);

			}

			sql = "select count(estado) numero from proyecto_grupo proyectoGrupo,grupo_investigacion grupo,"
					+ "proyecto proyecto where grupo.id=? and grupo.id=proyectogrupo.id_grupo "
					+ "and proyectoGrupo.id_proyecto=proyecto.id and proyecto.estado=1";
			stmt = reg.prepareStatement(sql);
			stmt.setInt(1, idGrupo);
			rs = stmt.executeQuery();

			if (rs.next()) {
				grupo.put("proTerminados", rs.getInt("numero"));

			}

			sql = "select count(estado) numero from proyecto_grupo proyectoGrupo,grupo_investigacion grupo,"
					+ "proyecto proyecto where grupo.id=? and grupo.id=proyectogrupo.id_grupo "
					+ "and proyectoGrupo.id_proyecto=proyecto.id and proyecto.estado=0";
			stmt = reg.prepareStatement(sql);
			stmt.setInt(1, idGrupo);
			rs = stmt.executeQuery();
			if (rs.next()) {
				grupo.put("proEjecucion", rs.getInt("numero"));
			}

			general.put("grupo", grupo);
		} catch (Exception e) {
			throw new ExcepcionProductividad("error del servidor" + e);
		}

		finally {
			con.cerrarConexion();
		}
		return grupo;

	}

	public Object getGrupoCategoriaDirector() throws Exception {

		LinkedHashMap<String, Object> grupo = new LinkedHashMap<String, Object>();
		List<LinkedHashMap> array = new LinkedList<LinkedHashMap>();

		try {
			Connection reg = con.conectar("");
			String sql = "select grupo.id,grupo.nombre,grupo.fecha_creacion,grupo.codigo_colciencias,persona.nombre "
					+ "nombreDirector,categoria.nombre nombre_categoria from grupo_investigacion grupo,categoria_grupo"
					+ " categoria,persona persona where grupo.id_categoria=categoria.id_categoria and persona.id=grupo.director_grupo";

			PreparedStatement stmt = reg.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			// utilizar un linkedHashMap para preservar el orden de los datos ingresados
			LinkedHashMap<String, Object> datosEspecificos = null;
			while (rs.next()) {

				datosEspecificos = new LinkedHashMap<String, Object>();
				datosEspecificos.put("id", rs.getInt("id"));
				datosEspecificos.put("nombre", rs.getString("nombre"));
				datosEspecificos.put("fecha_creacion", rs.getString("fecha_creacion"));
				datosEspecificos.put("codigo_colciencias", rs.getString("codigo_colciencias"));
				datosEspecificos.put("nombreDirector", rs.getString("nombreDirector"));
				datosEspecificos.put("nombre_categoria", rs.getString("nombre_categoria"));
				array.add(datosEspecificos);
			}

			grupo.put("grupos", array);

			return grupo;
		} catch (Exception e) {
			throw new ExcepcionProductividad("error del servidor" + e);
		}

		finally {
			con.cerrarConexion();
		}

	}

	public List<LinkedHashMap> getDocenteNombreId() throws Exception {

		List<LinkedHashMap> array = new LinkedList<LinkedHashMap>();

		try {
			Connection reg = con.conectar("");
			String sql = "select docente.id_investigador id_docente,persona.nombre "
					+ "from docente_ufps docente,persona persona " + "where docente.id_investigador=persona.id";

			PreparedStatement stmt = reg.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			LinkedHashMap<String, Object> datosEspecificos = null;
			while (rs.next()) {

				datosEspecificos = new LinkedHashMap<String, Object>();
				datosEspecificos.put("id", rs.getInt("id_docente"));
				datosEspecificos.put("nombre", rs.getString("nombre"));
				array.add(datosEspecificos);
			}

			return array;
		} catch (Exception e) {
			throw new ExcepcionProductividad("error del servidor" + e);
		}

		finally {
			con.cerrarConexion();
		}

	}

	public Object getSemilleroDirector() throws Exception {

		try {
			Connection reg = con.conectar("");
			String sql = "select semillero.id id_semillero,semillero.nombre,semillero.sigla,"
					+ "semillero.fecha_creacion,persona.nombre nombre_tutor,semillero.ubicacion from semillero "
					+ "semillero,docente_ufps docente,persona persona where "
					+ "docente.id_investigador=semillero.id_director and persona.id=docente.id_investigador";

			PreparedStatement stmt = reg.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			LinkedHashMap<String, Object> semilleros = new LinkedHashMap<String, Object>();
			List<LinkedHashMap> arrays = new LinkedList<LinkedHashMap>();
			LinkedHashMap<String, Object> datosEspecificos = new LinkedHashMap<String, Object>();

			while (rs.next()) {

				datosEspecificos = new LinkedHashMap<String, Object>();
				datosEspecificos.put("id", rs.getInt("id_semillero"));
				datosEspecificos.put("nombre", rs.getString("nombre"));
				datosEspecificos.put("sigla", rs.getString("sigla"));
				datosEspecificos.put("fecha_creacion", rs.getString("fecha_creacion"));
				datosEspecificos.put("tutor", rs.getString("nombre_tutor"));
				arrays.add(datosEspecificos);
			}
			semilleros.put("semillero", arrays);
			return semilleros;
		} catch (Exception e) {
			throw new ExcepcionProductividad("error del servidor" + e);
		}

		finally {
			con.cerrarConexion();
		}

	}

	public Object getGrupoLineaSemilleroDocenteGrupo(int idGrupo) throws Exception {

		LinkedHashMap<String, Object> general = new LinkedHashMap<String, Object>();

		List<LinkedHashMap> lineaGrupo = new LinkedList<LinkedHashMap>();
		List<LinkedHashMap> docentes = new LinkedList<LinkedHashMap>();

		try {
			Connection reg = con.conectar("");

			String sql = "select persona.id id_docente,persona.nombre nombre_docente from grupo_investigacion grupo,"
					+ "participante_grupo participante,persona persona,roles_sistema roles,tipo_rol tipoRol where"
					+ " grupo.id=participante.id_grupo and persona.id=participante.id_participante and"
					+ " tipoRol.id=? and roles.persona_id=persona.id and grupo.id=? and persona.id "
					+ "NOT IN  (select id_director from semillero)";

			PreparedStatement stmt = reg.prepareStatement(sql);
			stmt.setInt(1, 3);
			stmt.setInt(2, idGrupo);

			ResultSet rs = stmt.executeQuery();
			// utilizar un linkedHashMap para preservar el orden de los datos ingresados
			LinkedHashMap<String, Object> datosDocente = null;
			while (rs.next()) {

				datosDocente = new LinkedHashMap<String, Object>();
				datosDocente.put("id", rs.getInt("id_docente"));
				datosDocente.put("nombre", rs.getString("nombre_docente"));
				docentes.add(datosDocente);
			}

			sql = "select linea.id,linea.nombre from linea_investigacion linea,linea_grupo "
					+ "lineaGrupo,grupo_investigacion grupo"
					+ " where linea.id=lineaGrupo.id_linea and grupo.id=lineaGrupo.id_grupo and grupo.id=?";
			stmt = reg.prepareStatement(sql);
			stmt.setInt(1, idGrupo);
			rs = stmt.executeQuery();

			LinkedHashMap<String, Object> datosLinea = null;
			while (rs.next()) {

				datosLinea = new LinkedHashMap<String, Object>();

				datosLinea.put("id", rs.getInt("id"));
				datosLinea.put("nombre", rs.getString("nombre"));
				lineaGrupo.add(datosLinea);

			}

			general.put("director", docentes);
			general.put("linea_grupo", lineaGrupo);

			return general;
		} catch (Exception e) {
			throw new ExcepcionProductividad("error del servidor" + e);
		}

		finally {
			con.cerrarConexion();
		}

	}

	public Object getLineasGrupoTipoProyectoGrupo(int tipoSession, int idGruSemillero) throws Exception {

		LinkedHashMap<String, Object> general = new LinkedHashMap<String, Object>();

		List<LinkedHashMap> lineaGrupoSemillero = new LinkedList<LinkedHashMap>();
		List<LinkedHashMap> tipoProyecto = new LinkedList<LinkedHashMap>();

		try {

			Connection reg = con.conectar("");

			String sql = "";

			if (tipoSession == 1) {
				sql = "select linea.id,linea.nombre from grupo_investigacion grupo,linea_investigacion linea,linea_grupo"
						+ " lineaGrupo where linea.id=lineaGrupo.id_linea and lineaGrupo.id_grupo=grupo.id and grupo.id=?";
			} else {
				sql = "select linea.id,linea.nombre from semillero semillero,linea_investigacion linea,linea_semillero"
						+ " lineaSemillero where linea.id=lineaSemillero.id and lineaSemillero.id_semillero=semillero.id and semillero.id=?";
			}

			PreparedStatement stmt = reg.prepareStatement(sql);
			stmt.setInt(1, idGruSemillero);

			ResultSet rs = stmt.executeQuery();
			// utilizar un linkedHashMap para preservar el orden de los datos ingresados
			LinkedHashMap<String, Object> datosLinea = null;
			while (rs.next()) {

				datosLinea = new LinkedHashMap<String, Object>();
				datosLinea.put("id", rs.getInt("id"));
				datosLinea.put("nombre", rs.getString("nombre"));
				lineaGrupoSemillero.add(datosLinea);
			}

			sql = "select tipo.id,tipo.nombre from tipoproyecto tipo";
			stmt = reg.prepareStatement(sql);
			rs = stmt.executeQuery();

			LinkedHashMap<String, Object> datosTipoProyecto = null;

			while (rs.next()) {

				datosTipoProyecto = new LinkedHashMap<String, Object>();

				datosTipoProyecto.put("id", rs.getInt("id"));
				datosTipoProyecto.put("nombre", rs.getString("nombre"));
				tipoProyecto.add(datosTipoProyecto);

			}

			general.put("lineasGrupoSemillero", lineaGrupoSemillero);
			general.put("TipoProyecto", tipoProyecto);

			return general;
		} catch (Exception e) {
			throw new ExcepcionProductividad("error del servidor" + e);
		}

		finally {
			con.cerrarConexion();
		}

	}

	public Object getProyectoResponsable(int idGrupoSemillero, int tipoSession) throws Exception {

		try {
			Connection reg = con.conectar("");

			String sql = "select persona.nombre,proyecto.id,rol.nombre rol from participante_proyecto "
					+ "participantePro,persona persona,proyecto proyecto,rol rol where "
					+ "persona.id=participantePro.id_participante and "
					+ "proyecto.id=participantePro.id_proyecto and rol.id=participantePro.id_rol";

			PreparedStatement stmt = reg.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			ArrayList<String> integranteProyecto = new ArrayList<String>();

			while (rs.next()) {
				integranteProyecto
						.add(rs.getString("id") + "-" + rs.getString("nombre") + " (" + rs.getString("rol") + ")");
			}

			if (tipoSession == 1) {

				sql = "select proyecto.id,proyecto.titulo,linea.nombre nombreLinea,proyecto.fecha_inicio,"
						+ "proyecto.fecha_final,proyecto.tiempo_ejecucion,proyecto.costoTotal from proyecto "
						+ "proyecto,proyecto_grupo proyecto_grupo,linea_investigacion linea"
						+ " where proyecto.id=proyecto_grupo.id_proyecto and "
						+ "linea.id=proyecto.id_linea and proyecto_grupo.id_grupo=?";

			} else {
				sql = "select proyecto.id,proyecto.titulo,linea.nombre nombreLinea,proyecto.fecha_inicio,"
						+ "proyecto.fecha_final,proyecto.tiempo_ejecucion,proyecto.costoTotal from proyecto "
						+ "proyecto,proyecto_semillero proyectoSemillero,linea_investigacion linea where "
						+ "proyectoSemillero.id_semillero=? and proyecto.id=proyectoSemillero.id_proyecto "
						+ "and linea.id=proyecto.id_linea";

			}

			stmt = reg.prepareStatement(sql);
			stmt.setInt(1, idGrupoSemillero);
			rs = stmt.executeQuery();

			LinkedHashMap<String, Object> general = new LinkedHashMap<String, Object>();
			List<LinkedHashMap> arrays = new LinkedList<LinkedHashMap>();
			LinkedHashMap<String, Object> datosEspecificos = new LinkedHashMap<String, Object>();
			List<String> integrantes = new LinkedList<String>();

			while (rs.next()) {

				datosEspecificos = new LinkedHashMap<String, Object>();
				int id_proyecto = rs.getInt("id");
				datosEspecificos.put("id", id_proyecto);
				datosEspecificos.put("titulo", rs.getString("titulo"));
				datosEspecificos.put("linea", rs.getString("nombreLinea"));
				datosEspecificos.put("fecha_inicio", rs.getString("fecha_inicio"));
				datosEspecificos.put("fecha_final", rs.getString("fecha_final"));
				datosEspecificos.put("tiempo_ejecucion", rs.getString("tiempo_ejecucion"));
				datosEspecificos.put("costoTotal", rs.getString("costoTotal"));

				int id = 0;
				for (String integrante : integranteProyecto) {

					id = Integer.parseInt(integrante.split("-")[0]);
					if (id == id_proyecto) {
						integrantes.add(integrante.split("-")[1]);
					}
				}

				datosEspecificos.put("responsable", integrantes);
				arrays.add(datosEspecificos);
				integrantes = new LinkedList<String>();
			}

			general.put("proyecto", arrays);

			return general;
		} catch (Exception e) {
			throw new ExcepcionProductividad("error del servidor" + e);
		}

		finally {
			con.cerrarConexion();
		}
	}

	/// proyectosNuevosIntegrantes/:idGrupoSemillero/session/:tipoSession

	public Object getproyectosNuevosIntegrantes(int idGrupoSemillero, int tipoSession) throws Exception {

		try {
			Connection reg = con.conectar("");

			// 1.consultas de proyectos nuevos
			LinkedHashMap<String, Object> general = new LinkedHashMap<String, Object>();

			String sql = "";
			if (tipoSession == 1) {
				sql = "select DISTINCT proyecto.id,proyecto.titulo from proyecto_grupo "
						+ "proyectoGrupo,proyecto proyecto,plan_accion_grupo_proyecto plan where "
						+ "proyectoGrupo.id_proyecto=proyecto.id "
						+ "and proyectoGrupo.id_grupo=? and proyectoGrupo.id_proyecto  "
						+ "NOT IN (select DISTINCT id_proyecto from plan_accion_grupo_proyecto)";
			} else {
				sql = "select DISTINCT proyecto.id,proyecto.titulo from proyecto_semillero "
						+ "proyectoSemillero,proyecto proyecto,proyecto_plan_semillero plan where"
						+ " proyectoSemillero.id_proyecto=proyecto.id and proyectoSemillero.id_semillero=? and "
						+ "proyectoSemillero.id_proyecto  NOT IN (select DISTINCT id_proyecto "
						+ "from proyecto_plan_semillero)";
			}
			PreparedStatement stmt = reg.prepareStatement(sql);
			stmt.setInt(1, idGrupoSemillero);
			ResultSet rs = stmt.executeQuery();

			List<LinkedHashMap> arrays = new LinkedList<LinkedHashMap>();
			LinkedHashMap<String, Object> datosEspecificos = new LinkedHashMap<String, Object>();

			boolean hayDatos = false;
			while (rs.next()) {

				hayDatos = true;
				datosEspecificos.put("id", rs.getInt("id"));
				datosEspecificos.put("titulo", rs.getString("titulo"));
				arrays.add(datosEspecificos);
				datosEspecificos = new LinkedHashMap<String, Object>();
			}

			if (!hayDatos) {

				if (tipoSession == 1) {
					sql = "select proyecto.id,proyecto.titulo from proyecto_grupo "
							+ "proyectoGrupo,proyecto proyecto where proyectoGrupo.id_proyecto"
							+ "=proyecto.id and proyectoGrupo.id_grupo=?;";
				} else {
					sql = "select proyecto.id,proyecto.titulo from proyecto_semillero proyectoSemillero"
							+ ",proyecto proyecto where "
							+ "proyectoSemillero.id_proyecto=proyecto.id and proyectoSemillero.id_semillero=?";
				}

				stmt = reg.prepareStatement(sql);
				stmt.setInt(1, idGrupoSemillero);
				rs = stmt.executeQuery();

				while (rs.next()) {
					datosEspecificos.put("id", rs.getInt("id"));
					datosEspecificos.put("titulo", rs.getString("titulo"));
					arrays.add(datosEspecificos);
					datosEspecificos = new LinkedHashMap<String, Object>();
				}

			}

			general.put("proyectoNuevo", arrays);

			if (tipoSession == 1) {
				sql = "select DISTINCT persona.id,persona.nombre from participante_grupo participanteGrupo,"
						+ "persona persona where " + "participanteGrupo.id_grupo=? and "
						+ "participanteGrupo.id_participante=persona.id";
			} else {
				sql = "select DISTINCT persona.id,persona.nombre from participante_semillero participanteSe,"
						+ "persona persona where "
						+ "participanteSe.id_semillero=? and participanteSe.id_participante=persona.id;";
			}

			stmt = reg.prepareStatement(sql);
			stmt.setInt(1, idGrupoSemillero);
			rs = stmt.executeQuery();

			List<LinkedHashMap> arrays2 = new LinkedList<LinkedHashMap>();
			LinkedHashMap<String, Object> datosEspecificos2 = new LinkedHashMap<String, Object>();
			while (rs.next()) {
				datosEspecificos2.put("nombre", rs.getString("nombre"));
				arrays2.add(datosEspecificos2);
				datosEspecificos2 = new LinkedHashMap<String, Object>();
			}

			general.put("integrante", arrays2);

			return general;
		} catch (Exception e) {
			throw new ExcepcionProductividad("error del servidor" + e);
		}

		finally {
			con.cerrarConexion();
		}
	}

	public Object getcreatePlanGrupoSemillero(int idGrupoSemillero, int tipoSession, String year, String semestre)
			throws Exception {

		try {
			Connection reg = con.conectar("");

			String sql = "";
			if (tipoSession == 1) {
				sql = "insert into plan_accion_grupo(year,semestre,id_grupo) values (?,?,?)";
			} else {
				sql = "insert into plan_semillero(year,semestre,id_semillero) values (?,?,?)";
			}
			PreparedStatement stmt = reg.prepareStatement(sql);
			stmt.setString(1, year);
			stmt.setString(2, semestre);
			stmt.setInt(3, idGrupoSemillero);

			List<LinkedHashMap> arrays = new LinkedList<LinkedHashMap>();
			LinkedHashMap<String, Object> datosEspecificos = new LinkedHashMap<String, Object>();

			if (stmt.executeUpdate() > 0) {

				if (tipoSession == 1) {
					return new PlanAccionGrupo(year, semestre, idGrupoSemillero);
				} else {
					return new PlanAccionSemillero(year, semestre, idGrupoSemillero);
				}
			}

			return null;
		} catch (Exception e) {
			throw new ExcepcionProductividad("error del servidor" + e);
		}

		finally {
			con.cerrarConexion();
		}

	}

	public Object getProyectosActividadesNoterminadoPlanAccionGrupoSemillero(int idGrupoSemillero, int tipoSession)
			throws Exception {

		try {
			Connection reg = con.conectar("");

			// 1.consultar proyectos que no este terminado en los planes de accion de grupos
			// y semilleros
			LinkedHashMap<String, Object> general = new LinkedHashMap<String, Object>();

			String sql = "";
			if (tipoSession == 1) {
				sql = "SELECT DISTINCT proyecto.id,proyecto.titulo,proyecto.estado FROM "
						+ "plan_accion_grupo_proyecto plan,proyecto proyecto where "
						+ "plan.id_proyecto=proyecto.id and proyecto.estado=0 and plan.id_grupo=?";
			} else {
				sql = "SELECT distinct proyecto.id,proyecto.titulo,proyecto.estado FROM "
						+ "proyecto_plan_semillero plan,proyecto proyecto " + "where plan.id_proyecto=proyecto.id and "
						+ "plan.id_semillero=? and proyecto.estado=0";
			}

			PreparedStatement stmt = reg.prepareStatement(sql);
			stmt.setInt(1, idGrupoSemillero);
			ResultSet rs = stmt.executeQuery();

			List<LinkedHashMap> arrays = new LinkedList<LinkedHashMap>();
			LinkedHashMap<String, Object> datosEspecificos = new LinkedHashMap<String, Object>();
			while (rs.next()) {
				datosEspecificos.put("id", rs.getInt("id"));
				datosEspecificos.put("titulo", rs.getString("titulo"));
				datosEspecificos.put("estado", rs.getInt("estado"));
				arrays.add(datosEspecificos);
				datosEspecificos = new LinkedHashMap<String, Object>();
			}

			general.put("proyectosNoTerminado", arrays);

			if (tipoSession == 1) {
				sql = "SELECT distinct plan.id_actividad id,actividad.nombre,actividad.estado "
						+ "FROM plan_accion_grupo_actividad plan,actividad_investigacion_grupo"
						+ " actividad where plan.id_actividad=actividad.id and actividad.estado=0"
						+ " and plan.id_grupo=?";
			} else {
				sql = "SELECT  distinct actividad.id,actividad.nombre,actividad.estado from "
						+ "actividad_plan_semillero plan,actividad_investigacion_semillero "
						+ "actividad where plan.id_actividad=actividad.id and plan.id_semillero=?"
						+ " and actividad.estado=0";
			}

			stmt = reg.prepareStatement(sql);
			stmt.setInt(1, idGrupoSemillero);
			rs = stmt.executeQuery();

			List<LinkedHashMap> arrays2 = new LinkedList<LinkedHashMap>();
			LinkedHashMap<String, Object> datosEspecificos2 = new LinkedHashMap<String, Object>();
			while (rs.next()) {
				datosEspecificos2.put("id", rs.getInt("id"));
				datosEspecificos2.put("nombre", rs.getString("nombre"));
				datosEspecificos2.put("estado", rs.getInt("estado"));
				arrays2.add(datosEspecificos2);
				datosEspecificos2 = new LinkedHashMap<String, Object>();
			}

			general.put("actividadesNoterminada", arrays2);

			return general;
		} catch (Exception e) {
			throw new ExcepcionProductividad("error del servidor" + e);
		}

		finally {
			con.cerrarConexion();
		}
	}

	public Object eventoNoTerminadoPlanAccionGrupo(int idGrupo) throws Exception {

		try {
			Connection reg = con.conectar("");

			// 1.consultar proyectos que no este terminado en los planes de accion de grupos
			// y semilleros
			LinkedHashMap<String, Object> general = new LinkedHashMap<String, Object>();

			String sql = "";

			sql = "SELECT DISTINCT evento.id,evento.nombre,evento.estado FROM "
					+ "evento_plan_accion_grupo plan,evento evento where "
					+ "plan.id_evento=evento.id and evento.estado=0 and plan.id_grupo=?";

			PreparedStatement stmt = reg.prepareStatement(sql);
			stmt.setInt(1, idGrupo);
			ResultSet rs = stmt.executeQuery();

			List<LinkedHashMap> arrays = new LinkedList<LinkedHashMap>();
			LinkedHashMap<String, Object> datosEspecificos = new LinkedHashMap<String, Object>();
			while (rs.next()) {
				datosEspecificos.put("id", rs.getInt("id"));
				datosEspecificos.put("nombre", rs.getString("nombre"));
				datosEspecificos.put("estado", rs.getInt("estado"));
				arrays.add(datosEspecificos);
				datosEspecificos = new LinkedHashMap<String, Object>();
			}

			general.put("EventoNoTerminado", arrays);

			return general;
		} catch (Exception e) {
			throw new ExcepcionProductividad("error del servidor" + e);
		}

		finally {
			con.cerrarConexion();
		}

	}

	public Object getCapacitacionNoTerminadoPlanAccionSemillero(int idSemillero) throws Exception {

		try {
			Connection reg = con.conectar("");

			LinkedHashMap<String, Object> general = new LinkedHashMap<String, Object>();

			String sql = "";

			sql = "SELECT DISTINCT  capacita.id,capacita.nombre,capacita.estado FROM "
					+ "plan_accion_capacitacion plan,capacitacion capacita where "
					+ "plan.id_capacitacion=capacita.id and capacita.estado=0 and plan.id_semillero=?;";

			PreparedStatement stmt = reg.prepareStatement(sql);
			stmt.setInt(1, idSemillero);
			ResultSet rs = stmt.executeQuery();

			List<LinkedHashMap> arrays = new LinkedList<LinkedHashMap>();
			LinkedHashMap<String, Object> datosEspecificos = new LinkedHashMap<String, Object>();
			while (rs.next()) {
				datosEspecificos.put("id", rs.getInt("id"));
				datosEspecificos.put("nombre", rs.getString("nombre"));
				datosEspecificos.put("estado", rs.getInt("estado"));
				arrays.add(datosEspecificos);
				datosEspecificos = new LinkedHashMap<String, Object>();
			}

			general.put("EventoNoTerminado", arrays);

			return general;
		} catch (Exception e) {
			throw new ExcepcionProductividad("error del servidor" + e);
		}

		finally {
			con.cerrarConexion();
		}

	}

	public Object CreateEventoGrupoAsignarPlanAccion(String year, String semestre, int idGrupo, String nombre,
			String caracterEvento, String responsables, String instituciones_promo, String entidades,
			String fecha_inicio, String fecha_final) throws Exception {

		Connection reg = null;
		int id = -1;
		try {

			reg = con.conectar("");

			String sql = "INSERT INTO evento(id,nombre,caracter_evento,responsables,instituiones_promo,"
					+ "entidades_participantes,fecha_inicio,fecha_final,estado) values (?,?,?,?,?,?,?,?,?)";
			PreparedStatement pst;
			String generatedColumns[] = { "id" };
			pst = reg.prepareStatement(sql, generatedColumns);
			pst.setInt(1, 0);
			pst.setString(2, nombre);
			pst.setString(3, caracterEvento);
			pst.setString(4, responsables);
			pst.setString(5, instituciones_promo);
			pst.setString(6, entidades);
			pst.setString(7, fecha_inicio);
			pst.setString(8, fecha_final);
			pst.setInt(9, 0);

			pst.executeUpdate();

			ResultSet generatedKeys = pst.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getInt(1);
			}

			sql = "INSERT INTO evento_plan_accion_grupo(id_evento,year,semestre,id_grupo) values (?,?,?,?)";

			pst = reg.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setString(2, year);
			pst.setString(3, semestre);
			pst.setInt(4, idGrupo);

			pst.executeUpdate();

			return new Evento(id, year, semestre, idGrupo);

		} catch (java.sql.SQLIntegrityConstraintViolationException e) {
			throw new ExcepcionProductividad("ya hay grupo con ese nombre  asociado");
		} catch (Exception e) {
			throw new ExcepcionProductividad("error del servidor" + e);// mientras colocamos e por desarrollo para
			// mirar las consultas
		}

		finally {
			con.cerrarConexion();
		}

	}

	public Object createActividadGrupoSemilleroAsignarPlanAccion(String year, String semestre, int idGrupoSemillero,
			int tipoSession, String nombre, String responsables, String producto, String fecha_inicio,
			String fecha_final) throws Exception {

		Connection reg = null;
		int id = -1;
		try {

			reg = con.conectar("");
			String sql = "";

			if (tipoSession == 1) {
				sql = "INSERT INTO actividad_investigacion_grupo(id,nombre,responsables,producto,fecha_inicio,"
						+ "fecha_fin,estado) values (?,?,?,?,?,?,?)";
			} else {
				sql = "INSERT INTO actividad_investigacion_semillero(id,nombre,responsable,producto,fecha_inicio,"
						+ "fecha_final,estado) values (?,?,?,?,?,?,?)";
			}

			PreparedStatement pst;
			String generatedColumns[] = { "id" };
			pst = reg.prepareStatement(sql, generatedColumns);
			pst.setInt(1, 0);
			pst.setString(2, nombre);
			pst.setString(3, responsables);
			pst.setString(4, producto);
			pst.setString(5, fecha_inicio);
			pst.setString(6, fecha_final);
			pst.setInt(7, 0);

			pst.executeUpdate();

			ResultSet generatedKeys = pst.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getInt(1);
			}

			if (tipoSession == 1) {
				sql = "INSERT INTO plan_accion_grupo_actividad(id_actividad,year,semestre,id_grupo) "
						+ "values (?,?,?,?)";
			} else {
				sql = "INSERT INTO actividad_plan_semillero(id_actividad,year,semestre,id_semillero)"
						+ "values (?,?,?,?)";
			}

			pst = reg.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setString(2, year);
			pst.setString(3, semestre);
			pst.setInt(4, idGrupoSemillero);

			pst.executeUpdate();

			if (tipoSession == 1) {
				return new Plan_accion_grupo_actividad(id, year, semestre, idGrupoSemillero);
			} else {
				return new Actividad_plan_semillero(id, year, semestre, idGrupoSemillero);
			}

		} catch (java.sql.SQLIntegrityConstraintViolationException e) {
			throw new ExcepcionProductividad("ya hay grupo con ese nombre  asociado");
		} catch (Exception e) {
			throw new ExcepcionProductividad("error del servidor" + e);// mientras colocamos e por desarrollo para
			// mirar las consultas
		}

		finally {
			con.cerrarConexion();
		}

	}

	public Object capacitacionCrearSemilleroAsignarPlanAccion(String year, String semestre, int idSemillero,
			String nombre, String objetivo, String responsables, int n_asistentes, String fecha_ini, String fecha_fin)
			throws Exception {

		Connection reg = null;
		int id = -1;
		try {

			reg = con.conectar("");
			String sql = "";

			sql = "INSERT INTO capacitacion(id,nombre,objetivo,responsables,n_asistentes,"
					+ "fecha_ini,fecha_fin,estado) values (?,?,?,?,?,?,?,?)";

			PreparedStatement pst;
			String generatedColumns[] = { "id" };
			pst = reg.prepareStatement(sql, generatedColumns);
			pst.setInt(1, 0);
			pst.setString(2, nombre);
			pst.setString(3, objetivo);
			pst.setString(4, responsables);
			pst.setInt(5, n_asistentes);
			pst.setString(6, fecha_ini);
			pst.setString(7, fecha_fin);
			pst.setInt(8, 0);

			pst.executeUpdate();

			ResultSet generatedKeys = pst.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getInt(1);
			}

			sql = "INSERT INTO plan_accion_capacitacion(id_capacitacion,year,semestre,id_semillero)"
					+ "values (?,?,?,?)";

			pst = reg.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setString(2, year);
			pst.setString(3, semestre);
			pst.setInt(4, idSemillero);

			pst.executeUpdate();

			return new Plan_accion_capacitacion(id, year, semestre, idSemillero);

		} catch (java.sql.SQLIntegrityConstraintViolationException e) {
			throw new ExcepcionProductividad("ya hay grupo con ese nombre  asociado");
		} catch (Exception e) {
			throw new ExcepcionProductividad("error del servidor" + e);// mientras colocamos e por desarrollo para
			// mirar las consultas
		}

		finally {
			con.cerrarConexion();
		}

	}

	public Object asignarProyectoPlanAccion(String year, String semestre, int idGrupoSemillero, int tipoSession,
			int id_proyecto) throws Exception {

		int id = -1;
		try {
			Connection reg = con.conectar("");

			// 1.consultar proyectos que no este terminado en los planes de accion de grupos
			// y semilleros
			LinkedHashMap<String, Object> general = new LinkedHashMap<String, Object>();

			String sql = "";
			if (tipoSession == 1) {
				sql = "INSERT INTO plan_accion_grupo_proyecto(id_proyecto,year,semestre,id_grupo) values (?,?,?,?)";
			} else {
				sql = "INSERT INTO proyecto_plan_semillero(id_proyecto,year,semestre,id_semillero)"
						+ " values (?,?,?,?)";
			}

			PreparedStatement pst;
			pst = reg.prepareStatement(sql);
			pst.setInt(1, id_proyecto);
			pst.setString(2, year);
			pst.setString(3, semestre);
			pst.setInt(4, idGrupoSemillero);

			pst.executeUpdate();

			if (tipoSession == 1) {
				return new Plan_accion_grupo_proyecto(id_proyecto, year, semestre, idGrupoSemillero);
			} else {
				return new Proyecto_plan_semillero(id_proyecto, year, semestre, idGrupoSemillero);
			}

		} catch (Exception e) {
			throw new ExcepcionProductividad("error del servidor" + e);
		}

		finally {
			con.cerrarConexion();
		}

	}

	public Object getAsignarActividadesPlanAccionGrupoSemillero(String year, String semestre, int idGrupoSemillero,
			int tipoSession, int id_actividad) throws Exception {

		try {
			Connection reg = con.conectar("");

			// 1.consultar proyectos que no este terminado en los planes de accion de grupos
			// y semilleros
			LinkedHashMap<String, Object> general = new LinkedHashMap<String, Object>();

			String sql = "";
			if (tipoSession == 1) {
				sql = "INSERT INTO plan_accion_grupo_actividad(id_actividad,year,semestre,id_grupo) values (?,?,?,?)";
			} else {
				sql = "INSERT INTO actividad_plan_semillero(id_actividad,year,semestre,id_semillero)"
						+ " values (?,?,?,?)";
			}

			PreparedStatement pst;
			pst = reg.prepareStatement(sql);
			pst.setInt(1, id_actividad);
			pst.setString(2, year);
			pst.setString(3, semestre);
			pst.setInt(4, idGrupoSemillero);

			pst.executeUpdate();

			if (tipoSession == 1) {
				return new Plan_accion_grupo_actividad(id_actividad, year, semestre, idGrupoSemillero);
			} else {
				return new Actividad_plan_semillero(id_actividad, year, semestre, idGrupoSemillero);
			}

		} catch (Exception e) {
			throw new ExcepcionProductividad("error del servidor" + e);
		}

		finally {
			con.cerrarConexion();
		}

	}

	public Object asignarCapacitacionPlanAccionSemillero(String year, String semestre, int idSemillero,
			int id_capacitacion) throws Exception {

		try {
			Connection reg = con.conectar("");

			LinkedHashMap<String, Object> general = new LinkedHashMap<String, Object>();

			String sql = "INSERT INTO plan_accion_capacitacion(id_capacitacion,year,semestre,id_semillero) values (?,?,?,?)";

			PreparedStatement pst;
			pst = reg.prepareStatement(sql);
			pst.setInt(1, id_capacitacion);
			pst.setString(2, year);
			pst.setString(3, semestre);
			pst.setInt(4, idSemillero);

			pst.executeUpdate();

			return new Plan_accion_capacitacion(id_capacitacion, year, semestre, idSemillero);

		} catch (Exception e) {
			throw new ExcepcionProductividad("error del servidor" + e);
		}

		finally {
			con.cerrarConexion();
		}

	}

	public Object asignarEventoPlanAccionGrupo(String year, String semestre, int idGrupo, int id_evento)
			throws Exception {

		try {
			Connection reg = con.conectar("");

			LinkedHashMap<String, Object> general = new LinkedHashMap<String, Object>();

			String sql = sql = "INSERT INTO evento_plan_accion_grupo(id_evento,year,semestre,id_grupo)"
					+ "values (?,?,?,?)";

			PreparedStatement pst;
			pst = reg.prepareStatement(sql);
			pst.setInt(1, id_evento);
			pst.setString(2, year);
			pst.setString(3, semestre);
			pst.setInt(4, idGrupo);

			pst.executeUpdate();

			return new Evento(id_evento, year, semestre, idGrupo);

		} catch (Exception e) {
			throw new ExcepcionProductividad("error del servidor" + e);
		}

		finally {
			con.cerrarConexion();
		}
	}

	public Object getTipologiaProductos1() throws Exception {

		List<LinkedHashMap> array = new LinkedList<LinkedHashMap>();

		try {
			Connection reg = con.conectar("");
			String sql = "SELECT * from tipologia_producto;";

			PreparedStatement stmt = reg.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			LinkedHashMap<String, Object> datosEspecificos = null;
			while (rs.next()) {

				datosEspecificos = new LinkedHashMap<String, Object>();
				datosEspecificos.put("id", rs.getInt("id"));
				datosEspecificos.put("nombre", rs.getString("nombre"));
				array.add(datosEspecificos);
			}

			return array;
		} catch (Exception e) {
			throw new ExcepcionProductividad("error del servidor" + e);
		}

		finally {
			con.cerrarConexion();
		}

	}

	public Object getTipologiaProductos2(int id_tipologia1) throws Exception {

		List<LinkedHashMap> array = new LinkedList<LinkedHashMap>();

		try {
			Connection reg = con.conectar("");
			String sql = "SELECT T2.id,T2.nombre from subtipo_tipologia T2 where T2.tipologia_producto_id=?";

			PreparedStatement stmt = reg.prepareStatement(sql);
			stmt.setInt(1, id_tipologia1);
			ResultSet rs = stmt.executeQuery();

			LinkedHashMap<String, Object> datosEspecificos = null;
			while (rs.next()) {

				datosEspecificos = new LinkedHashMap<String, Object>();
				datosEspecificos.put("id", rs.getInt("id"));
				datosEspecificos.put("nombre", rs.getString("nombre"));
				array.add(datosEspecificos);
			}

			return array;
		} catch (Exception e) {
			throw new ExcepcionProductividad("error del servidor" + e);
		}

		finally {
			con.cerrarConexion();
		}

	}

	public Object getTipologiaProductos3(int id_tipologia2) throws Exception {

		List<LinkedHashMap> array = new LinkedList<LinkedHashMap>();

		try {
			Connection reg = con.conectar("");
			String sql = "SELECT T3.id,T3.nombre from tipo_producto T3 where T3.subtipo_tipologia_id=?";

			PreparedStatement stmt = reg.prepareStatement(sql);
			stmt.setInt(1, id_tipologia2);
			ResultSet rs = stmt.executeQuery();

			LinkedHashMap<String, Object> datosEspecificos = null;
			while (rs.next()) {

				datosEspecificos = new LinkedHashMap<String, Object>();
				datosEspecificos.put("id", rs.getInt("id"));
				datosEspecificos.put("nombre", rs.getString("nombre"));
				array.add(datosEspecificos);
			}

			return array;
		} catch (Exception e) {
			throw new ExcepcionProductividad("error del servidor" + e);
		}

		finally {
			con.cerrarConexion();
		}

	}

	public Object getcategoriaProductos(int id_tipologia3) throws Exception {

		List<LinkedHashMap> array = new LinkedList<LinkedHashMap>();

		try {
			Connection reg = con.conectar("");
			String sql = "SELECT ca.id,ca.nombre FROM categoria ca where ca.id_tipo_producto=?";

			PreparedStatement stmt = reg.prepareStatement(sql);
			stmt.setInt(1, id_tipologia3);
			ResultSet rs = stmt.executeQuery();

			LinkedHashMap<String, Object> datosEspecificos = null;
			while (rs.next()) {

				datosEspecificos = new LinkedHashMap<String, Object>();
				datosEspecificos.put("id", rs.getInt("id"));
				datosEspecificos.put("nombre", rs.getString("nombre"));
				array.add(datosEspecificos);
			}

			return array;
		} catch (Exception e) {
			throw new ExcepcionProductividad("error del servidor" + e);
		}

		finally {
			con.cerrarConexion();
		}

	}

	public Object planesAccionGrupoSemillero(int idGrupoSemillero, int tipoSession) throws Exception {

		LinkedHashMap general = new LinkedHashMap<>();
		List<LinkedHashMap> array = new LinkedList<LinkedHashMap>();

		try {
			Connection reg = con.conectar("");
			String sql = "";

			if (tipoSession == 1) {
				sql = "select * from plan_accion_grupo plan where  plan.id_grupo=?";
			} else {
				sql = "select * from plan_semillero plan where  plan.id_semillero=?";
			}

			PreparedStatement stmt = reg.prepareStatement(sql);
			stmt.setInt(1, idGrupoSemillero);
			ResultSet rs = stmt.executeQuery();

			LinkedHashMap<String, Object> datosEspecificos = null;
			while (rs.next()) {

				if (tipoSession == 1) {
					datosEspecificos = new LinkedHashMap<String, Object>();
					datosEspecificos.put("year", rs.getString("year"));
					datosEspecificos.put("semestre", rs.getString("semestre"));
					datosEspecificos.put("id_grupo", rs.getString("id_grupo"));
					array.add(datosEspecificos);
				} else {
					datosEspecificos = new LinkedHashMap<String, Object>();
					datosEspecificos.put("year", rs.getString("year"));
					datosEspecificos.put("semestre", rs.getString("semestre"));
					datosEspecificos.put("id_semillero", rs.getString("id_semillero"));
					array.add(datosEspecificos);

				}
             
			}

			return array;
		} catch (Exception e) {
			throw new ExcepcionProductividad("error del servidor" + e);
		}

		finally {
			con.cerrarConexion();
		}

	}

	public Object tipoReferencia() throws Exception {

		LinkedHashMap general = new LinkedHashMap<>();
		List<LinkedHashMap> array = new LinkedList<LinkedHashMap>();

		try {
			Connection reg = con.conectar("");
			String sql = "select * from tipo_referencia ;";

			PreparedStatement stmt = reg.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			LinkedHashMap<String, Object> datosEspecificos = null;
			while (rs.next()) {
				datosEspecificos = new LinkedHashMap<String, Object>();
				datosEspecificos.put("id", rs.getInt("id"));
				datosEspecificos.put("nombre", rs.getString("nombre"));
				array.add(datosEspecificos);
			}

			return array;
		} catch (Exception e) {
			throw new ExcepcionProductividad("error del servidor" + e);
		}

		finally {
			con.cerrarConexion();
		}

	}
	

	public Object createLibro(String nombre,String descripcion,int id_proyecto,int id_tipo_producto,
			String titulo,String ISBN,String fecha_publica,String autores,String editorial,String lugar_publica,
			String certificacion_entidad,String curriculo,int tipo_desarrollo ) throws Exception {
		
	
		int id=-1;
		try {
			Connection reg = con.conectar("");
			
			
			String sql = "insert into producto (id, nombre, id_proyecto, descripcion, id_tipo_producto) "
					+ "values (?,?,?,?,?)";
			String[] generatedKeys = { "id" };
			PreparedStatement stmt = reg.prepareStatement(sql, generatedKeys);
			stmt.setInt(1, 0);
			stmt.setString(2, nombre);
			stmt.setInt(3, id_proyecto);
			stmt.setString(4, descripcion);
			stmt.setInt(5, id_tipo_producto);
			
			if(stmt.executeUpdate() > 0) {
				ResultSet gks = stmt.getGeneratedKeys();
				if(gks.next())
					id = gks.getInt(1);
			}
			
			
			sql = "insert into libro(id_producto,titulo,ISBN,fecha_publica,autores,editorial,lugar_publica,"
					+ "certificacion_entidad,curriculo,tipo_desarrollo_id) values(?,?,?,?,?,?,?,?,?,?)";
			stmt = reg.prepareStatement(sql);
			stmt.setInt(1,id);
			stmt.setString(2,titulo);
			stmt.setString(3,ISBN);
			stmt.setString(4,fecha_publica);
			stmt.setString(5, autores);
			stmt.setString(6,editorial);
			stmt.setString(7,lugar_publica);
			stmt.setString(8,certificacion_entidad);
			stmt.setString(9,curriculo);
			stmt.setInt(10,tipo_desarrollo);

			if(stmt.executeUpdate() > 0)
				return new Libro(id,titulo,ISBN,fecha_publica,autores,editorial,lugar_publica,
						certificacion_entidad,curriculo,tipo_desarrollo);
		} catch(Exception e) {
			throw new ExcepcionProductividad("error del servidor: " + e);
		} finally {
			con.cerrarConexion();
		}
		return null;
	}
	
	public Object tesis(String nombre,String descripcion,int id_proyecto,
			int id_tipo_producto,String titulo,String institucion,String anio,String reconocimiento) throws Exception  {
		
		int id=-1;
		try {
			Connection reg = con.conectar("");
			
			
			String sql = "insert into producto (id, nombre, id_proyecto, descripcion, id_tipo_producto) "
					+ "values (?,?,?,?,?)";
			String[] generatedKeys = { "id" };
			PreparedStatement stmt = reg.prepareStatement(sql, generatedKeys);
			stmt.setInt(1, 0);
			stmt.setString(2, nombre);
			stmt.setInt(3, id_proyecto);
			stmt.setString(4, descripcion);
			stmt.setInt(5, id_tipo_producto);
			
			if(stmt.executeUpdate() > 0) {
				ResultSet gks = stmt.getGeneratedKeys();
				if(gks.next())
					id = gks.getInt(1);
			}
			
			
			sql = "insert into trabajos_tesis(id_producto,titulo,institucion,anio,reconocimientos)"
					+ " values(?,?,?,?,?)";
			stmt = reg.prepareStatement(sql);
			stmt.setInt(1,id);
			stmt.setString(2,titulo);
			stmt.setString(3,institucion);
			stmt.setString(4,anio);
			stmt.setString(5,reconocimiento);
		

			if(stmt.executeUpdate() > 0)
				return new Tesis(id,titulo,institucion,anio,reconocimiento);
		} catch(Exception e) {
			throw new ExcepcionProductividad("error del servidor: " + e);
		} finally {
			con.cerrarConexion();
		}
		return null;
		
	} 
	
	public Object login(String usuario,String clave)throws Exception {
		

		LinkedHashMap general = new LinkedHashMap<>();
		List<LinkedHashMap> array = new LinkedList<LinkedHashMap>();

		try {
			Connection reg = con.conectar("");
			String sql ="SELECT rolSistema.usuario,rolSistema.persona_id,tipoRol.nombre,p.nombre nombre_completo," + 
					"p.correo_electronico correo " + 
					"FROM roles_sistema rolSistema,tipo_rol tipoRol,persona p where "
					+ "rolSistema.tipo_rol_id=tipoRol.id and " + 
					"rolSistema.usuario=? and rolSistema.clave=? " + 
					"and p.id=rolSistema.persona_id";

			PreparedStatement stmt = reg.prepareStatement(sql);
			stmt.setString(1,usuario);
			stmt.setString(2,clave);
		
			ResultSet rs = stmt.executeQuery();

			LinkedHashMap<String, Object> datosEspecificos = null;
			if (rs.next()) {
				general.put("exito","1");
				datosEspecificos = new LinkedHashMap<String, Object>();
				datosEspecificos.put("id", rs.getInt("persona_id"));
				datosEspecificos.put("nombre_usuario", rs.getString("usuario"));
				datosEspecificos.put("Rol",rs.getString("nombre"));
				datosEspecificos.put("nombre",rs.getString("nombre_completo"));
				datosEspecificos.put("correo",rs.getString("correo"));
				array.add(datosEspecificos);
				general.put("usuario",datosEspecificos);
			}else {
				general.put("exito","0");
				general.put("usuario",datosEspecificos);
			}

			return general;
		} catch (Exception e) {
			throw new ExcepcionProductividad("error del servidor" + e);
		}

		finally {
			con.cerrarConexion();
		}

		
	}

}

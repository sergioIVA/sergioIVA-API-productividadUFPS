package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import conexion.Conexion;
import model.Departamento;
import model.JovenInvestigador;
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
					+ " tipoRol.id=? and roles.persona_id=persona.id and grupo.id=?";

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
				sql = "select linea.id,linea.nombre from grupo_investigacion,linea_investigacion linea,linea_grupo"
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

			sql = "select * from tipo_proyecto";
			stmt = reg.prepareStatement(sql);
			rs = stmt.executeQuery();

			LinkedHashMap<String, Object> datosTipoProyecto = null;
			while (rs.next()) {

				datosLinea = new LinkedHashMap<String, Object>();

				datosTipoProyecto.put("id", rs.getInt("id"));
				datosTipoProyecto.put("nombre", rs.getString("nombre"));
				tipoProyecto.add(datosLinea);

			}

			general.put("lineasGrupoSemillero",lineaGrupoSemillero);
			general.put("TipoProyecto",tipoProyecto);

			return general;
		} catch (Exception e) {
			throw new ExcepcionProductividad("error del servidor" + e);
		}

		finally {
			con.cerrarConexion();
		}

	}

}

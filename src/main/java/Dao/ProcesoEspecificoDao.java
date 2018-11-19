package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;

import conexion.Conexion;
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

}

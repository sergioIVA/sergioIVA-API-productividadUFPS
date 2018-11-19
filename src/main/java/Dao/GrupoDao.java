package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import conexion.Conexion;
import model.Facultad;
import model.Grupo;
import util.ExcepcionProductividad;

public class GrupoDao {

	final Conexion con = new Conexion();

	public GrupoDao() {

	}

	public Grupo createGrupo(String nombre, String sigla, String ubicacion, String fecha_creacion,
			String codigo_colciencias, int clasificado, String correo, int id_categoria, int id_unidad,
			int director_grupo) throws Exception {

		Connection reg = null;
		int id = -1;
		try {

			reg = con.conectar("");

			String sql = "INSERT INTO grupo_investigacion(id,nombre,sigla,ubicacion,fecha_creacion"
					+ ",codigo_colciencias,clasificado,correo,id_categoria,id_unidad,director_grupo)"
					+ "values (?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pst;
			String generatedColumns[] = { "id" };
			pst = reg.prepareStatement(sql, generatedColumns);
			pst.setInt(1, 0);
			pst.setString(2, nombre);
			pst.setString(3, sigla);
			pst.setString(4, ubicacion);
			pst.setString(5, fecha_creacion);
			pst.setString(6, codigo_colciencias);
			pst.setInt(7, clasificado);
			pst.setString(8, correo);
			pst.setInt(9, id_categoria);
			pst.setInt(10, id_unidad);
			pst.setInt(11, director_grupo);
			pst.executeUpdate();

			ResultSet generatedKeys = pst.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getInt(1);
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
		return new Grupo(id, nombre, sigla, ubicacion, fecha_creacion, codigo_colciencias, clasificado, correo,
				id_categoria, id_unidad, director_grupo);

	}

	public Object getGrupos() throws Exception {
		
		List<Grupo> resp = new LinkedList<Grupo>();

		try {
			Connection reg = con.conectar("");
			Statement stmt = reg.createStatement();
			ResultSet rs = stmt.executeQuery("select * from grupo_investigacion");

			while (rs.next()) {
				resp.add(new Grupo(rs.getInt("id"), rs.getString("nombre"), rs.getString("sigla"),
						rs.getString("ubicacion"), rs.getString("fecha_creacion"), rs.getString("codigo_colciencias"),
						rs.getInt("clasificado"), rs.getString("correo"), rs.getInt("id_categoria"),
						rs.getInt("id_unidad"), rs.getInt("director_grupo")));
			}

		} catch (Exception e) {
			throw new ExcepcionProductividad("error del servidor" + e);
		} finally {
			con.cerrarConexion();
		}
		return resp;
	}

	public Grupo getGrupo(int id) throws Exception {
     
		Connection reg = con.conectar("");
		Grupo grupo = null;

		try {

			String sql = "select * from grupo_investigacion where id= ?";
			PreparedStatement stmt = reg.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				grupo = new Grupo(rs.getInt("id"), rs.getString("nombre"), rs.getString("sigla"),
						rs.getString("ubicacion"), rs.getString("fecha_creacion"), rs.getString("codigo_colciencias"),
						rs.getInt("clasificado"), rs.getString("correo"), rs.getInt("id_categoria"),
						rs.getInt("id_unidad"), rs.getInt("director_grupo"));
			}
			con.cerrarConexion();
		} catch (Exception e) {
			throw new ExcepcionProductividad("error del servidor" + e);
		}

		finally {
			con.cerrarConexion();
		}
		return grupo;
		
	}

	public Grupo updateGrupo(Grupo grupo, String nombre, String sigla, String ubicacion, String fecha_creacion,
			String codigo_colciencias, int clasificado, String correo, int id_categoria, int id_unidad,
			int director_grupo) throws Exception {

		try {
			Connection reg = con.conectar("");
			String sql = "update facultad set nombre= ? set sigla=? set ubicacion=? fecha_creacion=?"
					+ "set codigo_colciencias=? set clasificado=? set correo=? set id_categoria=?"
					+ "set id_unidad=? set director_grupo=? where id=?";
			PreparedStatement stmt = reg.prepareStatement(sql);
			stmt.setString(1, nombre);
			stmt.setString(2, sigla);
			stmt.setString(3, ubicacion);
			stmt.setString(4, fecha_creacion);
			stmt.setString(5, codigo_colciencias);
			stmt.setInt(6, clasificado);
			stmt.setString(7, correo);
			stmt.setInt(8, id_categoria);
			stmt.setInt(9, id_unidad);
			stmt.setInt(10, director_grupo);
			stmt.setInt(11, grupo.getId());

			if (stmt.executeUpdate() > 0) {
				grupo.setNombre(nombre);
				grupo.setSigla(sigla);
				grupo.setUbicacion(ubicacion);
				grupo.setFecha_creacion(fecha_creacion);
				grupo.setCodigo_colciencias(codigo_colciencias);
				grupo.setClasificado(clasificado);
				grupo.setCorreo(correo);
				grupo.setId_categoria(id_categoria);
				grupo.setId_unidad(id_unidad);
				grupo.setDirector_grupo(director_grupo);
			}
		} catch (Exception e) {
			throw new ExcepcionProductividad("error del servidor" + e);
		} finally {
			con.cerrarConexion();
		}
		return grupo;
	}

	public boolean deleteGrupo(int id) throws Exception {

		int value = -1;
		try {
			Connection reg = con.conectar("");

			String sql = "delete from grupo_investigacion where id = ?";
			PreparedStatement stmt = reg.prepareStatement(sql);
			stmt.setInt(1, id);
			value = stmt.executeUpdate();
		} catch (Exception e) {
			con.cerrarConexion();
			throw new ExcepcionProductividad("error del servidor" + e);
		}

		finally {
			con.cerrarConexion();
		}

		return value > 0;
	}

}

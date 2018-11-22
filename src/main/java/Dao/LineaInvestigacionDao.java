package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;

import conexion.Conexion;
import model.LineaInvestigacion;
import model.LineaGrupo;
import model.LineaSemillero;
import util.ExcepcionProductividad;

public class LineaInvestigacionDao {

	static final Conexion con = new Conexion();
	
	public LineaInvestigacionDao() {
		// TODO Auto-generated constructor stub
	}
	
	public LineaInvestigacion createLineaInvestigacion(String nombre, String descripcion, int id_tipo_linea, String lider_linea) throws Exception {
		
		LineaInvestigacion linea = null;
		try {
			Connection reg = con.conectar("");
			String sql = "insert into linea_investigacion (id, nombre, descripcion, id_tipo_linea, lider_linea) values (?,?,?,?,?)";
			String generatedColumns[] = { "id" };
			PreparedStatement stmt = reg.prepareStatement(sql, generatedColumns);
			
			stmt.setInt(1, 0);
			stmt.setString(2, nombre);
			stmt.setString(3, descripcion);
			stmt.setInt(4, id_tipo_linea);
			stmt.setString(5, lider_linea);
			
			int id = -1;
			
			if(stmt.executeUpdate() > 0) {
				ResultSet keys = stmt.getGeneratedKeys();
				if(keys.next())
					id = keys.getInt(1);
				
				linea = new LineaInvestigacion(id, id_tipo_linea, nombre, descripcion, lider_linea);
			}
		} catch(Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e);
		}
		finally {
			con.cerrarConexion();
		}
		return linea;
	}
	
	public Object getLineasInvestigacion() throws Exception {
		
		LinkedHashMap<String, Object> lineas = new LinkedHashMap<String, Object>();
		LinkedHashMap<String, Object> tipo_linea = new LinkedHashMap<String, Object>();
		try {
			Connection reg = con.conectar("");
			String sql = "select l.*, tl.nombre tipolinea from linea_investigacion l inner join tipo_linea tl on l.id_tipo_linea = tl.id order by l.id";
			PreparedStatement stmt = reg.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				lineas.put("id", rs.getInt("id"));
				lineas.put("nombre", rs.getString("nombre"));
				lineas.put("descripcion", rs.getString("descripcion"));
				lineas.put("lider-linea", rs.getString("lider_linea"));
				
				tipo_linea.put("id", rs.getInt("id_tipo_linea"));
				tipo_linea.put("nombre", rs.getString("tipolinea"));
				
				lineas.put("tipo-linea", tipo_linea);
			}
		} catch(Exception e) {
			throw new ExcepcionProductividad("Error de servidor: " + e);
		}
		finally {
			con.cerrarConexion();
		}
		
		return lineas;
	}
	
	public Object getLineaInvestigacion(int id_linea) throws Exception {
		
		LinkedHashMap<String, Object> lineas = new LinkedHashMap<String, Object>();
		LinkedHashMap<String, Object> tipo_linea = new LinkedHashMap<String, Object>();
		try {
			Connection reg = con.conectar("");
			String sql = "select l.*, tl.nombre tipolinea from linea_investigacion l inner join tipo_linea tl on l.id_tipo_linea = tl.id order by l.id where l.id = ?";
			PreparedStatement stmt = reg.prepareStatement(sql);
			stmt.setInt(1, id_linea);
			
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				lineas.put("id", rs.getInt("id"));
				lineas.put("nombre", rs.getString("nombre"));
				lineas.put("descripcion", rs.getString("descripcion"));
				lineas.put("lider-linea", rs.getString("lider_linea"));
				
				tipo_linea.put("id", rs.getInt("id_tipo_linea"));
				tipo_linea.put("nombre", rs.getString("tipolinea"));
				
				lineas.put("tipo-linea", tipo_linea);
			}
		} catch(Exception e) {
			throw new ExcepcionProductividad("Error de servidor: " + e);
		}
		finally {
			con.cerrarConexion();
		}
		
		return lineas;
	}
	
	public LineaSemillero asociarLineaSemillero(int id_semillero, int id_linea) throws Exception {
		
		LineaSemillero linea = null;
		try {
			Connection reg = con.conectar("");
			String sql = "insert into linea_semillero (id, id_semillero) values (?,?)";
			PreparedStatement stmt = reg.prepareStatement(sql);
			stmt.setInt(1, id_linea);
			stmt.setInt(2, id_semillero);
			
			if(stmt.executeUpdate() > 0)
				linea = new LineaSemillero(id_linea, id_semillero);
		} catch(Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e);
		}
		finally {
			con.cerrarConexion();
		}
		return linea;
	}
	
	public LineaGrupo asociarLineaGrupo(int id_grupo, int id_linea) throws Exception {
		
		LineaGrupo linea = null;
		try {
			Connection reg = con.conectar("");
			String sql = "insert into linea_grupo(id_linea, id_grupo) values(?,?)";
			PreparedStatement stmt = reg.prepareStatement(sql);
			stmt.setInt(1, id_linea);
			stmt.setInt(2, id_grupo);
			
			if(stmt.executeUpdate() > 0)
				linea = new LineaGrupo(id_grupo, id_linea);
		} catch(Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e);
		}
		finally {
			con.cerrarConexion();
		}
		return linea;
	}
	
	public Object getLineasGrupo(int id_grupo) throws Exception {
		
		LinkedHashMap<String, Object> lineas = new LinkedHashMap<String, Object>();
		try {
			Connection reg = con.conectar("");
			String sql = "select lg.id_linea, li.nombre, li.descripcion, g.nombre nombregrupo, g.sigla siglagrupo, g.codigo_colciencias, p.id iddirector, p.nombre nombredirector "
					+ "from linea_grupo lg inner join linea_investigacion li on li.id = lg.id_linea inner join grupo_investigacion g on g.id = lg.id_grupo "
					+ "inner join docente_ufps d on d.id_investigador = g.director_grupo inner join persona p on p.id = d.id_investigador where lg.id_grupo = ?";
			PreparedStatement stmt = reg.prepareStatement(sql);
			stmt.setInt(1, id_grupo);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				lineas.put("id", rs.getInt("id_linea"));
				lineas.put("nombre", rs.getString("nombre"));
				lineas.put("descripcion", rs.getString("descripcion"));
				lineas.put("nombre-grupo", rs.getString("nombregrupo"));
				lineas.put("sigla-grupo", rs.getString("siglagrupo"));
				lineas.put("grupo-colciencias", rs.getString("codigo_colciencias"));
				lineas.put("id-director", rs.getString("iddirector"));
				lineas.put("director", rs.getString("nombredirector"));
			}
		} catch(Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e);
		}
		finally {
			con.cerrarConexion();
		}
		return lineas;
	}
	
	public Object getLineasSemillero(int id_semillero) throws Exception {
		
		LinkedHashMap<String, Object> lineas = new LinkedHashMap<String, Object>();
		try {
			Connection reg = con.conectar("");
			String sql = "select li.id, li.nombre nombrelinea, ls.id_semillero, s.codigo, s.nombre, s.sigla, p.id iddirector, p.nombre nombredirector "
					+ "from linea_semillero ls inner join semillero s on ls.id_semillero = s.id "
					+ "inner join linea_investigacion li on li.id = lg.id_linea "
					+ "inner join docente_ufps d on d.id_investigador = s.id_director "
					+ "inner join persona p on p.id = d.id_investigador where ls.id_semillero = ?";
			PreparedStatement stmt = reg.prepareStatement(sql);
			stmt.setInt(1, id_semillero);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				lineas.put("id", rs.getInt("id"));
				lineas.put("nombre", rs.getString("nombrelinea"));
				lineas.put("id-semillero", rs.getInt("id_semillero"));
				lineas.put("codigo-semillero", rs.getString("codigo"));
				lineas.put("nombre-semillero", rs.getString("nombre"));
				lineas.put("sigla-semillero", rs.getString("sigla"));
				lineas.put("id-director", rs.getString("iddirector"));
				lineas.put("director", rs.getString("nombredirector"));
			}
		} catch(Exception e) {
			throw new ExcepcionProductividad("Error del servidor: " + e);
		}
		finally {
			con.cerrarConexion();
		}
		return lineas;
	}
}

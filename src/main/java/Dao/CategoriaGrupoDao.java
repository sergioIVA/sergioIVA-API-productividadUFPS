package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import conexion.Conexion;
import model.CategoriaGrupo;
import model.Facultad;
import util.ExcepcionProductividad;

public class CategoriaGrupoDao {

	final Conexion con = new Conexion();
	
	public CategoriaGrupoDao() {
		// TODO Auto-generated constructor stub
	}
	
	
	public CategoriaGrupo createCategoriaGrupo(String nombre) throws Exception {

		Connection reg = null;
		int id = -1;
		try {
		reg = con.conectar("");
		
		String sql = "INSERT INTO categoria_grupo(id,nombre)values (?,?)";
		PreparedStatement pst;
		String generatedColumns[] = { "id" };
		pst = reg.prepareStatement(sql, generatedColumns);
		pst.setInt(1, 0);
		pst.setString(2,nombre);
		pst.executeUpdate();

		ResultSet generatedKeys = pst.getGeneratedKeys();
		if (generatedKeys.next()) {
			id = generatedKeys.getInt(1);
		}

		} catch (java.sql.SQLIntegrityConstraintViolationException e) {
			throw new ExcepcionProductividad("ya hay una categoria grupo con ese  nombre  asociado");
		} catch (Exception e) {
			throw new ExcepcionProductividad("error del servidor" +e);// mientras colocamos e por desarrollo para
			// mirar las consultas
		}
		finally {
			con.cerrarConexion();
		}
		return new CategoriaGrupo(id,nombre);

	}

	public List<CategoriaGrupo> getCategoriaGrupos() throws Exception {

		List<CategoriaGrupo> resp = new LinkedList<CategoriaGrupo>();
		try {
		Connection reg = con.conectar("");
		Statement stmt = reg.createStatement();
		ResultSet rs = stmt.executeQuery("select * from categoria_grupo");

		while (rs.next()) {
			resp.add(new CategoriaGrupo(rs.getInt("id_categoria"),rs.getString("nombre")));
		}
		} catch (Exception e) {
			throw new ExcepcionProductividad("error del servidor" +e);// mientras colocamos e por desarrollo para
			// mirar las consultas
		}
		finally {
			con.cerrarConexion();
		}
		con.cerrarConexion();
		return resp;

	}
	
	public CategoriaGrupo getCategoriaGrupo(int id)throws Exception  {
		
		CategoriaGrupo categoriaGrupo = null;
		
		try {
		Connection reg = con.conectar("");
		String sql = "select * from categoria_grupo where id_categoria= ?";
		PreparedStatement stmt =  reg.prepareStatement(sql);
		stmt.setInt(1, id);
		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			categoriaGrupo = new CategoriaGrupo(rs.getInt("id_categoria"),rs.getString("nombre"));
		}
		
		}catch (Exception e) {
				throw new ExcepcionProductividad("error del servidor" +e);// mientras colocamos e por desarrollo para
				// mirar las consultas
			}
			finally {
				con.cerrarConexion();
			}
		
		return categoriaGrupo;
	}
	
	public CategoriaGrupo updateCategoriaGrupo(CategoriaGrupo g,String nombre)throws Exception {
 
        Connection reg = con.conectar("");
        
        try {
        String sql ="update categoria_grupo set nombre= ? where id_categoria= ?"; 
        PreparedStatement stmt = reg.prepareStatement(sql);
        stmt.setString(1, nombre);
        stmt.setInt(2, g.getId_categoria());

        if(stmt.executeUpdate() > 0) {
        	g.setNombre(nombre);
        }
        
        }
        catch (java.sql.SQLIntegrityConstraintViolationException e) {
			throw new ExcepcionProductividad("ya hay una categoria grupo con ese nombre  asociado");
        }
        catch (Exception e) {
			throw new ExcepcionProductividad("error del servidor" +e);// mientras colocamos e por desarrollo para
			// mirar las consultas
		}
		finally {
			con.cerrarConexion();
		}
         return g;
                   
	}
	
	public boolean deleteFacultad(int id)throws Exception {
		
		int value = -1;
		try {
		Connection reg = con.conectar("");
		String sql = "delete from facultad where id_unidad = ?";
		PreparedStatement stmt = reg.prepareStatement(sql);
		stmt.setInt(1, id);
		
		value=stmt.executeUpdate();
		} catch (Exception e) {
				throw new ExcepcionProductividad("error del servidor" +e);// mientras colocamos e por desarrollo para
				// mirar las consultas
			}
			finally {
				con.cerrarConexion();
			}
		return value > 0;  
	}


}

package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import conexion.Conexion;
import model.Facultad;
import model.Programa;
import model.User;
import services.FacultadService;
import util.ExcepcionProductividad;

public class FacultadDao {

	final Conexion con = new Conexion();

	public FacultadDao() {
		// TODO Auto-generated constructor stub
	}

	public Facultad createFacultad(int codigo,String nombre) throws Exception {

		Connection reg = null;
		int id = -1;
		try {
		reg = con.conectar("");
		
		String sql = "INSERT INTO unidad_academica(id,codigo,id_tipo_unidad)values (?,?,?)";
		PreparedStatement pst;
		String generatedColumns[] = { "id" };
		pst = reg.prepareStatement(sql, generatedColumns);
		pst.setInt(1, 0);
		pst.setInt(2, codigo);
		pst.setInt(3, 1);
		pst.executeUpdate();

		ResultSet generatedKeys = pst.getGeneratedKeys();
		if (generatedKeys.next()) {
			id = generatedKeys.getInt(1);
		}

		sql = "INSERT INTO facultad(id_unidad,nombre)values (?,?)";
		pst = reg.prepareStatement(sql, generatedColumns);
		pst.setInt(1, id);
		pst.setString(2, nombre);
		pst.executeUpdate();

		} catch (java.sql.SQLIntegrityConstraintViolationException e) {
			throw new ExcepcionProductividad("ya hay una facultad con ese codigo  asociado");
		} catch (Exception e) {
			throw new ExcepcionProductividad("error del servidor" +e);// mientras colocamos e por desarrollo para
			// mirar las consultas
		}
		finally {
			con.cerrarConexion();
		}
		return new Facultad(id,codigo,nombre);

	}

	public List<Facultad> getFacultades() throws Exception {

		List<Facultad> resp = new LinkedList<Facultad>();
		try {
		Connection reg = con.conectar("");
		Statement stmt = reg.createStatement();
		ResultSet rs = stmt.executeQuery("select * from facultad f,unidad_academica u where f.id_unidad=u.id");

		while (rs.next()) {
			System.out.println(rs.getInt("codigo"));
			resp.add(new Facultad(rs.getInt("id_unidad"),rs.getInt("codigo"), rs.getString("nombre")));
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
	
	public Facultad getFacultad(int id)throws Exception  {
		
		Facultad facultad = null;
		
		try {
		Connection reg = con.conectar("");
		String sql = "select * from facultad f inner join unidad_academica u on f.id_unidad=u.id where f.id_unidad= ?";
		PreparedStatement stmt =  reg.prepareStatement(sql);
		stmt.setInt(1, id);
		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			facultad = new Facultad(rs.getInt("id_unidad"), rs.getInt("codigo"),rs.getString("nombre"));
		}
		
		}catch (Exception e) {
				throw new ExcepcionProductividad("error del servidor" +e);// mientras colocamos e por desarrollo para
				// mirar las consultas
			}
			finally {
				con.cerrarConexion();
			}
		
		return facultad;
	}
	
	public Facultad updateFacultad(Facultad f,String nombre,int codigo)throws Exception {
 
        Connection reg = con.conectar("");
        
        try {
        String sql ="update facultad set nombre= ? where id_unidad= ?"; 
        PreparedStatement stmt = reg.prepareStatement(sql);
        stmt.setString(1, nombre);
        stmt.setInt(2, f.getId());

        if(stmt.executeUpdate() > 0) {
        	f.setNombre(nombre);
        }
        
        sql="update unidad_academica set codigo=? where id=?";
        stmt = reg.prepareStatement(sql);
        stmt.setInt(1, codigo);
        stmt.setInt(2, f.getId());
        
        if(stmt.executeUpdate() > 0) {
        	f.setNombre(nombre);
            f.setCodigo(codigo);
        }
        }
        catch (java.sql.SQLIntegrityConstraintViolationException e) {
			throw new ExcepcionProductividad("ya hay una facultad con ese codigo  asociado");
        }
        catch (Exception e) {
			throw new ExcepcionProductividad("error del servidor" +e);// mientras colocamos e por desarrollo para
			// mirar las consultas
		}
		finally {
			con.cerrarConexion();
		}
         return f;
                   
	}
	
	public boolean deleteFacultad(int id)throws Exception {
		
		
		int value = -1;
		try {
		Connection reg = con.conectar("");
		String sql = "delete from facultad where id_unidad = ?";
		PreparedStatement stmt = reg.prepareStatement(sql);
		stmt.setInt(1, id);
		
		if(stmt.executeUpdate() > 0) {
			sql = "delete from unidad_academica where id = ?";
			stmt = reg.prepareStatement(sql);
			stmt.setInt(1, id);
			
			value = stmt.executeUpdate();
		}
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

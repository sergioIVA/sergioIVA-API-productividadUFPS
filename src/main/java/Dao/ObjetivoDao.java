package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import conexion.Conexion;
import model.Grupo;
import model.Objetivo;
import util.ExcepcionProductividad;

public class ObjetivoDao {

	final Conexion con = new Conexion();
	
	public ObjetivoDao() {
		// TODO Auto-generated constructor stub
	}
	
	public Objetivo createObjetivoEspecifico(String nombre,int idProyecto) throws Exception {

		Connection reg = null;
		int id = -1;
		try {

			reg = con.conectar("");

			String sql = "INSERT INTO objetivo(id,nombre,id_proyecto) values (?,?,?)";
			PreparedStatement pst;
			String generatedColumns[] = { "id" };
			pst = reg.prepareStatement(sql, generatedColumns);
			pst.setInt(1, 0);
			pst.setString(2, nombre);
			pst.setInt(3,idProyecto);
			
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
		return new Objetivo(id,nombre,idProyecto);

	}

}

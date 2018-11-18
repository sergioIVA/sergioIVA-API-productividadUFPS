package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import conexion.Conexion;
import model.Programa;
import util.ExcepcionProductividad;

public class ProgramaDao {

	static final Conexion con = new Conexion();

	public ProgramaDao() {
		// TODO Auto-generated constructor stub
	}

	public Programa crearPrograma(int codigo, int id_facultad, String nombre) throws Exception {

		Connection reg = null;
		int id = -1;

		try {
			reg = con.conectar("");

			String sql = "INSERT INTO unidad_academica(id, codigo, id_tipo_unidad) VALUES (?,?,?)";
			PreparedStatement pst;
			String generatedColumns[] = { "id" };
			pst = reg.prepareStatement(sql, generatedColumns);
			pst.setInt(1, 0);
			pst.setInt(2, codigo);
			pst.setInt(3, 3);
			pst.executeUpdate();

			ResultSet generatedKeys = pst.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getInt(1);
			}

			sql = "insert into programa(id_unidad, id_facultad, nombre) values (?,?,?)";
			pst = reg.prepareStatement(sql, generatedColumns);
			pst.setInt(1, id);
			pst.setInt(2, id_facultad);
			pst.setString(3, nombre);

			pst.executeUpdate();

		} catch (java.sql.SQLIntegrityConstraintViolationException e) {
			throw new ExcepcionProductividad("ya hay un programa con ese codigo  asociado");
		} catch (Exception e) {
			throw new ExcepcionProductividad("error del servidor" + e);// mientras colocamos e por desarrollo para
			// mirar las consultas
		} finally {
			con.cerrarConexion();
		}

		return new Programa(id, nombre, codigo, id_facultad);
	}

	public List<Programa> getProgramas() throws Exception {
		List<Programa> resp = new LinkedList<Programa>();
		try {

			Connection reg = con.conectar("");
			Statement stmt = reg.createStatement();
			ResultSet rs = stmt
					.executeQuery("select * from programa p inner join unidad_academica u on u.id = p.id_unidad");

			while (rs.next()) {
				// System.out.println(rs.getInt("id_unidad");
				resp.add(new Programa(rs.getInt("id_unidad"), rs.getString("nombre"), rs.getInt("codigo"),
						rs.getInt("id_facultad")));
			}

		} catch (Exception e) {
			throw new ExcepcionProductividad("error del servidor" + e);// mientras colocamos e por desarrollo para
			// mirar las consultas
		} finally {
			con.cerrarConexion();
		}
		return resp;
	}

	public Programa getPrograma(int id_programa) throws Exception {

		Programa programa = null;

		try {
			Connection reg = con.conectar("");

			String sql = "select * from programa p inner join unidad_academica u on u.id = p.id_unidad where p.id_unidad = ?";
			PreparedStatement stmt = reg.prepareStatement(sql);
			stmt.setInt(1, id_programa);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				programa = new Programa(rs.getInt("id_unidad"), rs.getString("nombre"), rs.getInt("codigo"),
						rs.getInt("id_facultad"));
			}

		} catch (Exception e) {
			throw new ExcepcionProductividad("error del servidor" + e);// mientras colocamos e por desarrollo para
			// mirar las consultas
		} finally {
			con.cerrarConexion();
		}

		return programa;
	}

	public Programa updatePrograma(Programa programa, String nombre, int codigo) throws Exception {
		
		try{
			Connection reg = con.conectar("");
			String sql = "update programa set nombre = ? where id_unidad = ?";
			PreparedStatement stmt = reg.prepareStatement(sql);
			stmt.setString(1, nombre);
			stmt.setInt(2, programa.getId());

			if (stmt.executeUpdate() > 0) {
				programa.setNombre(nombre);
			}

			sql = "update unidad_academica set codigo=? where id=?";
			stmt = reg.prepareStatement(sql);
			stmt.setInt(1, codigo);
			stmt.setInt(2, programa.getId());

			if (stmt.executeUpdate() > 0) {
				programa.setNombre(nombre);
				programa.setCodigo(codigo);
			}

		  }catch (java.sql.SQLIntegrityConstraintViolationException e) {
					throw new ExcepcionProductividad("ya hay un programa con ese codigo  asociado");
		        }
		        catch (Exception e) {
					throw new ExcepcionProductividad("error del servidor" +e);// mientras colocamos e por desarrollo para
					// mirar las consultas
				}
				finally {
					con.cerrarConexion();
				}
	
		con.cerrarConexion();

		return programa;
	}

	public boolean deletePrograma(int id_unidad) throws Exception {
        
		Connection reg = con.conectar("");
		int value = -1;
		try{
			String sql = "delete from programa where id_unidad = ?";
			PreparedStatement stmt = reg.prepareStatement(sql);
			stmt.setInt(1, id_unidad);

			if (stmt.executeUpdate() > 0) {
				sql = "delete from unidad_academica where id = ?";
				stmt = reg.prepareStatement(sql);
				stmt.setInt(1, id_unidad);

				value = stmt.executeUpdate();
			}
		  
		}catch (Exception e) {
					throw new ExcepcionProductividad("error del servidor" +e);// mientras colocamos e por desarrollo para
					// mirar las consultas
				}
				finally {
					con.cerrarConexion();
				}
	
		return value > 0;
	}
}

package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import conexion.Conexion;
import model.Departamento;
import util.ExcepcionProductividad;

public class DepartamentoDao {

	static final Conexion con = new Conexion();

	public DepartamentoDao() {
		// TODO Auto-generated constructor stub
	}

	public Departamento createDepartamento(int codigo, String nombre, int idFacultad) throws Exception {

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
			pst.setInt(3, 2); // 1-facultad,2-departamento,3-programa
			pst.executeUpdate();

			ResultSet generatedKeys = pst.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getInt(1);
			}

			sql = "insert into departamento(id_unidad, id_facultad, nombre) values (?,?,?)";
			pst = reg.prepareStatement(sql, generatedColumns);
			pst.setInt(1, id);
			pst.setInt(2, idFacultad);
			pst.setString(3, nombre);

			pst.executeUpdate();

		} catch (java.sql.SQLIntegrityConstraintViolationException e) {
			throw new ExcepcionProductividad("ya hay una departamento con ese codigo  asociado");
		} catch (Exception e) {
			throw new ExcepcionProductividad("error del servidor" + e);// mientras colocamos e por desarrollo para
			// mirar las consultas
		} finally {
			con.cerrarConexion();
		}

		return new Departamento(id, nombre, codigo, idFacultad);
	}

	public List<Departamento> getDepartamentos() throws Exception {
        
		List<Departamento> resp = new LinkedList<Departamento>();
		try{
			Connection reg = con.conectar("");
			Statement stmt = reg.createStatement();
			ResultSet rs = stmt
					.executeQuery("select * from departamento d inner join unidad_academica u on u.id = d.id_unidad");

			while (rs.next()) {
				// System.out.println(rs.getInt("id_unidad");
				resp.add(new Departamento(rs.getInt("id_unidad"), rs.getString("nombre"), rs.getInt("codigo"),
						rs.getInt("id_facultad")));
			}
		}
		        catch (Exception e) {
					throw new ExcepcionProductividad("error del servidor" +e);// mientras colocamos e por desarrollo para
					// mirar las consultas
				}
				finally {
					con.cerrarConexion();
				}

		return resp;
	}

	public Departamento getDepartamento(int id_departamento) throws Exception {
       
		Departamento departamento = null;
		try{
			Connection reg = con.conectar("");
			

			String sql = "select * from departamento d inner join unidad_academica u on u.id = "
					+ "d.id_unidad where d.id_unidad = ?";
			PreparedStatement stmt = reg.prepareStatement(sql);
			stmt.setInt(1, id_departamento);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				departamento = new Departamento(rs.getInt("id_unidad"), rs.getString("nombre"), rs.getInt("codigo"),
						rs.getInt("id_facultad"));
			}

		  
		} catch (Exception e) {
					throw new ExcepcionProductividad("error del servidor" +e);// mientras colocamos e por desarrollo para
					// mirar las consultas
				}
				finally {
					con.cerrarConexion();
				}

		return departamento;
	}

	public Departamento updateDepartamento(Departamento departamento, String nombre, int codigo) throws Exception {
            
		try{
			Connection reg = con.conectar("");
			   
			String sql = "update departamento set nombre = ? where id_unidad = ?";
			PreparedStatement stmt = reg.prepareStatement(sql);
			stmt.setString(1, nombre);
			stmt.setInt(2, departamento.getId());

			if (stmt.executeUpdate() > 0) {
				departamento.setNombre(nombre);
			}

			sql = "update unidad_academica set codigo=? where id=?";
			stmt = reg.prepareStatement(sql);
			stmt.setInt(1, codigo);
			stmt.setInt(2, departamento.getId());

			if (stmt.executeUpdate() > 0) {
				departamento.setNombre(nombre);
				departamento.setCodigo(codigo);
			}

		  }catch (java.sql.SQLIntegrityConstraintViolationException e) {
					throw new ExcepcionProductividad("ya hay un departamento con ese codigo  asociado");
		        }
		        catch (Exception e) {
					throw new ExcepcionProductividad("error del servidor" +e);// mientras colocamos e por desarrollo para
					// mirar las consultas
				}
				finally {
					con.cerrarConexion();
				}

		return departamento;
	}

	public boolean deleteDepartamento(int id_unidad) throws Exception {
         
		int value = -1;
		try{
			Connection reg = con.conectar("");
	
			String sql = "delete from departamento where id_unidad = ?";
			PreparedStatement stmt = reg.prepareStatement(sql);
			stmt.setInt(1, id_unidad);

			if (stmt.executeUpdate() > 0) {
				sql = "delete from unidad_academica where id = ?";
				stmt = reg.prepareStatement(sql);
				stmt.setInt(1, id_unidad);

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

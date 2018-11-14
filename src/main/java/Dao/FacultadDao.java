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
import model.User;
import services.FacultadService;

public class FacultadDao {

	static final Conexion con = new Conexion();

	public FacultadDao() {
		// TODO Auto-generated constructor stub
	}

	public Facultad createFacultad(int codigo,String nombre) throws SQLException {

		Connection reg = null;
		int id = -1;
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

		ResultSet generatedKeys2 = pst.getGeneratedKeys();
		if (generatedKeys.next()) {
			id = generatedKeys.getInt(1);
		}

		con.cerrarConexion();

		return new Facultad(id,codigo,nombre);

	}

	public List<Facultad> getFacultades() throws SQLException {

		List<Facultad> resp = new LinkedList<Facultad>();
		Connection reg = con.conectar("");
		Statement stmt = reg.createStatement();
		ResultSet rs = stmt.executeQuery("select * from facultad f,unidad_academica u where f.id_unidad=u.id");

		while (rs.next()) {
			System.out.println(rs.getInt("codigo"));
			resp.add(new Facultad(rs.getInt("id_unidad"),rs.getInt("codigo"), rs.getString("nombre")));
		}
		con.cerrarConexion();
		return resp;

	}
	
	public Facultad getFacultad(int id)throws SQLException  {
		
		Connection reg = con.conectar("");
		Statement stmt = reg.createStatement();
		Facultad f=null;
		
		ResultSet rs = stmt.executeQuery("select * from facultad f,unidad_academica u where f.id_unidad=u.id and id_unidad='"+id+"'");
	
		    if(rs.next()) {
		    	f=new Facultad(id,rs.getInt("codigo"),rs.getString("nombre"));
		    }
		    
		con.cerrarConexion();
		return f;
		
	}
	
	public Facultad updateFacultad(Facultad f,String nombre,int codigo)throws SQLException {
 
        Connection reg = con.conectar("");
        Statement pst = reg.createStatement();
        String sql ="update facultad set nombre='"+f.getNombre()+"' where id_unidad='"+f.getId()+"'"; 
        pst.executeUpdate(sql);
        
        sql="update unidad_academica set codigo='"+codigo+"' where id='"+f.getId()+"'";
        pst.executeUpdate(sql);
        con.cerrarConexion();
        
          f.setNombre(nombre);
          f.setCodigo(codigo);
        
         return f;
                            
	}
	
	public boolean deleteFacultad(int id)throws SQLException {
		Connection reg = con.conectar("");
        Statement pst = reg.createStatement();
        String sql ="delete from facultad where id_unidad='"+id+"'"; 
        int n=pst.executeUpdate(sql);
        sql="delete from unidad_academica where id='"+id+"'"; 
        n=pst.executeUpdate(sql);
        con.cerrarConexion();
                         if(n>0){
                         return true;
                         }
                         return false;    
	}

}

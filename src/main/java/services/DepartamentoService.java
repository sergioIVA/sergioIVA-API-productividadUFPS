package services;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import Dao.DepartamentoDao;
import model.Departamento;

public class DepartamentoService {

 final DepartamentoDao departamentoDao=new DepartamentoDao();	
	
	public DepartamentoService() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Departamento createDepartamento(int codigo,String nombre,int idFacultad)throws SQLException{
	        return this.departamentoDao.createDepartamento(codigo, nombre, idFacultad);
	}
	
	public Object getDepartamentos()throws SQLException {
		//crear estructura json
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("departamentos",this.departamentoDao.getDepartamentos());
		   return map;	   
	}
	
	public Departamento getDepartamento(int id)throws SQLException {
		   return this.departamentoDao.getDepartamento(id);
	}
	
	public Departamento updateDepartamento(Departamento departamento,String nombre,int codigo)throws SQLException{
		  return this.departamentoDao.updateDepartamento(departamento, nombre, codigo);
	}
	
	public boolean deleteDepartamento(int id)throws SQLException{
		 return this.departamentoDao.deleteDepartamento(id);
	}

}

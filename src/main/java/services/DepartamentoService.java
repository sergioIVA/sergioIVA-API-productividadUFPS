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
	
	
	public Departamento createDepartamento(int codigo,String nombre,int idFacultad)throws Exception{
	        return this.departamentoDao.createDepartamento(codigo, nombre, idFacultad);
	}
	
	public Object getDepartamentos()throws Exception {
		//crear estructura json
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("departamentos",this.departamentoDao.getDepartamentos());
		   return map;	   
	}
	
	public Departamento getDepartamento(int id)throws Exception {
		   return this.departamentoDao.getDepartamento(id);
	}
	
	public Departamento updateDepartamento(Departamento departamento,String nombre,int codigo)throws Exception{
		  return this.departamentoDao.updateDepartamento(departamento, nombre, codigo);
	}
	
	public boolean deleteDepartamento(int id)throws Exception{
		 return this.departamentoDao.deleteDepartamento(id);
	}

}

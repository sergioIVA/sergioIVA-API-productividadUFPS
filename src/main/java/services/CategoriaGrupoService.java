package services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import Dao.CategoriaGrupoDao;
import model.CategoriaGrupo;

public class CategoriaGrupoService {
	
	final CategoriaGrupoDao categoriaGrupoDao=new CategoriaGrupoDao() ;  

	public CategoriaGrupoService(){
		// TODO Auto-generated constructor stub
	}
	
	public Object getCategoriasGrupo()throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
	      return map.put("categoria_grupo",this.categoriaGrupoDao.getCategoriaGrupos());	
	}
	
	public CategoriaGrupo getCategoriaGrupo(int id)throws Exception {
		return this.categoriaGrupoDao.getCategoriaGrupo(id);
	}
	
	public CategoriaGrupo createCategoriaGrupo(String nombre)throws Exception {
		  return this.categoriaGrupoDao.createCategoriaGrupo(nombre);
	}
	
	public CategoriaGrupo updateCategoriaGrupo(CategoriaGrupo g,String nombre)throws Exception {
		return this.categoriaGrupoDao.updateCategoriaGrupo(g, nombre);
	}
	
	public boolean deleteCategoriaGrupo(int id)throws Exception {
		  return this.categoriaGrupoDao.deleteFacultad(id);
	}

}

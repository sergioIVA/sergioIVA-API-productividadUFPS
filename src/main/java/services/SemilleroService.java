package services;

import java.util.LinkedHashMap;

import Dao.SemilleroDao;

public class SemilleroService {

	final SemilleroDao dao = new SemilleroDao();
	
	public SemilleroService() {
		
	}
	
	public Object createSemillero(String codigo, String nombre, String sigla, String ubicacion, 
			String fecha_creacion, int id_grupo, int id_linea_grupo, int id_director, String correo) throws Exception {
		
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("semillero", this.dao.createSemillero(codigo, nombre, sigla, ubicacion, fecha_creacion, id_grupo, id_linea_grupo, id_director, correo));
		return map;
	}
	
	public Object getSemilleros() throws Exception{
		return this.dao.getSemilleros();
	}
	
	public Object getSemillero(String codigo) throws Exception{
		return this.dao.getSemillero(codigo);
	}
	
	public boolean deleteSemillero(int id) throws Exception{
		return this.dao.deleteSemillero(id);
	}
}

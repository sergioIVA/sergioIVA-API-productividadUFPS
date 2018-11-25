package services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import Dao.DocenteDao;

public class DocenteService {

	static final DocenteDao dao = new DocenteDao();
	static final Gson gson = new Gson();
	
	public DocenteService() {
		// TODO Auto-generated constructor stub
	}
	
	public Object createDocente(int tipo_identificacion, String nombre, String fecha_nacimiento, String direccion,
			String telefono, String celular, String sexo, String correo_electronico, String foto, String nacionalidad,
			String numero_identificacion, String codigo, int id_departamento, int id_modalidad 
			) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("response-date", new Date());
		map.put("msg", "peticion correcta");
		map.put("docente-ufps", this.dao.createDocente(tipo_identificacion, nombre, fecha_nacimiento, direccion, telefono, celular, sexo, correo_electronico, 
				foto, nacionalidad, numero_identificacion, codigo, id_departamento, id_modalidad));
			return map;
	}
	
	public Object getDocentes() throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("docente-ufps", this.dao.getDocentes());
			return map;
	}
	
	public Object getDocente(int id) throws Exception{
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("response-date", new Date());
		map.put("msg", "peticion correcta");
		map.put("docente-ufps", this.dao.getDocente(id));
			return map;
	}
	
	public boolean deleteDocente(int id) throws Exception{
		return this.dao.deleteDocente(id);
	}
}

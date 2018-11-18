package services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import Dao.EstudianteDao;

public class EstudianteService {

	static final EstudianteDao dao = new EstudianteDao();
	static final Gson gson = new Gson();
	
	public EstudianteService() {
		
	}
	
	public Object createEstudiante(int tipo_identificacion, String nombre, String fecha_nacimiento, String direccion,
			String telefono, String celular, String sexo, String correo_electronico, String foto, String nacionalidad,
			String numero_identificacion, String codigo, int semestre, int id_programa) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("response-date", new Date());
		map.put("msg", "peticion correcta");
		map.put("estudiante", this.dao.createEstudiante(tipo_identificacion, nombre, fecha_nacimiento, direccion, telefono, celular, sexo, correo_electronico, 
				foto, nacionalidad, numero_identificacion, codigo, semestre, id_programa));
		
		return map;
	}
	
	public Object getEstudiantes() throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("response-date", new Date());
		map.put("msg", "peticion correcta");
		map.put("estudiante", this.dao.getEstudiantes());
		return map;
	}
	
	public Object getEstudiante(int id_estudiante) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("response-date", new Date());
		map.put("msg", "peticion correcta");
		map.put("estudiante", this.dao.getEstudiante(id_estudiante));
		return map;
	}
	
	public boolean deleteEstudiante(int id_estudiante) throws Exception {
		return this.dao.deleteEstudiante(id_estudiante);
	}
}

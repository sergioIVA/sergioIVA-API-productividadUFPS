package services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import Dao.JovenInvestigadorDao;

public class JovenInvestigadorService {

	static final JovenInvestigadorDao dao = new JovenInvestigadorDao();
	static final Gson gson = new Gson();
	
	public JovenInvestigadorService() {
		
	}
	
	public Object createJoven(int tipo_identificacion, String nombre, String fecha_nacimiento, String direccion,
			String telefono, String celular, String sexo, String correo_electronico, String foto, String nacionalidad,
			String numero_identificacion, String propuesta_desarrollada, String periodo_beca, String modalidad, int tutor) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("response-date", new Date());
		map.put("msg", "peticion correcta");
		map.put("joven-investigador", this.dao.createJoven(tipo_identificacion, nombre, fecha_nacimiento, direccion, telefono, celular, sexo, correo_electronico, foto, nacionalidad, numero_identificacion, propuesta_desarrollada, periodo_beca, modalidad, tutor));
		return map;
	}
	
	public Object getJovenes() throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("response-date", new Date());
		map.put("msg", "peticion correcta");
		map.put("joven-investigador", this.dao.getJovenes());
		return map;
	}
	
	public Object getJoven(int id_investigador) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("response-date", new Date());
		map.put("msg", "peticion correcta");
		map.put("joven-investigador", this.dao.getJoven(id_investigador));
		return map;
	}
	
	public boolean deleteJoven(int id_investigador) throws Exception {
		
		return this.dao.deleteJoven(id_investigador);
	}
}

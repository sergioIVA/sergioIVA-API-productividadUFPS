package services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import Dao.ExternoDao;

public class ExternoService {

	static final ExternoDao dao = new ExternoDao();
	static final Gson gson = new Gson();
	
	public ExternoService() {
		
	}
	
	public Object createExterno(int tipo_identificacion, String nombre, String fecha_nacimiento, String direccion,
			String telefono, String celular, String sexo, String correo_electronico, String foto, String nacionalidad,
			String numero_identificacion, String institucion_empresa, String cargo, String profesion) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("response-date", new Date());
		map.put("msg", "peticion correcta");
		map.put("investigador-externo", this.dao.createExterno(tipo_identificacion, nombre, fecha_nacimiento, direccion, telefono, celular, sexo, correo_electronico, foto, nacionalidad, numero_identificacion, institucion_empresa, cargo, profesion));
		return map;
	}
	
	public Object getExternos() throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("response-date", new Date());
		map.put("msg", "peticion correcta");
		map.put("investigador-externo", this.dao.getExternos());
		return map;
	}
	
	public Object getExterno(int id_investigador) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("response-date", new Date());
		map.put("msg", "peticion correcta");
		map.put("investigador-externo", this.dao.getExterno(id_investigador));
		return map;
	}
	
	public boolean deleteExterno(int id_investigador) throws Exception {
		return this.dao.deleteExterno(id_investigador);
	}
}

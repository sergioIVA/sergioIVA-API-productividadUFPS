package services;

import java.util.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import Dao.SubTipologiaDao;
import model.SubTipologia;

public class SubTipologiaService {

	static final SubTipologiaDao dao = new SubTipologiaDao();
	static final Gson gson = new Gson();
	
	public SubTipologiaService() {
		// TODO Auto-generated constructor stub
	}
	
	public Object createSubTipologia(int id_tipologia, String nombre) throws SQLException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("response-date", new Date());
		map.put("msg", "peticion correcta");
		map.put("subtipo-producto", this.dao.crearSubTipologia(id_tipologia, nombre));
			return map;
		
	}
	
	public Object getSubTipologias() throws SQLException{
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("subtipo-producto", this.dao.getSubTipologias());
			return map;
			
	}
	
	public Object getSubTipologiasDeTipo(int tipo_producto) throws SQLException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tipo-producto", tipo_producto);
		map.put("subtipo-producto", this.dao.getSubTipologiaDeTipo(tipo_producto));
			return map;
			
	}
	
	public SubTipologia getSubTipologia(int id) throws SQLException {
		
		return this.dao.getSpecificSubTipologia(id);
	}
	
	public boolean deleteSubTipologia(int id) throws SQLException {
		
		return this.dao.deleteSubTipologia(id);
	}
}

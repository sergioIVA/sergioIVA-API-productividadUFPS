package services;

import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import Dao.TipologiaDao;
import model.Tipologia;

public class TipologiaService {

	static final TipologiaDao tipologiaDao = new TipologiaDao();
	static final Gson gson = new Gson();
	
	public TipologiaService() {
		//.
	}
	
	public Object createTipologia(int id, String nombre) throws SQLException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("response-date", "");
		map.put("msg", "peticion correcta");
		map.put("tipologia-producto", this.tipologiaDao.createTipologia(nombre));
			return map;
			
	}
	
	public Object getProgramas() throws SQLException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tipologia-producto", this.tipologiaDao.getTipologias());
			return map;
			
	}
	
	public Tipologia getTipologia(int id) throws SQLException {
		
		return this.tipologiaDao.getTipologia(id);
	}
	
	public Object updateTipologia(Tipologia tipologia, String nombre) throws SQLException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("response-date", "");
		map.put("msg", "peticion correcta");
		map.put("tipologia-producto", this.tipologiaDao.updateTipologia(tipologia, nombre));
			return map;
	}
	
	public boolean deleteTipologia(int id) throws SQLException {
		
		return this.tipologiaDao.deleteTipologia(id);
	}
}

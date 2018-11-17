package services;

import java.util.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import Dao.ProgramaDao;
import model.Programa;

public class ProgramaService {

	static final ProgramaDao programaDao = new ProgramaDao();
	static final Gson gson = new Gson();
	
	public ProgramaService() {
		// TODO Auto-generated constructor stub
	}
	
	public Object createPrograma(int id_unidad, int id_facultad, String nombre) throws SQLException{
		Map<String, Object>  map = new HashMap<String, Object>();
		map.put("programa", this.programaDao.crearPrograma(id_unidad, id_facultad, nombre));
			return map;
	}
	
	public Object getProgramas() throws SQLException{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("programa", this.programaDao.getProgramas());
			return map;
	}
	
	public Programa getPrograma(int id) throws SQLException{
		return this.programaDao.getPrograma(id);
	}
	
	public Programa updatePrograma(Programa programa, String nombre,int codigo) throws SQLException {
		return this.programaDao.updatePrograma(programa, nombre,codigo);	
	}
	
	public boolean deletePrograma(int id) throws SQLException{
		return this.programaDao.deletePrograma(id);
	}
}

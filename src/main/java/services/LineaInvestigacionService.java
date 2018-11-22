package services;

import model.LineaInvestigacion;
import model.LineaSemillero;

import java.util.LinkedHashMap;

import com.google.gson.Gson;

import Dao.LineaInvestigacionDao;
import model.LineaGrupo;

public class LineaInvestigacionService {

	static final LineaInvestigacionDao dao = new LineaInvestigacionDao();
	static final Gson gson = new Gson();
	
	public LineaInvestigacionService() {}
	
	public Object createLineaInvestigacion(String nombre, String descripcion, int id_tipo_linea, String lider_linea) throws Exception {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("linea-investigacion", this.dao.createLineaInvestigacion(nombre, descripcion, id_tipo_linea, lider_linea));
		return map;
	}
	
	public Object getLineasInvestigacion() throws Exception {	
		return this.dao.getLineasInvestigacion();
	}
	
	public Object getLineaInvestigacion(int id_linea) throws Exception {	
		return this.dao.getLineaInvestigacion(id_linea);
	}
	
	public Object asociarLineaGrupo(int id_linea, int id_grupo) throws Exception{
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("linea-grupo", this.dao.asociarLineaGrupo(id_grupo, id_linea));
		return map;
	}
	
	public Object asociarLineaSemillero(int id_linea, int id_semillero) throws Exception{
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("linea-semillero", this.dao.asociarLineaSemillero(id_semillero, id_linea));
		return map;
	}
	
	public Object getLineasGrupo(int id_grupo) throws Exception{
		return this.dao.getLineasGrupo(id_grupo);
	}
	
	public Object getLineasSemillero(int id_semillero) throws Exception{
		return this.dao.getLineasSemillero(id_semillero);
	}
}

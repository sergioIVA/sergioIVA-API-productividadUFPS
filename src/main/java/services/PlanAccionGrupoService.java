package services;

import java.util.LinkedHashMap;
import Dao.PlanAccionGrupoDao;

public class PlanAccionGrupoService {

	final PlanAccionGrupoDao dao = new PlanAccionGrupoDao();
	
	public PlanAccionGrupoService() {
		
	}
	
	public Object createPlan(int year, int semestre, int id_grupo) throws Exception {
		
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("msg", "exitoso");
		map.put("plan", this.dao.createPlan(year, semestre, id_grupo));
		return map;
	}
	
	public Object createActividadPlan(int id_actividad, int year, int semestre, int id_grupo) throws Exception {
		
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("msg", "exitoso");
		map.put("actividad", this.dao.createActividadPlan(id_actividad, year, semestre, id_grupo));
		return map;
	}
	
	public Object createProyectoPlan(int year, int semestre, int id_grupo, int id_proyecto) throws Exception {
		
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("msg", "exitoso");
		map.put("actividad", this.dao.createProyectoPlan(year, semestre, id_grupo, id_proyecto));
		return map;
	}
	
	public Object getPlanes() throws Exception {
		
		return this.dao.getPlanes();
	}
	
	public Object getPlan(int id_grupo) throws Exception {
		
		return this.dao.getPlanesGrupo(id_grupo);
	}
}

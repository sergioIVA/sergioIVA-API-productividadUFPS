package services;

import java.util.LinkedHashMap;

import Dao.ProyectoDao;
import model.Proyecto;

public class ProyectoService {

	final ProyectoDao dao = new ProyectoDao();
	
	public ProyectoService() {}
	
	public Object  createProyecto(int costoTotal, int id_tipo, int id_linea,String tiempo_ejecucion, int
			tipo_participacion_id, String titulo,String fecha_inicio, String fecha_final,String 
			resultados_esperados, String n_contrato,String resumen,String objetivoGeneral,int tipoSession) throws Exception {
		
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("proyecto", this.dao.createProyecto(costoTotal, id_tipo, id_linea, tiempo_ejecucion,
				tipo_participacion_id,titulo, fecha_inicio, fecha_final, resultados_esperados,
				n_contrato, resumen, objetivoGeneral,tipoSession));
		return map;
	}
	
	public Proyecto updateProyecto(int id, Proyecto p) throws Exception{
		return this.dao.updateProyecto(id, p);
	}
	
	public boolean deleteProyecto(int id) throws Exception{
		return this.dao.deleteProyecto(id);
	}
	
	public Object getProyectos() throws Exception{
		return this.dao.getProyectos();
	}
	
	public Object getProyectosGrupo(int id_grupo) throws Exception{
		return this.dao.getProyectosGrupo(id_grupo);
	}
	
	public Object getSpecificProyecto(int id) throws Exception{
		return this.dao.getSpecificProyecto(id);
	}
}

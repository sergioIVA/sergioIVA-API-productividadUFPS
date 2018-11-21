package services;

import java.util.LinkedHashMap;

import Dao.ProyectoDao;
import model.Proyecto;

public class ProyectoService {

	final ProyectoDao dao = new ProyectoDao();
	
	public ProyectoService() {}
	
	public Object createProyecto(int costoTotal, int porcentaje_cumplimiento, int id_tipo, int id_linea, int duracion,
			int tiempo_total_ejecucion, int id_facultad, int tipo_participacion_id, int estado, String titulo,
			String fecha_inicio, String fecha_final, String valor_financiado, String institucion,
			String resultados_esperados, String representante_facultad, String documento_proyecto, String n_contrato) throws Exception {
		
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("proyecto", this.dao.createProyecto(costoTotal, porcentaje_cumplimiento, id_tipo, id_linea, duracion, tiempo_total_ejecucion, id_facultad, tipo_participacion_id, estado, titulo, fecha_inicio, fecha_final, valor_financiado, institucion, resultados_esperados, representante_facultad, documento_proyecto, n_contrato));
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

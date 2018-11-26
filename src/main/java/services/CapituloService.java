package services;

import java.util.LinkedHashMap;

import Dao.CapituloDao;

public class CapituloService {

	final CapituloDao dao = new CapituloDao();
	
	public CapituloService() {}
	
	public Object createCapitulo(int tipo_desarrollo_id, String titulo_libro, String titulo_capitulo,
			String iSBN_librol, String fecha_publica, String autores, String editorial, String lugar_publica,
			String certificacion_entidad, String curriculo_capitulo, String nombre, String descripcion, int id_proyecto) throws Exception {
		
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("capitulo", this.dao.createCapitulo(tipo_desarrollo_id, titulo_libro, titulo_capitulo, iSBN_librol, fecha_publica, autores, editorial, lugar_publica, certificacion_entidad, curriculo_capitulo, nombre, descripcion, id_proyecto));
		return map;
	}
	
	public Object getCapitulos() throws Exception{
		return this.dao.getCapitulos();
	}
	
	public Object getCapitulo(int id_producto) throws Exception{
		return this.dao.getCapitulo(id_producto);
	}
}

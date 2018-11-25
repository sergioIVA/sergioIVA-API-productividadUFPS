package services;

import java.util.LinkedHashMap;

import Dao.ArticuloDao;

public class ArticuloService {

	final ArticuloDao dao = new ArticuloDao();
	
	public ArticuloService() {
		// TODO Auto-generated constructor stub
	}
	
	public LinkedHashMap<String, Object> createArticulo(int id_producto, int tipo_referencia, String nombre_revista, String titulo_articulo, String autores,
			String anio, String mes, String volumen, String numero, String paginas_ini, String paginas_final,
			String iSSN, String paginaWeb, String dOI) throws Exception {
		
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("msg", "peticion exitosa");
		map.put("articulo", this.dao.createArticulo(id_producto, tipo_referencia, nombre_revista, titulo_articulo, autores, anio, mes, volumen, numero, paginas_ini, paginas_final, iSSN, paginaWeb, dOI));
		return map;
		
	}
	
	public boolean deleteArticulo(int id_producto) throws Exception {
		return this.dao.deleteArticulo(id_producto);
	}
	
	public Object getArticulos() throws Exception {
		return this.dao.getArticulos();
	}
	
	public Object getArticuloProducto(int id_producto) throws Exception {
		return this.dao.getArticuloProducto(id_producto);
	}
}

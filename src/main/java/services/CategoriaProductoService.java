package services;

import java.util.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import Dao.CategoriaProductoDao;
import model.CategoriaProducto;

public class CategoriaProductoService {

	static final CategoriaProductoDao dao = new CategoriaProductoDao();
	static final Gson gson = new Gson();
	
	public CategoriaProductoService() {
		// TODO Auto-generated constructor stub
	}
	
	public Object createCategoria(int id_tipo_producto, int id_subtipo_producto, String nombre) throws Exception{
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("response-date", new Date());
		map.put("msg", "peticion correcta");
		map.put("categoria-producto", this.dao.crearCategoriaProducto(id_tipo_producto, id_subtipo_producto, nombre));
			return map;
	}
	
	public Object getCategoriasXTipo(int id_tipo_producto) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("response-date", new Date());
		map.put("msg", "peticion correcta");
		map.put("categoria-producto", this.dao.getCategoriasXTipologia(id_tipo_producto));
			return map;
	}
	
	public Object getCategoriasXSubTipo(int id_subtipo_producto) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("response-date", new Date());
		map.put("msg", "peticion correcta");
		map.put("categoria-producto", this.dao.getCategoriasXSubTipo(id_subtipo_producto));
			return map;
	}
	
	public CategoriaProducto getSpecificCategoria(int id) throws Exception {
		return this.dao.getSpecificCategoria(id);
	}
	
	public boolean deleteCategoria(int id) throws SQLException {
		return this.deleteCategoria(id);
	}
}

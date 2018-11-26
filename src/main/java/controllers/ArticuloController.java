package controllers;

import services.ArticuloService;
import spark.Request;
import spark.Response;

public class ArticuloController {

	final ArticuloService service = new ArticuloService();
	
	public ArticuloController() {
		// TODO Auto-generated constructor stub
	}
	
	public Object createArticulo(Request req, Response res) {
		res.type("application/json");
		
		int id_producto = Integer.parseInt(req.queryParams("id_producto"));
		int tipo_referencia = Integer.parseInt(req.queryParams("tipo_referencia"));
		String nombre_revista = req.queryParams("nombre_revista");
		String titulo_articulo = req.queryParams("titulo_articulo");
		String autores = req.queryParams("autores");
		String anio = req.queryParams("anio");
		String mes = req.queryParams("mes");
		String volumen = req.queryParams("volumen");
		String numero = req.queryParams("numero");
		String paginas_ini = req.queryParams("paginas_ini");
		String paginas_final = req.queryParams("paginas_final");
		String ISSN = req.queryParams("ISSN");
		String paginaWeb = req.queryParams("paginaWeb");
		String DOI = req.queryParams("DOI");
		
		Object obj = null;
		try {
			obj = this.service.createArticulo(id_producto, tipo_referencia, nombre_revista,
					titulo_articulo, autores, anio, mes, volumen, numero, paginas_ini, paginas_final,
					ISSN, paginaWeb, DOI);
			res.status(201);
		} catch(Exception e) {
			res.status(500);
			return e.toString();
		}
		
		return obj;
	}
	
	public Object deleteArticulo(Request req, Response res) {
		res.type("application/json");
		String cad = req.params(":id");
		
		int id = -1;
		try {
			id = Integer.parseInt(cad);
		} catch (Exception e) {
			res.status(400);// 400 BAD REQUEST
			return "parametro alfanumero";
		}
		
		try {
			Object obj = this.service.getArticuloProducto(id);
			if(obj == null) {
				res.status(400);// 400 BAD REQUEST
				return "articulo inexistente";
			}
			
			boolean ok = this.service.deleteArticulo(id);
			if(!ok) {
				res.status(400);
				return "error";
			}else {
				res.status(200);
				return "exito";
			}
		} catch (Exception e) {
			res.status(500);// 500 INTERNAL SERVER ERROR
			return e.toString();
		}
	}
	
	public Object getArticulos(Request req, Response res) {
		res.type("application/json");
		try {
			res.status(200);// 200 OK
			return this.service.getArticulos();
			
		} catch (Exception e) {

			res.status(500);// 500 INTERNAL SERVER ERROR
			return e.toString();

		}
	}
	
	public Object getArticuloProducto(Request req, Response res) {
		res.type("application/json");
		String cad = req.params(":id");

		if (cad == null) {

			res.status(400);// 400 BAD REQUEST
			return "parametro faltante";

		}

		int id = Integer.parseInt(cad);

		try {

			Object obj = this.service.getArticuloProducto(id);
			if (obj == null) {
				res.status(400);// 400 BAD REQUEST
				return "no ha sido encontrado";

			}

			res.status(200);// 200 OK
			return obj;
		} catch (Exception e) {
			res.status(500);// 500 INTERNAL SERVER ERROR
			return e.toString();
		}
	}
}

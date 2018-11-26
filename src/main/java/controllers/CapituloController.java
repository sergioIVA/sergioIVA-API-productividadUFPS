package controllers;

import services.CapituloService;
import spark.Request;
import spark.Response;

public class CapituloController {

	final CapituloService service = new CapituloService();
	
	public CapituloController() {}
	
	public Object createCapitulo(Request req, Response res) {
		
		String nombre = req.queryParams("nombre");
		int id_proyecto = Integer.parseInt(req.queryParams("id_proyecto"));
		String descripcion = req.queryParams("descripcion");
		int id_tipo_producto = Integer.parseInt(req.queryParams("id_tipo_producto"));
		
		String titulo_libro = req.queryParams("titulo_libro");
		String titulo_capitulo = req.queryParams("titulo_capitulo");
		String ISBN_librol = req.queryParams("ISBN_librol");
		String fecha_publica = req.queryParams("fecha_publica");
		String autores = req.queryParams("autores");
		String editorial = req.queryParams("editorial");
		String lugar_publica = req.queryParams("lugar_publica");
		int tipo_desarrollo_id = Integer.parseInt(req.queryParams("tipo_desarrollo_id"));
		String certificacion_entidad = req.queryParams("certificacion_entidad");
		String curriculo_capitulo = req.queryParams("curriculo_capitulo");
		
		Object obj = null;
		try {
			obj = this.service.createCapitulo(tipo_desarrollo_id, titulo_libro, titulo_capitulo, ISBN_librol, fecha_publica, autores, editorial, lugar_publica, certificacion_entidad, curriculo_capitulo, nombre, descripcion, id_proyecto, id_tipo_producto);
			res.status(201);
		}catch(Exception e) {
			res.status(500);
			return e.toString();
		}
		return obj;
	}
	
	public Object getCapitulos(Request req, Response res) {
		res.type("application/json");
		try {
			res.status(200);// 200 OK
			return this.service.getCapitulos();
			
		} catch (Exception e) {

			res.status(500);// 500 INTERNAL SERVER ERROR
			return e.toString();

		}
	}
	
	public Object getCapitulo(Request req, Response res) {
		res.type("application/json");
		String cad = req.params(":id");

		if (cad == null) {

			res.status(400);// 400 BAD REQUEST
			return "parametro faltante";

		}

		int id = Integer.parseInt(cad);

		try {

			Object obj = this.service.getCapitulo(id);
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

package controllers;

import services.GrupoService;
import services.SemilleroService;
import spark.Request;
import spark.Response;

public class SemilleroController {
	
	static final SemilleroService semilleroService = new SemilleroService();

	public SemilleroController() {}
	
	public Object createSemillero(Request req, Response res) {
		res.type("application/json");
		String codigo = req.queryParams("codigo");
		String nombre = req.queryParams("nombre");
		String sigla = req.queryParams("sigla");
		String ubicacion = req.queryParams("ubicacion");
		String fecha_creacion = req.queryParams("fecha_creacion");
		int idGrupo = Integer.parseInt(req.queryParams("idGrupo"));
		String correo = req.queryParams("correo");
		int idLinea = Integer.parseInt(req.queryParams("idLinea"));
		int idDirector=Integer.parseInt(req.queryParams("id_director"));
		
		 System.out.println(req.body());

		Object obj = null;
		try {
			obj = semilleroService.createSemillero(codigo, nombre, sigla, ubicacion, fecha_creacion,
					idGrupo, idLinea, idDirector,correo);
			res.status(201);// 201 CREATED
		} 
		
		catch (Exception e) {
			// res.header("error", e.toString());
			res.status(500);// 500 INTERNAL SERVER ERROR
			return e.toString();
		}
		return obj;
	}
	
	public Object getSemillero(Request req, Response res) {
		
		res.type("application/json");
		int idSemillero =Integer.parseInt( req.params("idSemillero"));
		

		Object obj = null;
		try {
			obj = semilleroService.getSemillero(idSemillero);
			res.status(201);// 201 CREATED
		} 
		
		catch (Exception e) {
			// res.header("error", e.toString());
			res.status(500);// 500 INTERNAL SERVER ERROR
			return e.toString();
		}
		return obj;
	}
	

}

package controllers;

import services.ObjetivoService;
import services.ProcesoEspecificoService;
import spark.Request;
import spark.Response;

public class ObjetivoController {
	
	final ObjetivoService objetivoService =new ObjetivoService();
	
	public ObjetivoController() {}

	public Object createObjetivoEspecifico(Request req, Response res) {
		
		res.type("application/json");
		String nombre = req.queryParams("nombre");
		int idProyecto = Integer.parseInt(req.queryParams("idProyecto"));
	
		Object obj;
		try {
			obj = objetivoService.createObjetivoEspecifico(nombre, idProyecto);
			res.status(201);// 201 CREATED
		}catch (Exception e) {
			// res.header("error", e.toString());
			res.status(500);// 500 INTERNAL SERVER ERROR
			return e.getMessage();
		}
		return obj;
	}

}

package controllers;

import spark.Request;
import spark.Response;

public class ObjetivoController {
	
	public ObjetivoController() {}

	public Object createObjetivoEspecifico(Request req, Response res) {
		
		res.type("application/json");
		String nombre = req.queryParams("nombre");
		int idProyecto = Integer.parseInt(req.params(":idProyecto"));
	
		Object obj;
		try {
			obj = grupoService.createGrupo(nombre,sigla,ubicacion,fecha_creacion,codigo_colciencias,clasificado,
					correo,id_categoria,id_unidad,director_grupo);
			res.status(201);// 201 CREATED
		} 
		
		catch (Exception e) {

			// res.header("error", e.toString());
			res.status(500);// 500 INTERNAL SERVER ERROR
			return e.getMessage();
		}
		return obj;
	}

}

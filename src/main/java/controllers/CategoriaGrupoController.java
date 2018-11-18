package controllers;

import spark.Request;
import spark.Response;

public class CategoriaGrupoController {
	
	public CategoriaGrupoController() {
		
	}

	public Object getFacultad(Request req, Response res) {

		res.type("application/json");
		String cad = req.params(":nombre");

		if (cad == null) {
			res.status(400);// 400 BAD REQUEST
			return "parametro faltante";
		}
		
		/**
		try {

			
			Object obj = this.facultadService.getFacultad(cad);
			if (obj == null) {
				res.status(400);// 400 BAD REQUEST
				return "no ha sido encontrado la facultad con ese id";
			}
			res.status(200);// 200 OK
			return obj;
			
		} catch (Exception e) {
			res.status(500);// 500 INTERNAL SERVER ERROR
			return e.toString();
		}
		*/
		return null;
	}
}
